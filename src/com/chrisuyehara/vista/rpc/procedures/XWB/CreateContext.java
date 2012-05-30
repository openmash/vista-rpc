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
