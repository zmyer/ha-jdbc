/*
 * HA-JDBC: High-Availability JDBC
 * Copyright (c) 2004-2007 Paul Ferraro
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
package net.sf.hajdbc.distributable;

import java.util.Map;
import java.util.concurrent.locks.Lock;

import org.jgroups.Address;

import net.sf.hajdbc.LockManager;

/**
 * @author Paul Ferraro
 * @since 2.0
 */
public class ReleaseLockDecree extends AbstractLockDecree
{
	private static final long serialVersionUID = -2410005124267913965L;

	public ReleaseLockDecree()
	{
		super();
	}

	public ReleaseLockDecree(String id, Address address)
	{
		super(id, address);
	}

	/**
	 * @see net.sf.hajdbc.distributable.AbstractLockDecree#prepare(net.sf.hajdbc.LockManager)
	 */
	@Override
	public boolean prepare(LockManager lockManager, Map<LockDecree, Lock> lockMap)
	{
		return true;
	}

	/**
	 * @see net.sf.hajdbc.distributable.AbstractLockDecree#commit(net.sf.hajdbc.LockManager)
	 */
	@Override
	public boolean commit(Map<LockDecree, Lock> lockMap)
	{
		this.unlock(lockMap);
		
		return true;
	}

	/**
	 * @see net.sf.hajdbc.distributable.AbstractLockDecree#abort(net.sf.hajdbc.LockManager)
	 */
	@Override
	public void abort(Map<LockDecree, Lock> lockMap)
	{
		// Do nothing
	}
}