//package com.feilong.ssh.biz;
//
//import java.util.List;
//
//import com.feilong.ssh.db.FeiLongSpringDAO;
//
///**
// * 父类业务类 简化每个业务类代码
// * 
// * @author 金鑫 2009-8-11下午03:44:44
// */
//public class FeiLongBaseBiz{
//
//	/**
//	 * dao类
//	 */
//	protected FeiLongSpringDAO	dao;
//
//	/**
//	 * hql 语句
//	 */
//	protected String			hql;
//
//	/**
//	 * sql 语句,不建议使用,推荐使用hql查询
//	 * 
//	 * @deprecated
//	 */
//	@Deprecated
//	protected String			sql;
//
//	/**
//	 * hql组要组合的用这个 (性能高)
//	 */
//	protected StringBuilder		hqlBuilder;
//
//	/**
//	 * object数组参数
//	 */
//	protected Object[]			params;
//
//	/**
//	 * list集合参数
//	 */
//	protected List<Object>		paramsList;
//
//	/**
//	 * list,用于存放普通的查询返回list
//	 */
//	protected List				list;
//
//	public void setDao(FeiLongSpringDAO dao){
//		this.dao = dao;
//	}
//}