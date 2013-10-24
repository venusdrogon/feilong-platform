package elsepackage.temple.io;

import com.feilong.commons.core.enumeration.CharsetType;
//package com.feilong.commons.core.temple.io;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.Hashtable;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//import java.util.zip.ZipInputStream;
//
//import org.apache.commons.lang3.ClassUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * jAR文件是归档文件，因此需要对该归档文件进行解析
// * 
// * @author 金鑫 2010-2-5 下午12:03:47
// */
//public final class JarResources{
//
//	private final static Logger	log				= LoggerFactory.getLogger(JarResources.class);
//
//	// 一些重要字段用来跟踪和存储指定JAR文件的内容。
//	public boolean				debugOn			= false;
//
//	private Hashtable			htSizes			= new Hashtable();
//
//	private Hashtable			htJarContents	= new Hashtable();
//
//	private String				jarFileName;
//
//	/**
//	 * 在类进行实例化时设置JAR的文件名，然后调用init()方法完成所有的初始化工作
//	 * 
//	 * @param jarFileName
//	 *            jar文件名称
//	 */
//	public JarResources(String jarFileName){
//		this.jarFileName = jarFileName;
//		init();
//	}
//
//	/**
//	 * init()方法把指定JAR文件的全部内容装入到一个杂凑表(hashtable)中(杂凑表名可以从资源名进行访问)<br>
//	 * init()是一个功能相当强大的方法，让我们来逐步理解它的功能。<br>
//	 * ZipFile类使我们基本上能访问JAR/Zip压缩文档的头部信息。这和一个文件系统的目录信息相似。<br>
//	 * 在这里我们列出Zip文件的所有条目(entry)，并且按照文档中的每个资源的尺寸创建htSizes杂凑表(hashtable)。
//	 */
//	private void init(){
//		try{
//			// extracts just sizes only.
//			ZipFile zipFile = new ZipFile(jarFileName);
//			Enumeration enumeration = zipFile.entries();
//			while (enumeration.hasMoreElements()){
//				ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
//				if (debugOn){
//					log.debug(dumpZipEntry(zipEntry));
//				}
//				htSizes.put(zipEntry.getName(), new Integer((int) zipEntry.getSize()));
//			}
//			zipFile.close();
//			/**
//			 * 我们使用ZipInputStream类访问压缩文档。ZipInputStream类完成了所有的工作以使我们能读出压缩文档中任意一个资源。我们从包含每个资源的压缩文档中读出准确数目的字节，并将它们存储在一个可由资源名访问的htJarContents杂凑表中
//			 */
//			// extract resources and put them into the hashtable.
//			FileInputStream fileInputStream = new FileInputStream(jarFileName);
//			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//			ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
//			ZipEntry zipEntry = null;
//			while ((zipEntry = zipInputStream.getNextEntry()) != null){
//				if (zipEntry.isDirectory()){
//					continue;// 啊哟!没有处理子目录中的资源啊
//				}
//				if (debugOn){
//					log.debug("ze.getName()=" + zipEntry.getName() + "," + "getSize()=" + zipEntry.getSize());
//				}
//				int size = (int) zipEntry.getSize();
//				// -1 means unknown size.
//				if (size == -1){
//					size = ((Integer) htSizes.get(zipEntry.getName())).intValue();
//				}
//				byte[] b = new byte[size];
//				int rb = 0;
//				int chunk = 0;
//				while ((size - rb) > 0){
//					chunk = zipInputStream.read(b, rb, size - rb);
//					if (chunk == -1){
//						break;
//					}
//					rb += chunk;
//				}
//				// add to internal resource hashtable
//				htJarContents.put(zipEntry.getName(), b);
//				if (debugOn){
//					log.debug(zipEntry.getName() + " rb=" + rb + ",size=" + size + ",csize=" + zipEntry.getCompressedSize());
//				}
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Dumps a zip entry into a string.
//	 * 
//	 * @param zipEntry
//	 *            a ZipEntry
//	 */
//	private String dumpZipEntry(ZipEntry zipEntry){
//		StringBuffer sb = new StringBuffer();
//		if (zipEntry.isDirectory()){
//			sb.append("d ");
//		}else{
//			sb.append("f ");
//		}
//		if (zipEntry.getMethod() == ZipEntry.STORED){
//			sb.append("stored   ");
//		}else{
//			sb.append("defalted ");
//		}
//		sb.append(zipEntry.getName());
//		sb.append("\t");
//		sb.append("" + zipEntry.getSize());
//		if (zipEntry.getMethod() == ZipEntry.DEFLATED){
//			sb.append("/" + zipEntry.getCompressedSize());
//		}
//		return sb.toString();
//	}
//
//	/**
//	 * Extracts a jar resource as a blob. <br>
//	 * 用来确定每个资源的名字是压缩文档中资源的实际路径名，而不是包(package)中类的名字。<br>
//	 * 也就是说，java.util.zip包中的ZipEntry类的名字应为“java/util /zip.ZipEntry”，而不是"java.util.zip.ZipEntry"。
//	 * 
//	 * @param resourceName
//	 *            a resource name.
//	 */
//	public byte[] getResource(String resourceName){
//		return (byte[]) htJarContents.get(resourceName);
//	}
//
//	/**
//	 * 获得资源内容
//	 * 
//	 * @param jarFileName
//	 *            jarFileName 绝对路径的JAR文件
//	 * @param resourceName
//	 *            resourceName 资源文件的路径 形如 bin/template/java/action.template 即前面不要/
//	 * @throws UnsupportedEncodingException
//	 */
//	public static String getResourceContent(String jarFileName,String resourceName) throws UnsupportedEncodingException{
//		JarResources jarResources = new JarResources(jarFileName);
//		byte[] buff = jarResources.getResource(resourceName);
//		return new String(buff, CharsetType.UTF8);
//	}
//
//	public static Class[] getClassFromPackage(String entirePackagePath){
//		Class thisClazz = ClassUtils.class;
//		String thisName = thisClazz.getName();
//		String thisFileName = thisName.substring(thisName.lastIndexOf('.') + 1) + ".class";
//		URL url = thisClazz.getResource(thisFileName);
//		String strUrl = url.toString();
//		boolean isInJar = false;
//		// get classes dictionary or get jar file who contains the classes
//		if (strUrl.substring(0, 4).equals("jar:")){
//			strUrl = strUrl.substring(10);
//			int index = strUrl.indexOf(".jar");
//			strUrl = strUrl.substring(0, index + 4);
//			isInJar = true;
//		}else{
//			// use in the develop process,
//			// in the run time the class must in the jar
//			strUrl = strUrl.substring(6);
//			int index = strUrl.lastIndexOf("classes");
//			strUrl = strUrl.substring(0, index + 7);
//		}
//		List classes = new ArrayList();
//		sun.tools.java.ClassPath classPath = new ClassPath(strUrl);
//		Identifier iden = Identifier.lookup(entirePackagePath);
//		Class stateClass;
//		try{
//			sun.tools.java.Package pkg = new sun.tools.java.Package(classPath, iden);
//			java.util.Enumeration e = pkg.getBinaryFiles();
//			String classFileName;
//			int classIndex;
//			String className;
//			for (; e.hasMoreElements();){
//				classFileName = ((ClassFile) e.nextElement()).getName();
//				if (isInJar){
//					String tmp = classFileName.substring(entirePackagePath.length() + 1);
//					// 在jar中运行是。会递归取得包下的Class
//					// 开发环境时则不会
//					if (tmp.indexOf('/') != -1){
//						continue;
//					}
//				}
//				classIndex = classFileName.lastIndexOf('.');
//				classFileName = classFileName.substring(0, classIndex);
//				// 在jar中运行时，取得的classFileName为XXX/XXX/...../ActivityState
//				// 在开发环境时 是 ActivityState
//				if (isInJar){
//					className = classFileName.replace('/', '.');
//				}else{
//					className = entirePackagePath + "." + classFileName;
//				}
//				stateClass = Class.forName(className);
//				;
//				classes.add(stateClass);
//				;
//			}
//		}catch (IOException ex){
//			System.out.println("未找到包路径");
//			ex.printStackTrace();
//		}catch (ClassNotFoundException ex){
//			System.out.println("未找到需要加载的类");
//			ex.printStackTrace();
//		}
//		Class[] result = new Class[classes.size()];
//		return result;
//	}
//}
