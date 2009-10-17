/*
 * HA-JDBC: High-Availability JDBC
 * Copyright 2004-2009 Paul Ferraro
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.hajdbc.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.hajdbc.Database;
import net.sf.hajdbc.util.reflect.ProxyFactory;

/**
 * @author Paul Ferraro
 * @param <D> 
 */
public class StatementInvocationStrategy<Z, D extends Database<Z>> extends DriverWriteInvocationStrategy<Z, D, Connection, Statement, SQLException>
{
	private Connection connection;
	private TransactionContext<Z, D> transactionContext;
	
	/**
	 * @param connection
	 * @param transactionContext
	 */
	public StatementInvocationStrategy(Connection connection, TransactionContext<Z, D> transactionContext)
	{
		this.connection = connection;
		this.transactionContext = transactionContext;
	}
	
	/**
	 * @see net.sf.hajdbc.sql.DriverWriteInvocationStrategy#invoke(net.sf.hajdbc.sql.SQLProxy, net.sf.hajdbc.sql.Invoker)
	 */
	@Override
	public Statement invoke(SQLProxy<Z, D, Connection, SQLException> proxy, Invoker<Z, D, Connection, Statement, SQLException> invoker) throws SQLException
	{
		return ProxyFactory.createProxy(java.sql.Statement.class, new StatementInvocationHandler<Z, D>(this.connection, proxy, invoker, this.invokeAll(proxy, invoker), this.transactionContext, new FileSupportImpl<SQLException>(proxy.getExceptionFactory())));
	}
}