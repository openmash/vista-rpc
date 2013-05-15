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

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.chrisuyehara.vista.rpc.parameters.ListParameter;
import com.chrisuyehara.vista.rpc.parameters.LiteralParameter;

public class RemoteProcedureTest {

    @Test
    public void test_RPC_TIU_UPDATE_RECORD() {
        RemoteProcedure r = new RemoteProcedure("TIU UPDATE RECORD");
        LiteralParameter literal = new LiteralParameter("3");

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put(".01", "20");
        params.put("1201", "1");
        params.put("1301", "3120513.1724");
        params.put("1701", "");

        ListParameter list = new ListParameter(params);

        r.addParameter(literal);
        r.addParameter(list);

        System.out.println(r.toProcedure());
        char[] chars = r.toProcedure().toCharArray();
        for (char c : chars) {
            System.out.print(Integer.toHexString((int) c) + " ");
        }
    }
}
