/**
 * Fast
 * Create By baoliang.shen@pbin.net
 * 2015-4-15 上午11:13:23
 * 
 */
package com.fast.jdbc.pool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.ConnectionEventListener;
import javax.sql.StatementEventListener;

/**
 * TODO
 * 
 */
public class FastConnectionHolder {

	private Connection connection;
	private FastDataSource dataSource;
	private List<ConnectionEventListener> connectionEventListeners = new ArrayList<ConnectionEventListener>();
	private List<StatementEventListener> statementEventListeners = new ArrayList<StatementEventListener>();

	public FastConnectionHolder(Connection connection, FastDataSource dataSource) {
		super();
		this.connection = connection;
		this.dataSource = dataSource;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public FastDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(FastDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<ConnectionEventListener> getConnectionEventListeners() {
		return connectionEventListeners;
	}

	public void setConnectionEventListeners(List<ConnectionEventListener> connectionEventListeners) {
		this.connectionEventListeners = connectionEventListeners;
	}

	public List<StatementEventListener> getStatementEventListeners() {
		return statementEventListeners;
	}

	public void setStatementEventListeners(List<StatementEventListener> statementEventListeners) {
		this.statementEventListeners = statementEventListeners;
	}

}
