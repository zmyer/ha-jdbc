/*
 * HA-JDBC: High-Availability JDBC
 * Copyright (c) 2004-2006 Paul Ferraro
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation; either version 2.1 of the License, or (at your 
 * option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Contact: ferraro@users.sourceforge.net
 */
package net.sf.hajdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author  Paul Ferraro
 * @version $Revision$
 * @param <T> 
 * @since   1.0
 */
public interface Database<T> extends ActiveDatabaseMBean, Comparable<Database>
{
	/**
	 * Connects to the database using the specified connection factory.
	 * @param connectionFactory a factory object for creating connections
	 * @return a database connection
	 * @throws SQLException if connection fails
	 */
	public Connection connect(T connectionFactory) throws SQLException;
	
	/**
	 * Factory method for creating a connection factory object for this database.
	 * @return a connection factory object
	 * @throws IllegalArgumentException if connection factory could not be created
	 */
	public T createConnectionFactory();
	
	/**
	 * @return the class implemented by connection factory objects for this database.
	 */
	public Class<T> getConnectionFactoryClass();
	
	public Class<? extends ActiveDatabaseMBean> getActiveMBeanClass();
	
	public Class<? extends InactiveDatabaseMBean> getInactiveMBeanClass();
	
	public boolean isDirty();
	
	public void clean();
}
