package loxia.support.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import loxia.support.cache.annotation.CacheEvict;
import loxia.support.cache.annotation.CacheParam;
import loxia.support.cache.annotation.Cacheable;
import loxia.utils.EncodeUtil;
import net.spy.memcached.CASMutation;
import net.spy.memcached.CASMutator;
import net.spy.memcached.MemcachedClient;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

@Aspect
@SuppressWarnings("all")
public class CacheAspect implements Ordered{

	private static final Logger				logger	= LoggerFactory.getLogger(CacheAspect.class);

	@Autowired
	private MemcachedClient					cacheClient;

	@Autowired
	private SerializingStringSetTranscoder	transcoder;

	private static NullObject				NULL	= new NullObject();

	@Around("@annotation(loxia.support.cache.annotation.CacheEvict)")
	public Object doEvict(ProceedingJoinPoint pjp) throws Throwable{
		Object rtnValue = pjp.proceed(pjp.getArgs());
		try{
			Method m = getMethod(pjp, CacheEvict.class);
			Map<String, Object> params = getParams(m, pjp.getArgs());
			for (String key : params.keySet()){
				Object value = params.get(key);
				if (value != null && value instanceof java.util.Collection<?>){
					java.util.Collection<?> coll = (java.util.Collection<?>) value;
					for (Object o : coll){
						String pkey = CacheUtil.getParamKey(key.substring(1), o);
						CacheUtil.clearCache(cacheClient, pkey, transcoder);
					}
				}else if (value != null && value.getClass().isArray()){
					List<Object> objs = Arrays.asList(value);
					for (Object o : objs){
						String pkey = CacheUtil.getParamKey(key.substring(1), o);
						CacheUtil.clearCache(cacheClient, pkey, transcoder);
					}
				}else{
					String pkey = CacheUtil.getParamKey(key.substring(1), value);
					CacheUtil.clearCache(cacheClient, pkey, transcoder);
				}
			}
			CacheEvict ce = m.getAnnotation(CacheEvict.class);
			String[] methodNames = ce.value();
			for (int i = 0; i < methodNames.length; i++){
				String mn = methodNames[i];
				if (mn.trim().length() > 0){
					CacheUtil.clearCache(cacheClient, "_m::" + (ce.encode() ? encodeStr(mn.trim()) : mn.trim()), transcoder);
				}
			}
		}catch (Exception e){
			logger.error(e.getClass().getName(), e);
		}
		return rtnValue;
	}

	private String encodeStr(String key){
		int delim = key.indexOf(".");
		if (delim < 0){
			throw new RuntimeException("Cache key in CacheEvict is error.");
		}
		String c = key.substring(0, delim);
		String m = key.substring(delim + 1);
		return EncodeUtil.intToBase62(c.hashCode()) + "." + EncodeUtil.intToBase62(m.hashCode());
	}

	@Around("@annotation(loxia.support.cache.annotation.Cacheable)")
	public Object doGet(ProceedingJoinPoint pjp) throws Throwable{
		Method m = getMethod(pjp, Cacheable.class);
		Map<String, Object> params = getParams(m, pjp.getArgs());
		Cacheable c = m.getAnnotation(Cacheable.class);
		String methodName = c.value().equals("") ? EncodeUtil.intToBase62(m.getDeclaringClass().getSimpleName().hashCode()) + "."
						+ EncodeUtil.intToBase62(m.getName().hashCode()) : c.value();
		/*
		 * String methodName = c.value().equals("") ? m.getDeclaringClass().getSimpleName() + "." + m.getName() : c.value();
		 */
		String cacheKey = getCacheKey(methodName, params);
		logger.debug("get cache with key: {}", cacheKey);
		Object value = cacheClient.get(cacheKey);
		if (value != null){
			if (value instanceof NullObject){
				value = null;
			}
			logger.debug("Cached value: {} will be returned as type {} with key [{}]", new Object[] { value, m.getReturnType(), cacheKey });
			return value;
		}else{
			value = pjp.proceed(pjp.getArgs());
			cacheValue(methodName, c.cacheKey(), cacheKey, value == null ? NULL : value, c.expire(), params);
			return value;
		}
	}

	private Method getMethod(ProceedingJoinPoint pjp,Class<? extends Annotation> clazz){
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		if (ms.getMethod().isAnnotationPresent(clazz)){
			return ms.getMethod();
		}
		Method m = ms.getMethod();
		try{
			Method m1 = pjp.getTarget().getClass().getMethod(m.getName(), m.getParameterTypes());
			if (m1.isAnnotationPresent(clazz)){
				return m1;
			}
		}catch (Exception e){
			// do nothing
			logger.error(e.getClass().getName(), e);
		}
		throw new RuntimeException("No Proper annotation found.");
	}

	/**
	 * Build cache key
	 * 
	 * @param name
	 * @param params
	 * @return
	 */
	private String getCacheKey(String name,Map<String, Object> params){
		StringBuilder sb = new StringBuilder();
		sb.append(name + "::");
		for (String key : params.keySet()){
			sb.append(key.substring(1) + ":");
			sb.append(CacheValueConvertor.convert(params.get(key)) + ",");
		}
		return sb.toString();
	}

	private void cacheValue(String name,boolean needCacheWithMethodName,String cacheKey,Object value,int expire,Map<String, Object> params)
					throws Exception{
		for (String key : params.keySet()){
			if (key.startsWith("M")){
				appendParamCache(CacheUtil.getParamKey(key.substring(1), params.get(key)), cacheKey);
			}
		}
		if (needCacheWithMethodName){
			appendParamCache("_m::" + name, cacheKey);
		}
		logger.debug("Cache Value [{}]:[{}] with expire setting: {}.", new Object[] { cacheKey, value, expire });
		cacheClient.set(cacheKey, expire, value);
	}

	private Set<String> appendParamCache(String key,final String cacheKey) throws Exception{
		CASMutation<Set<String>> mutation = new CASMutation<Set<String>>(){

			@Override
			public Set<String> getNewValue(Set<String> current){
				// current = new HashSet<String>(current);
				current.add(cacheKey);
				return current;
			}
		};
		// Set<String> initSet = Collections.singleton(cacheKey);
		Set<String> initSet = new HashSet<String>();
		initSet.add(cacheKey);
		CASMutator<Set<String>> mutator = new CASMutator<Set<String>>(cacheClient, transcoder);
		return mutator.cas(key, initSet, 0, mutation);
	}

	private Map<String, Object> getParams(Method m,Object[] args){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		Annotation[][] paramAnnos = m.getParameterAnnotations();
		for (int i = 0; i < paramAnnos.length; i++){
			for (int j = 0; j < paramAnnos[i].length; j++){
				if (paramAnnos[i][j] != null && paramAnnos[i][j] instanceof CacheParam){
					CacheParam cp = (CacheParam) paramAnnos[i][j];
					params.put((cp.ignore() ? "I" : "M") + cp.value(), args[i]);
					break;
				}
			}
		}
		return params;
	}

	@Override
	public int getOrder(){
		return 15;
	}

	//@Test
	public void name(){
		String methodName = "activityCache";
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("IspikeCode", "Jordon11");
		String cacheKey = getCacheKey(methodName, params);

		logger.info(cacheKey);
	}
}
