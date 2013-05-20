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

import com.chrisuyehara.vista.rpc.procedures.XWB.ImHere;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Date: 10/31/12
 * Time: 4:14 PM
 */
public class RPCConnectionPoolTest {

    @Test
    public void testRPCConnectionPool() throws Exception {
        RPCConnectionPool connectionPool = RPCConnectionPool.getInstance();
        RPCConnectionPoolSource poolSource = new RPCConnectionPoolSource();

        int initialPoolSize = 2;
        poolSource.setInitialPoolSize(initialPoolSize);

        connectionPool.setPoolSource(poolSource);

        Thread.sleep(1000 * 10);

        Assert.assertTrue(initialPoolSize == connectionPool.getConnectionsAvailable());

        RPCConnection connection = connectionPool.acquire();

        Assert.assertTrue(initialPoolSize - 1 == connectionPool.getConnectionsAvailable());

        ImHere here = new ImHere();
        connection.call(here);

        Assert.assertTrue(here.isAlive());

        connection.release();
        Assert.assertTrue(initialPoolSize == connectionPool.getConnectionsAvailable());
    }

    @Test
    public void testPoolExpansion() throws Exception {
        RPCConnectionPool connectionPool = RPCConnectionPool.getInstance();
        RPCConnectionPoolSource poolSource = new RPCConnectionPoolSource();

        System.out.println("Initial: " + poolSource.getInitialPoolSize());
        System.out.println("Expansion: " + poolSource.getExpandPoolSize());
        System.out.println("Max: " + poolSource.getMaxPoolSize());
        System.out.println("Min: " + poolSource.getMinPoolSize());
        System.out.println();

        connectionPool.setPoolSource(poolSource);

        ArrayList<RPCConnection> connections = new ArrayList<RPCConnection>();

        for (int i = 0; i < 100; i++) {
            RPCConnection connection = connectionPool.acquire();
            if (null != connection) {
                System.out.println("Connection acquired");
            } else {
                System.out.println("Connection acquired FAILED!");
            }
            connections.add(connection);
            System.out.println("pool availability: " + connectionPool.getConnectionsAvailable());
            System.out.println();
            Thread.sleep(1000 * 5);
        }

    }

    @Test
    public void testSetPoolSource() throws Exception {
        RPCConnectionPoolSource poolSource = new RPCConnectionPoolSource();

        RPCConnectionPool instance = RPCConnectionPool.getInstance();
        instance.setPoolSource(poolSource);

        Thread.sleep(1000 * 10);
        System.out.println("Connections available: " + instance.getConnectionsAvailable());
        Assert.assertTrue(instance.getConnectionsAvailable() > 0);
    }
}
