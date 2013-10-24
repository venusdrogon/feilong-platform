//package com.feilong.ssh.biz;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.feilong.commons.core.entity.Pager;
//
///**
// * 基类查询的业务类
// * 
// * @author 金鑫 2010-4-13 下午10:49:29
// * @deprecated 不建议继承此类 ,建议使用 FeiLongPagerUtil类方法
// */
//@Deprecated
//public class FeiLongBaseSearchBiz extends FeiLongBaseBiz{
//
//	/**
//	 * 封装分页请求属性
//	 * 
//	 * @param clazz
//	 *            从哪个pojo里面查询
//	 * @param mapIfs
//	 *            拼装的条件
//	 * @param maxPageItems
//	 *            每页显示多少行
//	 * @param orderBy
//	 *            排序
//	 * @param request
//	 *            当前请求
//	 * @author 金鑫
//	 * @version 1.0 2010-1-9 上午11:52:45
//	 */
//	protected void setRequestAttribute(Class clazz,Map mapIfs,int maxPageItems,String orderBy,HttpServletRequest request){
//		// 从第几页开始查找
//		Integer pageNo = PagerUtil.getCurrentPageNo(request);
//		Pager pageModel = dao.getPageModel(clazz, mapIfs, pageNo, maxPageItems, orderBy);
//		pageModel.setMaxPageItems(maxPageItems);
//		PagerUtil.setPagerAttribute(pageModel, request);
//	}
//	
//	/**
//	 * 封装分页请求属性(控制查询条数)
//	 * 
//	 * @param clazz
//	 *            从哪个pojo里面查询
//	 * @param mapIfs
//	 *            拼装的条件
//	 * @param maxPageItems
//	 *            每页显示多少行
//	 * @param orderBy
//	 *            排序
//	 * @param limit
//	 *            控制显示总条数
//	 * @param request
//	 *            当前请求
//	 * @author 金鑫
//	 * @version 1.0 2010-1-9 上午11:52:45
//	 */
//	protected void setRequestAttribute(Class clazz,Map mapIfs,int maxPageItems,String orderBy,int limit,HttpServletRequest request){
//		// 从第几页开始查找
//		Integer pageNo = PagerUtil.getCurrentPageNo(request);
//		Pager pageModel = dao.getPageModel(clazz, mapIfs, pageNo, maxPageItems, orderBy,limit);
//		pageModel.setMaxPageItems(maxPageItems);
//		PagerUtil.setPagerAttribute(pageModel, request);
//	}
//
//	/**
//	 * 封装分页请求属性
//	 * 
//	 * @param clazz
//	 *            从哪个pojo里面查询
//	 * @param mapIfs
//	 *            拼装的条件
//	 * @param maxPageItems
//	 *            每页显示多少行
//	 * @param groupByField
//	 *            分组字段参数获取
//	 * @param groupBy
//	 *            分组字段
//	 * @param orderBy
//	 *            排序
//	 * @param request
//	 *            当前请求
//	 * @author 金鑫
//	 * @version 1.0 2010-1-9 上午11:52:45
//	 */
//	protected void setRequestAttribute(Class clazz,Map mapIfs,int maxPageItems,String groupByField,String groupBy,String orderBy,HttpServletRequest request){
//		// 从第几页开始查找
//		Integer pageNo = PagerUtil.getCurrentPageNo(request);
//		Pager pageModel = dao.getPageModel(clazz, mapIfs, pageNo, maxPageItems, groupByField, groupBy, orderBy);
//		pageModel.setMaxPageItems(maxPageItems);
//		PagerUtil.setPagerAttribute(pageModel, request);
//	}
//
//	/**
//	 * 封装分页请求属性 (select * from '')
//	 * 
//	 * @param request
//	 *            当前请求
//	 * @param clazz
//	 *            从哪个pojo里面查询
//	 * @param mapIfs
//	 *            拼装的条件
//	 * @param pagesize
//	 *            每页显示多少行
//	 * @param orderBy
//	 *            排序
//	 * @author 金鑫
//	 * @version 1.0 时间:2009-12-7下午02:20:11
//	 */
//	protected void setRequestAttribute(String fromString,Map mapIfs,int pagesize,String orderBy,HttpServletRequest request){
//		// 从第几页开始查找
//		int pageNo = PagerUtil.getCurrentPageNo(request);
//		Pager pageModel = dao.getPageModel(fromString, mapIfs, pageNo, pagesize, orderBy);
//		pageModel.setMaxPageItems(pagesize);
//		PagerUtil.setPagerAttribute(pageModel, request);
//	}
//}