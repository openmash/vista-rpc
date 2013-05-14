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

package com.chrisuyehara.vista.rpc.procedures;

import org.junit.Test;
import com.chrisuyehara.vista.rpc.parameters.KeyValuePair;
import com.chrisuyehara.vista.rpc.parameters.ListParameter;
import com.chrisuyehara.vista.rpc.parameters.LiteralParameter;
import com.chrisuyehara.vista.rpc.parameters.OrderedList;

public class RemoteProcedureTest {

    @Test
    public void test_RPC_TIU_UPDATE_RECORD() {
        RemoteProcedure r = new RemoteProcedure("TIU UPDATE RECORD");
        LiteralParameter literal = new LiteralParameter("3");

        OrderedList o = new OrderedList();
        o.add(new KeyValuePair(".01", "20"));
        o.add(new KeyValuePair("1201", "1"));
        o.add(new KeyValuePair("1301", "3120513.1724"));
        o.add(new KeyValuePair("1701", ""));

        ListParameter list = new ListParameter(o);

        r.addParameter(literal);
        r.addParameter(list);

        System.out.println(r.toProcedure());
        char[] chars = r.toProcedure().toCharArray();
        for (char c : chars) {
            System.out.print(Integer.toHexString((int) c) + " ");
        }
    }
}
