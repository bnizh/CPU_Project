package server.database.connectionpool;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import server.database.manager.DbInfo;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
	private int maxActive = 1000;
	private int maxWait = 10000;
	private int maxIdle = 50;
	private static BasicDataSource dataSource;
	private static ConnectionPool connectionPool;

	private ConnectionPool(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl(DbInfo.DATABASE_INSTANCE);
		dataSource.setUsername(DbInfo.DATABASE_USERNAME);
		dataSource.setPassword(DbInfo.DATABASE_PASSWORD);
		dataSource.setMaxTotal(maxActive);
		dataSource.setMaxWaitMillis(maxWait);
		dataSource.setMaxIdle(maxIdle);
		ConnectionPool.dataSource = dataSource;
	}

	public static ConnectionPool getInstance() throws SQLException {
		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
		}
		return connectionPool;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
