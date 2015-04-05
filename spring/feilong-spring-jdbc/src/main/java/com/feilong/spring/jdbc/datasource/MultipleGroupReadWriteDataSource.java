package com.feilong.spring.jdbc.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import loxia.dao.ReadWriteSupport;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.feilong.commons.core.util.Validator;

/**
 * 多数据源,带分组的概念 {@link #readWriteDataSourceCommandMap}
 * <p>
 * 此map中,key 是 dataSource group 名字(group 可以理解为不同类型/不同的数据库,比如 金宝贝商城DB,金宝贝O2O DB);<br>
 * value,是这个组的读写数据源
 * </p>
 * 
 * <h3>主要做两件事情:</h3>
 * 
 * <blockquote>
 * <p>
 * <ol>
 * <li>{@link #determineCurrentLookupKey()}</li>
 * <li>{@link #afterPropertiesSet()}</li>
 * </ol>
 * </p>
 * </blockquote>
 * 
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年4月1日 下午1:58:15
 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
 * @since 1.1.1
 */
public class MultipleGroupReadWriteDataSource extends AbstractRoutingDataSource{

    /** key 是 dataSource group 名字;. */
    private Map<String, ReadWriteDataSourceCommand> readWriteDataSourceCommandMap;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet(){
        if (Validator.isNullOrEmpty(readWriteDataSourceCommandMap)){
            throw new NullPointerException("the writeDataSource is null or empty!");
        }
        Object defaultTargetDataSource = null;
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        for (Map.Entry<String, ReadWriteDataSourceCommand> entry : readWriteDataSourceCommandMap.entrySet()){
            String groupName = entry.getKey();
            ReadWriteDataSourceCommand readWriteDataSourceCommand = entry.getValue();

            DataSource readDataSource = readWriteDataSourceCommand.getReadDataSource();
            DataSource writeDataSource = readWriteDataSourceCommand.getWriteDataSource();

            // 默认读的是 default 组的 写库
            if (MultipleGroupReadWriteUtil.DEFAULT_GROUP_NAME.equals(groupName)){
                defaultTargetDataSource = writeDataSource;
            }

            targetDataSources.put(MultipleGroupReadWriteUtil.getTargetDataSourcesKey(groupName, ReadWriteSupport.WRITE), writeDataSource);
            targetDataSources.put(MultipleGroupReadWriteUtil.getTargetDataSourcesKey(groupName, ReadWriteSupport.READ), readDataSource);
        }

        this.setTargetDataSources(targetDataSources);
        this.setDefaultTargetDataSource(defaultTargetDataSource);
        // this.setDataSourceLookup(dataSourceLookup);
        // this.setLenientFallback(defaultTargetDataSource);
        // this.setLoginTimeout(timeout);
        // this.setLogWriter(pw);
        super.afterPropertiesSet();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey(){
        String dataSourceName = MultipleGroupReadWriteStatusHolder.getMultipleDataSourceGroupName();
        logger.debug("Current LookupKey:" + dataSourceName);

        // TODO
        // if (dataSourceName == null){
        // return ReadWriteSupport.WRITE;
        // }
        return dataSourceName;
    }

    /**
     * Sets the key 是 dataSource group 名字;.
     * 
     * @param readWriteDataSourceCommandMap
     *            the readWriteDataSourceCommandMap to set
     */
    public void setReadWriteDataSourceCommandMap(Map<String, ReadWriteDataSourceCommand> readWriteDataSourceCommandMap){
        this.readWriteDataSourceCommandMap = readWriteDataSourceCommandMap;
    }
}
