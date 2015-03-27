/*
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
package com.feilong.spring.io;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.web.context.support.ServletContextResource;

/**
 * {@link org.springframework.core.io.Resource} 工具类.
 * 
 * <h3>Resource接口:</h3>
 * 
 * <blockquote>
 * <p>
 * 
 * <ul>
 * <li>{@link Resource#getInputStream()} 定位并打开资源，返回读取此资源的一个 InputStream。<br>
 * <b>每次调用预期会返回一个新的 InputStream，由调用者负责关闭这个流。</b></li>
 * <li>{@link Resource#exists()}返回标识这个资源在物理上是否的确存在的 boolean 值。</li>
 * <li>{@link Resource#isOpen()}返回标识这个资源是否有已打开流的处理类的 boolean 值。<br>
 * 如果为 true，则此InputStream 就不能被多次读取， 而且只能被读取一次然后关闭以避免资源泄漏。 <br>
 * 除了 InputStreamResource，常见的resource实现都会返回 false。</li>
 * <li>{@link Resource#getDescription()}返回资源的描述，一般在与此资源相关的错误输出时使用。 此描述通常是完整的文件名或实际的URL地址。</li>
 * </ul>
 * 
 * </p>
 * </blockquote>
 * 
 * 
 * <h3>Spring提供了很多 Resource 的实现:</h3>
 * 
 * <blockquote>
 * <p>
 * 
 * <blockquote>
 * <table border="1" cellspacing="0" cellpadding="4">
 * <tr style="background-color:#ccccff">
 * <th align=left>实现</th>
 * <th align=left>说明</th>
 * </tr>
 * <tr valign=top>
 * <td>{@link UrlResource}</td>
 * <td>封装了java.net.URL，它能够被用来访问任何通过URL可以获得的对象，例如：文件、HTTP对象、FTP对象等。<br>
 *   所有的URL都有个标准的 String表示，这些标准前缀可以标识不同的URL类型， 包括 file:访问文件系统路径， http: 通过HTTP协议访问的资源， ftp: 通过FTP访问的资源等等。<br>
 * {@link UrlResource} 对象可以在Java代码中显式地使用 UrlResource 构造函数来创建。 但更多的是通过调用带表示路径的 String 参数的API函数隐式地创建。<br>
 * 
 * 在后一种情况下，JavaBeans的 {@link java.beans.PropertyEditor} 会最终决定哪种类型的 Resource 被创建。<br>
 * 如果这个字符串包含一些众所周知的前缀，比如 classpath:，它就会创建一个对应的已串行化的 Resource。 <br>
 * 然而，如果不能分辨出这个前缀，就会假定它是个标准的URL字符串，然后创建{@link UrlResource}。</td>
 * </tr>
 * <tr valign=top style="background-color:#eeeeff">
 * <td>{@link ClassPathResource}</td>
 * <td>这个类标识从classpath获得的资源。 它会使用线程context的类加载器(class loader)、给定的类加载器或者用来载入资源的给定类。 <br>
 * 如果类路径上的资源存在于文件系统里，这个 Resource 的实现会提供类似于java.io.File的功能。 <br>
 * 而如果资源是存在于还未解开(被servlet引擎或其它的环境解开)的jar包中，这些 Resource 实现会提供类似于java.net.URL 的功能。   {@link ClassPathResource}
 * 对象可以在Java代码中显式地使用ClassPathResource 构造函数来创建。<br>
 * 但更多的是通过调用带表示路径的String参数的API函数隐式地创建。<br>
 * 在后一种情况下，JavaBeans的 PropertyEditor 会分辨字符串中 classpath: 前缀，然后相应创建 {@link ClassPathResource}。</td>
 * </tr>
 * <tr valign=top >
 * <td>{@link FileSystemResource}</td>
 * <td>这是为处理 java.io.File 而准备的Resource实现。 <br>
 * 它既可以作为File提供，也可以作为URL。</td>
 * </tr>
 * <tr valign=top style="background-color:#eeeeff">
 * <td>{@link ServletContextResource}</td>
 * <td>这是为 ServletContext 资源提供的 Resource 实现，它负责解析相关web应用根目录中的相对路径。 <br>
 * 它始终支持以流和URL的方式访问。 但是只有当web应用包被解开并且资源在文件系统的物理路径上时，才允许以 java.io.File 方式访问。 <br>
 * 是否解开并且在文件系统中访问，还是直接从JAR包访问或以其它方式访问如DB(这是可以想象的)，仅取决于Servlet容器。</td>
 * </tr>
 * <tr valign=top >
 * <td>{@link InputStreamResource}</td>
 * <td>这是为给定的 InputStream 而准备的 Resource 实现。 它只有在没有其它合适的 Resource 实现时才使用。<br>
 * 而且，只要有可能就尽量使用{@link ByteArrayResource} 或者其它基于文件的Resource 实现。 <br>
 * 与其它 Resource 实现不同的是，这是个 已经 打开资源的描述符-因此 isOpen() 函数返回 true。<br>
 * 如果你需要在其它位置保持这个资源的描述符或者多次读取一个流，请不要使用它。</td>
 * </tr>
 * <tr valign=top style="background-color:#eeeeff">
 * <td>{@link ByteArrayResource}</td>
 * <td>这是为给定的byte数组准备的 Resource 实现。 它会为给定的byte数组构造一个 {@link ByteArrayResource}。 <br>
 * 它在从任何给定的byte数组读取内容时很有用，因为不用转换成单一作用的 InputStreamResource。</td>
 * </tr>
 * </table>
 * </blockquote>
 * 
 * </p>
 * </blockquote>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年3月8日 下午4:42:40
 * @see org.springframework.core.io.Resource
 * @see org.springframework.core.io.ResourceLoader
 * @see org.springframework.core.io.UrlResource
 * @see org.springframework.core.io.ClassPathResource
 * @see org.springframework.util.ResourceUtils
 * @see org.springframework.web.context.support.ServletContextResource
 * @since 1.0.9
 */
public final class ResourceUtil{

    /** Don't let anyone instantiate this class. */
    private ResourceUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 获得 resource.
     *
     * @param urlOrPath
     *            the url or path
     * @return the resource
     * @see org.springframework.core.io.DefaultResourceLoader#DefaultResourceLoader()
     * @see org.springframework.core.io.ResourceLoader
     */
    public static Resource getResource(String urlOrPath){
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(urlOrPath);
        return resource;
    }
}