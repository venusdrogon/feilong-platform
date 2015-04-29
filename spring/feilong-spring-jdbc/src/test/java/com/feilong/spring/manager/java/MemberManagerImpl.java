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
package com.feilong.spring.manager.java;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feilong.spring.jdbc.datasource.MultipleGroupDataSource;
import com.feilong.test.User;

/**
 * The Class MemberManagerImpl.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年4月6日 上午5:39:18
 * @since 1.1.1
 */
@MultipleGroupDataSource("storeSource")
@Service
public class MemberManagerImpl implements MemberManager{

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.spring.manager.MemberManager#getUser(java.lang.String)
     */
    @Override
    public User getUser(String name){
        return new User(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.spring.manager.MemberManager#addUser(java.lang.String)
     */
    @Override
    @Transactional()
    public void addUser(String name){

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.spring.manager.MemberManager#getUserReadOnly(java.lang.String)
     */
    @Transactional(readOnly = true)
    @Override
    public User getUserReadOnly(String name){
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.spring.manager.java.MemberManager#getUserExcludeJsonException(java.io.InputStream)
     */
    @Override
    public User getUserExcludeJsonException(Object inputStream){
        return null;
    }
}
