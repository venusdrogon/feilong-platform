package com.baozun.nebula.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * cahce接口,在底层封装了jedis的api
 * 
 * 业务方法的缓存
 * 1.缓存读取步骤：
 * *先通过key以及field查找缓存
 * *如果查不到，则查询数据源(db,solr,file等) ；如果查到，直接返回缓存数据
 * *将数据源的数据保存到缓存中，并返回
 * 
 * 2.无参数方法的缓存：
 * 直接使用key/value来保存缓存
 * 
 * 3.带参数方法的缓存：
 * *使用redis的map结构来保存缓存数据(这边有专门的方法如setMapObject)
 * *使用key定位方法
 * *field为参数的组合，在方法内能唯一指向数据，根据需要自己将参数拼装为field,如简单类型：p1-v1-p2-v2;如列表：v1-v2-v3;对象f1-f2-f3
 * *无参数时，field为默认值default-field;
 * 
 * 4.缓存方法的嵌套：
 * *原则上允许嵌套使用，但不建议嵌套及过多的层级嵌套
 * *最内层的缓存方法表示为方法体内不会有其它读取缓存的方法，方法名称没有专门的标识
 * *外层的缓存方法需要设置过期时间,默认为一小时
 * 
 * 5.缓存的更新：
 * *最内层的缓存方法,定义一个对应的更新缓存的方法,当数据发生改变时，调用此方法更新。并根据需要决定是否调用外层缓存方法对应的删除缓存方法
 * *对于拥有复杂参数的方法，或是无法通过更新某个对象就能够指定方法参数的情况，不建议定义缓存的更新方法，应该直接删除
 * *如：某方法参数是多个对象拼成的，但我只是更新了某个对象的数据，完全不清楚其它对象应该传哪些参数
 * 
 * 
 * 6.缓存的删除:
 * *外层的缓存方法，定义一个对应的删除缓存的方法,当数据发生改变时，可以根据需求来进行调用(实时性要求高，不推荐)
 * *对于无参数的缓存方法，对应的删除缓存方法直接通过key来进行删除
 * *对于带参数的缓存方法，对应的删除缓存方法需要两种情况：通过key来删除整个方法的缓存数据;传递通过key与参数组合来删除对应参数的缓存数据(一般不建议)
 * 
 * 7.哪些数据可以被缓存：
 * *原则上只读的业务方法都可以加上缓存
 * *推荐是访问量大，但修改少的数据
 * *绝对不允许将写的次数多于读的次数的数据加入缓存
 * 
 * 8.方法命名规则
 * *读取缓存的方法必须以fromCache结尾
 * *更新缓存的方法以refreshCache结尾
 * *删除缓存的方法以removeCache结尾
 * *它们是成对的存在，除了后辍，应该一致(有时候不一定会有更新缓存的方法)
 * 
 * 9.key的定义原则
 * *key必须定义到CacheKeyConstant中,不允许写死在方法中
 * *key的规则为MC_[package]_[className]_[methodName]
 * *MC表示方法缓存
 * *package为全包名
 * *classname为接口的类名
 * *methodName为方法名
 * 
 * 
 * 临时存储的应用(待收集较齐全后再来完善)
 * 1.限制用户单位时间内的操作次数，如登录密码错十次，淘宝就暂时不让此人登录了
 * 2.暂时存储一些统计数据，如点击量，高并发时参与活动等
 * 3.最新上架商品
 * 
 * 
 * 不同数据结构的应用场景：
 * 1.key/value：将查询出的单个对象或是列表，或是复杂map保存到对象中，这里的数据都是整存整取
 * 2.队列：需要单个添加，列表查询的数据(零存整取的列表)，如最新上架商品top10,后台每修改一个商品加到队首，读取时则为最新10条等
 * 3.map:一般是单个对象放到map中，有单个字段属性读取的需求(零取零取，或零存整取),带参的方法使用此结构来存储
 * 4.set:和队列类似，但支持去重，却是无序的
 * 5.Sorted set：与set比起来就是多了一个顺序
 * 
 * @author Justin Hu
 *
 */
public interface CacheManager{

    /**
     * 按照默认的方式生成map的field
     * 其中的obj最后都会调用toString()方法
     * 
     * @param obj
     * @return
     */
    public String generateMapFieldByDefault(Object...obj);

    /**
     * 保存一个对象
     * 方法中会序列化对象成字符串
     * 
     * @param key
     * @param t
     */
    public <T> void setObject(String key,T obj);

    /**
     * 保存一个对象
     * 方法中会序列化对象成字符串
     * 
     * @param key
     * @param t
     * @param expireSeconds
     *            过期时间
     */
    public <T> void setObject(String key,T t,Integer expireSeconds);

    /**
     * 获取一个对象
     * 反序列化字符串
     * 
     * @param key
     * @return 对象
     */
    public <T> T getObject(String key);

    /**
     * 保存字符串到缓存中
     * 
     * @param key
     * @param value
     *            字符串值
     * @param expireSeconds
     *            过期时间(秒)
     */
    public void setValue(String key,String value,Integer expireSeconds);

    /**
     * 保存字符串到缓存中(没有过期时间)
     * 
     * @param key
     * @param value
     */
    public void setValue(String key,String value);

    /**
     * 通过key移除数据
     * 
     * @param key
     * @return
     */
    public Long remove(String key);

    /**
     * 通过key获取数据
     * 
     * @param key
     * @return
     */
    public String getValue(String key);

    /**
     * 通过key以及字段名称，保存字符到一个map中
     * 使用时通过key与field来进行获取
     * 
     * @param key
     * @param field
     * @param value
     */
    public void setMapValue(String key,String field,String value,int seconds);

    /**
     * 通过key以及map的field来获取具体的值
     * 
     * @param key
     * @param field
     * @return
     */
    public String getMapValue(String key,String field);

    /**
     * 删除某个key的map对应field的值
     * 
     * @param key
     * @param field
     * @return
     */
    public void removeMapValue(String key,String field);

    /**
     * 通过key以及字段名称，保存对象到一个map中
     * 使用时通过key与field来进行获取
     * 
     * @param key
     * @param field
     * @param value
     * @param seconds
     *            有效期时间
     */
    public <T> void setMapObject(String key,String field,T t,int seconds);

    /**
     * 通过key以及map的field来获取具体的对象值
     * 
     * @param key
     * @param field
     * @return
     */
    public <T> T getMapObject(String key,String field);

    /**
     * 通过key获取整个map
     * 
     * @param key
     * @return
     */
    public Map<String, String> getAllMap(String key);

    /**
     * 将当前字符串数组插入到队列的头部
     * 
     * @param key
     * @param values
     *            字符串数组
     */
    public void pushToListHead(String key,String[] values);

    /**
     * 将当前字符串插入到队列的头部
     * 
     * @param key
     * @param value
     */
    public void pushToListHead(String key,String value);

    /**
     * 将当前字符串数组插入到队列的尾部
     * 
     * @param key
     * @param values
     */
    public void pushToListFooter(String key,String[] values);

    /**
     * 将当前字符串插入到队列的尾部
     * 
     * @param key
     * @param value
     */
    public void pushToListFooter(String key,String value);

    /**
     * 取出并返回队列头部的数据元素
     * 会在队列中删除返回的元素
     * 
     * @param key
     * @return
     */
    public String popListHead(String key);

    /**
     * 取出并返回队到尾部的数据元素
     * 会在队列中删除返回的元素
     * 
     * @param key
     * @return
     */
    public String popListFooter(String key);

    /**
     * 返回某个index的队列元素
     * 
     * @param key
     * @param index
     * @return
     */
    public String findListItem(String key,long index);

    /**
     * 获取队列数据，通过start以及end,如果想获取全部数据，start填写0,end填写maxLong的值
     * 
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> findLists(String key,long start,long end);

    /**
     * 返回队列中元素数量
     * 
     * @param key
     * @return
     */
    public long listLen(String key);

    /**
     * 添加字符串数组到set中
     * 
     * @param key
     * @param values
     */
    public void addSet(String key,String[] values);

    /**
     * 从set中返回一个元素
     * 
     * @param key
     * @return
     */
    public String popSet(String key);

    /**
     * set中是否已存在某个值
     * 
     * @param key
     * @param member
     * @return
     */
    public boolean existsInSet(String key,String member);

    /**
     * 获取整个set
     * 
     * @param key
     * @return
     */
    public Set<String> findSetAll(String key);

    /**
     * 获取set的数量
     * 
     * @param key
     * @return
     */
    public long findSetCount(String key);

    /**
     * 添加value到一个sortset中
     * 
     * @param key
     * @param value
     * @param sortNo
     */
    public void addSortSet(String key,String value,long sortNo);

    /**
     * 获取sortSet中sortNo为start到end的数据
     * 
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> findSortSets(String key,long start,long end);

    /**
     * 获取sortSet的数量
     * 
     * @param key
     * @return
     */
    public long findSortSetCount(String key);

    /**
     * 获取sortSet数量，通过sortNo的min到max进行筛选定位
     * 
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long findSortSetCount(String key,long min,long max);
}
