package loxia.support.cache;

import java.util.Set;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.Transcoder;

public class CacheUtil{

	public static String getParamKey(String name,Object value){
		return "_p::" + name + ":" + CacheValueConvertor.convert(value);
	}

	public static void clearCache(MemcachedClient cacheClient,String key,Transcoder<Set<String>> transcoder){
		Set<String> values = cacheClient.get(key, transcoder);
		cacheClient.delete(key);
		if (values != null){
			for (String k : values)
				if (k.length() > 0)
					cacheClient.delete(k);
		}
	}

	public static void clearCache(MemcachedClient cacheClient,String name,Object value,Transcoder<Set<String>> transcoder){
		CacheUtil.clearCache(cacheClient, getParamKey(name, value), transcoder);
	}
}
