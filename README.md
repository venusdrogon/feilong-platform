feilong-platform
================

Reduce development, Release ideas


#Welcome to feilong-platform.
------------------------------------------------------------

1. 基于Apache2 协议,您可以下载,代码用于闭源项目,但每个修改的过的文件必须放置版权说明;
1. 基于maven2.2.1构建;
1. 需要jdk1.5及以上环境;


# 类和方法介绍: 
------------------------------------------------------------
## com.feilong.commons.core.date 包,时间日期操作核心类:

* DateUtil时间工具类  

***

# Maven使用配置
------------------------------------------------------------
```XML
	<project>
		....
		<repositories>
			<repository>
				<id>feilong-repository</id>
				<url>https://raw.github.com/venusdrogon/feilong-platform/master</url>
			</repository>
		</repositories>
		
		<dependencies>
			<dependency>
				<groupId>com.feilong.platform.commons</groupId>
				<artifactId>feilong-core</artifactId>
				<version>1.0.7</version>
			</dependency>
		</dependencies>
	</project>
```

# 项目依赖
------------------------------------------------------------
```XML
<dependencies>
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.1</version>
      <scope>compile</scope>
    </dependency>
    
    <dependency>
      <groupId>commons-beanutils</groupId>
      <artifactId>commons-beanutils</artifactId>
      <version>1.8.3</version>
      <scope>compile</scope>
    </dependency>
    
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.10</version>
      <scope>test</scope>
    </dependency>
    
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.6</version>
      <scope>compile</scope>
    </dependency>
    
  </dependencies>
```
***

# About
------------------------------------------------------------
* 新浪微博:http://weibo.com/venusdrogon 
* iteye博客:http://feitianbenyue.iteye.com/

