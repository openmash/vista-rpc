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

import com.chrisuyehara.vista.rpc.parameters.LiteralParameter;
import com.chrisuyehara.vista.rpc.procedures.RemoteProcedure;

public class CreateContext extends RemoteProcedure {

    private String context;

    public CreateContext(String context) {
        super("XWB CREATE CONTEXT");
        this.context = context;
        addParameter(new LiteralParameter("1" + context + "1"));
    }

    public boolean isValidContext() {
        return "1".equals(getResponse());
    }

    public String getContext() {
        return context;
    }
}
