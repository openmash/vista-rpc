/*
 * Java VistA RPC Client
 * Copyright (C) 2012 Chris Uyehara / chris.uyehara@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
