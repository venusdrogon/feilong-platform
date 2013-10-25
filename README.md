feilong-platform
================

feilong-platform


#Welcome to feilong-platform.

1. 基于Apache2 协议,您可以下载,代码用于闭源项目,但每个修改的过的文件必须放置版权说明,
1. 基于maven2.2.1构建,
1. 需要jdk1.5及以上环境

***
# 项目依赖

```XML
<dependencies>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
      <scope>compile</scope>
    </dependency>
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
      <version>1.6.4</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>
```

***

# 类和方法介绍: 

## com.feilong.commons.core.date 包下有以下几个类:

* ★ DateUtil 时间工具类
* ★ CalendarUtil 日历工具类
* DatePattern 时间格式,用于formart
* ConstellationType 星座枚举类
* ConstellationUtil 星座工具类
* LunarDateUtil 农历日期工具类
* SolarDateUtil 阳历日期工具类

***

# About
* 我的新浪微博:http://weibo.com/venusdrogon 
* 我的ITeye博客:http://feitianbenyue.iteye.com/