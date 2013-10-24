package loxia.support.json;

/*
Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * A JSONObject is an unordered collection of name/value pairs. Its
 * external form is a string wrapped in curly braces with colons between the
 * names and values, and commas between the values and names. The internal form
 * is an object having <code>get</code> and <code>opt</code> methods for
 * accessing the values by name, and <code>put</code> methods for adding or
 * replacing values by name. The values can be any of these types:
 * <code>Boolean</code>, <code>JSONArray</code>, <code>JSONObject</code>,
 * <code>Number</code>, <code>String</code>, or the <code>JSONObject.NULL</code>
 * object. A JSONObject constructor can be used to convert an external form
 * JSON text into an internal form whose values can be retrieved with the
 * <code>get</code> and <code>opt</code> methods, or to convert values into a
 * JSON text using the <code>put</code> and <code>toString</code> methods.
 * A <code>get</code> method returns a value if one can be found, and throws an
 * exception if one cannot be found. An <code>opt</code> method returns a
 * default value instead of throwing an exception, and so is useful for
 * obtaining optional values.
 * <p>
 * The generic <code>get()</code> and <code>opt()</code> methods return an
 * object, which you can cast or query for type. There are also typed
 * <code>get</code> and <code>opt</code> methods that do type checking and type
 * coersion for you.
 * <p>
 * The <code>put</code> methods adds values to an object. For example, <pre>
 *     myString = new JSONObject().put("JSON", "Hello, World!").toString();</pre>
 * produces the string <code>{"JSON": "Hello, World"}</code>.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to
 * the JSON sysntax rules.
 * The constructors are more forgiving in the texts they will accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just
 *     before the closing brace.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single
 *     quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a quote
 *     or single quote, and if they do not contain leading or trailing spaces,
 *     and if they do not contain any of these characters:
 *     <code>{ } [ ] / \ : , = ; #</code> and if they do not look like numbers
 *     and if they are not the reserved words <code>true</code>,
 *     <code>false</code>, or <code>null</code>.</li>
 * <li>Keys can be followed by <code>=</code> or <code>=></code> as well as
 *     by <code>:</code>.</li>
 * <li>Values can be followed by <code>;</code> <small>(semicolon)</small> as
 *     well as by <code>,</code> <small>(comma)</small>.</li>
 * <li>Numbers may have the <code>0-</code> <small>(octal)</small> or
 *     <code>0x-</code> <small>(hex)</small> prefix.</li>
 * <li>Comments written in the slashshlash, slashstar, and hash conventions
 *     will be ignored.</li>
 * </ul>
 * @author JSON.org
 * @version 2
 */
public class JSONObject extends AbstractJSONObject{
    /**
     * The hash map where the JSONObject's properties are kept.
     */
    private HashMap<String,Object> myHashMap;
    
    /**
     * Construct an empty JSONObject.
     */
    public JSONObject() {
    	super();
        this.myHashMap = new HashMap<String,Object>();        
    }


    /**
     * Construct a JSONObject from a subset of another JSONObject.
     * An array of strings is used to identify the keys that should be copied.
     * Missing keys are ignored.
     * @param jo A JSONObject.
     * @param names An array of strings.
     * @exception JSONException If a value is a non-finite number.
     */
    public JSONObject(JSONObject jo, String[] names) throws JSONException {
    	super();
        for (int i = 0; i < names.length; i += 1) {
            putOpt(names[i], jo.opt(names[i]));
        }
    }


    /**
     * Construct a JSONObject from a JSONTokener.
     * @param x A JSONTokener object containing the source string.
     * @throws JSONException If there is a syntax error in the source string.
     */
    public JSONObject(JSONTokener x) throws JSONException {
    	this();
        char c;
        String key;

        if (x.nextClean() != '{') {
            throw x.syntaxError("A JSONObject text must begin with '{'");
        }
        for (;;) {
            c = x.nextClean();
            switch (c) {
            case 0:
                throw x.syntaxError("A JSONObject text must end with '}'");
            case '}':
                return;
            default:
                x.back();
                key = x.nextValue().toString();
            }

            /*
             * The key is followed by ':'. We will also tolerate '=' or '=>'.
             */

            c = x.nextClean();
            if (c == '=') {
                if (x.next() != '>') {
                    x.back();
                }
            } else if (c != ':') {
                throw x.syntaxError("Expected a ':' after a key");
            }
            put(key, x.nextValue());

            /*
             * Pairs are separated by ','. We will also tolerate ';'.
             */

            switch (x.nextClean()) {
            case ';':
            case ',':
                if (x.nextClean() == '}') {
                    return;
                }
                x.back();
                break;
            case '}':
                return;
            default:
                throw x.syntaxError("Expected a ',' or '}'");
            }
        }
    }
    
    public void setMap(Map<String, ? extends Object> map){
    	setMap(map, "");
    }

    @SuppressWarnings("unchecked")
	public void setMap(Map<String,? extends Object> map, String propFilterStr){
    	JSONPropFilter filter = new JSONPropFilter(propFilterStr, objStrTransferMap.keySet());
    	this.myHashMap = new HashMap<String, Object>();
    	for(String key: map.keySet()){
    		Object obj = map.get(key);
    		if(filter.isValid(key,obj)){ 
    			if(obj == null) this.myHashMap.put(key, NULL);
            	else if(obj instanceof Map){
    				Map<String,Object> m = (Map<String,Object>) obj;
    				this.myHashMap.put(key, new JSONObject(m,filter.getFilterStr(key),objStrTransferMap));
    			}else if(obj instanceof Collection){
    				Collection<? extends Object> c = (Collection<? extends Object>)obj;
    				this.myHashMap.put(key, new JSONArray(c,filter.getFilterStr(key),objStrTransferMap));
    			}else if(obj.getClass().isArray()){
    				try {
						this.myHashMap.put(key, new JSONArray(obj,filter.getFilterStr(key),objStrTransferMap));
					} catch (JSONException e) {
						//forget it
					}
    			}else if(filter.isSupportedClass(obj)){
    				try {
						this.put(key, obj);
					} catch (JSONException e) {
						this.myHashMap.put(key, NULL);
					}
    			}else{
    				this.myHashMap.put(key, new JSONObject(obj,filter.getFilterStr(key),objStrTransferMap));
    			}
    		}
    	}
    }
    
    /**
     * Construct a JSONObject from a Map.
     * @param map A map object that can be used to initialize the contents of
     *  the JSONObject.
     */
    public JSONObject(Map<String,? extends Object> map) {
        this(map, "");
    }
    
	public JSONObject(Map<String,? extends Object> map, String propFilterStr){
    	super();
    	setMap(map, propFilterStr);
    }
    
	public JSONObject(Map<String,? extends Object> map, String propFilterStr, 
    		Map<Class<? extends Object>, JSONValueTranslator> objStrTransferMap){
    	this.objStrTransferMap = objStrTransferMap;
    	setMap(map, propFilterStr);
    }
    
    @SuppressWarnings("unchecked")
	public void setObject(Object bean, String propFilterStr){
    	JSONPropFilter filter = new JSONPropFilter(propFilterStr, objStrTransferMap.keySet());
    	this.myHashMap = new HashMap<String, Object>();
    	Class<? extends Object> klass = bean.getClass();
        Method[] methods = klass.getMethods();
        for (int i = 0; i < methods.length; i += 1) {
            try {
                Method method = methods[i];
                String name = method.getName();
                String key = "";
                if (name.startsWith("get")) {
                    key = name.substring(3);
                } else if (name.startsWith("is")) {
                    key = name.substring(2);
                }
                if (key.length() > 0 &&
                        Character.isUpperCase(key.charAt(0)) &&
                        method.getParameterTypes().length == 0) {
                    if (key.length() == 1) {
                        key = key.toLowerCase();
                    } else if (!Character.isUpperCase(key.charAt(1))) {
                        key = key.substring(0, 1).toLowerCase() +
                            key.substring(1);
                    }
                    Object value = method.invoke(bean, (Object[])null);
                    
                    if(filter.isValid(key,value)){ 
                    	if(value == null){ 
                    		Class<? extends Object> c = method.getReturnType();
                    		if(Map.class.isAssignableFrom(c) ||
                    				Collection.class.isAssignableFrom(c) ||
                    				c.isArray()) continue;
                    		this.myHashMap.put(key, NULL);                    		
                    	}else if(value instanceof Map){
            				Map<String,Object> m = (Map<String,Object>) value;
            				this.myHashMap.put(key, new JSONObject(m,filter.getFilterStr(key),objStrTransferMap));
            			}else if(value instanceof Collection){
            				Collection<? extends Object> c = (Collection<? extends Object>)value;
            				this.myHashMap.put(key, new JSONArray(c,filter.getFilterStr(key),objStrTransferMap));
            			}else if(value.getClass().isArray()){
            				this.myHashMap.put(key, new JSONArray(value,filter.getFilterStr(key),objStrTransferMap));
            			}else if(filter.isSupportedClass(value)){
            				this.put(key, value);
            			}else{
            				this.myHashMap.put(key, new JSONObject(value,filter.getFilterStr(key),objStrTransferMap));
            			}
            		}
                }
            } catch (Exception e) {
            	e.printStackTrace();
                /* forget about it */
            }
        }
    }


    /**
     * Construct a JSONObject from an Object using bean getters.
     * It reflects on all of the public methods of the object.
     * For each of the methods with no parameters and a name starting
     * with <code>"get"</code> or <code>"is"</code> followed by an uppercase letter,
     * the method is invoked, and a key and the value returned from the getter method
     * are put into the new JSONObject.
     *
     * The key is formed by removing the <code>"get"</code> or <code>"is"</code> prefix. If the second remaining
     * character is not upper case, then the first
     * character is converted to lower case.
     *
     * For example, if an object has a method named <code>"getName"</code>, and
     * if the result of calling <code>object.getName()</code> is <code>"Larry Fine"</code>,
     * then the JSONObject will contain <code>"name": "Larry Fine"</code>.
     *
     * @param bean An object that has getter methods that should be used
     * to make a JSONObject.
     */
    public JSONObject(Object bean) {
        this(bean,"");
    }
    
    public JSONObject(Object bean, String propFilterStr) {
    	super();
    	setObject(bean, propFilterStr);
    }
    
    public JSONObject(Object bean, String propFilterStr,
    		Map<Class<? extends Object>, JSONValueTranslator> objStrTransferMap) {
    	this.objStrTransferMap = objStrTransferMap;
    	setObject(bean, propFilterStr);
    }

    /**
     * Construct a JSONObject from a source JSON text string.
     * This is the most commonly used JSONObject constructor.
     * @param source    A string beginning
     *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *  with <code>}</code>&nbsp;<small>(right brace)</small>.
     * @exception JSONException If there is a syntax error in the source string.
     */
    public JSONObject(String source) throws JSONException {
        this(new JSONTokener(source));
    }


    /**
     * Accumulate values under a key. It is similar to the put method except
     * that if there is already an object stored under the key then a
     * JSONArray is stored under the key to hold all of the accumulated values.
     * If there is already a JSONArray, then the new value is appended to it.
     * In contrast, the put method replaces the previous value.
     * @param key   A key string.
     * @param value An object to be accumulated under the key.
     * @return this.
     * @throws JSONException If the value is an invalid number
     *  or if the key is null.
     */
    public JSONObject accumulate(String key, Object value)
            throws JSONException {
        testValidity(value);
        Object o = opt(key);
        if (o == null) {
            put(key, value instanceof JSONArray ?
                    new JSONArray().put(value) :
                    value);
        } else if (o instanceof JSONArray) {
            ((JSONArray)o).put(value);
        } else {
            put(key, new JSONArray().put(o).put(value));
        }
        return this;
    }


    /**
     * Append values to the array under a key. If the key does not exist in the
     * JSONObject, then the key is put in the JSONObject with its value being a
     * JSONArray containing the value parameter. If the key was already
     * associated with a JSONArray, then the value parameter is appended to it.
     * @param key   A key string.
     * @param value An object to be accumulated under the key.
     * @return this.
     * @throws JSONException If the key is null or if the current value
     *  associated with the key is not a JSONArray.
     */
    public JSONObject append(String key, Object value)
            throws JSONException {
        testValidity(value);
        Object o = opt(key);
        if (o == null) {
            put(key, new JSONArray().put(value));
        } else if (o instanceof JSONArray) {
            put(key, ((JSONArray)o).put(value));
        } else {
            throw new JSONException("JSONObject[" + key +
                    "] is not a JSONArray.");
        }
        return this;
    }

    /**
     * Get the value object associated with a key.
     *
     * @param key   A key string.
     * @return      The object associated with the key.
     * @throws   JSONException if the key is not found.
     */
    public Object get(String key) throws JSONException {
        Object o = opt(key);
        if (o == null) {
            throw new JSONException("JSONObject[" + quote(key) +
                    "] not found.");
        }
        return o;
    }


    /**
     * Get the boolean value associated with a key.
     *
     * @param key   A key string.
     * @return      The truth.
     * @throws   JSONException
     *  if the value is not a Boolean or the String "true" or "false".
     */
    public boolean getBoolean(String key) throws JSONException {
        Object o = get(key);
        if (o.equals(Boolean.FALSE) ||
                (o instanceof String &&
                ((String)o).equalsIgnoreCase("false"))) {
            return false;
        } else if (o.equals(Boolean.TRUE) ||
                (o instanceof String &&
                ((String)o).equalsIgnoreCase("true"))) {
            return true;
        }
        throw new JSONException("JSONObject[" + quote(key) +
                "] is not a Boolean.");
    }


    /**
     * Get the double value associated with a key.
     * @param key   A key string.
     * @return      The numeric value.
     * @throws JSONException if the key is not found or
     *  if the value is not a Number object and cannot be converted to a number.
     */
    public double getDouble(String key) throws JSONException {
        Object o = get(key);
        try {
            return o instanceof Number ?
                ((Number)o).doubleValue() :
                Double.valueOf((String)o).doubleValue();
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(key) +
                "] is not a number.");
        }
    }


    /**
     * Get the int value associated with a key. If the number value is too
     * large for an int, it will be clipped.
     *
     * @param key   A key string.
     * @return      The integer value.
     * @throws   JSONException if the key is not found or if the value cannot
     *  be converted to an integer.
     */
    public int getInt(String key) throws JSONException {
        Object o = get(key);
        return o instanceof Number ?
                ((Number)o).intValue() : (int)getDouble(key);
    }


    /**
     * Get the JSONArray value associated with a key.
     *
     * @param key   A key string.
     * @return      A JSONArray which is the value.
     * @throws   JSONException if the key is not found or
     *  if the value is not a JSONArray.
     */
    public JSONArray getJSONArray(String key) throws JSONException {
        Object o = get(key);
        if (o instanceof JSONArray) {
            return (JSONArray)o;
        }
        throw new JSONException("JSONObject[" + quote(key) +
                "] is not a JSONArray.");
    }


    /**
     * Get the JSONObject value associated with a key.
     *
     * @param key   A key string.
     * @return      A JSONObject which is the value.
     * @throws   JSONException if the key is not found or
     *  if the value is not a JSONObject.
     */
    public JSONObject getJSONObject(String key) throws JSONException {
        Object o = get(key);
        if (o instanceof JSONObject) {
            return (JSONObject)o;
        }
        throw new JSONException("JSONObject[" + quote(key) +
                "] is not a JSONObject.");
    }


    /**
     * Get the long value associated with a key. If the number value is too
     * long for a long, it will be clipped.
     *
     * @param key   A key string.
     * @return      The long value.
     * @throws   JSONException if the key is not found or if the value cannot
     *  be converted to a long.
     */
    public long getLong(String key) throws JSONException {
        Object o = get(key);
        return o instanceof Number ?
                ((Number)o).longValue() : (long)getDouble(key);
    }


    /**
     * Get an array of field names from a JSONObject.
     *
     * @return An array of field names, or null if there are no names.
     */
    public static String[] getNames(JSONObject jo) {
    	int length = jo.length();
    	if (length == 0) {
    		return null;
    	}
    	Iterator<String> i = jo.keys();
    	String[] names = new String[length];
    	int j = 0;
    	while (i.hasNext()) {
    		names[j] = (String)i.next();
    		j += 1;
    	}
        return names;
    }


    /**
     * Get an array of field names from an Object.
     *
     * @return An array of field names, or null if there are no names.
     */
    @Deprecated
    public static String[] getNames(Object object) {
    	if (object == null) {
    		return null;
    	}
    	Class<? extends Object> klass = object.getClass();
    	Field[] fields = klass.getFields();
    	int length = fields.length;
    	if (length == 0) {
    		return null;
    	}
    	String[] names = new String[length];
    	for (int i = 0; i < length; i += 1) {
    		names[i] = fields[i].getName();
    	}
        return names;
    }


    /**
     * Get the string associated with a key.
     *
     * @param key   A key string.
     * @return      A string which is the value.
     * @throws   JSONException if the key is not found.
     */
    public String getString(String key) throws JSONException {
        return get(key).toString();
    }


    /**
     * Determine if the JSONObject contains a specific key.
     * @param key   A key string.
     * @return      true if the key exists in the JSONObject.
     */
    public boolean has(String key) {
        return this.myHashMap.containsKey(key);
    }


    /**
     * Determine if the value associated with the key is null or if there is
     *  no value.
     * @param key   A key string.
     * @return      true if there is no value associated with the key or if
     *  the value is the JSONObject.NULL object.
     */
    public boolean isNull(String key) {
        return JSONObject.NULL.equals(opt(key));
    }


    /**
     * Get an enumeration of the keys of the JSONObject.
     *
     * @return An iterator of the keys.
     */
    public Iterator<String> keys() {
        return this.myHashMap.keySet().iterator();
    }


    /**
     * Get the number of keys stored in the JSONObject.
     *
     * @return The number of keys in the JSONObject.
     */
    public int length() {
        return this.myHashMap.size();
    }


    /**
     * Produce a JSONArray containing the names of the elements of this
     * JSONObject.
     * @return A JSONArray containing the key strings, or null if the JSONObject
     * is empty.
     */
    public JSONArray names() {
        JSONArray ja = new JSONArray();
        Iterator<String>  keys = keys();
        while (keys.hasNext()) {
            ja.put(keys.next());
        }
        return ja.length() == 0 ? null : ja;
    }
    
    /**
     * Get an optional value associated with a key.
     * @param key   A key string.
     * @return      An object which is the value, or null if there is no value.
     */
    public Object opt(String key) {
        return key == null ? null : this.myHashMap.get(key);
    }


    /**
     * Get an optional boolean associated with a key.
     * It returns false if there is no such key, or if the value is not
     * Boolean.TRUE or the String "true".
     *
     * @param key   A key string.
     * @return      The truth.
     */
    public boolean optBoolean(String key) {
        return optBoolean(key, false);
    }


    /**
     * Get an optional boolean associated with a key.
     * It returns the defaultValue if there is no such key, or if it is not
     * a Boolean or the String "true" or "false" (case insensitive).
     *
     * @param key              A key string.
     * @param defaultValue     The default.
     * @return      The truth.
     */
    public boolean optBoolean(String key, boolean defaultValue) {
        try {
            return getBoolean(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * Put a key/value pair in the JSONObject, where the value will be a
     * JSONArray which is produced from a Collection.
     * @param key   A key string.
     * @param value A Collection value.
     * @return      this.
     * @throws JSONException
     */
    public JSONObject put(String key, Collection<? extends Object> value) throws JSONException {
        put(key, new JSONArray(value));
        return this;
    }


    /**
     * Get an optional double associated with a key,
     * or NaN if there is no such key or if its value is not a number.
     * If the value is a string, an attempt will be made to evaluate it as
     * a number.
     *
     * @param key   A string which is the key.
     * @return      An object which is the value.
     */
    public double optDouble(String key) {
        return optDouble(key, Double.NaN);
    }


    /**
     * Get an optional double associated with a key, or the
     * defaultValue if there is no such key or if its value is not a number.
     * If the value is a string, an attempt will be made to evaluate it as
     * a number.
     *
     * @param key   A key string.
     * @param defaultValue     The default.
     * @return      An object which is the value.
     */
    public double optDouble(String key, double defaultValue) {
        try {
            Object o = opt(key);
            return o instanceof Number ? ((Number)o).doubleValue() :
                new Double((String)o).doubleValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * Get an optional int value associated with a key,
     * or zero if there is no such key or if the value is not a number.
     * If the value is a string, an attempt will be made to evaluate it as
     * a number.
     *
     * @param key   A key string.
     * @return      An object which is the value.
     */
    public int optInt(String key) {
        return optInt(key, 0);
    }


    /**
     * Get an optional int value associated with a key,
     * or the default if there is no such key or if the value is not a number.
     * If the value is a string, an attempt will be made to evaluate it as
     * a number.
     *
     * @param key   A key string.
     * @param defaultValue     The default.
     * @return      An object which is the value.
     */
    public int optInt(String key, int defaultValue) {
        try {
            return getInt(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * Get an optional JSONArray associated with a key.
     * It returns null if there is no such key, or if its value is not a
     * JSONArray.
     *
     * @param key   A key string.
     * @return      A JSONArray which is the value.
     */
    public JSONArray optJSONArray(String key) {
        Object o = opt(key);
        return o instanceof JSONArray ? (JSONArray)o : null;
    }


    /**
     * Get an optional JSONObject associated with a key.
     * It returns null if there is no such key, or if its value is not a
     * JSONObject.
     *
     * @param key   A key string.
     * @return      A JSONObject which is the value.
     */
    public JSONObject optJSONObject(String key) {
        Object o = opt(key);
        return o instanceof JSONObject ? (JSONObject)o : null;
    }


    /**
     * Get an optional long value associated with a key,
     * or zero if there is no such key or if the value is not a number.
     * If the value is a string, an attempt will be made to evaluate it as
     * a number.
     *
     * @param key   A key string.
     * @return      An object which is the value.
     */
    public long optLong(String key) {
        return optLong(key, 0);
    }


    /**
     * Get an optional long value associated with a key,
     * or the default if there is no such key or if the value is not a number.
     * If the value is a string, an attempt will be made to evaluate it as
     * a number.
     *
     * @param key   A key string.
     * @param defaultValue     The default.
     * @return      An object which is the value.
     */
    public long optLong(String key, long defaultValue) {
        try {
            return getLong(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * Get an optional string associated with a key.
     * It returns an empty string if there is no such key. If the value is not
     * a string and is not null, then it is coverted to a string.
     *
     * @param key   A key string.
     * @return      A string which is the value.
     */
    public String optString(String key) {
        return optString(key, "");
    }


    /**
     * Get an optional string associated with a key.
     * It returns the defaultValue if there is no such key.
     *
     * @param key   A key string.
     * @param defaultValue     The default.
     * @return      A string which is the value.
     */
    public String optString(String key, String defaultValue) {
        Object o = opt(key);
        return o != null ? o.toString() : defaultValue;
    }


    /**
     * Put a key/boolean pair in the JSONObject.
     *
     * @param key   A key string.
     * @param value A boolean which is the value.
     * @return this.
     * @throws JSONException If the key is null.
     */
    public JSONObject put(String key, boolean value) throws JSONException {
        put(key, value ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }


    /**
     * Put a key/double pair in the JSONObject.
     *
     * @param key   A key string.
     * @param value A double which is the value.
     * @return this.
     * @throws JSONException If the key is null or if the number is invalid.
     */
    public JSONObject put(String key, double value) throws JSONException {
        put(key, new Double(value));
        return this;
    }


    /**
     * Put a key/int pair in the JSONObject.
     *
     * @param key   A key string.
     * @param value An int which is the value.
     * @return this.
     * @throws JSONException If the key is null.
     */
    public JSONObject put(String key, int value) throws JSONException {
        put(key, new Integer(value));
        return this;
    }


    /**
     * Put a key/long pair in the JSONObject.
     *
     * @param key   A key string.
     * @param value A long which is the value.
     * @return this.
     * @throws JSONException If the key is null.
     */
    public JSONObject put(String key, long value) throws JSONException {
        put(key, new Long(value));
        return this;
    }


    /**
     * Put a key/value pair in the JSONObject, where the value will be a
     * JSONObject which is produced from a Map.
     * @param key   A key string.
     * @param value A Map value.
     * @return      this.
     * @throws JSONException
     */
    public JSONObject put(String key, Map<String,Object> value) throws JSONException {
        put(key, new JSONObject(value));
        return this;
    }


    /**
     * Put a key/value pair in the JSONObject. If the value is null,
     * then the key will be removed from the JSONObject if it is present.
     * @param key   A key string.
     * @param value An object which is the value. It should be of one of these
     *  types: Boolean, Double, Integer, JSONArray, JSONObject, Long, String,
     *  or the JSONObject.NULL object.
     * @return this.
     * @throws JSONException If the value is non-finite number
     *  or if the key is null.
     */
    public JSONObject put(String key, Object value) throws JSONException {
        if (key == null) {
            throw new JSONException("Null key.");
        }
        if (value != null) {
            testValidity(value);
            this.myHashMap.put(key, value);
        } else {
            remove(key);
        }
        return this;
    }


    /**
     * Put a key/value pair in the JSONObject, but only if the
     * key and the value are both non-null.
     * @param key   A key string.
     * @param value An object which is the value. It should be of one of these
     *  types: Boolean, Double, Integer, JSONArray, JSONObject, Long, String,
     *  or the JSONObject.NULL object.
     * @return this.
     * @throws JSONException If the value is a non-finite number.
     */
    public JSONObject putOpt(String key, Object value) throws JSONException {
        if (key != null && value != null) {
            put(key, value);
        }
        return this;
    }

    /**
     * Remove a name and its value, if present.
     * @param key The name to be removed.
     * @return The value that was associated with the name,
     * or null if there was no value.
     */
    public Object remove(String key) {
        return this.myHashMap.remove(key);
    }
   
    /**
     * Get an enumeration of the keys of the JSONObject.
     * The keys will be sorted alphabetically.
     *
     * @return An iterator of the keys.
     */
    public Iterator<String> sortedKeys() {
      return new TreeSet<String>(this.myHashMap.keySet()).iterator();
    }

    /**
     * Produce a JSONArray containing the values of the members of this
     * JSONObject.
     * @param names A JSONArray containing a list of key strings. This
     * determines the sequence of the values in the result.
     * @return A JSONArray of values.
     * @throws JSONException If any of the values are non-finite numbers.
     */
    public JSONArray toJSONArray(JSONArray names) throws JSONException {
        if (names == null || names.length() == 0) {
            return null;
        }
        JSONArray ja = new JSONArray();
        for (int i = 0; i < names.length(); i += 1) {
            ja.put(this.opt(names.getString(i)));
        }
        return ja;
    }

    /**
     * Make a JSON text of this JSONObject. For compactness, no whitespace
     * is added. If this would not result in a syntactically correct JSON text,
     * then null will be returned instead.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     *
     * @return a printable, displayable, portable, transmittable
     *  representation of the object, beginning
     *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *  with <code>}</code>&nbsp;<small>(right brace)</small>.
     */
    public String toString() {
        try {
            Iterator<String> keys = keys();
            StringBuffer sb = new StringBuffer("{");

            while (keys.hasNext()) {
                if (sb.length() > 1) {
                    sb.append(',');
                }
                Object o = keys.next();
                sb.append(quote(o.toString()));
                sb.append(':');
                sb.append(valueToString(this.myHashMap.get(o), objStrTransferMap));
            }
            sb.append('}');
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Make a prettyprinted JSON text of this JSONObject.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     * @param indentFactor The number of spaces to add to each level of
     *  indentation.
     * @return a printable, displayable, portable, transmittable
     *  representation of the object, beginning
     *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *  with <code>}</code>&nbsp;<small>(right brace)</small>.
     * @throws JSONException If the object contains an invalid number.
     */
    public String toString(int indentFactor) throws JSONException {
        return toString(indentFactor, 0);
    }


    /**
     * Make a prettyprinted JSON text of this JSONObject.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     * @param indentFactor The number of spaces to add to each level of
     *  indentation.
     * @param indent The indentation of the top level.
     * @return a printable, displayable, transmittable
     *  representation of the object, beginning
     *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *  with <code>}</code>&nbsp;<small>(right brace)</small>.
     * @throws JSONException If the object contains an invalid number.
     */
    public String toString(int indentFactor, int indent) throws JSONException {
        int j;
        int n = length();
        if (n == 0) {
            return "{}";
        }
        Iterator<String>     keys = sortedKeys();
        StringBuffer sb = new StringBuffer("{");
        int          newindent = indent + indentFactor;
        Object       o;
        if (n == 1) {
            o = keys.next();
            sb.append(quote(o.toString()));
            sb.append(": ");
            sb.append(valueToString(this.myHashMap.get(o), objStrTransferMap, indentFactor,
                    indent));
        } else {
            while (keys.hasNext()) {
                o = keys.next();
                if (sb.length() > 1) {
                    sb.append(",\n");
                } else {
                    sb.append('\n');
                }
                for (j = 0; j < newindent; j += 1) {
                    sb.append(' ');
                }
                sb.append(quote(o.toString()));
                sb.append(": ");
                sb.append(valueToString(this.myHashMap.get(o), objStrTransferMap, indentFactor,
                        newindent));
            }
            if (sb.length() > 1) {
                sb.append('\n');
                for (j = 0; j < indent; j += 1) {
                    sb.append(' ');
                }
            }
        }
        sb.append('}');
        return sb.toString();
    }
    
     /**
      * Write the contents of the JSONObject as JSON text to a writer.
      * For compactness, no whitespace is added.
      * <p>
      * Warning: This method assumes that the data structure is acyclical.
      *
      * @return The writer.
      * @throws JSONException
      */
     public Writer write(Writer writer) throws JSONException {
        try {
            boolean  b = false;
            Iterator<String> keys = keys();
            writer.write('{');

            while (keys.hasNext()) {
                if (b) {
                    writer.write(',');
                }
                Object k = keys.next();
                writer.write(quote(k.toString()));
                writer.write(':');
                Object v = this.myHashMap.get(k);
                if (v instanceof JSONObject) {
                    ((JSONObject)v).write(writer);
                } else if (v instanceof JSONArray) {
                    ((JSONArray)v).write(writer);
                } else {
                    writer.write(valueToString(v, objStrTransferMap));
                }
                b = true;
            }
            writer.write('}');
            return writer;
        } catch (IOException e) {
            throw new JSONException(e);
        }
     }
}