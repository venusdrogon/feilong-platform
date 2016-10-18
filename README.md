feilong-platform
================

Reduce development, Release ideas

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
![build](https://img.shields.io/jenkins/s/https/jenkins.qa.ubuntu.com/precise-desktop-amd64_default.svg "build") 
![JDK 1.7](https://img.shields.io/badge/JDK-1.7-green.svg "JDK 1.7")


#Welcome to feilong-platform

这是所有feilong 项目的 parent pom项目,定义常用的 jar以及 plugins


#子项目地址

child 			| Description 										|Since
:---- 				| :---------										| :---------:
[feilong-core](https://github.com/venusdrogon/feilong-core)  		| 核心jar,所有feilong-platform的基础		|1.2.0
[feilong-servlet](https://github.com/venusdrogon/feilong-servlet)	| 封装了j2ee常用类,是feilong-platform web相关jar 的基础	|1.2.0
[feilong-io](https://github.com/venusdrogon/feilong-io)			| 	封装了io操作的常用类		|1.5.4
[feilong-taglib](https://github.com/venusdrogon/feilong-taglib)		| 封装了常用的自定义标签		|1.2.0
[feilong-spring](https://github.com/venusdrogon/feilong-spring)		| 对spring相关类的快速封装,以便快速使用		|1.2.0

# 一图概述:

![one-feilong-platform](http://venusdrogon.github.io/feilong-platform/mysource/one-feilong-platform.png) 


# :dragon: Maven使用配置

所有feilong jar 你可以直接在 [仓库](https://github.com/venusdrogon/feilong-platform/tree/repository/com/feilong/platform "仓库") 浏览 

如果你使用 `maven`, 您可以通过以下方式来配置 `pom.xml`:

```XML

	<project>
	
		....
		<properties>
			<version.feilong-platform>1.9.1</version.feilong-platform>
			....
		</properties>
		
		....
		<repositories>
			<repository>
				<id>feilong-repository</id>
				<url>https://raw.github.com/venusdrogon/feilong-platform/repository</url>
			</repository>
		</repositories>
		
		....
	</project>
```


# :memo: 说明

1. 基于 [Apache2](https://www.apache.org/licenses/LICENSE-2.0) 协议,您可以下载代码用于闭源项目,但每个修改的过的文件必须放置版权说明;
1. 1.5.0及以上版本需要`jdk1.7`及以上环境(1.5.0以下版本需要`jdk1.6`及以上环境);

# :panda_face: About

如果您对本项目有任何建议和批评,可以使用下面的联系方式：

* iteye博客:http://feitianbenyue.iteye.com/