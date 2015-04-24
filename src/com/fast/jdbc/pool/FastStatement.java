/**
 * Fast
 * Create By baoliang.shen@pbin.net
 * 2015-4-13 上午10:18:37
 * 
 */
package com.fast.jdbc.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * TODO
 * 
 */
public class FastStatement extends PoolableWrapper implements Statement {

	private final Statement stmt;
	protected final FastConnection conn;

	public FastStatement(Statement stmt, FastConnection conn) {
		super(stmt);
		this.stmt = stmt;
		this.conn = conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeQuery(java.lang.String)
	 */
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rst = stmt.executeQuery(sql);
		FastResultSet frst = new FastResultSet(rst, this);
		return frst;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeUpdate(java.lang.String)
	 */
	@Override
	public int executeUpdate(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.executeUpdate(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#close()
	 */
	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		stmt.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getMaxFieldSize()
	 */
	@Override
	public int getMaxFieldSize() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getMaxFieldSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setMaxFieldSize(int)
	 */
	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		// TODO Auto-generated method stub
		stmt.setMaxFieldSize(max);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getMaxRows()
	 */
	@Override
	public int getMaxRows() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getMaxRows();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setMaxRows(int)
	 */
	@Override
	public void setMaxRows(int max) throws SQLException {
		// TODO Auto-generated method stub
		stmt.setMaxRows(max);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setEscapeProcessing(boolean)
	 */
	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		// TODO Auto-generated method stub
		stmt.setEscapeProcessing(enable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getQueryTimeout()
	 */
	@Override
	public int getQueryTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getQueryTimeout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setQueryTimeout(int)
	 */
	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		stmt.setQueryTimeout(seconds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#cancel()
	 */
	@Override
	public void cancel() throws SQLException {
		// TODO Auto-generated method stub
		stmt.cancel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getWarnings()
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getWarnings();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#clearWarnings()
	 */
	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub
		stmt.clearWarnings();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setCursorName(java.lang.String)
	 */
	@Override
	public void setCursorName(String name) throws SQLException {
		// TODO Auto-generated method stub
		stmt.setCursorName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#execute(java.lang.String)
	 */
	@Override
	public boolean execute(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.execute(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getResultSet()
	 */
	@Override
	public ResultSet getResultSet() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getResultSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getUpdateCount()
	 */
	@Override
	public int getUpdateCount() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getUpdateCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getMoreResults()
	 */
	@Override
	public boolean getMoreResults() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getMoreResults();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setFetchDirection(int)
	 */
	@Override
	public void setFetchDirection(int direction) throws SQLException {
		// TODO Auto-generated method stub
		stmt.setFetchDirection(direction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getFetchDirection()
	 */
	@Override
	public int getFetchDirection() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getFetchDirection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setFetchSize(int)
	 */
	@Override
	public void setFetchSize(int rows) throws SQLException {
		// TODO Auto-generated method stub
		stmt.setFetchSize(rows);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getFetchSize()
	 */
	@Override
	public int getFetchSize() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getFetchSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getResultSetConcurrency()
	 */
	@Override
	public int getResultSetConcurrency() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getResultSetConcurrency();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getResultSetType()
	 */
	@Override
	public int getResultSetType() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getResultSetType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#addBatch(java.lang.String)
	 */
	@Override
	public void addBatch(String sql) throws SQLException {
		// TODO Auto-generated method stub
		stmt.addBatch(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#clearBatch()
	 */
	@Override
	public void clearBatch() throws SQLException {
		// TODO Auto-generated method stub
		stmt.clearBatch();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeBatch()
	 */
	@Override
	public int[] executeBatch() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.executeBatch();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getConnection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getMoreResults(int)
	 */
	@Override
	public boolean getMoreResults(int current) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getMoreResults(current);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getGeneratedKeys()
	 */
	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getGeneratedKeys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeUpdate(java.lang.String, int)
	 */
	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.executeUpdate(sql, autoGeneratedKeys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeUpdate(java.lang.String, int[])
	 */
	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.executeUpdate(sql, columnIndexes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeUpdate(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.executeUpdate(sql, columnNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#execute(java.lang.String, int)
	 */
	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.execute(sql, autoGeneratedKeys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#execute(java.lang.String, int[])
	 */
	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.execute(sql, columnIndexes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#execute(java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		return stmt.execute(sql, columnNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getResultSetHoldability()
	 */
	@Override
	public int getResultSetHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.getResultSetHoldability();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#isClosed()
	 */
	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.isClosed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setPoolable(boolean)
	 */
	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		// TODO Auto-generated method stub
		stmt.setPoolable(poolable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#isPoolable()
	 */
	@Override
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.isPoolable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#closeOnCompletion()
	 */
	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		stmt.closeOnCompletion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#isCloseOnCompletion()
	 */
	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return stmt.isCloseOnCompletion();
	}

}
