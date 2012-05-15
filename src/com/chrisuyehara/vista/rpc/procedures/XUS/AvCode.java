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

import com.chrisuyehara.vista.rpc.parameters.LiteralParameter;
import com.chrisuyehara.vista.rpc.procedures.RemoteProcedure;

public class AvCode extends RemoteProcedure {

    public AvCode(String accessCode, String verifyCode) {
        super("XUS AV CODE");

        LiteralParameter parameter = new LiteralParameter("1" + accessCode + ";" + verifyCode + "1");
        addParameter(parameter);
    }

    public boolean isLoggedIn() {
        return !(null == getResponse() || getResponse().contains("Not a valid ACCESS CODE/VERIFY CODE pair"));
    }
}
