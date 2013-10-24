package loxia.support.cache;

import java.util.Collection;
import java.util.Date;

public class CacheValueConvertor{

	public static final String	NULL	= "_nil_";

	public static String convert(Object value){
		if (value == null)
			return NULL;
		if (value instanceof Integer)
			return Integer.toHexString((Integer) value);
		if (value instanceof Long)
			return Long.toHexString((Long) value);
		if (value instanceof Date)
			return Long.toHexString(((Date) value).getTime());
		if (value instanceof Collection){
			Collection<?> coll = (Collection<?>) value;
			StringBuffer sb = new StringBuffer();
			for (Object o : coll)
				sb.append("," + convert(o));
			return sb.toString().substring(1);
		}
		/*
		 * if(value.getClass().isArray()) return convert(Arrays.asList(value));
		 */
		return value.toString();
	}
}
