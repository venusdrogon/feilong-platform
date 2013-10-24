package loxia.support.json;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONPropFilter {
	static final Logger logger = LoggerFactory.getLogger("loxia.support.json");
	
	private String filterStr;
	private Set<String> includeProps = new HashSet<String>();
	private Set<String> excludeProps = new HashSet<String>();
	private Map<String,String> propFilterMap = new HashMap<String, String>();
	private Set<Class<? extends Object>> supportedClazzes;
	private boolean excludeAll = false;
	private boolean includeSimple = false;
	private boolean includeAll = false;
	private boolean includeAllDelegate = false;
	
	public JSONPropFilter(String filterStr, Set<Class<? extends Object>> supportedClazzes){
		this.filterStr = filterStr;
		this.supportedClazzes = new HashSet<Class<? extends Object>>(supportedClazzes);
		if(filterStr != null && filterStr.trim().length() > 0){
			String[] strs = filterStr.split(",");
			for(String str : strs){
				if(str.trim().equals("**")){
					includeAll = true;
				}else if(str.trim().equals("***"))
					includeAllDelegate = true;
			}
			if(includeAll || includeAllDelegate){
				for(String str : strs){
					if(str.charAt(0) == '-'){
						excludeProps.add(str.substring(1));
					}
				}				
			}else{
				for(String str : strs){
					str = str.trim();
					if(str.length() == 0) continue;
					if(str.equals("-*")) excludeAll = true;
					else if(str.equals("*")) includeSimple = true;
					else if(str.indexOf('.') > 0){
						int delim = str.indexOf('.');
						String objPropName = str.substring(0,delim);
						String addiStr = str.substring(delim+1).trim();
						includeProps.add(objPropName);
						String propFilterStr = propFilterMap.get(objPropName);
						if(addiStr.length() > 0){
							if(propFilterStr == null){
								propFilterMap.put(objPropName, addiStr);
							}else{
								propFilterMap.put(objPropName, propFilterStr + "," + addiStr);
							}
						}
					}else{
						if(str.charAt(0) == '-'){
							excludeProps.add(str.substring(1));
						}else{
							includeProps.add(str);
						}
					}
				}
			}
		}else{
			includeSimple = true;
		}
		if(logger.isDebugEnabled()){
			logger.debug("Filter String: {}" ,filterStr);
			logger.debug("Filter Includes: {}" ,includeProps);
			logger.debug("Filter Excludes: {}" ,excludeProps);
			logger.debug("Filter Map: {}" ,propFilterMap);
			logger.debug("Exclude all: {}" ,excludeAll);
			logger.debug("Include Simple: {}" ,includeSimple);
			logger.debug("Include All: {}" ,includeAll);
			logger.debug("Include AllDelegate: {}" ,includeAllDelegate);
		}
	}
	
	public boolean isValid(String key, Object value){		
		if(includeProps.contains(key)) return true;
		else if(excludeProps.contains(key)) return false;
		else if(includeAll || includeAllDelegate) return true;
		else if(includeSimple && isSupportedClass(value) && !excludeAll) return true;
		return false;
	}
	
	public boolean isSupportedClass(Object value){
		if(value == null || AbstractJSONObject.NULL.equals(value) || 
				value instanceof AbstractJSONObject) return true;
		if(supportedClazzes == null || supportedClazzes.size() == 0) return false;
		for(Class<? extends Object> clazz : supportedClazzes){
			if(clazz.isAssignableFrom(value.getClass())){
				return true;
			}
		}
		return false;
	}
	
	public String getFilterStr(String key){
		if(includeAll)
			return "*";
		if(includeAllDelegate)
			return "***";
		return propFilterMap.get(key);
	}

	public String getFilterStr() {
		return filterStr;
	}
}
