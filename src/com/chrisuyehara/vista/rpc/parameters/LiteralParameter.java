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

public class LiteralParameter implements Parameter {

    public static final String BROKER_VALUE = "0";

    private RPCString rpcString;

    public LiteralParameter(String value) {
        rpcString = new RPCString(value);
    }

    @Override
    public String toParameter() {
        StringBuffer sb = new StringBuffer();
        sb.append(BROKER_VALUE);
        sb.append(rpcString.packString(PACK_STRING_LENGTH));
        sb.append("f");

        return sb.toString();
    }
}
