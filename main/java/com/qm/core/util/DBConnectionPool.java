package com.qm.core.util;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * 数据库连接对象
 * 
 */
public class DBConnectionPool {
	private static volatile DBConnectionPool dbConnection;
	private ComboPooledDataSource cpds;

	/**
	 * 在构造函数初始化的时候获取数据库连接
	 */
	private DBConnectionPool() {
		try {
			/** 通过属性文件获取数据库连接的参数值 **/
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(
					"F:/jdbc.properties");
			properties.load(fileInputStream);
			/** 获取属性文件中的值 **/
			String driverClassName = properties.getProperty("jdbc.driverClass");
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.userName");
			String password = properties.getProperty("jdbc.password");

			/** 数据库连接池对象 **/
			cpds = new ComboPooledDataSource();

			/** 设置数据库连接驱动 **/
			cpds.setDriverClass(driverClassName);
			/** 设置数据库连接地址 **/
			cpds.setJdbcUrl(url);
			/** 设置数据库连接用户名 **/
			cpds.setUser(username);
			/** 设置数据库连接密码 **/
			cpds.setPassword(password);

			/** 初始化时创建的连接数,应在minPoolSize与maxPoolSize之间取值.默认为3 **/
			cpds.setInitialPoolSize(3);
			/** 连接池中保留的最大连接数据.默认为15 **/
			cpds.setMaxPoolSize(10);
			/** 当连接池中的连接用完时，C3PO一次性创建新的连接数目; **/
			cpds.setAcquireIncrement(1);
			/** 隔多少秒检查所有连接池中的空闲连接,默认为0表示不检查; **/
			cpds.setIdleConnectionTestPeriod(60);
			/** 最大空闲时间,超过空闲时间的连接将被丢弃.为0或负数据则永不丢弃.默认为0; **/
			cpds.setMaxIdleTime(3000);

			/**
			 * 因性能消耗大请只在需要的时候使用它。如果设为true那么在每个connection提交的
			 * 时候都将校验其有效性。建议使用idleConnectionTestPeriod或automaticTestTable
			 * 等方法来提升连接测试的性能。Default: false
			 **/
			cpds.setTestConnectionOnCheckout(true);

			/** 如果设为true那么在取得连接的同时将校验连接的有效性。Default: false **/
			cpds.setTestConnectionOnCheckin(true);
			/** 定义在从数据库获取新的连接失败后重复尝试获取的次数，默认为30; **/
			cpds.setAcquireRetryAttempts(30);
			/** 两次连接中间隔时间默认为1000毫秒 **/
			cpds.setAcquireRetryDelay(1000);
			/**
			 * 获取连接失败将会引起所有等待获取连接的线程异常,
			 * 但是数据源仍有效的保留,并在下次调用getConnection()的时候继续尝试获取连接.如果设为true,
			 * 那么尝试获取连接失败后该数据源将申明已经断开并永久关闭.默认为false
			 **/
			cpds.setBreakAfterAcquireFailure(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取数据库连接对象，单例
	 *
	 * @return
	 */
	public static DBConnectionPool getInstance() {
		if (dbConnection == null) {
			synchronized (DBConnectionPool.class) {
				if (dbConnection == null) {
					dbConnection = new DBConnectionPool();
				}
			}
		}
		return dbConnection;
	}

	/**
	 * 获取数据库连接
	 *
	 * @return 数据库连接
	 */
	public final synchronized Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}

	/**
	 * finalize()方法是在垃圾收集器删除对象之前对这个对象调用的。
	 *
	 * @throws Throwable
	 */
	protected void finalize() throws Throwable {
		DataSources.destroy(cpds);
		super.finalize();
	}

	/**
	 * 可以执行新增，修改，删除
	 *
	 * @param sql
	 *            sql语句
	 * @param bindArgs
	 *            绑定参数
	 * @return 影响的行数
	 * @throws SQLException
	 *             SQL异常
	 */
	public static int executeUpdate(String sql, Object[] bindArgs) throws SQLException {
		/** 影响的行数 **/
		int affectRowCount = -1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			/** 从数据库连接池中获取数据库连接 **/
			connection = DBConnectionPool.getInstance().getConnection();
			/** 执行SQL预编译 **/
			preparedStatement = connection.prepareStatement(sql.toString());
			/** 设置不自动提交，以便于在出现异常的时候数据库回滚 **/
			connection.setAutoCommit(false);
			// System.out.println(getExecSQL(sql, bindArgs));
			if (bindArgs != null) {
				/** 绑定参数设置sql占位符中的值 **/
				for (int i = 0; i < bindArgs.length; i++) {
					preparedStatement.setObject(i + 1, bindArgs[i]);
				}
			}
			/** 执行sql **/
			affectRowCount = preparedStatement.executeUpdate();
			connection.commit();
			String operate;
			if (sql.toUpperCase().indexOf("DELETE FROM") != -1) {
				operate = "删除";
			} else if (sql.toUpperCase().indexOf("INSERT INTO") != -1) {
				operate = "新增";
			} else {
				operate = "修改";
			}
			System.out.println("成功" + operate + "了" + affectRowCount + "行");
			System.out.println();
		} catch (Exception e) {
			if (connection != null) {
				connection.rollback();
			}
			e.printStackTrace();
			throw e;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return affectRowCount;
	}

	private static List<Map<String, Object>> getDatas(ResultSet rs) throws SQLException {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount(); // Map rowData;
		while (rs.next()) {
			Map<String, Object> rowData = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}

	/**
	 * 执行查询
	 *
	 * @param sql
	 *            要执行的sql语句
	 * @param bindArgs
	 *            绑定的参数
	 * @return List<Map<String, Object>>结果集对象
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public static List<Map<String, Object>> executeQuery(String sql, Object[] bindArgs) throws SQLException {
		List<Map<String, Object>> datas = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			/** 获取数据库连接池中的连接 **/
			connection = DBConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if (bindArgs != null) {
				/** 设置sql占位符中的值 **/
				for (int i = 0; i < bindArgs.length; i++) {
					preparedStatement.setObject(i + 1, bindArgs[i]);
				}
			}
			// System.out.println(getExecSQL(sql, bindArgs));
			/** 执行sql语句，获取结果集 **/
			resultSet = preparedStatement.executeQuery();
			datas = getDatas(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return datas;
	}

	/**
	 * 执行数据库插入操作
	 *
	 * @param valueMap
	 *            插入数据表中key为列名和value为列对应的值的Map对象
	 * @param tableName
	 *            要插入的数据库的表名
	 * @return 影响的行数
	 * @throws SQLException
	 *             SQL异常
	 */
	public static int insert(String tableName, Map<String, Object> valueMap) throws SQLException {

		/** 获取数据库插入的Map的键值对的值 **/
		Set<String> keySet = valueMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		/** 要插入的字段sql，其实就是用key拼起来的 **/
		StringBuilder columnSql = new StringBuilder();
		/** 要插入的字段值，其实就是？ **/
		StringBuilder unknownMarkSql = new StringBuilder();
		Object[] bindArgs = new Object[valueMap.size()];
		int i = 0;
		while (iterator.hasNext()) {
			String key = iterator.next();
			columnSql.append(i == 0 ? "" : ",");
			columnSql.append(key);

			unknownMarkSql.append(i == 0 ? "" : ",");
			unknownMarkSql.append("?");
			bindArgs[i] = valueMap.get(key);
			i++;
		}
		/** 开始拼插入的sql语句 **/
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(tableName);
		sql.append(" (");
		sql.append(columnSql);
		sql.append(" )  VALUES (");
		sql.append(unknownMarkSql);
		sql.append(" )");
		return executeUpdate(sql.toString(), bindArgs);
	}

	/**
	 * 执行更新操作
	 *
	 * @param tableName
	 *            表名
	 * @param valueMap
	 *            要更改的值
	 * @param whereMap
	 *            条件
	 * @return 影响的行数
	 * @throws SQLException
	 *             SQL异常
	 */
	public static int update(String tableName, Map<String, Object> valueMap, Map<String, Object> whereMap)
			throws SQLException {
		/** 获取数据库插入的Map的键值对的值 **/
		Set<String> keySet = valueMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		/** 开始拼插入的sql语句 **/
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append(tableName);
		sql.append(" SET ");

		/** 要更改的的字段sql，其实就是用key拼起来的 **/
		StringBuilder columnSql = new StringBuilder();
		int i = 0;
		List<Object> objects = new ArrayList<>();
		while (iterator.hasNext()) {
			String key = iterator.next();
			columnSql.append(i == 0 ? "" : ",");
			columnSql.append(key + " = ? ");
			objects.add(valueMap.get(key));
			i++;
		}
		sql.append(columnSql);

		/** 更新的条件:要更改的的字段sql，其实就是用key拼起来的 **/
		StringBuilder whereSql = new StringBuilder();
		int j = 0;
		if (whereMap != null && whereMap.size() > 0) {
			whereSql.append(" WHERE ");
			iterator = whereMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				whereSql.append(j == 0 ? "" : " AND ");
				whereSql.append(key + " = ? ");
				objects.add(whereMap.get(key));
				j++;
			}
			sql.append(whereSql);
		}
		return executeUpdate(sql.toString(), objects.toArray());
	}

	/**
	 * 执行删除操作
	 *
	 * @param tableName
	 *            要删除的表名
	 * @param whereMap
	 *            删除的条件
	 * @return 影响的行数
	 * @throws SQLException
	 *             SQL执行异常
	 */
	public static int delete(String tableName, Map<String, Object> whereMap) throws SQLException {
		/** 准备删除的sql语句 **/
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ");
		sql.append(tableName);

		/** 更新的条件:要更改的的字段sql，其实就是用key拼起来的 **/
		StringBuilder whereSql = new StringBuilder();
		Object[] bindArgs = null;
		if (whereMap != null && whereMap.size() > 0) {
			bindArgs = new Object[whereMap.size()];
			whereSql.append(" WHERE ");
			/** 获取数据库插入的Map的键值对的值 **/
			Set<String> keySet = whereMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				String key = iterator.next();
				whereSql.append(i == 0 ? "" : " AND ");
				whereSql.append(key + " = ? ");
				bindArgs[i] = whereMap.get(key);
				i++;
			}
			sql.append(whereSql);
		}
		return executeUpdate(sql.toString(), bindArgs);
	}
}
