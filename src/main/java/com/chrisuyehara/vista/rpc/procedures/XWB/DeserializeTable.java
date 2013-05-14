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

import com.chrisuyehara.vista.rpc.models.SymbolTable;
import com.chrisuyehara.vista.rpc.parameters.ListParameter;
import com.chrisuyehara.vista.rpc.parameters.OrderedList;
import com.chrisuyehara.vista.rpc.procedures.RemoteProcedure;

/**
 * Date: 10/25/12
 * Time: 8:55 AM
 */
public class DeserializeTable extends RemoteProcedure {

    public static final String RECORD_SEPARATOR = new String(new byte[]{0x1E});

    public DeserializeTable(SymbolTable symbolTable) {
        super("XWB DESERIALIZE");

        OrderedList rpcList = new OrderedList();

        int i = 1;
        for (String key : symbolTable.keySet()) {
            String value = key + RECORD_SEPARATOR + symbolTable.get(key);
            rpcList.add(Integer.toString(i), value);
            i++;
        }


        addParameter(new ListParameter(rpcList));
    }


}
