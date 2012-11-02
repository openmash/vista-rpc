/*
 * Copyright 2012 Chris Uyehara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chrisuyehara.vista.rpc.pool;

import com.chrisuyehara.vista.rpc.procedures.XWB.Disconnect;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class RPCConnectionPool {

    private static final RPCConnectionPool INSTANCE = new RPCConnectionPool();

    public static RPCConnectionPool getInstance() {
        return INSTANCE;
    }

    private RPCConnectionPool() {
    }

    private LinkedBlockingQueue<RPCConnection> pool = new LinkedBlockingQueue<RPCConnection>();
    private RPCConnectionFactory connectionFactory = null;

    private RPCConnectionPoolThread rpcConnectionPoolThread = null;
    private Thread poolThread = null;
    private int acquireTimeout = 60;

    /**
     * Acquire a connection from the pool
     *
     * @return
     */
    public synchronized RPCConnection acquire() {
        if (null == connectionFactory.getPoolSource()) {
            throw new IllegalStateException("RPCConnectionPoolSource must be set before calling acquire");
        }

        try {
            RPCConnection connection = pool.poll(acquireTimeout, TimeUnit.SECONDS);
            if (null == connection) {
                return null;
            }

            connection.setConnectionPool(this);
            return connection;
        } catch (InterruptedException e) {
            e.printStackTrace(); // TODO log appropriately
            return null;
        }
    }

    /**
     * Release (also sometimes called returning) the connection back to the pool
     *
     * @param connection
     */
    public void release(RPCConnection connection) {
        if (null == connection) {
            return;
        }

        if (pool.size() >= connectionFactory.getPoolSource().getMaxPoolSize()) {
            try {
                connection.call(new Disconnect());
                connection = null;
            } catch (IOException e) {
            }
        } else {
            // return connection back to the connectionPool
            pool.offer(connection);
        }
    }

    public RPCConnectionPoolSource getPoolSource() {
        return connectionFactory.getPoolSource();
    }

    public void setPoolSource(RPCConnectionPoolSource poolSource) {
        if (null == poolSource) {
            throw new NullPointerException("RPCConnectionPoolSource cannot be null");
        }

        // TODO implement code to teardown existing connections from a pool when a new source is given
        // TODO implement code to teardown pool threading

        connectionFactory = new RPCConnectionFactory(poolSource);

        rpcConnectionPoolThread = new RPCConnectionPoolThread(pool, connectionFactory);
        poolThread = new Thread(rpcConnectionPoolThread);
        poolThread.setName(RPCConnectionPoolThread.class.getName());
        poolThread.setDaemon(true);
        poolThread.start();
    }

    /**
     * Returns the number of connections available in the pool
     *
     * @return
     */
    public int getConnectionsAvailable() {
        return pool.size();
    }

    public int getAcquireTimeout() {
        return acquireTimeout;
    }

    public void setAcquireTimeout(int acquireTimeout) {
        this.acquireTimeout = acquireTimeout;
    }
}
