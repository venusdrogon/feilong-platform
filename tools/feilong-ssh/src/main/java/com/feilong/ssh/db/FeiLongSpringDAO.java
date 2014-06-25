package com.feilong.ssh.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.feilong.commons.core.lang.ObjectUtil;
import com.feilong.commons.core.util.ListUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 通用springDAO
 * 
 * @param <T>
 *            可以使用泛型
 * @author 金鑫 2009-8-5下午04:12:41
 */
public class FeiLongSpringDAO<T> extends HibernateDaoSupport{

	private final static Logger	log	= LoggerFactory.getLogger(FeiLongSpringDAO.class);

	// [start] 查询
	/** ****************************** 查询 *************************************** */
	// load get
	/**
	 * 查询单个信息
	 * 
	 * @param clz
	 *            存储这条信息的类
	 * @param id
	 *            查询信息的主键
	 * @return 存储这条信息的对象
	 */
	@SuppressWarnings("cast")
	public T load(Class<T> clz,Serializable id){
		try{
			// 如果找不到符合条件的纪录，而load()将会报出ObjectNotFoundEcception
			return (T) super.getHibernateTemplate().load(clz, id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询单个信息
	 * 
	 * @param clz
	 *            存储这条信息的类
	 * @param id
	 *            查询信息的主键
	 * @return 存储这条信息的对象
	 * @author 金鑫
	 * @version 1.0 2009-12-25 下午11:31:17
	 */
	public T load(Class<T> clz,Object id){
		return load(clz, ObjectUtil.toBigDecimal(id));
	}

	/**
	 * 根据类名查询所有
	 * 
	 * @param cl
	 *            类名
	 * @return 对象集合
	 */
	public List<T> loadAll(final Class<T> cl){
		return super.getHibernateTemplate().loadAll(cl);
	}

	/**
	 * 查询单个信息 金鑫
	 * 
	 * @param clz
	 *            存储这条信息的类
	 * @param id
	 *            查询信息的主键
	 * @return 存储这条信息的对象
	 */
	@SuppressWarnings("cast")
	public T get(Class<T> clz,Serializable id){
		// 如果找不到符合条件的纪录，get()方法将返回null．
		// 根据主键加载特定持久化类的实例
		return (T) super.getHibernateTemplate().get(clz, id);
	}

	/**
	 * 通过hql语句查询
	 * 
	 * @param hql
	 *            hql语句,不带参数查询
	 * @return 查询到的集合
	 * @author 金鑫
	 * @version 1.0 2009-4-10下午05:12:20
	 */
	public List find(String hql){
		// 根据HQL查询字符串来返回实例集合
		return super.getHibernateTemplate().find(hql);
	}

	/**
	 * 参数绑定查询
	 * 
	 * @param hql
	 *            hql语句
	 * @param paramNames
	 *            参数数组
	 * @param values
	 *            值数组
	 * @return 查询到的集合
	 * @author 金鑫
	 * @version 1.0 2010-1-11 上午11:03:27
	 */
	public List findByNamedParam(String hql,String[] paramNames,Object[] values){
		// String hql = "from TradeRecord as tr where tr.TradeTime>= :startTime and tr.TradeTime <= :endTime and tr.CustomerId =:cid";
		// String[] params = { "startTime", "endTime", "cid" };
		// Object[] args = { startTime, endTime, new Long(cid) };
		// List list = this.getHibernateTemplate().findByNamedParam(hql, params, args);
		return super.getHibernateTemplate().findByNamedParam(hql, paramNames, values);
	}

	/**
	 * 执行查询的方法 适用于参数较多的hql查询,建议使用public List search(final String hql,final Object...params)
	 * 
	 * @param hql
	 *            编写的hql语句
	 * @param paramsList
	 *            存储值的集合(参数必须要用 ? 占位符)
	 * @return 查到数据的集合,该方法建议用动态参数代替
	 * @author 金鑫
	 * @version 1.0 2010-3-10 下午03:57:16
	 */
	@Deprecated
	public List search(final String hql,final List paramsList){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session){
				return getQuery(session, hql, paramsList).list();
			}
		});
	}

	/**
	 * 执行查询的方法 可变参数
	 * 
	 * @param hql
	 *            编写的hql语句
	 * @param params
	 *            可变参数
	 * @return 查询之后的集合
	 * @author 金鑫
	 * @version 1.0 2009-12-24 上午10:47:17
	 */
	public List search(final String hql,final Object...params){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session){
				return getQuery(session, hql, params).list();
			}
		});
	}

	/**
	 * 查询出集合中的第一个元素item对象
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            可变参数
	 * @return 第一个元素item对象
	 * @author 金鑫
	 * @version 1.0 2009-12-25 下午10:53:06
	 */
	public Object searchListFirstItem(final String hql,final Object...params){
		List list = search(hql, params);
		return ListUtil.getFirstItem(list);
	}

	/**
	 * 是否存在
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            动态参数
	 * @return 存在返回true,不存在返回false
	 * @author 金鑫
	 * @version 1.0 2010-3-10 下午03:52:21
	 */
	public boolean isExist(String hql,Object...params){
		List list = search(hql, params);
		return Validator.isNotNullOrEmpty(list);
	}

	/**
	 * 简单的是否存在,自动拼装hql语句
	 * 
	 * @param pojoName
	 *            pojo名称 如User
	 * @param filedName
	 *            字段名称 如userName
	 * @param paramValue
	 *            值 注意类型
	 * @return 存在返回true,不存在返回false
	 * @author 金鑫
	 * @version 1.0 2010-7-27 下午01:49:53
	 */
	public boolean isExist(String pojoName,String filedName,Object paramValue){
		String hql = "select " + filedName + " from " + pojoName + " where " + filedName + "=?";
		return isExist(hql, paramValue);
	}

	/**
	 * 查询第一行数据
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            可变参数
	 * @return 第一行数据
	 * @author 金鑫
	 * @version 1.0 2009-12-28 下午01:32:59
	 */
	public int searchUniqueResult(String hql,Object...params){
		Object uniqueResult = searchUniqueResult_Object(hql, params);
		if (Validator.isNotNullOrEmpty(uniqueResult)){
			// 是否返回的是BigDecimal类型的
			if (uniqueResult instanceof Number){
				return ((Number) uniqueResult).intValue();
			}
		}
		return 0;
	}

	/**
	 * 查询第一行数据
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            可变参数
	 * @return 第一行数据 object类型的
	 * @author 金鑫
	 * @version 1.0 2009-12-28 下午01:38:16
	 */
	public Object searchUniqueResult_Object(final String hql,final Object...params){
		return super.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session){
				// 如果有多个值抛NonUniqueResultException;
				// 如果有值且只有一个,返回一个object;
				// 如果没值,返回null
				return getQuery(session, hql, params).uniqueResult();
			}
		});
	}

	/**
	 * 将命名参数与一个对象的属性值绑定在一起查询
	 * 
	 * @param hql
	 *            编写的hql语句,含有命名参数
	 * @param object
	 *            hql参数对应的entity
	 * @return 查到数据的集合
	 * @author 金鑫
	 * @version 1.0 2010-1-11 下午01:25:39
	 */
	public List searchByProperties(final String hql,final Object object){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session){
				// Customer customer=new Customer();
				// customer.setName(“pansl”);
				// customer.setAge(80);
				// Query query=session.createQuery(“from Customer c where c.name=:name and c.age=:age ”);
				// query.setProperties(customer);
				// setProperties()方法会自动将customer对象实例的属性值匹配到命名参数上，但是要求命名参数名称必须要与实体对象相应的属性同名。
				Query query = session.createQuery(hql);
				if (null != object){
					query.setProperties(object);
				}
				return query.list();
			}
		});
	}

	/**
	 * 它会把命名参数与一个持久化对象相关联 查询
	 * 
	 * @param hql
	 *            编写的hql语句,含有命名参数
	 * @param map
	 *            命名参数和持久对象 组合的map
	 * @return 查到的集合
	 * @author 金鑫
	 * @version 1.0 2010-1-11 下午01:35:57
	 */
	public List searchByEntity(final String hql,final Map<String, Object> map){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session){
				// 这里还有一个特殊的setEntity()方法，它会把命名参数与一个持久化对象相关联，如下面代码所示：
				// Customer customer=(Customer)session.load(Customer.class,”1”);
				// Query query=session.createQuery(“from Order order where order.customer=:customer ”);
				// query. setEntity(“customer”,customer);
				// List list=query.list();
				// 上面的代码会生成类似如下的SQL语句：
				// Select * from order where customer_ID=’1’;
				Query query = session.createQuery(hql);
				if (Validator.isNotNullOrEmpty(map)){
					for (Map.Entry<String, Object> entry : map.entrySet()){
						query.setEntity(entry.getKey(), entry.getValue());
					}
				}
				return query.list();
			}
		});
	}

	/**
	 * 利用准则实现模糊Anywhere查询
	 * 
	 * @param clz
	 *            对象名
	 * @param object
	 *            对象模版(包含所有的查询条件等)
	 * @return 集合
	 */
	public List selectLikeInAnywhere(final Class clz,final Object object){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session){
				return getCriteriaList(session, clz, object, MatchMode.ANYWHERE);
			}
		});
	}

	/**
	 * 利用准则实现模糊Start查询
	 * 
	 * @param clz
	 *            对象名
	 * @param object
	 *            对象模版(包含所有的查询条件等)
	 * @return 集合
	 */
	public List selectLikeInStart(final Class clz,final Object object){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session){
				return getCriteriaList(session, clz, object, MatchMode.START);
			}
		});
	}

	// [end]
	/**
	 * 利用准则,获得查询集合
	 * 
	 * @param session
	 *            session
	 * @param clz
	 *            对象名
	 * @param object
	 *            对象模版(包含所有的查询条件等)
	 * @param matchMode
	 *            匹配模式
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-1-11 下午01:17:27
	 */
	List getCriteriaList(Session session,Class clz,Object object,MatchMode matchMode){
		Criteria criteria = session.createCriteria(clz);
		if (null != object){
			// Criteria criteria = session.createCriteria(TRegister.class);//生成一个Criteria实例
			// criteria.add(Restrictions.eq("userName","fengyan"));//等价于where name = 'fengyan'
			// List list = criteria.list();
			// Restrictions.eq()：equal,=
			// Restrictions.allEq()： 参数为Map对象，使用key/value进行多个等于的对比，相当于多个 Restrictions.eq()的效果
			// Restrictions.gt()：greater-than，<
			// Restrictions.lt()：less-than,<
			// Restrictions.le：less-equal，<=
			// Restrictions.between()：对应SQL的between子句。
			// Restrictions.like()：对应SQL的like子句。
			// Restrictions.in()：对应SQL的in子句。
			// Restrictions.and()：and 关系。
			// Restrictions.or()：or 关系。
			// Restrictions.isNull()：判断属性是否为空，为空返回true,否则返回false。
			// Restrictions.isNoyNull()：与上面的相反。
			// Order.asc()：根据传入的字段进行升序排序。
			// Order.desc()：与上相反
			// MatchMode.EXACT：字符串中精确匹配，相当于like 'value'
			// MatchMode.ANYWHERE：字符串在中间位置，相当于like'%value%'
			// MatchMode.START：字符串在最前面，相当于like'value%'
			// MatchMode.END：字符串在最后，相当于like'%value'
			criteria.add(Example.create(object).enableLike(matchMode));
		}
		return criteria.list();
	}

	// [start] 增删改
	/** *********************** 增删改 ************************************** */
	/**
	 * 增加 保存新的实例
	 * 
	 * @param object
	 *            增加的对象 保存新的实例
	 * @return 是否增加成功 成功true
	 * @author 金鑫
	 * @version 1.0 2010-7-30 下午12:14:44
	 */
	public boolean add(T object){
		try{
			super.getHibernateTemplate().save(object);
		}catch (Exception ex){
			log.debug(object.toString());
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 更新对象(修改) 更新实例的状态，要求entity是持久状态
	 * 
	 * @param object
	 *            需要被修改的对象 更新实例的状态，要求entity是持久状态
	 * @return 是否更新成功
	 * @author 金鑫
	 * @version 1.0 2010-7-30 下午12:14:07
	 */
	public boolean update(T object){
		try{
			super.getHibernateTemplate().update(object);
		}catch (Exception ex){
			log.debug(object.toString());
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据对象的状态决定是insert还是update
	 * 
	 * <pre>
	 * 
	 * 
	 * 其根本是通过xml文件中unsaved-value来确定的。
	 * 
	 * 如果设置null，系统会根据传入的对象的id的值判断，
	 * 如果是null， 则表示对象不存在，那么insert;
	 * 如果不是Null，则表示已经存在，那么update.
	 * 如果设置为none，那么表示对象不存在，会始终调用insert;
	 * 如果设置为any，那么表示对象始终存在，会始终调用update
	 * </pre>
	 * 
	 * @param object
	 *            object
	 * @return 成功true
	 * @author 金鑫
	 * @version 1.0 2010-7-30 下午12:15:24
	 */
	public boolean saveOrUpdate(Object object){
		try{
			this.getHibernateTemplate().saveOrUpdate(object);
		}catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除
	 * 
	 * @param object
	 *            删除的实体
	 * @return 成功true 否则flase
	 * @author 金鑫
	 * @version 1.0 2010-4-12 下午09:22:10
	 */
	public boolean delete(Object object){
		try{
			super.getHibernateTemplate().delete(object);
		}catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 批删除方法 删除集合内全部持久化类实例
	 * 
	 * @param entities
	 *            删除的实体集合
	 * @return 成功true
	 * @author 金鑫
	 * @version 1.0 2010-1-11 下午01:02:57
	 */
	public boolean deleteAll(Collection entities){
		try{
			super.getHibernateTemplate().deleteAll(entities);
		}catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据主键删除对象
	 * 
	 * @param cl
	 *            类名
	 * @param id
	 *            主键
	 * @return 成功true 否则flase
	 * @author 金鑫
	 * @version 1.0 2010-4-12 下午09:21:37
	 */
	public boolean deleteById(Class cl,Object id){
		return deleteById(cl, ObjectUtil.toBigDecimal(id));
	}

	/**
	 * 根据主键删除对象
	 * 
	 * @param cl
	 *            类名
	 * @param id
	 *            主键
	 * @return 成功true 否则flase
	 * @author 徐新望
	 * @version 1.0
	 */
	public boolean deleteById(Class cl,Serializable id){
		try{
			getHibernateTemplate().delete(getHibernateTemplate().get(cl, id));
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * hql语句添加删除
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            可变参数
	 * @version 1.0 2009-5-5下午02:51:41
	 * @version 2.0 2010-1-11 下午12:06:57
	 */
	public boolean executeUpdateHQL(final String hql,final Object...params){
		int rI = executeUpdate(hql, params);
		return rI > 0 ? true : false;
	}

	/**
	 * 执行hql语句，进行增删该等操作
	 * 
	 * @param hql
	 *            hql
	 * @param params
	 *            可变参数
	 * @return 返回操作数量
	 * @author 金鑫
	 * @version 1.0 2010-1-11 下午12:04:39
	 */
	public Integer executeUpdate(final String hql,final Object...params){
		return (Integer) super.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException,SQLException{
				Query query = getQuery(session, hql, params);
				return query.executeUpdate();
			}
		});
	}

	// [end]
	// [start] 分页
	//** *********************** 分页 ************************************** */
	//	/**
	//	 * 分页 将生成的参数封装到PageModel类中
	//	 * 
	//	 * @param fromString
	//	 *            从哪里查找
	//	 * @param selectString
	//	 *            查询什么
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param showPage
	//	 *            从第几页开始
	//	 * @param showRows
	//	 *            显示多少行
	//	 * @param orderBy
	//	 *            排序
	//	 * @return PageModel
	//	 * @author 金鑫
	//	 * @version 1.0 时间:2009-11-27下午05:16:27
	//	 */
	//	public Pager getPageModel(String fromString,String selectString,Map mapIfs,int showPage,int showRows,String orderBy){
	//		Integer total = getCountPage(fromString, mapIfs);
	//		List datas = getPageObjects(fromString, selectString, mapIfs, showPage, showRows, orderBy);
	//		return FeiLongDbUtil.setPageModel(total, datas);
	//	}

	//	/**
	//	 * 分页 将生成的参数封装到PageModel类中
	//	 * 
	//	 * @param fromString
	//	 *            从哪里查找
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param showPage
	//	 *            从第几页开始
	//	 * @param showRows
	//	 *            显示多少行
	//	 * @param orderBy
	//	 *            排序
	//	 * @return PageModel
	//	 * @author 金鑫
	//	 * @version 1.0 时间:2009-11-27下午05:16:27
	//	 */
	//	public Pager getPageModel(String fromString,Map mapIfs,int showPage,int showRows,String orderBy){
	//		Integer total = getCountPage(fromString, mapIfs);
	//		List datas = getPageObjects(fromString, mapIfs, showPage, showRows, orderBy);
	//		return FeiLongDbUtil.setPageModel(total, datas);
	//	}

	//	/**
	//	 * 分页 将生成的参数封装到PageModel类中
	//	 * 
	//	 * @param clazz
	//	 *            查询的类名
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param showPage
	//	 *            从第几条记录开始查询
	//	 * @param showRows
	//	 *            每页显示多少条记录
	//	 * @param orderBy
	//	 *            排序
	//	 * @return PageModel
	//	 * @author 王伟
	//	 * @version 1.0
	//	 */
	//	public Pager getPageModel(Class clazz,Map mapIfs,int showPage,int showRows,String orderBy){
	//		// 带条件查询总条数
	//		Integer totalCount = getCountPage(clazz, mapIfs);
	//		// 多条件查询(并列条件)+分页查询+排序(Order)
	//		List datas = getObjectByIfsPage(clazz, mapIfs, showPage, showRows, orderBy);
	//		return FeiLongDbUtil.setPageModel(totalCount, datas);
	//	}

	//	/**
	//	 * 分页 将生成的参数封装到PageModel类中（控制查询条数）
	//	 * 
	//	 * @param clazz
	//	 *            查询的类名
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param showPage
	//	 *            从第几条记录开始查询
	//	 * @param showRows
	//	 *            每页显示多少条记录
	//	 * @param orderBy
	//	 *            排序
	//	 * @param limit
	//	 *            控制显示总条数
	//	 * @return PageModel
	//	 * @author 王伟
	//	 * @version 1.0
	//	 */
	//	public Pager getPageModel(Class clazz,Map mapIfs,int showPage,int showRows,String orderBy,int limit){
	//		// 带条件查询总条数
	//		Integer totalCount = getCountPage(clazz, mapIfs);
	//		if (totalCount > limit)
	//			totalCount = limit;
	//		// 多条件查询(并列条件)+分页查询+排序(Order)
	//		List datas = getObjectByIfsPage(clazz, mapIfs, showPage, showRows, orderBy);
	//		return FeiLongDbUtil.setPageModel(totalCount, datas);
	//	}

	//	/**
	//	 * 分页 将生成的参数封装到PageModel类中
	//	 * 
	//	 * @param clazz
	//	 *            查询的类名
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param showPage
	//	 *            从第几条记录开始查询
	//	 * @param showRows
	//	 *            每页显示多少条记录
	//	 * @param groupByField
	//	 *            分组字段参数获取
	//	 * @param groupBy
	//	 *            分组字段
	//	 * @param orderBy
	//	 *            排序
	//	 * @return PageModel
	//	 * @author xxw
	//	 * @version 1.0
	//	 */
	//	public Pager getPageModel(Class clazz,Map mapIfs,int showPage,int showRows,String groupByField,String groupBy,String orderBy){
	//		// 带条件查询总页数
	//		Integer totalCount = getCountPage(clazz, mapIfs, groupBy);
	//		// 多条件查询(并列条件)+分页查询+排序(Order)+分组字段
	//		List datas = getObjectByIfsPage(clazz, mapIfs, showPage, showRows, groupByField, groupBy, orderBy);
	//		return FeiLongDbUtil.setPageModel(totalCount, datas);
	//	}

	//	/**
	//	 * 带条件查询总页数
	//	 * 
	//	 * @param clazz
	//	 * @param mapIfs
	//	 * @return Integer
	//	 * @author 徐新望
	//	 */
	//	public Integer getCountPage(final Class clazz,final Map<String, Serializable> mapIfs){
	//		return (Integer) super.getHibernateTemplate().execute(new HibernateCallback(){
	//
	//			public Object doInHibernate(Session session) throws HibernateException,SQLException{
	//				StringBuilder stringBuilder = new StringBuilder();
	//				String simpleName = clazz.getSimpleName();
	//				stringBuilder.append("select count(*) from ");
	//				stringBuilder.append(simpleName);
	//				// 添加条件
	//				FeiLongDbUtil.joinWhere(stringBuilder, mapIfs);
	//				try{
	//					Query query = session.createQuery(stringBuilder.toString());
	//					Integer countRow = Integer.parseInt(query.uniqueResult().toString());
	//					return countRow;
	//				}catch (Exception e){
	//					log.debug(stringBuilder.toString());
	//				}
	//				return 0;
	//			}
	//		});
	//	}

	//	/**
	//	 * 带条件查询总页数(分组group by)
	//	 * 
	//	 * @param clazz
	//	 * @param mapIfs
	//	 * @param groupBy
	//	 *            分组字段
	//	 * @return Integer
	//	 * @author 徐新望
	//	 */
	//	public Integer getCountPage(final Class clazz,final Map<String, Serializable> mapIfs,final String groupBy){
	//		return (Integer) super.getHibernateTemplate().execute(new HibernateCallback(){
	//
	//			public Object doInHibernate(Session session) throws HibernateException,SQLException{
	//				StringBuilder sb = new StringBuilder();
	//				sb.append("select count(o) from ");
	//				sb.append(clazz.getSimpleName());
	//				sb.append(" o");
	//				// 添加条件
	//				FeiLongDbUtil.joinWhere(sb, mapIfs);
	//				sb.append(" group by ").append(groupBy);
	//				Query query = session.createQuery(sb.toString());
	//				Integer countRow = query.list().size();
	//				return countRow;
	//			}
	//		});
	//	}

	//	/**
	//	 * 多条件查询(并列条件)+分页查询+排序(Order)
	//	 * 
	//	 * @param clazz
	//	 * @param mapIfs
	//	 * @param showPage
	//	 * @param orderBy
	//	 *            排序
	//	 * @return List
	//	 * @author 徐新望
	//	 */
	//	@SuppressWarnings("unchecked")
	//	public List<T> getObjectByIfsPage(
	//			final Class<T> clazz,
	//			final Map<String, Serializable> mapIfs,
	//			final Integer showPage,
	//			final Integer showRows,
	//			final String orderBy){
	//		return super.getHibernateTemplate().executeFind(new HibernateCallback(){
	//
	//			public Object doInHibernate(Session session) throws HibernateException,SQLException{
	//				StringBuilder sb = new StringBuilder();
	//				sb.append("FROM ").append(clazz.getSimpleName());
	//				// 添加条件
	//				FeiLongDbUtil.joinWhere(sb, mapIfs);
	//				FeiLongDbUtil.joinOrderBy(sb, orderBy);
	//				Query query = getPageQuery(session, sb.toString(), showPage, showRows);
	//				try{
	//					return query.list();
	//				}catch (Exception e){
	//					log.debug(sb.toString());
	//				}
	//				return null;
	//			}
	//		});
	//	}

	//	/**
	//	 * 多条件查询(并列条件)+分页查询+排序(Order)+分组（group）
	//	 * 
	//	 * @param clazz
	//	 * @param mapIfs
	//	 * @param showPage
	//	 * @param groupByField
	//	 *            分组字段参数获取
	//	 * @param groupBy
	//	 *            分组字段
	//	 * @param orderBy
	//	 *            排序
	//	 * @return List
	//	 * @author 徐新望
	//	 */
	//	@SuppressWarnings("unchecked")
	//	public List<T> getObjectByIfsPage(
	//			final Class<T> clazz,
	//			final Map<String, Serializable> mapIfs,
	//			final Integer showPage,
	//			final Integer showRows,
	//			final String groupByField,
	//			final String groupBy,
	//			final String orderBy){
	//		return super.getHibernateTemplate().executeFind(new HibernateCallback(){
	//
	//			public Object doInHibernate(Session session) throws HibernateException,SQLException{
	//				StringBuilder sb = new StringBuilder();
	//				sb.append("SELECT ").append(groupByField);
	//				sb.append(" FROM ").append(clazz.getSimpleName());
	//				// 添加条件
	//				FeiLongDbUtil.joinWhere(sb, mapIfs);
	//				sb.append(" group by ").append(groupBy);
	//				FeiLongDbUtil.joinOrderBy(sb, orderBy);
	//				Query query = getPageQuery(session, sb.toString(), showPage, showRows);
	//				return query.list();
	//			}
	//		});
	//	}

	//	/**
	//	 * 获得分页数据
	//	 * 
	//	 * @param fromString
	//	 *            from部分字符串
	//	 * @param selectString
	//	 *            select部分字符串
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param showPage
	//	 *            从第几页开始
	//	 * @param showRows
	//	 *            显示多少行
	//	 * @param orderBy
	//	 *            条件 以desc_或者asc_开头
	//	 * @return 分页查询后的对象
	//	 * @author 金鑫
	//	 * @version 1.0 时间:2009-11-23下午12:03:00
	//	 */
	//	public List getPageObjects(
	//			final String fromString,
	//			final String selectString,
	//			final Map<String, Serializable> mapIfs,
	//			final int showPage,
	//			final int showRows,
	//			final String orderBy){
	//		return super.getHibernateTemplate().executeFind(new HibernateCallback(){
	//
	//			public Object doInHibernate(Session session) throws HibernateException,SQLException{
	//				StringBuilder sb = new StringBuilder();
	//				sb.append("select ");
	//				sb.append(selectString);
	//				sb.append(" from ");
	//				sb.append(fromString);
	//				// 添加条件
	//				FeiLongDbUtil.joinWhere(sb, mapIfs);
	//				FeiLongDbUtil.joinOrderBy(sb, orderBy);
	//				Query query = getPageQuery(session, sb.toString(), showPage, showRows);
	//				return query.list();
	//			}
	//		});
	//	}

	//	/**
	//	 * 获得分页数据
	//	 * 
	//	 * @param fromString
	//	 *            from部分字符串
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param showPage
	//	 *            从第几页开始
	//	 * @param showRows
	//	 *            显示多少行
	//	 * @param orderBy
	//	 *            条件 以desc_或者asc_开头
	//	 * @return 分页查询后的对象
	//	 * @author 金鑫
	//	 * @version 1.0 时间:2009-11-23下午12:03:00
	//	 */
	//	@SuppressWarnings("unchecked")
	//	public List<T> getPageObjects(final String fromString,final Map<String, Serializable> mapIfs,final int showPage,final int showRows,final String orderBy){
	//		return super.getHibernateTemplate().executeFind(new HibernateCallback(){
	//
	//			public Object doInHibernate(Session session) throws HibernateException,SQLException{
	//				StringBuilder sb = new StringBuilder();
	//				sb.append("FROM ");
	//				sb.append(fromString);
	//				// 添加条件
	//				FeiLongDbUtil.joinWhere(sb, mapIfs);
	//				FeiLongDbUtil.joinOrderBy(sb, orderBy);
	//				Query query = getPageQuery(session, sb.toString(), showPage, showRows);
	//				return query.list();
	//			}
	//		});
	//	}

	//	/**
	//	 * 通过pageModel 封装条件查询
	//	 * 
	//	 * @param pageModel
	//	 *            pageModel实体封装参数
	//	 * @return List
	//	 * @author 金鑫
	//	 * @version 1.0 2010-1-21 下午04:59:50
	//	 */
	//	public List search(final Pager pageModel){
	//		return super.getHibernateTemplate().executeFind(new HibernateCallback(){
	//
	//			public Object doInHibernate(Session session) throws HibernateException,SQLException{
	//				Query query = getQuery(session, FeiLongDbUtil.joinHql(pageModel));
	//				return query.list();
	//			}
	//		});
	//	}

	// [end]
	// [start] 查询
	//	/**
	//	 * 查询
	//	 * 
	//	 * @param clzFrom
	//	 *            从哪个对象查询
	//	 * @param listSelect
	//	 *            查询什么字段
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param groupByString
	//	 *            分组部分 直接写group By后面的部分 不需要"group By" 字眼,没有则传null
	//	 * @param orderByString
	//	 *            排序部分 直接写order By后面的部分 不需要"order By" 字眼,没有则传null
	//	 * @return 查询的list
	//	 * @author 金鑫
	//	 * @version 1.0 2010-1-21 下午05:26:53
	//	 */
	//	public List search(Class clzFrom,LinkedList<String> listSelect,Map<String, Serializable> mapIfs,String groupByString,String orderByString){
	//		Pager pageModel = new Pager();
	//		pageModel.setListSelect(listSelect);
	//		pageModel.setClzFrom(clzFrom);
	//		pageModel.setMapIfs(mapIfs);
	//		pageModel.setGroupByString(groupByString);
	//		pageModel.setOrderByString(orderByString);
	//		return search(pageModel);
	//	}

	//	/**
	//	 * 查询
	//	 * 
	//	 * @param clzFrom
	//	 *            从哪个对象查询
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param orderByString
	//	 *            排序部分 直接写order By后面的部分 不需要"order By" 字眼,没有则传null
	//	 * @return 查询的list
	//	 * @author 金鑫
	//	 * @version 1.0 2010-4-16 下午12:57:11
	//	 */
	//	public List search(Class clzFrom,Map<String, Serializable> mapIfs,String orderByString){
	//		Pager pageModel = new Pager();
	//		pageModel.setClzFrom(clzFrom);
	//		pageModel.setMapIfs(mapIfs);
	//		pageModel.setOrderByString(orderByString);
	//		return search(pageModel);
	//	}

	//	/**
	//	 * 查询( 查询字段是数组)
	//	 * 
	//	 * @param clzFrom
	//	 *            从哪个对象查询
	//	 * @param selectColumns
	//	 *            查询什么字段(数组)
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param groupByString
	//	 *            分组部分 直接写group By后面的部分 不需要"group By" 字眼,没有则传null
	//	 * @param orderByString
	//	 *            排序部分 直接写order By后面的部分 不需要"order By" 字眼,没有则传null
	//	 * @return 查询的list
	//	 * @author 金鑫
	//	 * @version 1.0 2010-2-22 下午01:52:51
	//	 */
	//	public List search(Class clzFrom,String[] selectColumns,Map<String, Serializable> mapIfs,String groupByString,String orderByString){
	//		return search(clzFrom, ArrayUtil.toLinkedList(selectColumns), mapIfs, groupByString, orderByString);
	//	}

	//	/**
	//	 * 获得条件之后的总数
	//	 * 
	//	 * @param fromString
	//	 *            从哪里查
	//	 * @param mapIfs
	//	 *            条件map
	//	 * @return Integer
	//	 * @author 金鑫
	//	 * @version 1.0 时间:2009-11-27下午05:03:30
	//	 */
	//	public Integer getCountPage(final String fromString,final Map<String, Serializable> mapIfs){
	//		return (Integer) super.getHibernateTemplate().execute(new HibernateCallback(){
	//
	//			public Object doInHibernate(Session session) throws HibernateException,SQLException{
	//				StringBuilder sb = new StringBuilder();
	//				sb.append("select count(*) from ");
	//				sb.append(fromString);
	//				// 添加条件
	//				FeiLongDbUtil.joinWhere(sb, mapIfs);
	//				Query query = session.createQuery(sb.toString());
	//				Integer countRow = Integer.parseInt(query.uniqueResult().toString());
	//				return countRow;
	//			}
	//		});
	//	}

	/**
	 * 分页查询,返回集合
	 * 
	 * @param firstPage
	 *            第几页
	 * @param pageSize
	 *            显示行数
	 * @param hql
	 *            hql语句
	 * @param paramsList
	 *            所需要参数
	 * @return List
	 * @author 金鑫
	 * @version 1.0 2010-2-26 上午10:36:04
	 */
	public List searchPageList(final int firstPage,final int pageSize,final String hql,final List paramsList){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session){
				Query query = getPageQuery(session, hql, firstPage, pageSize, paramsList);
				return query.list();
			}
		});
	}

	/**
	 * 分页查询 带泛型
	 * 
	 * @param hql
	 *            sql语句
	 * @param params
	 *            参数
	 * @param firstPage
	 *            第几页
	 * @param pageSize
	 *            一页几条
	 * @return List
	 * @author 徐新望
	 */
	public List pageQuery(String hql,Object[] params,Integer firstPage,Integer pageSize){
		return searchPageList(firstPage, pageSize, hql, params);
	}

	/**
	 * 分页查询2 不带泛型
	 * 
	 * @param hql
	 *            sql语句
	 * @param params
	 *            参数
	 * @param firstPage
	 *            第几页
	 * @param pageSize
	 *            一页几条
	 * @return List
	 * @author 徐新望
	 */
	public List pageQuery2(String hql,Object[] params,Integer firstPage,Integer pageSize){
		return searchPageList(firstPage, pageSize, hql, params);
	}

	/**
	 * 分页查询,返回集合
	 * 
	 * @param firstPage
	 *            第几页
	 * @param pageSize
	 *            显示行数
	 * @param hql
	 *            hql语句
	 * @param params
	 *            可变参数
	 * @return List
	 * @author 金鑫
	 * @version 1.0 2009-12-25 下午04:57:41
	 */
	public List searchPageList(final Integer firstPage,final Integer pageSize,final String hql,final Object...params){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session){
				Query query = getPageQuery(session, hql, firstPage, pageSize, params);
				return query.list();
			}
		});
	}

	/**
	 * 用于 "查询最新的?条" 业务
	 * 
	 * @param hql
	 *            hql语句
	 * @param pageSize
	 *            显示行数
	 * @param params
	 *            可变参数
	 * @return 集合
	 * @author 金鑫
	 * @version 1.0 2009-12-25 下午05:06:44
	 */
	public List searchPagination_New(final String hql,final int pageSize,final Object...params){
		return searchPageList(1, pageSize, hql, params);
	}

	//	/**
	//	 * 最新的数据
	//	 * 
	//	 * @param clazz
	//	 *            类
	//	 * @param mapIfs
	//	 *            条件
	//	 * @param rowCount
	//	 *            数据条数
	//	 * @param orderBy
	//	 *            排序
	//	 * @return List
	//	 * @author 金鑫
	//	 * @version 1.0 2010-4-13 下午06:45:36
	//	 */
	//	public List searchPagination_New(final Class<T> clazz,final Map<String, Serializable> mapIfs,final Integer rowCount,final String orderBy){
	//		return getObjectByIfsPage(clazz, mapIfs, 1, rowCount, orderBy);
	//	}

	// [end]
	// [start] 获得 org.hibernate.Query对象
	/**
	 * 获得 org.hibernate.Query对象 把参数也设定好
	 * 
	 * @param session
	 *            org.hibernate.Session
	 * @param hql
	 *            hql语句
	 * @param params
	 *            可变参数
	 * @return 参数设定好的org.hibernate.Query对象
	 * @author 金鑫
	 * @version 1.0 2009-12-28 下午01:35:12
	 */
	Query getQuery(Session session,String hql,Object...params){
		Query query = session.createQuery(hql);
		if (Validator.isNotNullOrEmpty(params)){
			for (int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		return query;
	}

	/**
	 * 获得分页的query
	 * 
	 * @param session
	 * @param hql
	 *            组合好的hql语句
	 * @param showPage
	 *            从第几页开始
	 * @param showRows
	 *            显示多少行
	 * @param params
	 *            可变参数
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-1-21 下午04:28:00
	 */
	Query getPageQuery(Session session,String hql,Integer showPage,Integer showRows,Object...params){
		Query query = getQuery(session, hql, params);
		return setPageQuery(query, showPage, showRows);
	}

	/**
	 * 获得分页的query
	 * 
	 * @param session
	 * @param hql
	 *            组合好的hql语句
	 * @param showPage
	 *            从第几页开始
	 * @param showRows
	 *            显示多少行
	 * @param paramsList
	 *            参数集合
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-1-21 下午04:40:53
	 */
	Query getPageQuery(Session session,String hql,int showPage,int showRows,List paramsList){
		Query query = getQuery(session, hql, paramsList);
		return setPageQuery(query, showPage, showRows);
	}

	/**
	 * 将query设置成分页的query
	 * 
	 * @param query
	 *            Query
	 * @param showPage
	 *            从第几页开始 第一页传进来1
	 * @param showRows
	 *            显示多少行
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-1-21 下午04:44:53
	 */
	Query setPageQuery(Query query,Integer showPage,Integer showRows){
		if (Validator.isNotNullOrEmpty(showPage) && Validator.isNotNullOrEmpty(showRows)){
			// 分页查询
			if (showPage > 0 && showRows > 0){
				query.setFirstResult((showPage - 1) * showRows);
				query.setMaxResults(showRows);
			}
		}
		return query;
	}

	/**
	 * 获得 org.hibernate.Query对象 把参数也设定好
	 * 
	 * @param session
	 *            org.hibernate.Session
	 * @param hql
	 *            hql语句
	 * @param paramsList
	 *            参数列表
	 * @return 参数设定好的org.hibernate.Query对象
	 * @author 金鑫
	 * @version 1.0 2010年1月9日 16:23:40
	 */
	Query getQuery(Session session,String hql,List paramsList){
		Query query = session.createQuery(hql);
		if (Validator.isNotNullOrEmpty(paramsList)){
			for (int i = 0; i < paramsList.size(); i++){
				query.setParameter(i, paramsList.get(i));
			}
		}
		return query;
	}

	// [end]
	// [start] 其它
	/** ****************************** 其它 *************************************** */
	/**
	 * 刷新object对象的状态
	 * 
	 * @param object
	 * @author 金鑫
	 * @version 1.0 2009-9-19下午05:06:07
	 */
	public void refresh(Object object){
		super.getHibernateTemplate().refresh(object);
	}

	/**
	 * 查询的是缓存而不是数据库
	 * 
	 * @param object
	 * @return 缓存中是否包含该对象
	 * @author 金鑫
	 * @version 1.0 2009-9-19下午05:15:10
	 */
	public boolean contains(Object object){
		return getHibernateTemplate().contains(object);
	}

	// [end]
	/** ***************************** 获得主键编号 ****************************** */
	/**
	 * 添加对象时,生成主键编号.
	 * 
	 * <pre>
	 * 获得对象最大编号,将编号+1返回
	 * </pre>
	 * 
	 * @param pojoName
	 *            pojo对象名称
	 * @param idName
	 *            主键字段名称
	 * @return 添加对象时,生成主键编号.
	 * @author 金鑫
	 * @version 1.0 2009-9-3下午02:52:21
	 */
	public BigDecimal createMaxId(String pojoName,String idName){
		return new BigDecimal(searchUniqueResult("select max(" + idName + ") from " + pojoName + "") + 1);
	}

	/**
	 * 添加对象时,生成主键编号.,缺省idName,自动生成idName
	 * 
	 * <pre>
	 * 慎用,须确保主键和表名同步更改
	 * idName必须命名方法 _id
	 * idName生成方式:pojoName首字母小写,添加&quot;Id&quot; 后缀
	 * </pre>
	 * 
	 * @param pojoName
	 *            pojo对象名称
	 * @return 添加对象时,生成主键编号.
	 * @author 金鑫
	 * @version 1.0 2010-8-9 上午11:11:53
	 */
	public BigDecimal createMaxId(String pojoName){
		String idName = StringUtil.firstCharToLowerCase(pojoName) + "Id";
		return createMaxId(pojoName, idName);
	}

	// [start] SQL相关
	/** ***************************** SQL相关 ********************************** */
	/**
	 * 执行SQL语句
	 * 
	 * @param hql
	 * @param p
	 * @param page
	 * @param size
	 * @return List
	 */
	public List executeSql(final String hql,final Object[] p,final Integer page,final Integer size){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session s) throws HibernateException,SQLException{
				Query query = s.createSQLQuery(hql);
				// 判断参数
				if (p != null){
					for (int i = 0; i < p.length; i++){
						if (p[i].getClass() == String.class){
							query.setString(i, (String) p[i]);
						}else if (p[i].getClass() == Integer.class){
							query.setInteger(i, (Integer) p[i]);
						}else if (p[i].getClass() == Float.class){
							query.setFloat(i, (Float) p[i]);
						}else if (p[i].getClass() == Double.class){
							query.setDouble(i, (Double) p[i]);
						}else if (p[i].getClass() == Date.class){
							query.setDate(i, (Date) p[i]);
						}else if (p[i].getClass() == BigDecimal.class){
							query.setBigDecimal(i, (BigDecimal) p[i]);
						}
					}
				}
				setPageQuery(query, page, size);
				return query.list();
			}
		});
	}
	// [end]
}