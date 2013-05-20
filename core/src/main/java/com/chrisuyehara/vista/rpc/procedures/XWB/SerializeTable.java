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

import com.chrisuyehara.vista.rpc.RPCString;
import com.chrisuyehara.vista.rpc.models.SymbolTable;
import com.chrisuyehara.vista.rpc.procedures.RemoteProcedure;

/**
 * Date: 10/25/12
 * Time: 7:39 AM
 */
public class SerializeTable extends RemoteProcedure {

    public static final String RECORD_SEPARATOR = new String(new byte[]{0x1E});
    public static final String UNIT_SEPARATOR = new String(new byte[]{0x1F});

    public SerializeTable() {
        super("XWB SERIALIZE");
    }

    public SymbolTable getSymbolTable() {
        SymbolTable symbolTable = new SymbolTable();

        String[] splitSymbols = RPCString.split(UNIT_SEPARATOR, getResponse());

        for (String symbol : splitSymbols) {
            if (null == symbol || symbol.isEmpty()) {
                continue;
            }

            String[] keyValue = RPCString.split(RECORD_SEPARATOR, symbol);
            if (2 != keyValue.length) {
                continue;
            }

            String key = keyValue[0];
            String value = keyValue[1];

            symbolTable.put(key, value);
        }

        return symbolTable;
    }
}
