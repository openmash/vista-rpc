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

package com.chrisuyehara.vista.rpc.parameters;

import com.chrisuyehara.vista.rpc.RPCString;

public class GlobalParameter implements Parameter {

    public static final String BROKER_VALUE = "3";

    private RPCString rpcStringKey;
    private RPCString rpcStringValue;

    public GlobalParameter(String key, String value) {
        StringBuffer sbKey = new StringBuffer();

        try {
            sbKey.append(Integer.parseInt(key));
        } catch (NumberFormatException e) {
            sbKey.append(key);
            if (!key.startsWith("\"") || !key.endsWith("\"")) {
                if (!key.startsWith("\"")) {
                    sbKey.insert(0, "\"");
                }
                if (!key.endsWith("\"")) {
                    sbKey.append("\"");
                }
            }
        }

        if (null == value || "".equals(value)) {
            value = "\u0001";
        }

        rpcStringKey = new RPCString(sbKey.toString());
        rpcStringValue = new RPCString(value);
    }

    @Override
    public String toParameter() {
        StringBuffer sb = new StringBuffer();
        sb.append(BROKER_VALUE);
        sb.append(rpcStringKey.packString(PACK_STRING_LENGTH));
        sb.append(rpcStringValue.packString(PACK_STRING_LENGTH));
        sb.append("f");

        return sb.toString();
    }

}
