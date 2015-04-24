/**
 * Fast
 * Create By baoliang.shen@pbin.net
 * 2015-4-14 上午10:06:50
 * 
 */
package com.fast.jdbc.pool;

import java.sql.SQLException;
import java.sql.Wrapper;

/**
 * TODO
 * 
 */
public class PoolableWrapper implements Wrapper {

	private final Wrapper wrapper;

	public PoolableWrapper(Wrapper wraaper) {
		this.wrapper = wraaper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		if (null == wrapper) {
			// Best to log error.
			return null;
		}

		if (iface == null) {
			return null;
		}

		if (iface == wrapper.getClass()) {
			return (T) wrapper;
		}

		if (iface == this.getClass()) {
			return (T) this;
		}

		return wrapper.unwrap(iface);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		if (null == wrapper) {
			// Best to log error.
			return false;
		}

		if (iface == null) {
			return false;
		}

		if (iface == wrapper.getClass()) {
			return true;
		}

		if (iface == this.getClass()) {
			return true;
		}

		return wrapper.isWrapperFor(iface);
	}

}
