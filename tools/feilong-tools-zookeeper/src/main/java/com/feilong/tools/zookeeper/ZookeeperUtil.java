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

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZookeeperUtil ZooKeeper服务上传工具类.
 * 
 * @author Wenqiang Sun
 * @date 2013年11月6日 上午10:21:28
 */
public class ZookeeperUtil{

	private static final Logger		log						= LoggerFactory.getLogger(ZookeeperUtil.class);

	/** ZooKeeper 服务连接超时时间. */
	private static final Integer	ZK_CONNECTION_TIMEROUT	= 30000;

	/**
	 * 指定某个目录下的全部properties配置 文件上传到ZooKeeper<br>
	 * 1.遍历本地目录下的全部properties文件<br>
	 * 2.逐级检查zk节点是否存在，如果不存在，则逐级建立<br>
	 * 3.如果叶节点存在，则删除后重新建立<br>
	 * 
	 * @param zooHost
	 *            zookeeper 地址
	 * @param localPath
	 *            本地存放Properties配置文件的路径
	 * @param zooPath
	 *            上传到ZooKeeper 的目录地址
	 */
	public static void uploadAllProperties(String zooHost,String localPath,String zooPath){
		try{
			ZooKeeper zooKeeper = new ZooKeeper(zooHost, ZK_CONNECTION_TIMEROUT, null);

			Properties properties = new Properties();

			// ZookeeperManager
			log.info("From:" + new File(localPath).getCanonicalPath());
			log.info("To:" + zooPath);

			// 读取localPath目录下的全部properties文件
			File file = new File(localPath);
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++){
				if (!files[i].isDirectory() && files[i].getPath().endsWith(".properties")
								&& !files[i].getPath().endsWith("init.properties")){
					log.info("File:" + files[i].getCanonicalPath());
					properties.load(new FileInputStream(files[i]));
				}
			}

			// 拆分节点路径，逐级建立
			String curPath = "";
			String[] paths = zooPath.split("/");
			for (int i = 1; i < paths.length; i++){
				if (paths[i] == ""){
					continue;
				}else{
					curPath = curPath + "/" + paths[i];
				}

				// 逐级建立zk节点
				if (zooKeeper.exists(curPath, false) == null){
					zooKeeper.create(curPath, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}else if (i == paths.length - 1){ // 如果zooPath存在，则删除节点再重新建立
					deleteNode(zooKeeper, curPath);
					zooKeeper.create(curPath, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}
			}

			// 将properties文件内容写入zk节点
			for (Object objKey : properties.keySet()){
				String key = objKey.toString();
				log.info(key + "\t" + properties.getProperty(key));
				zooKeeper.create(zooPath + "/" + key, properties.get(key).toString().getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}

		}catch (Exception e){
			log.error(e.getClass().getName(), e);
		}
	}

	/**
	 * 列表所有节点信息，控制台输出.
	 * 
	 * @param zooHost
	 *            the zoo host
	 * @param path
	 *            节点
	 */
	public static void listNodes(String zooHost,String path){
		try{
			ZooKeeper zk = new ZooKeeper(zooHost, ZK_CONNECTION_TIMEROUT, null);
			List<String> children = zk.getChildren(path, true);
			for (String child : children){
				log.info(child + "\t" + new String(zk.getData(path + "/" + child, null, null)));
			}

		}catch (Exception e){
			log.error(e.getClass().getName(), e);
		}
	}

	/**
	 * 删除指定的节点，并且递归删除其子节点.
	 * 
	 * @param zooHost
	 *            the zoo host
	 * @param path
	 *            节点
	 */
	public static void deleteNode(String zooHost,String path){
		try{
			ZooKeeper zk = new ZooKeeper(zooHost, ZK_CONNECTION_TIMEROUT, null);
			deleteNode(zk, path);
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
		}
	}

	/**
	 * 递归删除其子节点.
	 * 
	 * @param zk
	 *            zk主机
	 * @param path
	 *            节点
	 */
	private static void deleteNode(ZooKeeper zk,String path){
		try{
			List<String> children = zk.getChildren(path, true);
			if (children.size() == 0){
				zk.delete(path, -1);
			}else{
				for (String child : children){
					deleteNode(zk, path + "/" + child);
				}

				zk.delete(path, 0);
			}
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
		}
	}
}
