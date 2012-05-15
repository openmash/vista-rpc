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
