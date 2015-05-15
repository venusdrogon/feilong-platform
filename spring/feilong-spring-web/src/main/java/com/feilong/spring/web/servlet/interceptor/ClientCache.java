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
package com.feilong.spring.web.servlet.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * (为了提高速度一些浏览器会缓存浏览者浏览过的页面,通过下面的相关参数的定义,浏览器一般不会缓存页面,而且浏览器无法脱机浏览.<br>
 * 借鉴了 胡总的 {@link "NoClientCache"}
 * <p>
 * {@link #value()} 参数优先级最高
 * </p>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年3月30日 下午4:25:10
 * @since 1.0.9
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientCache{

    /**
     * 过期时间 = {@link #cacheControl()} max-age 属性,单位<span style="color:red">秒</span>. <br>
     * 举例:
     * Cache-Control: max-age=3600
     * 
     * 只需要设置 <code>@ClientCache(3600)</code>
     * <p>
     * if value <=0 表示不缓存<br>
     * 默认:0 不缓存
     * </p>
     *
     * @return the long
     * @since 1.0.9
     */
    long value() default 0;

    /**
     * Pragma头域用来包含实现特定的指令，最常用的是Pragma:no-cache。<br>
     * 在HTTP/1.1协议中，它的含义和Cache-Control:no-cache相同。.
     * 
     * <p>
     * 
     * 很多人认为在HTTP头信息中设置了Pragma: no-cache后会让内容无法被缓存。 <br>
     * 但事实并非如此：HTTP的规范中，响应型头信息没有任何关于Pragma属性的说明， <br>
     * 原文如下（来自 http1.1 规范）： <br>
     * Note: because the meaning of “Pragma: no-cache as a response header field is not actually specified, it does not provide a reliable
     * replacement for “Cache-Control: no-cache” in a response <br>
     * ，虽然少数集中缓存服务器会遵循这个头信息，但大部分不会。<br>
     * 用了Pragma也不起什么作用，要用就使 用下列头信息： {@link #expires()} {@link #cacheControl()}
     * 
     * 
     * </p>
     *
     * @return the string
     * @since HTTP/1.0
     * @deprecated
     */
    @SuppressWarnings("dep-ann")
    String pragma() default "no-cache";

    /**
     * Expires（过期时间） 属性是HTTP控制缓存的基本手段,Expires表示的是一个绝对的时刻.<br>
     * 这个属性告诉缓存器：相关副本在多长时间内是新鲜的。<br>
     * 过了这个时间，缓存器就会向源服务器发送请求，检查文档是否被修改。<br>
     * 几乎所有的缓存服务器都支持Expires（过期时间）属性；
     * 
     * <p>
     * 过期时间头信息属性值只能是HTTP格式的日期时间，其他的都会被解析成当前时间“之前”，副本会过期
     * </p>
     * 
     * <p>
     * 记住：HTTP的日期时间必须是格林威治时间（GMT），而不是本地时间。<br>
     * 举例： Expires: Fri, 30 Oct 1998 14:19:41 GMT
     * </p>
     * 
     * <p>
     * 需要注意的是，这里的Expires指示告诉Client如果这个response没有过期，没有必要再次访问。<br>
     * 但这并不意味着response过期之前，Client不能向Server发送请求。<br>
     * 同时，Expires也不意味在response过期以后，Client需要主动的去Server取最新的消息。<br>
     * 
     * </p>
     * 
     * <p style="color:red">
     * 对于 Expires 响应头我们需要注意一点，当响应头中已经设置了 Cache-Contrl:max-age 以后， max-age 将覆盖掉 expires 的效果<br>
     * （参见 http1.1 规范）原文如下：<br>
     * Note: if a response includes a Cache-Control field with the max-age directive (see section 14.9.3 ), that directive overrides the
     * Expires field.<br>
     * 对于 Expires 特别适合对于网站静态资源的缓存，比如 js,image,logo 等，这些资源不会经常发生变化，另外一个也适合于一些周期性更新的内容。
     * </p>
     * 
     * @return the long
     * @since HTTP/1.0
     * 
     * @deprecated
     */
    @SuppressWarnings("dep-ann")
    long expires() default -1;

    /**
     * HTTP 1.1介绍了另外一组头信息属性：Cache-Control响应头信息，让网站的发布者可以更全面的控制他们的内容，并定位过期时间的限制。
     * 
     * <h3>有用的 Cache-Control响应头信息包括：</h3>
     * 
     * <blockquote>
     * 
     * 
     * <blockquote>
     * <table border="1" cellspacing="0" cellpadding="4">
     * <tr style="background-color:#ccccff">
     * <th align="left">字段</th>
     * <th align="left">说明</th>
     * </tr>
     * <tr valign="top">
     * <td>max-age=[秒]</td>
     * <td>执行缓存被认为是最新的最长时间。<br>
     * 类似于过期时间，这个参数是基于请求时间的相对时间间隔，而不是绝对过期时间，<br>
     * [秒]是一个数字，单位是秒：从请求时间开始到过期时间之间的秒数。</td>
     * </tr>
     * <tr valign="top" style="background-color:#eeeeff">
     * <td>s-maxage=[秒]</td>
     * <td>类似于max-age属性，除了他应用于共享（如：代理服务器）缓存</td>
     * </tr>
     * <tr valign="top">
     * <td>public</td>
     * <td>标记认证内容也可以被缓存，一般来说： 经过HTTP认证才能访问的内容，输出是自动不可以缓存的；</td>
     * </tr>
     * <tr valign="top" style="background-color:#eeeeff">
     * <td>no-cache</td>
     * <td>强制每次请求直接发送给源服务器，而不经过本地缓存版本的校验。<br>
     * 这对于需要确认认证应用很有用（可以和public结合使用），或者严格要求使用最新数据的应用（不惜牺牲使用缓存的所有好处）；</td>
     * </tr>
     * <tr valign="top">
     * <td>no-store</td>
     * <td>强制缓存在任何情况下都不要保留任何副本</td>
     * </tr>
     * <tr valign="top" style="background-color:#eeeeff">
     * <td>must-revalidate</td>
     * <td>告诉缓存必须遵循所有你给予副本的新鲜度的，HTTP允许缓存在某些特定情况下返回过期数据，<br>
     * 指定了这个属性，你高速缓存，你希望严格的遵循你的规则。</td>
     * </tr>
     * <tr valign="top">
     * <td>proxy-revalidate</td>
     * <td>和 must-revalidate类似，除了他只对缓存代理服务器起作用</td>
     * </tr>
     * </table>
     * 
     * 举例:
     * Cache-Control: max-age=3600, must-revalidate
     * </blockquote>
     * 
     * <p style="color:red">
     * 对于 Expires 响应头我们需要注意一点，当响应头中已经设置了 Cache-Contrl:max-age 以后， max-age 将覆盖掉 expires 的效果<br>
     * （参见 http1.1 规范）原文如下：<br>
     * Note: if a response includes a Cache-Control field with the max-age directive (see section 14.9.3 ), that directive overrides the
     * Expires field.<br>
     * 对于 Expires 特别适合对于网站静态资源的缓存，比如 js,image,logo 等，这些资源不会经常发生变化，另外一个也适合于一些周期性更新的内容。
     * </p>
     *
     * @return the string
     * @since HTTP/1.1
     * @deprecated
     */
    @SuppressWarnings("dep-ann")
    String cacheControl() default "no-cache";
}
