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
package cn.javass.spring.chapter6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.javass.spring.chapter6.service.IHelloWorldService;

/**
 * The Class HelloWorldService.
 */
@SuppressWarnings("all")
public class HelloWorldService implements IHelloWorldService{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(HelloWorldService.class);

    /* (non-Javadoc)
     * @see cn.javass.spring.chapter6.service.IHelloWorldService#sayHello()
     */
    @Override
    public void sayHello(){
        log.info("============Hello World!");
    }
}
