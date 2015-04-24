/**
 * Fast
 * Create By baoliang.shen@pbin.net
 * 2015-4-13 上午9:45:14
 * 
 */
package com.fast.jdbc.pool;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Wrapper;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * TODO
 * 
 */
public class FastDataSource implements Serializable, Closeable, DataSource, Wrapper {

	Logger logger = Logger.getLogger(this.getClass().toString());
	private static final long serialVersionUID = -7373507657980197127L;
	/********************************************************************************/
	private ReentrantLock lock;
	private Condition wait;
	/********************************************************************************/
	private PrintWriter out = new PrintWriter(System.out);
	private Vector<FastConnectionHolder> connectionHolders = new Vector<FastConnectionHolder>();
	private Driver driver;
	private Properties properties;
	private int poolCount = 0;
	/********************************************************************************/
	private int initialSize = 0;
	private int maxActive = 8;
	private int minIdle = 0;
	private int maxIdle = 8;
	private long maxWait = -1;
	private int activeCount = 0;
	private int maxSize = 0;

	/********************************************************************************/
	private String username;
	private String password;
	private String jdbcUrl;
	private String driverClass;

	/********************************************************************************/
	private boolean inited = false;

	public FastDataSource() {
		lock = new ReentrantLock(false);
		wait = lock.newCondition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		this.out = out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		DriverManager.setLoginTimeout(seconds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getLoginTimeout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getParentLogger()
	 */
	@Override
	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		throw new SQLFeatureNotSupportedException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public FastConnection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		initFast();
		FastConnectionHolder holder = getLastConnectionHolder();
		FastConnection conn = new FastConnection(holder);
		return conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public FastConnection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		FastConnection conn = new FastConnection(getLastConnectionHolder());
		return conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		for (int i = initialSize; i > 0; i--) {
			FastConnectionHolder holder = connectionHolders.get(i);
			Connection conn = holder.getConnection();
			try {
				logger.info("[DataSource-" + i + " ] close");
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connectionHolders.remove(i);
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Connection createConnetion(String url, Properties info) throws SQLException {
		Connection conn = getDriver().connect(url, info);
		return conn;
	}

	public void initFast() throws SQLException {
		if (inited) {
			return;
		}
		try {
			this.driver = (Driver) Class.forName(driverClass).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (initialSize <= 0) {
			throw new IllegalArgumentException("Invalid initialSize");
		}
		if (maxActive <= 0) {
			throw new IllegalArgumentException("Invalid maxActive");
		}
		connectionHolders.setSize(maxActive);
		for (int i = 0; i < initialSize; i++) {
			Connection conn = createConnetion(jdbcUrl, properties);
			FastConnectionHolder holder = new FastConnectionHolder(conn, this);
			connectionHolders.add(holder);
			logger.info("[DataSource-" + i + " ] inited");
			poolCount++;
		}
		CreateConnectionThread thread = new CreateConnectionThread();
		thread.start();
		inited = true;
	}

	public FastConnectionHolder getLastConnectionHolder() {
		poolCount--;
		FastConnectionHolder obj = connectionHolders.get(poolCount);
		return obj;
	}

	class CreateConnectionThread extends Thread {

		public CreateConnectionThread() {
			super();
			setDaemon(true);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (;;) {
				try {
					lock.lockInterruptibly();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					break;
				}

				try {
					if (activeCount > maxSize) {
						logger.info("Current Pool has " + activeCount + "  CreateConnectionThread has wait..");
						wait.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

				try {
					Connection conn = createConnetion(jdbcUrl, getProperties());
					if (conn == null) {
						continue;
					}
					FastConnectionHolder holder = new FastConnectionHolder(conn, FastDataSource.this);
					connectionHolders.add(holder);
					poolCount++;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		if (iface == null) {
			return null;
		}

		if (iface.isInstance(this)) {
			return (T) this;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return iface != null && iface.isInstance(this);
	}

}
