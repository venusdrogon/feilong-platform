//package com.feilong.commons.db;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.servlet.jsp.jstl.sql.Result;
//import javax.servlet.jsp.jstl.sql.ResultSupport;
//import javax.sql.DataSource;
//
//import com.feilong.commons.core.util.Validator;
//import com.feilong.servlet.jsp.ResultUtil;
//
///**
// * 数据库操作公用类
// * 
// * @author 金鑫 时间:2009-2-12下午06:37:19
// */
//public class JDBCDAO implements FeiLongDAO{
//
//	// 数据库连接池名称
//	protected String			poolNameString		= "";
//
//	// ***************************************数据库相关对象************************************
//	// 与特定数据库的连接（会话）。
//	private Connection			connection			= null;
//
//	// 表示预编译的 SQL 语句的对象
//	private PreparedStatement	preparedStatement	= null;
//
//	// 用于执行静态 SQL 语句并返回它所生成结果的对象
//	private Statement			statement			= null;
//
//	// 用于执行 SQL 存储过程的接口
//	private CallableStatement	callableStatement	= null;
//
//	// 查询返回的结果集
//	private ResultSet			resultSet			= null;
//
//	// 查询返回的结果集转换而成的Result
//	private Result				result				= null;
//
//	//
//	// 结果Result 的每行数据对象
//	// protected Map row;
//	// *****************************************必须有poolNameString的构造方法***********************************************
//	public JDBCDAO(String poolNameString){
//		this.poolNameString = poolNameString;
//	}
//
//	// **************************查询******************************************
//	/**
//	 * 查询返回结果集第一行第一列
//	 * 
//	 * @param sql
//	 *            sql
//	 * @param params
//	 *            可变参数
//	 * @return 查询返回结果集第一行第一列
//	 * @author 金鑫
//	 * @version 1.0 2010-7-9 下午01:57:44
//	 */
//	public Object searchUniqueResult(String sql,Object...params){
//		result = executeQuery(sql, params);
//		return ResultUtil.getUniqueResult(result);
//	}
//
//	/**
//	 * 查询返回结果集第一行第一列
//	 * 
//	 * @param sql
//	 *            sql
//	 * @param paramsList
//	 *            参数列表
//	 * @return 查询返回结果集第一行第一列
//	 * @author 金鑫
//	 * @version 1.0 2010-8-30 下午05:52:39
//	 */
//	public Object searchUniqueResult(String sql,List<Object> paramsList){
//		result = executeQuery(sql, paramsList);
//		return ResultUtil.getUniqueResult(result);
//	}
//
//	/**
//	 * 创建最大的主键编号
//	 * 
//	 * @param tableName
//	 *            表名
//	 * @param primaryKeyName
//	 *            主键列
//	 * @return 创建最大的主键编号
//	 * @author 金鑫
//	 * @version 1.0 Aug 19, 2010 11:01:37 PM
//	 */
//	public Integer createMaxId(String tableName,String primaryKeyName){
//		Object object = searchUniqueResult("select max(" + primaryKeyName + ") as c from " + tableName + "");
//		if (null == object){
//			return 1;
//		}
//		return Integer.parseInt(object.toString()) + 1;
//	}
//
//	/**
//	 * 通用的查询方法
//	 * 
//	 * @param sql
//	 *            sql语句
//	 * @param paramsList
//	 *            参数list
//	 * @return 通用的查询方法
//	 * @author 金鑫
//	 * @version 1.0 2009-2-16下午05:25:33
//	 */
//	public Result executeQuery(String sql,List<Object> paramsList){
//		preparedStatement = getPreparedStatementAndSetParams(sql, paramsList);
//		try{
//			resultSet = preparedStatement.executeQuery();
//			result = ResultSupport.toResult(resultSet);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}finally{
//			closeDataBaseObject();
//		}
//		return result;
//	}
//
//	/**
//	 * 通用的查询方法
//	 * 
//	 * @param sql
//	 *            sql
//	 * @param params
//	 *            可变参数
//	 * @return 通用的查询方法
//	 * @author 金鑫
//	 * @version 1.0 2010-7-9 上午11:53:51
//	 */
//	public Result executeQuery(String sql,Object...params){
//		preparedStatement = getPreparedStatementAndSetParams(sql, params);
//		try{
//			resultSet = preparedStatement.executeQuery();
//			result = ResultSupport.toResult(resultSet);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}finally{
//			closeDataBaseObject();
//		}
//		return result;
//	}
//
//	/**
//	 * Result
//	 * 
//	 * @param pageModel
//	 *            pageModel
//	 * @return Result
//	 * @author 金鑫
//	 * @version 1.0 2010-7-5 下午05:39:19
//	 */
//	public Result executeQuery(Pager pageModel){
//		List<Object> paramsList = pageModel.getParamsList();
//		result = executeQuery(FeiLongDbUtil.getSql(pageModel), paramsList);
//		return result;
//	}
//
//	/**
//	 * 数据是否存在
//	 * 
//	 * @param sql
//	 *            sql
//	 * @param paramsList
//	 *            参数list
//	 * @return 存在返回true
//	 * @author 金鑫
//	 * @version 1.0 2010-5-19 下午02:24:09
//	 */
//	public boolean isExist(String sql,List<Object> paramsList){
//		Result result = executeQuery(sql, paramsList);
//		return null != result && result.getRowCount() > 0;
//	}
//
//	/**
//	 * 数据是否存在
//	 * 
//	 * @param sql
//	 * @param params
//	 *            可变参数
//	 * @return 存在返回true
//	 * @author 金鑫
//	 * @version 1.0 2010-7-9 上午11:52:46
//	 */
//	public boolean isExist(String sql,Object...params){
//		Result result = executeQuery(sql, params);
//		return null != result && result.getRowCount() > 0;
//	}
//
//	// *********************************存储过程相关******************************************
//	/**
//	 * 存储过程查询 格式:{call dbo.selectRepeate(?,?)}
//	 * 
//	 * @param procName
//	 *            存储过程名称 格式:{call dbo.selectRepeate(?,?)}
//	 * @param paramsList
//	 *            参数列表
//	 * @return Result
//	 * @author 金鑫
//	 * @version 1.0 2009-6-3下午01:24:19
//	 */
//	public Result executeCallableQuery(String procName,List<Object> paramsList){
//		callableStatement = getCallableStatementAndSetParams(procName, paramsList);
//		try{
//			resultSet = callableStatement.executeQuery();
//			result = ResultSupport.toResult(resultSet);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}finally{
//			closeDataBaseObject();
//		}
//		return result;
//	}
//
//	/**
//	 * 存储过程查询 格式:{call dbo.selectRepeate(?,?)}
//	 * 
//	 * @param procName
//	 *            存储过程名称 格式:{call dbo.selectRepeate(?,?)}
//	 * @param params
//	 *            可变参数
//	 * @return Result
//	 * @author 金鑫
//	 * @version 1.0 2010-9-1 下午02:08:31
//	 */
//	public Result executeCallableQuery(String procName,Object...params){
//		callableStatement = getCallableStatementAndSetParams(procName, params);
//		try{
//			resultSet = callableStatement.executeQuery();
//			result = ResultSupport.toResult(resultSet);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}finally{
//			closeDataBaseObject();
//		}
//		return result;
//	}
//
//	/**
//	 * 简单名称存储过程查询,自动拼接存储过程
//	 * 
//	 * <pre>
//	 * selectRepeate----&gt;{call dbo.selectRepeate(?,?)}
//	 * </pre>
//	 * 
//	 * @param simpleProcName
//	 *            格式:selectRepeate
//	 * @param paramsList
//	 *            参数列表
//	 * @return Result
//	 * @author 金鑫
//	 * @version 1.0 2010-9-1 下午02:39:54
//	 */
//	public Result executeCallableQueryWithSimpleProcName(String simpleProcName,List<Object> paramsList){
//		String procName = JdbcUtil.simpleProcNameToProcName(simpleProcName, paramsList);
//		return executeCallableQuery(procName, paramsList);
//	}
//
//	/**
//	 * 简单名称存储过程查询,自动拼接存储过程
//	 * 
//	 * <pre>
//	 * selectRepeate----&gt;{call dbo.selectRepeate(?,?)}
//	 * </pre>
//	 * 
//	 * @param simpleProcName
//	 *            格式:selectRepeate
//	 * @param params
//	 *            可变参数
//	 * @return Result
//	 * @author 金鑫
//	 * @version 1.0 2010-9-1 下午02:39:54
//	 */
//	public Result executeCallableQueryWithSimpleProcName(String simpleProcName,Object...params){
//		String procName = JdbcUtil.simpleProcNameToProcName(simpleProcName, params);
//		return executeCallableQuery(procName, params);
//	}
//
//	/**
//	 * 存储过程更新 格式:{call dbo.selectRepeate(?,?)}
//	 * 
//	 * @param procName
//	 *            存储过程名称 格式:{call dbo.selectRepeate(?,?)}
//	 * @param paramsList
//	 *            参数列表
//	 * @return 成功返回true
//	 * @author 金鑫
//	 * @version 1.0 2009-2-18下午06:59:30
//	 */
//	public boolean executeCallableUpdate(String procName,List<Object> paramsList){
//		callableStatement = getCallableStatementAndSetParams(procName, paramsList);
//		try{
//			return callableStatement.executeUpdate() > 0;
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}finally{
//			closeDataBaseObject();
//		}
//		return false;
//	}
//
//	// *******************************分页**************************************************
//	/**
//	 * mssql 分页
//	 * 
//	 * @param pageModel
//	 *            pageModel
//	 * @return 分页
//	 * @author 金鑫
//	 * @version 1.0 2010-8-30 上午11:37:46
//	 */
//	public Pager getPageModelForMSSQL(Pager pageModel){
//		/**
//		 * 总数
//		 */
//		Number total = (Number) searchUniqueResult(FeiLongDbUtil.getSqlCount(pageModel), pageModel.getParamsList());
//		/**
//		 * 结果
//		 */
//		Result result = executeQuery(FeiLongDbUtil.getSqlForMsSqlPager(pageModel), pageModel.getParamsList());
//		return FeiLongDbUtil.setPageModel(pageModel, total, result);
//	}
//
//	/**
//	 * mysql 分页
//	 * 
//	 * @param pageModel
//	 *            pageModel
//	 * @return 分页
//	 * @author 金鑫
//	 * @version 1.0 Aug 30, 2010 8:29:43 PM
//	 */
//	public Pager getPageModelForMySQL(Pager pageModel){
//		/**
//		 * 总数
//		 */
//		Number total = (Number) searchUniqueResult(FeiLongDbUtil.getSqlCount(pageModel), pageModel.getParamsList());
//		/**
//		 * 结果
//		 */
//		Result result = executeQuery(FeiLongDbUtil.getSqlForMySqlPager(pageModel), pageModel.getParamsList());
//		return FeiLongDbUtil.setPageModel(pageModel, total, result);
//	}
//
//	// ***************************增加 删除 更新*********************************************
//	/**
//	 * 处理所有增加 删除 更新的sql语句
//	 * 
//	 * @return 是否执行成功
//	 * @author 金鑫
//	 * @version 1.0 2009-2-15下午02:19:27
//	 */
//	public boolean executeUpdate(String sql,List<Object> paramsList){
//		preparedStatement = getPreparedStatementAndSetParams(sql, paramsList);
//		try{
//			return preparedStatement.executeUpdate() > 0;
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}finally{
//			closeDataBaseObject();
//		}
//		return false;
//	}
//
//	/**
//	 * 处理所有增加 删除 更新的sql语句 参数比较少的情况下或者无参数 建议使用可变参数
//	 * 
//	 * @param sql
//	 *            sql
//	 * @param params
//	 *            可变参数
//	 * @return 处理所有增加 删除 更新的sql语句
//	 * @author 金鑫
//	 * @version 1.0 2010-7-8 下午05:08:42
//	 */
//	public boolean executeUpdate(String sql,Object...params){
//		preparedStatement = getPreparedStatementAndSetParams(sql, params);
//		try{
//			return preparedStatement.executeUpdate() > 0;
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}finally{
//			closeDataBaseObject();
//		}
//		return false;
//	}
//
//	// ******************************底层**************************************
//	/**
//	 * 获得 CallableStatement对象
//	 * 
//	 * @param procName
//	 *            存储过程名称
//	 * @return CallableStatement对象
//	 * @author 金鑫
//	 * @version 1.0 2008-10-27下午04:47:27
//	 */
//	public CallableStatement getCallableStatement(String procName){
//		connection = getConnectionInPool();
//		try{
//			callableStatement = connection.prepareCall(procName);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}
//		return callableStatement;
//	}
//
//	/**
//	 * 获得 CallableStatement对象并且设置参数
//	 * 
//	 * @param procName
//	 *            存储过程名称
//	 * @param paramsList
//	 *            参数列表
//	 * @return 已经设置参数CallableStatement
//	 * @author 金鑫
//	 * @version 1.0 2010-9-1 下午02:00:29
//	 */
//	public CallableStatement getCallableStatementAndSetParams(String procName,List<Object> paramsList){
//		if (Validator.isNotNull(paramsList)){
//			// 集合转成数组
//			return getCallableStatementAndSetParams(procName, paramsList.toArray());
//		}
//		return getCallableStatementAndSetParams(procName);
//	}
//
//	/**
//	 * 获得 CallableStatement对象并且设置参数
//	 * 
//	 * @param procName
//	 *            存储过程名称
//	 * @param params
//	 *            可变参数
//	 * @return CallableStatement
//	 * @author 金鑫
//	 * @version 1.0 2010-9-1 下午02:05:21
//	 */
//	public CallableStatement getCallableStatementAndSetParams(String procName,Object...params){
//		callableStatement = getCallableStatement(procName);
//		try{
//			if (Validator.isNotNull(params)){
//				// 有参callableStatement 设置参数
//				for (int i = 0, j = params.length; i < j; i++){
//					callableStatement.setObject(i + 1, params[i]);
//				}
//			}
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}
//		return callableStatement;
//	}
//
//	// ***************************PreparedStatement******************
//	/**
//	 * 简练获得 PreparedStatement对象
//	 * 
//	 * @param sql
//	 *            sql语句
//	 * @return 简练获得 PreparedStatement对象
//	 * @author 金鑫
//	 * @version 1.0 2010-7-1 上午08:53:44
//	 */
//	private PreparedStatement getPreparedStatement(String sql){
//		connection = getConnectionInPool();
//		try{
//			preparedStatement = connection.prepareStatement(sql);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}
//		return preparedStatement;
//	}
//
//	/**
//	 * 获得PreparedStatement并且设置参数
//	 * 
//	 * @param sql
//	 *            sql
//	 * @param paramsList
//	 *            参数list
//	 * @return PreparedStatement
//	 * @author 金鑫
//	 * @version 1.0 2010-7-9 下午12:02:03
//	 */
//	public PreparedStatement getPreparedStatementAndSetParams(String sql,List<Object> paramsList){
//		if (Validator.isNotNull(paramsList)){
//			// 集合转成数组
//			return getPreparedStatementAndSetParams(sql, paramsList.toArray());
//		}
//		return getPreparedStatementAndSetParams(sql);
//	}
//
//	/**
//	 * 获得PreparedStatement并且设置参数
//	 * 
//	 * @param sql
//	 *            sql
//	 * @param params
//	 *            可变参数
//	 * @return PreparedStatement
//	 * @author 金鑫
//	 * @version 1.0 2010-7-9 上午11:59:01
//	 */
//	public PreparedStatement getPreparedStatementAndSetParams(String sql,Object...params){
//		preparedStatement = getPreparedStatement(sql);
//		try{
//			if (Validator.isNotNull(params)){
//				// 有参preparedStatement 设置参数
//				for (int i = 0, j = params.length; i < j; i++){
//					preparedStatement.setObject(i + 1, params[i]);
//				}
//			}
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}
//		return preparedStatement;
//	}
//
//	// ******************************Connection***********************************************
//	/**
//	 * 连接池获取数据库连接
//	 * 
//	 * @return 连接池获取数据库连接
//	 * @author 金鑫
//	 * @version 1.0 2009-5-24下午02:53:52
//	 */
//	public Connection getConnectionInPool(){
//		try{
//			Context context = new InitialContext();
//			if (Validator.isNull(poolNameString)){
//				throw new IllegalArgumentException("连接池名称未传递");
//			}
//			DataSource dataSource = (DataSource) context.lookup(poolNameString);
//			connection = dataSource.getConnection();
//			if (null == connection){
//				throw new RuntimeException("数据库链接错误");
//			}
//		}catch (NamingException e){
//			log.error(e.getClass().getName(), e);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}
//		return connection;
//	}
//
//	/**
//	 * 普通方法获得数据库连接
//	 * 
//	 * @param driver
//	 *            数据库驱动
//	 * @param url
//	 *            jdbc:subprotocol:subname 形式的数据库 url
//	 * @return Connection
//	 * @author 金鑫
//	 * @version 1.0 Aug 17, 2010 9:58:35 PM
//	 */
//	public Connection getConnection(String driver,String url){
//		try{
//			Class.forName(driver);
//			connection = DriverManager.getConnection(url);
//			return connection;
//		}catch (ClassNotFoundException e){
//			log.error(e.getClass().getName(), e);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}
//		return null;
//	}
//
//	/**
//	 * 普通方法获得数据库连接
//	 * 
//	 * @param driver
//	 *            驱动
//	 * @param url
//	 *            连接
//	 * @param userName
//	 *            用户名
//	 * @param password
//	 *            密码
//	 * @return Connection
//	 * @author 金鑫
//	 * @version 1.0 Aug 19, 2010 1:39:32 PM
//	 */
//	public Connection getConnection(String driver,String url,String userName,String password){
//		try{
//			Class.forName(driver);
//			connection = DriverManager.getConnection(url, userName, password);
//			return connection;
//		}catch (ClassNotFoundException e){
//			log.error(e.getClass().getName(), e);
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}
//		return null;
//	}
//
//	// ******************************关闭数据库***********************************************
//	/**
//	 * 关闭数据库连接相关对象
//	 * 
//	 * @author 金鑫
//	 * @version 1.0 Jul 1, 2010 6:21:19 PM
//	 */
//	public void closeDataBaseObject(){
//		try{
//			if (null != resultSet){
//				resultSet.close();
//				resultSet = null;
//			}
//			if (null != statement){
//				statement.close();
//				statement = null;
//			}
//			if (null != preparedStatement){
//				preparedStatement.close();
//				preparedStatement = null;
//			}
//			if (null != callableStatement){
//				callableStatement.close();
//				callableStatement = null;
//			}
//			if (null != connection && !connection.isClosed()){
//				connection.close();
//				connection = null;
//			}
//		}catch (SQLException e){
//			log.error(e.getClass().getName(), e);
//		}
//	}
//	// **********************************************************************************
//	/**
//	 * 按照列名分组罗列数量
//	 * 
//	 * @param nameCoulmn
//	 *            列名
//	 * @param countCoulmn
//	 *            数字列
//	 * @return
//	 */
//	// protected List<String[]> getNameCountGroupByName(String nameCoulmn,String countCoulmn){
//	// // 自动执行集成的sql语句
//	// executeQuery();
//	// List<String[]> list = null;
//	// if (null != rs && rowCount > 0){
//	// list = new ArrayList<String[]>();
//	// String[] gather_Count;
//	// for (int i = 0; i < rowCount; i++){
//	// row = rs.getRows()[i];
//	// gather_Count = new String[2];
//	// gather_Count[0] = trim(row.get(nameCoulmn));
//	// gather_Count[1] = row.get(countCoulmn).toString();
//	// list.add(gather_Count);
//	// }
//	// }
//	// return list;
//	// }
//	//
//	/**
//	 * 去掉读取的数据空格,注意该对象有可能是空的 作者:金鑫 时间:2009-6-3下午03:06:29
//	 * 
//	 * @param object
//	 * @return
//	 */
//	// protected String trimObject(Object object){
//	// return object == null ? "" : object.toString().trim();
//	// }
//	//
//	/**
//	 * 将返回的数据去除空格 作者:金鑫 时间:2009-6-3下午12:03:09
//	 * 
//	 * @param object
//	 * @return
//	 */
//	// protected String trim(Object object){
//	// return object == null ? "" : object.toString().trim();
//	// }
//	/**
//	 * 设置自动提交 作者:金鑫 时间:2009-6-10下午03:20:51
//	 * 
//	 * @param b
//	 */
//	// protected void setAutoCommit(boolean b){
//	// try{
//	// connection.setAutoCommit(b);
//	// }catch (SQLException e){
//	// log.error(e.getClass().getName(), e);
//	// }
//	// }
//	//
//	/**
//	 * 回滚 作者:金鑫 时间:2009-6-10下午03:22:29
//	 */
//	// protected void rollback(){
//	// try{
//	// // 回滚
//	// connection.rollback();
//	// }catch (SQLException e){
//	// log.error(e.getClass().getName(), e);
//	// }
//	// }
//}