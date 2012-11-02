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

import com.chrisuyehara.vista.rpc.RPCClient;
import com.chrisuyehara.vista.rpc.exceptions.ConnectException;
import com.chrisuyehara.vista.rpc.exceptions.LoginException;
import com.chrisuyehara.vista.rpc.models.SymbolTable;
import com.chrisuyehara.vista.rpc.procedures.XWB.SerializeTable;

import java.io.IOException;
import java.util.concurrent.Callable;

public class RPCConnectionFactory implements Callable<RPCConnection> {

    private RPCConnectionPoolSource poolSource;

    public RPCConnectionFactory(RPCConnectionPoolSource poolSource) {
        if (null == poolSource) {
            throw new NullPointerException("RPCConnectionFactory constructor arguments cannot be null");
        }
        this.poolSource = poolSource;
    }

    /**
     * Builds an RPCConnection that is not associated to a specific pool; the implementor must add the connection
     * to the pool.
     * @return
     * @throws IOException
     * @throws ConnectException
     * @throws LoginException
     */
    public RPCConnection build() throws IOException, ConnectException, LoginException {
        RPCClient rpcClient = new RPCClient(poolSource.getHostName(), poolSource.getPort());
        rpcClient.connect();

        SerializeTable serializeTable = new SerializeTable();
        rpcClient.call(serializeTable);

        SymbolTable symbolTable = serializeTable.getSymbolTable();
        rpcClient.login(poolSource.getAccessCode(), poolSource.getVerifyCode());

        RPCConnection connection = new RPCConnection(rpcClient, symbolTable);

        return connection;
    }

    @Override
    public RPCConnection call() throws Exception {
        return build();
    }

    public RPCConnectionPoolSource getPoolSource() {
        return poolSource;
    }
}
