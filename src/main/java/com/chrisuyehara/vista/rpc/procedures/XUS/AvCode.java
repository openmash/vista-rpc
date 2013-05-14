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
