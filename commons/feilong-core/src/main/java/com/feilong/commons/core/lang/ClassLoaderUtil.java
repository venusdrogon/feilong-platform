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
package com.feilong.commons.core.lang;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *这个类提供了一些根据类的class文件位置来定位的方法。
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-27 上午12:40:08
 * @since 1.0
 */
public class ClassLoaderUtil{

	private final static Logger	log	= LoggerFactory.getLogger(ClassLoaderUtil.class);

	/**
	 * 查找具有给定名称的资源。<br>
	 * "",表示classes 的根目录<br>
	 * e.q:<br>
	 * <ul>
	 * <li>getResource("")--->(maven)测试--->file:/E:/Workspaces/feilong/feilong-platform/feilong-common/target/test-classes/</li>
	 * <li>getResource("com")--->(maven)测试--->file:/E:/Workspaces/feilong/feilong-platform/feilong-common/target/test-classes/com</li>
	 * </ul>
	 * <br>
	 * 在web环境中,(即使打成jar的情形):<br>
	 * <ul>
	 * <li>file:/E:/Workspaces/feilong/feilong-platform/feilong-spring-test-2.5/src/main/webapp/WEB-INF/classes/</li>
	 * <li>file:/E:/Workspaces/feilong/feilong-platform/feilong-spring-test-2.5/src/main/webapp/WEB-INF/classes/com/</li>
	 * </ul>
	 * 
	 * @param name
	 *            资源名称
	 * @return 查找具有给定名称的资源
	 */
	public static URL getResource(String name){
		ClassLoader classLoader = ClassLoaderUtil.class.getClassLoader();
		// 查找具有给定名称的资源。
		URL url = classLoader.getResource(name);
		return url;
	}

	/**
	 * 获得 项目的 classpath,及classes编译的根目录
	 * 
	 * @see {@link #getResource(String)}
	 * @since 1.0
	 * @return 获得 项目的 classpath
	 */
	public static URL getClassPath(){
		URL url = getResource("");
		return url;
	}

	/**********************************************************************************/
	/**
	 * This is a convenience method to load a resource as a stream.
	 * <p/>
	 * The algorithm used to find the resource is given in getResource()
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 */
	public static InputStream getResourceAsStream(String resourceName,Class<?> callingClass){
		URL url = getResource(resourceName, callingClass);
		try{
			return (url != null) ? url.openStream() : null;
		}catch (IOException e){
			return null;
		}
	}

	/**
	 * Load a given resource.
	 * <p/>
	 * This method will try to load the resource using the following methods (in order):
	 * <ul>
	 * <li>From {@link Thread#getContextClassLoader() Thread.currentThread().getContextClassLoader()}
	 * <li>From {@link Class#getClassLoader() ClassLoaderUtil.class.getClassLoader()}
	 * <li>From the {@link Class#getClassLoader() callingClass.getClassLoader() }
	 * </ul>
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 */
	public static URL getResource(String resourceName,Class<?> callingClass){
		ClassLoader classLoader = getClassLoaderByCurrentThread();
		URL url = classLoader.getResource(resourceName);
		if (url == null){
			classLoader = getClassLoaderByClass(ClassLoaderUtil.class);
			url = classLoader.getResource(resourceName);
			if (url == null){
				classLoader = getClassLoaderByClass(callingClass);
				url = classLoader.getResource(resourceName);
			}
		}
		if (url == null){
			log.debug("resourceName:\"" + resourceName + "\" in all ClassLoader is null");
		}else{
			log.debug("In ClassLoader " + classLoader.getResource("") + " found the resourceName:" + resourceName);
		}
		return url;
	}

	/**
	 * Load resources
	 * 
	 * @param resourceName
	 * @param callingClass
	 * @throws IOException
	 */
	public static Enumeration<URL> getResources(String resourceName,Class<?> callingClass) throws IOException{
		ClassLoader classLoader = getClassLoaderByCurrentThread();
		Enumeration<URL> urls = classLoader.getResources(resourceName);
		if (urls == null){
			classLoader = getClassLoaderByClass(ClassLoaderUtil.class);
			urls = classLoader.getResources(resourceName);
			if (urls == null){
				classLoader = getClassLoaderByClass(callingClass);
				urls = classLoader.getResources(resourceName);
			}
		}
		if (urls == null){
			log.debug("resourceName:\"" + resourceName + "\" in all ClassLoader is null");
		}else{
			log.debug("In ClassLoader " + classLoader.getResource("") + " found the resourceName:" + resourceName);
		}
		return urls;
	}

	/**
	 * Load a class with a given name.
	 * <p/>
	 * It will try to load the class in the following order:
	 * <ul>
	 * <li>From {@link Thread#getContextClassLoader() Thread.currentThread().getContextClassLoader()}
	 * <li>Using the basic {@link Class#forName(java.lang.String) }
	 * <li>From {@link Class#getClassLoader() ClassLoaderUtil.class.getClassLoader()}
	 * <li>From the {@link Class#getClassLoader() callingClass.getClassLoader() }
	 * </ul>
	 * 
	 * @param className
	 *            The name of the class to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @throws ClassNotFoundException
	 *             If the class cannot be found anywhere.
	 */
	public static Class<?> loadClass(String className,Class<?> callingClass) throws ClassNotFoundException{
		ClassLoader classLoader = null;
		try{
			classLoader = getClassLoaderByCurrentThread();
			return classLoader.loadClass(className);
		}catch (ClassNotFoundException e){
			try{
				return Class.forName(className);
			}catch (ClassNotFoundException ex){
				try{
					classLoader = getClassLoaderByClass(ClassLoaderUtil.class);
					return classLoader.loadClass(className);
				}catch (ClassNotFoundException exc){
					classLoader = getClassLoaderByClass(callingClass);
					return classLoader.loadClass(className);
				}
			}
		}
	}

	/**********************************************************************************/
	/**
	 * 通过Thread.currentThread().getContextClassLoader() 获得ClassLoader
	 * 
	 * @return
	 */
	public static ClassLoader getClassLoaderByCurrentThread(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		log.debug("classLoader" + classLoader);
		log.debug("Thread.currentThread().getContextClassLoader() classpath is:" + classLoader.getResource(""));
		return classLoader;
	}

	/**
	 * 通过类来获得 classLoader
	 * 
	 * @param callingClass
	 * @return
	 */
	public static ClassLoader getClassLoaderByClass(Class<?> callingClass){
		ClassLoader classLoader = callingClass.getClassLoader();
		log.debug("classLoader" + classLoader);
		log.debug(callingClass.getName() + ".class.getClassLoader() classpath is:" + classLoader.getResource(""));
		return classLoader;
	}

	/********************************************************************************************************/
	/**
	 * Prints the current classloader hierarchy <br>
	 * useful for debugging.
	 */
	public static void printClassLoader(){
		log.info("use " + ClassLoaderUtil.class.getSimpleName() + ".printClassLoader");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		printClassLoader(classLoader);
	}

	/**
	 * Prints the classloader hierarchy from a given classloader<br>
	 * useful for debugging.
	 */
	public static void printClassLoader(ClassLoader classLoader){
		log.info(classLoader.toString());
		if (classLoader != null){
			printClassLoader(classLoader.getParent());
		}
	}
}
