#重要说明

Package | Description |Since
:---- | :---------| :---------:
feilong-core  | https://github.com/venusdrogon/feilong-core |1.2.0
feilong-servlet  | https://github.com/venusdrogon/feilong-servlet |1.2.0
feilong-spring  | https://github.com/venusdrogon/feilong-spring |1.2.0
feilong-taglib  | https://github.com/venusdrogon/feilong-taglib |1.2.0
feilong-webservice  | https://github.com/venusdrogon/feilong-webservice |1.2.0
feilong-framework  | https://github.com/venusdrogon/feilong-framework |1.2.0
feilong-netpay  | https://github.com/venusdrogon/feilong-netpay |1.5.4


feilong-platform
================

Reduce development, Release ideas

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

            .--.
           /    \
          ## a  a
          (   '._)
           |'-- |
         _.\___/_   ___feilong___
       ."\> \Y/|<'.  '._.-'
      /  \ \_\/ /  '-' /
      | --'\_/|/ |   _/
      |___.-' |  |`'`
        |     |  |
        |    / './
       /__./` | |
          \   | |
           \  | |
           ;  | |
           /  | |
     jgs  |___\_.\_
          `-"--'---'


#Welcome to feilong-platform

feilong platform,封装了常用的java工具方法,提供快速使用工具的功能,自己知识积累的同时，方便大家使用


# Maven使用配置

所有feilong jar 你可以在这里 https://github.com/venusdrogon/feilong-platform/tree/repository/com/feilong/platform 浏览 

在maven 中,您可以通过以下方式来配置:

```XML
	<project>
		....
		<repositories>
			<repository>
				<id>feilong-repository</id>
				<url>https://raw.github.com/venusdrogon/feilong-platform/repository</url>
			</repository>
		</repositories>
		
		....
		<dependencies>
			....
			<dependency>
				<groupId>com.feilong.platform</groupId>
				<artifactId>feilong-core</artifactId>
				<version>1.5.3</version>
			</dependency>
			....
		</dependencies>
		....
	</project>
```


#说明

1. 基于`Apache2` 协议,您可以下载,代码用于闭源项目,但每个修改的过的文件必须放置版权说明;
1. 基于`maven3.2`构建;
1. 1.5.0及以上版本需要`jdk1.7`及以上环境(1.5.0以下版本需要`jdk1.6`及以上环境);


# About

如果您对feilong platform 有任何建议，可以使用下面的联系方式：

* 新浪微博:http://weibo.com/venusdrogon
* iteye博客:http://feitianbenyue.iteye.com/