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
package com.feilong.tools.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContextException;

/**
 * The Class ZookeeperConfigurer.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-5-13 16:40:51
 */
public class ZookeeperConfigurer extends PropertyPlaceholderConfigurer implements Watcher{

    /** The log. */
    private Logger log = LoggerFactory.getLogger(ZookeeperConfigurer.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config
     * .ConfigurableListableBeanFactory, java.util.Properties)
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory configurableListableBeanFactory,Properties properties)
                    throws BeansException{
        String zkhost = properties.getProperty("zkhost");
        String znodes = properties.getProperty("znodes");
        try{
            ZooKeeper zooKeeper = new ZooKeeper(zkhost, 30000, this);
            try{
                for (String znode : znodes.split(",")){
                    boolean watch = true;
                    String path = znode.trim();

                    List<String> children = zooKeeper.getChildren(path, watch);
                    for (String child : children){

                        Watcher watcher = null;
                        Stat stat = null;

                        byte[] data = zooKeeper.getData(znode + "/" + child, watcher, stat);
                        String value = new String(data);
                        properties.setProperty(child, value);
                    }
                }
            }catch (KeeperException e){
                log.error("Failed to get property from zk server" + zkhost, e);
                throw new ApplicationContextException("Failed to get property from zk server" + zkhost, e);
            }catch (InterruptedException e){
                log.error("Failed to get property from zk server" + zkhost, e);
                throw new ApplicationContextException("Failed to get property from zk server" + zkhost, e);
            }finally{
                try{
                    zooKeeper.close();
                }catch (InterruptedException e){
                    log.error("Error found when close zookeeper connection.", e);
                }
            }
        }catch (IOException e){
            log.error("Failed to connect to zk server" + zkhost, e);
            throw new ApplicationContextException("Failed to connect to zk server" + zkhost, e);
        }
        super.processProperties(configurableListableBeanFactory, properties);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
     */
    @Override
    public void process(WatchedEvent event){
    }

}
