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

package com.chrisuyehara.vista.rpc.procedures.XWB;

import com.chrisuyehara.vista.rpc.RPCClient;
import com.chrisuyehara.vista.rpc.RPCConfiguration;
import com.chrisuyehara.vista.rpc.models.SymbolTable;
import org.junit.Test;

/**
 * Date: 10/26/12
 * Time: 11:45 AM
 */
public class DeserializeTableTest {

    @Test
    public void deserializeTest() throws Exception {
    	RPCConfiguration cfg = RPCConfiguration.instance();
        RPCClient rpcClient = new RPCClient(cfg.getHost(), cfg.getPort());
        rpcClient.login(cfg.getAccess(), cfg.getVerify());
        rpcClient.context(CreateContext.CONTEXT_CPRS);

        SymbolTable symbolTable = new SymbolTable();
        symbolTable.put("DT", "123");

        DeserializeTable remoteProcedure = new DeserializeTable(symbolTable);

        rpcClient.call(remoteProcedure);

        System.out.println(remoteProcedure.getResponse());
    }
}
