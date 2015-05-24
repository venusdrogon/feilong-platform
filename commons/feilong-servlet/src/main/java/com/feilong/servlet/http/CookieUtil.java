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
package com.feilong.servlet.http;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.util.Validator;
import com.feilong.servlet.http.entity.CookieEntity;

/**
 * {@link javax.servlet.http.Cookie} 工具 类.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 金鑫 2010-6-24 上午08:05:32
 * @version 金鑫 2012-5-18 14:53
 * @see javax.servlet.http.Cookie
 * @see "org.springframework.web.util.CookieGenerator"
 * @since 1.0.0
 */
public final class CookieUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(CookieUtil.class);

    /** Don't let anyone instantiate this class. */
    private CookieUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 取到Cookie值.
     * 
     * @param request
     *            HttpServletRequest
     * @param cookieName
     *            属性名
     * @return 取到Cookie值
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName){
        Cookie cookie = getCookie(request, cookieName);
        if (null != cookie){
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 获得cookie.
     * 
     * @param request
     *            the request
     * @param cookieName
     *            the cookie name
     * @return the cookie
     */
    public static Cookie getCookie(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();
        if (Validator.isNotNullOrEmpty(cookies)){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        log.warn("can'nt find the cookie:{}", cookieName);
        return null;
    }

    /**
     * 将cookie的 key 和value转成 map(TreeMap).
     *
     * @param request
     *            the request
     * @return the cookie map
     */
    public static Map<String, String> getCookieMap(HttpServletRequest request){
        Map<String, String> map = new TreeMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (Validator.isNotNullOrEmpty(cookies)){
            for (Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                map.put(name, value);
            }
        }
        return map;
    }

    /**
     * 删除cookie.
     * 
     * @param cookieName
     *            the cookie name
     * @param response
     *            the response
     */
    public static void deleteCookie(String cookieName,HttpServletResponse response){
        // 设置为0为立即删除该Cookie
        int expiry = 0;
        CookieEntity cookieEntity = new CookieEntity(cookieName, null, expiry);
        addCookie(cookieEntity, response);
    }

    /**
     * 创建个cookie.
     * 
     * @param cookieEntity
     *            cookieEntity
     * @param response
     *            response
     * 
     * @see "org.apache.catalina.connector.Response#generateCookieString(Cookie, boolean)"
     */
    public static void addCookie(CookieEntity cookieEntity,HttpServletResponse response){
        Cookie cookie = new Cookie(cookieEntity.getName(), cookieEntity.getValue());

        // 设置Cookie过期之前的时间,以秒计
        cookie.setMaxAge(cookieEntity.getMaxAge());//设置以秒计的cookie的最大存活时间。
        cookie.setComment(cookieEntity.getComment());//指定一个注释来描述cookie的目的。
        cookie.setDomain(cookieEntity.getDomain());// 指明cookie应当被声明的域。
        cookie.setPath(cookieEntity.getPath());//指定客户端将cookie返回的cookie的路径。
        cookie.setSecure(cookieEntity.getSecure());// 指定是否cookie应该只通过安全协议，例如HTTPS或SSL,传送给浏览器。
        cookie.setVersion(cookieEntity.getVersion());//设置本cookie遵循的cookie的协议的版本

        //@since Servlet 3.0
        //cookie.setHttpOnly(cookieEntity.getHttpOnly());

        response.addCookie(cookie);
    }
}
