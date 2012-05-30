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
import com.chrisuyehara.vista.rpc.procedures.RawRemoteProcedure;

public class Connect extends RawRemoteProcedure {

    public Connect(String ipAddress, String hostName) {
        StringBuffer sb = new StringBuffer();
        sb.append("[XWB]10304\nTCPConnect50");
        sb.append(RPCString.packString(ipAddress, 3));
        sb.append("f0");
        sb.append(RPCString.packString("0", 3));
        sb.append("f0");
        sb.append(RPCString.packString(hostName, 3));
        sb.append("f");
        sb.append((char) 4);

        payload = sb.toString();
    }

    public boolean isConnectionAccepted() {
        return "accept".equalsIgnoreCase(getResponse());
    }
}
