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

package com.chrisuyehara.vista.rpc.procedures.XUS;

import com.chrisuyehara.vista.rpc.RPCString;
import com.chrisuyehara.vista.rpc.models.NewPerson;
import com.chrisuyehara.vista.rpc.procedures.RemoteProcedure;

public class GetUserInfo extends RemoteProcedure {

    public GetUserInfo() {
        super("XUS GET USER INFO");
    }

    public NewPerson getNewPerson() {
        String s[] = RPCString.split("\r\n", getResponse());
        return new NewPerson(s[1], Integer.parseInt(s[0]));
    }
}
