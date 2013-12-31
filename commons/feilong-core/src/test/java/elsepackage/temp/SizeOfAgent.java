/**
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package elsepackage.temp;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;

/** Instrumentation agent used */
public class SizeOfAgent{

	static Instrumentation	inst;

	/** initializes agent */
	public static void premain(String agentArgs,Instrumentation instP){
		inst = instP;
	}

	/**
	 * Returns object size without member sub-objects.
	 * 
	 * @param o
	 *            object to get size of
	 * @return object size
	 */
	public static long sizeOf(Object o){
		if (inst == null){
			throw new IllegalStateException("Can not access instrumentation environment.n"
					+ "Please check if jar file containing SizeOfAgent class is n"
					+ "specified in the java's \"-javaagent\" command line argument.");
		}
		return inst.getObjectSize(o);
	}

	/**
	 * Calculates full size of object iterating over 037: * its hierarchy graph.
	 * 
	 * @param obj
	 *            object to calculate size of
	 * @return object size
	 */
	public static long fullSizeOf(Object obj){
		Map<Object, Object> visited = new IdentityHashMap<Object, Object>();
		Stack<Object> stack = new Stack<Object>();
		long result = internalSizeOf(obj, stack, visited);
		while (!stack.isEmpty()){
			result += internalSizeOf(stack.pop(), stack, visited);
		}
		visited.clear();
		return result;
	}

	private static boolean skipObject(Object obj,Map<Object, Object> visited){
		if (obj instanceof String){
			// skip interned string
			if (obj == ((String) obj).intern()){
				return true;
			}
		}
		return (obj == null) // skip visited object
				|| visited.containsKey(obj);
	}

	private static long internalSizeOf(Object obj,Stack<Object> stack,Map<Object, Object> visited){
		if (skipObject(obj, visited)){
			return 0;
		}
		visited.put(obj, null);
		long result = 0;
		// get size of object + primitive variables + member pointers
		result += SizeOfAgent.sizeOf(obj);
		// process all array elements
		Class clazz = obj.getClass();
		if (clazz.isArray()){
			if (clazz.getName().length() != 2){// skip primitive type array
				int length = Array.getLength(obj);
				for (int i = 0; i < length; i++){
					stack.add(Array.get(obj, i));
				}
			}
			return result;
		}
		// process all fields of the object
		while (clazz != null){
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++){
				if (!Modifier.isStatic(fields[i].getModifiers())){
					if (fields[i].getType().isPrimitive()){
						continue; // skip primitive fields
					}else{
						fields[i].setAccessible(true);
						try{
							// objects to be estimated are put to stack
							Object objectToAdd = fields[i].get(obj);
							if (objectToAdd != null){
								stack.add(objectToAdd);
							}
						}catch (IllegalAccessException ex){
							assert false;
						}
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
		return result;
	}
}
