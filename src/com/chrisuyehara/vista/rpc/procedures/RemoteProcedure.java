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

package com.chrisuyehara.vista.rpc.procedures;

import com.chrisuyehara.vista.rpc.RPCString;
import com.chrisuyehara.vista.rpc.parameters.Parameter;

import java.util.ArrayList;
import java.util.List;

public class RemoteProcedure extends AbstractRemoteProcedure {

    public static final String RPC_VERSION = "2.0";

    private String rpcName;

    private List<Parameter> parameterList;

    public RemoteProcedure(String rpcName) {
        this.rpcName = rpcName;
        parameterList = new ArrayList<Parameter>();
    }

    public String getRpcName() {
        return rpcName;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void addParameter(Parameter p) {
        parameterList.add(p);
    }

    @Override
    public final String toProcedure() {
        StringBuffer sb = new StringBuffer("5");

        for (Parameter p : parameterList) {
            sb.append(p.toParameter());
        }

        if (sb.toString().equalsIgnoreCase("5")) {
            sb.append("4f");
        }

        sb.append((char) 4);

        StringBuffer header = new StringBuffer();
        header.append("[XWB]11302");
        header.append(RPCString.packString(RPC_VERSION));
        header.append(RPCString.packString(rpcName));

        sb.insert(0, header.toString());

        return sb.toString();
    }
}
