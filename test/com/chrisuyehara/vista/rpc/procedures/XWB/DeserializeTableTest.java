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
import com.chrisuyehara.vista.rpc.parameters.KeyValuePair;
import com.chrisuyehara.vista.rpc.parameters.ListParameter;
import com.chrisuyehara.vista.rpc.parameters.OrderedList;
import com.chrisuyehara.vista.rpc.procedures.RemoteProcedure;
import com.chrisuyehara.vista.rpc.procedures.XWB.CreateContext;
import org.junit.Test;

/**
 * Date: 10/26/12
 * Time: 11:45 AM
 */
public class DeserializeTableTest {

    @Test
    public void deserializeTest() throws Exception {
        RPCClient rpcClient = new RPCClient(TestHarness.VISTA_HOSTNAME, TestHarness.VISTA_PORT);

        rpcClient.login(TestHarness.ACCESS_CODE, TestHarness.VERIFY_CODE);
        rpcClient.context(CreateContext.CONTEXT_CPRS);

        RemoteProcedure remoteProcedure = new RemoteProcedure("MDWS DESERIALIZE TABLE");

        OrderedList orderedList = new OrderedList();

        orderedList.add(new KeyValuePair("\"2\"","DT=3121024"));
        orderedList.add(new KeyValuePair("\"3\"","%DT=\"T\""));

        ListParameter listParameter = new ListParameter(orderedList);
        remoteProcedure.addParameter(listParameter);

        rpcClient.call(remoteProcedure);

        System.out.println(remoteProcedure.getResponse());
    }
}
