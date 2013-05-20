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

import org.junit.Assert;
import org.junit.Test;

public class RPCStringTest {

    @Test
    public void testPackLiteralPass() {
        String s = RPCString.packLiteral("TEST", 3);

        Assert.assertEquals("004TEST", s);
    }

    @Test
    public void testPackVariablePass1() {
        String s = RPCString.packVariable("XWB");

        Assert.assertEquals("|\u0003XWB", s);
    }

    @Test
    public void testPackVariablePass2() {
        String s = RPCString.packVariable("XWBB");

        Assert.assertEquals("|\u0004XWBB", s);
    }

    @Test
    public void testPackStringPass1() {
        String s = RPCString.packString("XWB");

        Assert.assertEquals("\u0003XWB", s);
    }

    @Test
    public void testPackStringPass2() {
        String s = RPCString.packString("XWB", 3);

        Assert.assertEquals("003XWB", s);
    }
}
