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
import com.chrisuyehara.vista.rpc.exceptions.ConnectException;
import com.chrisuyehara.vista.rpc.exceptions.LoginException;
import com.chrisuyehara.vista.rpc.models.NewPerson;
import com.chrisuyehara.vista.rpc.procedures.XUS.GetUserInfo;

import java.io.IOException;
import java.util.Timer;

public class RPCClientTest {

    @Test
    public void testConnect() {
        RPCClient c = new RPCClient("192.168.10.3", 9200);
        try {
            c.connect();
        } catch (ConnectException e) {
            Assert.fail("Connect failed...");
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }

        Assert.assertTrue(c.isConnected());
    }

    @Test
    public void testDisconnect() {
        RPCClient c = new RPCClient("192.168.10.3", 9200);
        try {
            c.connect();
        } catch (ConnectException e) {
            Assert.fail("Connect failed...");
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }
        c.disconnect();

        Assert.assertFalse(c.isConnected());
    }

    @Test
    public void testLoginPass() {
        RPCClient c = new RPCClient("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "rfvtgbyhn!UJM7");
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }
    }

    @Test
    public void testLoginFail() {
        RPCClient c = new RPCClient("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "abc");
        } catch (LoginException e) {
            return;
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }

        Assert.fail("Login should HAVE failed.");
    }

    @Test
    public void testLoginPass2() {
        RPCClient c = new RPCClient("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "rfvtgbyhn!UJM7");
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }

        GetUserInfo userInfo = new GetUserInfo();
        try {
            c.call(userInfo);
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }

        NewPerson newPerson = userInfo.getNewPerson();
        Assert.assertTrue(newPerson.getFullName().length() > 0);
        Assert.assertTrue(newPerson.getIen() > 0);
    }

    @Test
    public void testContextPass() {
        RPCClient c = new RPCClient("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "rfvtgbyhn!UJM7");
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }

        try {
            c.context("OR CPRS GUI CHART");
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }
    }

}
