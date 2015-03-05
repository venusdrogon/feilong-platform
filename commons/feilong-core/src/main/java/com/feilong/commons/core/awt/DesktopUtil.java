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
package com.feilong.commons.core.awt;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.net.URIUtil;

/**
 * 允许 Java应用程序启动已在本机桌面上注册的关联应用程序，以及处理 URI 或文件 .
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-5 下午05:07:45
 * @since 1.0.0
 * @since jdk 1.6
 */
public final class DesktopUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(DesktopUtil.class);

    /** Don't let anyone instantiate this class. */
    private DesktopUtil(){
        //AssertionError不是必须的。但它可以避免不小心在类的内部调用构造器。保证该类在任何情况下都不会被实例化。
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 使用系统默认浏览器,打开url.
     * 
     * @param url
     *            url地址
     */
    public static void browse(String url){
        // 判断当前系统是否支持Java AWT Desktop扩展
        if (Desktop.isDesktopSupported()){
            // 创建一个URI实例
            URI uri = URIUtil.create(url, CharsetType.UTF8);
            // 获取当前系统桌面扩展
            Desktop desktop = Desktop.getDesktop();
            // 判断系统桌面是否支持要执行的功能
            if (desktop.isSupported(Action.BROWSE)){
                // 获取系统默认浏览器打开链接
                try{
                    desktop.browse(uri);
                }catch (IOException e){
                    log.error("", e);
                }
            }
        }else{
            log.error("don'nt Support Desktop");
        }
    }

    /**
     * 启动关联应用程序来打开文件..
     * 
     * @param url
     *            url地址
     */
    public static void open(String url){
        // 判断当前系统是否支持Java AWT Desktop扩展
        if (Desktop.isDesktopSupported()){

            // 获取当前系统桌面扩展
            Desktop desktop = Desktop.getDesktop();

            // 判断系统桌面是否支持要执行的功能
            if (desktop.isSupported(Action.OPEN)){
                // 启动关联应用程序来打开文件
                File file = new File(url);
                try{
                    desktop.open(file);
                }catch (IOException e){
                    log.error(e.getClass().getName(), e);
                }
            }
        }else{
            log.error("don't Support Desktop");
        }
    }
}
