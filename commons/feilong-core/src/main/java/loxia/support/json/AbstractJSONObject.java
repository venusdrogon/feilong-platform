package loxia.support.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

public abstract class AbstractJSONObject{

	static final Logger	logger	= LoggerFactory.getLogger("loxia.support.json");

	/**
	 * JSONBaseObject.NULL is equivalent to the value that JavaScript calls null, whilst Java's null is equivalent to the value that JavaScript calls undefined.
	 */
	private static final class Null{

		/**
		 * There is only intended to be a single instance of the NULL object, so the clone method returns itself.
		 * 
		 * @return NULL.
		 */
		protected final Object clone(){
			return this;
		}

		/**
		 * A Null object is equal to the null value and to itself.
		 * 
		 * @param object
		 *            An object to test for nullness.
		 * @return true if the object parameter is the JSONObject.NULL object or null.
		 */
		public boolean equals(Object object){
			return object == null || object == this;
		}

		/**
		 * Get the "null" string value.
		 * 
		 * @return The string "null".
		 */
		public String toString(){
			return "null";
		}
	}

	/**
	 * It is sometimes more convenient and less ambiguous to have a <code>NULL</code> object than to use Java's <code>null</code> value.
	 * <code>JSONBaseObject.NULL.equals(null)</code> returns <code>true</code>. <code>JSONBaseObject.NULL.toString()</code> returns <code>"null"</code>.
	 */
	public static final Object	NULL	= new Null();

	protected class InnerJSONTranslator implements JSONValueTranslator{

		private String	pattern	= DatePattern.commonWithTime;

		public InnerJSONTranslator(){
			// pattern = LoxiaSupportSettings.getInstance().get("date.pattern");
		}

		public Object toConcreteObject(String str,Class<? extends Object> clazz) throws JSONException{
			if (str == null || "null".equals(str)){
				return null;
			}
			try{
				if (clazz.equals(String.class))
					return str;
				else if (clazz.equals(Boolean.class)){
					return new Boolean(str);
				}else if (clazz.equals(BigDecimal.class)){
					return new BigDecimal(str);
				}else if (clazz.equals(BigInteger.class)){
					return new BigInteger(str);
				}else if (clazz.equals(Byte.class)){
					return Byte.parseByte(str);
				}else if (clazz.equals(Double.class)){
					return Double.parseDouble(str);
				}else if (clazz.equals(Float.class)){
					return Float.parseFloat(str);
				}else if (clazz.equals(Integer.class)){
					return Integer.parseInt(str);
				}else if (clazz.equals(Long.class)){
					return Long.parseLong(str);
				}else if (clazz.equals(Short.class)){
					return Short.parseShort(str);
				}else if (JSONString.class.isAssignableFrom(clazz)){
					JSONString obj = (JSONString) clazz.newInstance();
					obj.fromJSONString(str);
					return obj;
				}else if (clazz.equals(Date.class)){
					return DateUtil.string2Date(str, pattern);
				}
			}catch (NumberFormatException e){
				throw new JSONException("Object Value translation error for :" + str + " to Class[" + clazz.getName() + "]");
			}catch (InstantiationException e){
				throw new JSONException("Object Value translation error for :" + str + " to Class[" + clazz.getName() + "]");
			}catch (IllegalAccessException e){
				throw new JSONException("Object Value translation error for :" + str + " to Class[" + clazz.getName() + "]");
			}
			throw new JSONException("Unsupported Class Type:" + clazz.getName());
		}

		public String toJSONValueString(Object obj) throws JSONException{
			if (obj == null || obj.equals(null)){
				logger.debug("[null] --> \"null\"");
				return "null";
			}
			if (obj instanceof JSONString){
				Object o;
				try{
					o = ((JSONString) obj).toJSONString();
				}catch (Exception e){
					throw new JSONException(e);
				}
				if (o instanceof String){
					logger.debug("[{}] --> {}", obj.getClass().getSimpleName(), quote((String) obj));
					return (String) o;
				}
				throw new JSONException("Bad value from toJSONString: " + o);
			}
			if (obj instanceof Number){
				logger.debug("[{}] --> {}", obj.getClass().getSimpleName(), quote(numberToString((Number) obj)));
				return numberToString((Number) obj);
			}
			if (obj instanceof Boolean){
				logger.debug("[{}] --> {}", obj.getClass().getSimpleName(), quote(obj.toString()));
				return obj.toString();
			}
			if (obj instanceof Date){
				String dateStr = DateUtil.date2String((Date) obj, pattern);
				logger.debug("[{}] --> {}", obj.getClass().getSimpleName(), quote(dateStr));
				return quote(dateStr);
			}
			logger.debug("[{}] --> {}", obj.getClass().getSimpleName(), quote(obj.toString()));
			return quote(obj.toString());
		}
	}

	protected Map<Class<? extends Object>, JSONValueTranslator>	objStrTransferMap;

	protected void initTranslatorMap(){
		if (objStrTransferMap == null)
			objStrTransferMap = new HashMap<Class<? extends Object>, JSONValueTranslator>();
		else
			objStrTransferMap.clear();
		JSONValueTranslator translator = new InnerJSONTranslator();
		objStrTransferMap.put(String.class, translator);
		objStrTransferMap.put(Boolean.class, translator);
		objStrTransferMap.put(BigDecimal.class, translator);
		objStrTransferMap.put(BigInteger.class, translator);
		objStrTransferMap.put(Byte.class, translator);
		objStrTransferMap.put(Double.class, translator);
		objStrTransferMap.put(Float.class, translator);
		objStrTransferMap.put(Integer.class, translator);
		objStrTransferMap.put(Long.class, translator);
		objStrTransferMap.put(Short.class, translator);
		objStrTransferMap.put(JSONString.class, translator);
		objStrTransferMap.put(Date.class, translator);
	}

	public AbstractJSONObject(){
		initTranslatorMap();
	}

	public AbstractJSONObject registTranslator(Class<? extends Object> clazz,JSONValueTranslator translator){
		objStrTransferMap.put(clazz, translator);
		return this;
	}

	public AbstractJSONObject unRegistTranslator(Class<? extends Object> clazz){
		objStrTransferMap.remove(clazz);
		return this;
	}

	/**
	 * Throw an exception if the object is an NaN or infinite number.
	 * 
	 * @param o
	 *            The object to test.
	 * @throws JSONException
	 *             If o is a non-finite number.
	 */
	static void testValidity(Object o) throws JSONException{
		if (o != null && (!NULL.equals(o))){
			if (o instanceof Double){
				if (((Double) o).isInfinite() || ((Double) o).isNaN()){
					throw new JSONException("JSON does not allow non-finite numbers.");
				}
			}else if (o instanceof Float){
				if (((Float) o).isInfinite() || ((Float) o).isNaN()){
					throw new JSONException("JSON does not allow non-finite numbers.");
				}
			}
		}
	}

	/**
	 * Produce a string from a double. The string "null" will be returned if the number is not finite.
	 * 
	 * @param d
	 *            A double.
	 * @return A String.
	 */
	static public String doubleToString(double d){
		if (Double.isInfinite(d) || Double.isNaN(d)){
			return "null";
		}

		// Shave off trailing zeros and decimal point, if possible.

		String s = Double.toString(d);
		if (s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0){
			while (s.endsWith("0")){
				s = s.substring(0, s.length() - 1);
			}
			if (s.endsWith(".")){
				s = s.substring(0, s.length() - 1);
			}
		}
		return s;
	}

	/**
	 * Produce a string from a Number.
	 * 
	 * @param n
	 *            A Number
	 * @return A String.
	 * @throws JSONException
	 *             If n is a non-finite number.
	 */
	static public String numberToString(Number n) throws JSONException{
		if (n == null){
			throw new JSONException("Null pointer");
		}
		testValidity(n);

		// Shave off trailing zeros and decimal point, if possible.

		String s = n.toString();
		if (s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0){
			while (s.endsWith("0")){
				s = s.substring(0, s.length() - 1);
			}
			if (s.endsWith(".")){
				s = s.substring(0, s.length() - 1);
			}
		}
		return s;
	}

	/**
	 * Produce a string in double quotes with backslash sequences in all the right places. A backslash will be inserted within </, allowing JSON text to be
	 * delivered in HTML. In JSON text, a string cannot contain a control character or an unescaped quote or backslash.
	 * 
	 * @param string
	 *            A String
	 * @return A String correctly formatted for insertion in a JSON text.
	 */
	public static String quote(String string){
		if (string == null || string.length() == 0){
			return "\"\"";
		}

		char b;
		char c = 0;
		int i;
		int len = string.length();
		StringBuffer sb = new StringBuffer(len + 4);
		String t;

		sb.append('"');
		for (i = 0; i < len; i += 1){
			b = c;
			c = string.charAt(i);
			switch (c) {
				case '\\':
				case '"':
					sb.append('\\');
					sb.append(c);
					break;
				case '/':
					if (b == '<'){
						sb.append('\\');
					}
					sb.append(c);
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\r':
					sb.append("\\r");
					break;
				default:
					if (c < ' ' || (c >= '\u0080' && c < '\u00a0') || (c >= '\u2000' && c < '\u2100')){
						t = "000" + Integer.toHexString(c);
						sb.append("\\u" + t.substring(t.length() - 4));
					}else{
						sb.append(c);
					}
			}
		}
		sb.append('"');
		return sb.toString();
	}

	/**
	 * Make a JSON text of an Object value. If the object has an value.toJSONString() method, then that method will be used to produce the JSON text. The method
	 * is required to produce a strictly conforming text. If the object does not contain a toJSONString method (which is the most common case), then a text will
	 * be produced by other means. If the value is an array or Collection, then a JSONArray will be made from it and its toJSONString method will be called. If
	 * the value is a MAP, then a JSONObject will be made from it and its toJSONString method will be called. Otherwise, the value's toString method will be
	 * called, and the result will be quoted.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 * 
	 * @param value
	 *            The value to be serialized.
	 * @return a printable, displayable, transmittable representation of the object, beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending
	 *         with <code>}</code>&nbsp;<small>(right brace)</small>.
	 * @throws JSONException
	 *             If the value is or contains an invalid number.
	 */
	static String valueToString(Object value,Map<Class<? extends Object>, JSONValueTranslator> objStrTransferMap) throws JSONException{
		if (value == null || value.equals(null)){
			return "null";
		}
		if (value instanceof AbstractJSONObject)
			return value.toString();
		for (Class<? extends Object> clazz : objStrTransferMap.keySet()){
			if (clazz.isAssignableFrom(value.getClass()))
				return objStrTransferMap.get(clazz).toJSONValueString(value);
		}
		throw new JSONException("Unknown String conversion for Object:" + value);
	}

	/**
	 * Make a prettyprinted JSON text of an object value.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 * 
	 * @param value
	 *            The value to be serialized.
	 * @param indentFactor
	 *            The number of spaces to add to each level of indentation.
	 * @param indent
	 *            The indentation of the top level.
	 * @return a printable, displayable, transmittable representation of the object, beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending
	 *         with <code>}</code>&nbsp;<small>(right brace)</small>.
	 * @throws JSONException
	 *             If the object contains an invalid number.
	 */
	static String valueToString(Object value,Map<Class<? extends Object>, JSONValueTranslator> objStrTransferMap,int indentFactor,int indent)
			throws JSONException{
		if (value == null || value.equals(null)){
			return "null";
		}
		if (value instanceof AbstractJSONObject)
			return ((AbstractJSONObject) value).toString(indentFactor, indent);
		for (Class<? extends Object> clazz : objStrTransferMap.keySet()){
			if (clazz.isAssignableFrom(value.getClass()))
				return objStrTransferMap.get(clazz).toJSONValueString(value);
		}
		throw new JSONException("Unknown String conversion for Object:" + value);
	}

	public abstract String toString(int indentFactor,int indent) throws JSONException;
}
