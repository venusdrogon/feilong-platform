package com.feilong.hibernate.search.model;

/**
 * 项目中所有Entity的基本接口，定义有每个Entity的默认状态列表，每个Entity在定义时初始值都是使用 STATUS_ENABLE
 * @author benjamin
 *
 */
public interface BaseModel {
	
	/**
	 * 禁用/删除/不活动/无效 状态
	 */
	public static final Integer STATUS_DISABLE = 0;
	
	/**
	 * 活动/有效 状态
	 */
    public static final Integer STATUS_ENABLE = 1;
    
    /**
     * 默认状态为有效状态
     */
    public static final Integer DEFAULT_STATUS = STATUS_ENABLE;
    
    /**
     * 返回Entity的类定义
     * @return
     */
    Class<?> getModelClass(); 
}
