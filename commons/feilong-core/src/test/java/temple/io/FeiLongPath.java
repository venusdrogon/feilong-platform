package temple.io;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * 这个类提供了一些根据类的class文件位置来定位的方法。
 * 
 * @author 金鑫 2010-2-5 下午01:24:40
 */
public class FeiLongPath{

	/**
	 * 获取一个类的class文件所在的绝对路径。 <br>
	 * 这个类可以是JDK自身的类，也可以是用户自定义的类，或者是第三方开发包里的类。<br>
	 * 只要是在本程序中可以被加载的类，都可以定位到它的class文件的绝对路径。
	 * 
	 * @param cls
	 *            一个对象的Class属性
	 * @return 这个类的class文件位置的绝对路径。 如果没有这个类的定义，则返回null。
	 */
	public static String getPathFromClass(Class cls){
		String path = null;
		if (cls == null){
			throw new NullPointerException();
		}
		URL url = getClassLocationURL(cls);
		if (url != null){
			path = url.getPath();
			if ("jar".equalsIgnoreCase(url.getProtocol())){
				try{
					path = new URL(path).getPath();
				}catch (MalformedURLException e){
					e.printStackTrace();
				}
				int location = path.indexOf("!/");
				if (location != -1){
					path = path.substring(0, location);
				}
			}
			File file = new File(path);
			try{
				path = file.getCanonicalPath();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * 这个方法可以通过与某个类的class文件的相对路径来获取文件或目录的绝对路径。 <br>
	 * 通常在程序中很难定位某个相对路径，特别是在B/S应用中。 通过这个方法，我们可以根据我们程序自身的类文件的位置来定位某个相对路径。<br>
	 * 比如：某个txt文件相对于程序的Test类文件的路径是../../resource/test.txt， 那么使用本方法Path.getFullPathRelateClass("../../resource/test.txt",Test.class) 得到的结果是txt文件的在系统中的绝对路径。
	 * 
	 * @param relatedPath
	 *            相对路径
	 * @param cls
	 *            用来定位的类
	 * @return 相对路径所对应的绝对路径
	 * @throws IOException
	 *             因为本方法将查询文件系统，所以可能抛出IO异常
	 */
	public static String getFullPathRelateClass(String relatedPath,Class cls){
		String path = null;
		String clsPath = getPathFromClass(cls);
		File clsFile = new File(clsPath);
		String tempPath = clsFile.getParent() + File.separator + relatedPath;
		File file = new File(tempPath);
		try{
			path = file.getCanonicalPath();
		}catch (IOException e){
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 获取类的class文件位置的URL。<br>
	 * 这个方法是本类最基础的方法，供其它方法调用。
	 */
	private static URL getClassLocationURL(final Class cls){
		if (cls == null){
			throw new IllegalArgumentException("null input: cls");
		}
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		// java.lang.Class contract does not specify
		// if 'pd' can ever be null;
		// it is not the case for Sun's implementations, but guard against null just in case:
		if (pd != null){
			final CodeSource cs = pd.getCodeSource();
			// 'cs' can be null depending on the classloader behavior:
			if (cs != null){
				result = cs.getLocation();
			}
			if (result != null){
				// Convert a code source location into a full class file location for some common cases:
				if ("file".equals(result.getProtocol())){
					try{
						if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip")){
							result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
						}else if (new File(result.getFile()).isDirectory()){
							result = new URL(result, clsAsResource);
						}
					}catch (MalformedURLException e){
						e.printStackTrace();
					}
				}
			}
		}
		if (result == null){
			// Try to find 'cls' definition as a resource;
			// this is not document．d to be legal, but Sun's implementations seem to allow this:
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}

	/**
	 * 获取工程所在目录
	 * 
	 * @return 获取工程所在目录
	 */
	public static String getProjectPath(){
		URL url = FeiLongPath.class.getProtectionDomain().getCodeSource().getLocation();
		String filePath = "";
		try{
			filePath = URLDecoder.decode(url.getPath(), CharsetType.UTF8);
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		if (filePath.endsWith(".jar")){
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		}
		return filePath;
	}
}
