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
import com.chrisuyehara.vista.rpc.models.SymbolTable;
import com.chrisuyehara.vista.rpc.procedures.AbstractRemoteProcedure;
import com.chrisuyehara.vista.rpc.procedures.XWB.CreateContext;
import com.chrisuyehara.vista.rpc.procedures.XWB.DeserializeTable;
import com.chrisuyehara.vista.rpc.procedures.XWB.Disconnect;

import java.io.IOException;

public class RPCConnection {

    private RPCConnectionPool connectionPool = null;
    private RPCClient rpcClient = null;
    private SymbolTable symbolTable = null;

    public RPCConnection(RPCClient rpcClient, SymbolTable symbolTable, RPCConnectionPool connectionPool) {
        this.rpcClient = rpcClient;
        this.symbolTable = symbolTable;
        this.connectionPool = connectionPool;
    }

    public RPCConnection(RPCClient rpcClient, SymbolTable symbolTable) {
        this.rpcClient = rpcClient;
        this.symbolTable = symbolTable;
    }

    protected void call(AbstractRemoteProcedure remoteProcedure) throws IOException {
        if (null == remoteProcedure) {
            throw new NullPointerException("remoteProcedure cannot be null");
        }
        if (remoteProcedure instanceof Disconnect) {
            rpcClient.disconnect();
            rpcClient = null;
            symbolTable = null;
            connectionPool = null;
        } else {
            rpcClient.call(remoteProcedure);
        }
    }

    public void release() {
        if (false == isConnectionPooled()) {
            return;
        }

        try {
            DeserializeTable deserializeTable = new DeserializeTable(symbolTable);
            rpcClient.call(deserializeTable);

            rpcClient.context(CreateContext.CONTEXT_SIGNON);
        } catch (IOException e) {

        }

        connectionPool.release(this);
    }

    public boolean isConnectionPooled() {
        return null != connectionPool;
    }

    public RPCConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(RPCConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public RPCClient getRpcClient() {
        return rpcClient;
    }
}
