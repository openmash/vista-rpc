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

package com.chrisuyehara.vista.rpc;

import junit.framework.Assert;
import org.junit.Test;
import com.chrisuyehara.vista.rpc.exceptions.LoginException;
import com.chrisuyehara.vista.rpc.parameters.GlobalParameter;
import com.chrisuyehara.vista.rpc.procedures.RemoteProcedure;

public class DebugParameters {

    @Test
    public void testLoginPass() {
        Client c = new Client("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "rfvtgbyhn!UJM7");
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        }

        RemoteProcedure r = new RemoteProcedure("XUS INTRO MSG");
        r.addParameter(new GlobalParameter("ABC", null));
        r.addParameter(new GlobalParameter("123", "ABC"));
        c.call(r);
    }

}
