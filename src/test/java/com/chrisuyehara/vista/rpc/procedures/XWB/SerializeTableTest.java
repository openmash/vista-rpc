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

import org.junit.Assert;
import org.junit.Test;

import com.chrisuyehara.vista.rpc.RPCClient;
import com.chrisuyehara.vista.rpc.RPCConfiguration;
import com.chrisuyehara.vista.rpc.models.SymbolTable;

/**
 * Date: 10/26/12
 * Time: 11:24 AM
 */
public class SerializeTableTest {

    @Test
    public void serializeTest() throws Exception {
    	RPCConfiguration cfg = RPCConfiguration.instance();
        RPCClient rpcClient = new RPCClient(cfg.getHost(), cfg.getPort());
        rpcClient.login(cfg.getAccess(), cfg.getVerify());
        rpcClient.context(CreateContext.CONTEXT_CPRS);

        SerializeTable serializeTable = new SerializeTable();
        rpcClient.call(serializeTable);

        SymbolTable symbolTable = serializeTable.getSymbolTable();

        Assert.assertTrue(symbolTable.size() > 0);
        Assert.assertTrue(!symbolTable.get("DUZ").isEmpty());
    }

}
