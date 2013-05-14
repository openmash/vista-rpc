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
