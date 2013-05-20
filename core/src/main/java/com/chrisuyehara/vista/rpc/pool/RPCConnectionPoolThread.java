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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Date: 11/1/12
 * Time: 9:13 AM
 */
public class RPCConnectionPoolThread implements Runnable {

    private LinkedBlockingQueue<RPCConnection> pool = null;
    private RPCConnectionFactory connectionFactory = null;
    private RPCConnectionPoolSource poolSource = null;

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static final LinkedBlockingQueue<Integer> QUEUE = new LinkedBlockingQueue<Integer>();
    private static final List<Future<RPCConnection>> FUTURES = new ArrayList<Future<RPCConnection>>();

    public RPCConnectionPoolThread(LinkedBlockingQueue<RPCConnection> pool, RPCConnectionFactory connectionFactory) {
        if (null == pool || null == connectionFactory) {
            throw new NullPointerException("pool and connectionFactory cannot be null");
        }

        this.pool = pool;
        this.connectionFactory = connectionFactory;
        this.poolSource = connectionFactory.getPoolSource();

        QUEUE.offer(poolSource.getInitialPoolSize());
    }

    public void run() {
        Callable<RPCConnection> worker = connectionFactory;

        while (true) {
            try {
                Integer connectionsToBuild = QUEUE.poll(1, TimeUnit.SECONDS);

                if (null == connectionsToBuild) {
                    if (pool.size() < poolSource.getMinPoolSize() &&
                            getConnectionsToBuild() < poolSource.getMinPoolSize()) {
                        connectionsToBuild = poolSource.getExpandPoolSize();
                        System.out.println("pool low: " + poolSource.getExpandPoolSize());
                    }
                }

                if (null != connectionsToBuild) {
                    System.out.println("build: " + connectionsToBuild);
                    for (int i = 0; i < connectionsToBuild; i++) {
                        Future<RPCConnection> future = executorService.submit(worker);
                        FUTURES.add(future);
                    }
                }

                Iterator<Future<RPCConnection>> iterator = FUTURES.iterator();
                while (iterator.hasNext()) {
                    Future<RPCConnection> future = iterator.next();
                    if (future.isDone()) {
                        if (!future.isCancelled()) {

                        }
                        pool.offer(future.get());
                        iterator.remove();
                    } else {
                        if (future.isCancelled()) {
                            iterator.remove();
                        } else {
                            // TODO log unknown condition
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace(); // TODO log exception
            }
        }

    }

    public int getConnectionsToBuild() {
        int connections = 0;

        Iterator<Integer> iterator = QUEUE.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            connections = connections + integer;
        }

        return (connections + FUTURES.size());
    }

}
