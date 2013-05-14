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
import com.chrisuyehara.vista.rpc.TestHarness;
import com.chrisuyehara.vista.rpc.models.SymbolTable;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Date: 10/26/12
 * Time: 11:24 AM
 */
public class SerializeTableTest {

    @Test
    public void serializeTest() throws Exception {
        RPCClient rpcClient = new RPCClient(TestHarness.VISTA_HOSTNAME, TestHarness.VISTA_PORT);

        rpcClient.login(TestHarness.ACCESS_CODE, TestHarness.VERIFY_CODE);
        rpcClient.context(CreateContext.CONTEXT_CPRS);

        SerializeTable serializeTable = new SerializeTable();
        rpcClient.call(serializeTable);

        SymbolTable symbolTable = serializeTable.getSymbolTable();

        Assert.assertTrue(symbolTable.size() > 0);
        Assert.assertTrue(!symbolTable.get("DUZ").isEmpty());
    }

}
