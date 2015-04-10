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
package com.feilong.spring.jdbc.datasource;

/**
 * 以 {@link ThreadLocal} 形式,维护数据源的key.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年3月31日 下午3:19:15
 * @see MultipleGroupReadWriteDataSource
 * @see java.lang.ThreadLocal
 * @see "org.springframework.web.context.request.RequestContextHolder"
 * @since 1.1.1
 * @since jdk 1.5
 */
public class MultipleGroupReadWriteStatusHolder{

    /** 为每一个线程维护变量的副本 . */
    private static final ThreadLocal<String> MULTIPLE_DATASOURCE_GROUP_NAME_HOLDER = new ThreadLocal<String>();

    /**
     * 设置当前线程的线程局部变量的值.
     *
     * @param dataSourceName
     *            the multiple data source group name
     * @see java.lang.ThreadLocal#set(Object)
     */
    public static void setMultipleDataSourceGroupName(String dataSourceName){
        MULTIPLE_DATASOURCE_GROUP_NAME_HOLDER.set(dataSourceName);
    }

    /**
     * 返回当前线程所对应的线程局部变量.
     *
     * @return the multiple data source group name
     * @see java.lang.ThreadLocal#get()
     */
    public static String getMultipleDataSourceGroupName(){
        return MULTIPLE_DATASOURCE_GROUP_NAME_HOLDER.get();
    }

    /**
     * 将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法.<br>
     * 需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收,<br>
     * 所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度
     *
     * @see java.lang.ThreadLocal#remove()
     * @since jdk1.5
     */
    public static void clearMultipleDataSourceGroupName(){
        MULTIPLE_DATASOURCE_GROUP_NAME_HOLDER.remove();
    }
}
