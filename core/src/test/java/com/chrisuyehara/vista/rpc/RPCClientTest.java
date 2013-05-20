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

import com.chrisuyehara.vista.rpc.exceptions.ConnectException;
import com.chrisuyehara.vista.rpc.exceptions.LoginException;
import com.chrisuyehara.vista.rpc.models.NewPerson;
import com.chrisuyehara.vista.rpc.procedures.XUS.GetUserInfo;
import com.chrisuyehara.vista.rpc.procedures.XWB.CreateContext;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RPCClientTest {

    @Test
    public void testConnect() {
    	RPCConfiguration cfg = RPCConfiguration.instance();
        RPCClient c = new RPCClient(cfg.getHost(), cfg.getPort());
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
    	RPCConfiguration cfg = RPCConfiguration.instance();
        RPCClient c = new RPCClient(cfg.getHost(), cfg.getPort());
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
    	RPCConfiguration cfg = RPCConfiguration.instance();
        RPCClient c = new RPCClient(cfg.getHost(), cfg.getPort());

        try {
            c.login(cfg.getAccess(), cfg.getVerify());
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }
    }

    @Test
    public void testLoginFail() {
    	RPCConfiguration cfg = RPCConfiguration.instance();
        RPCClient c = new RPCClient(cfg.getHost(), cfg.getPort());

        try {
            c.login("dummy", "invalid");
        } catch (LoginException e) {
            return;
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }

        Assert.fail("Login should HAVE failed.");
    }

    @Test
    public void testLoginPass2() {
    	RPCConfiguration cfg = RPCConfiguration.instance();
        RPCClient c = new RPCClient(cfg.getHost(), cfg.getPort());

        try {
            c.login(cfg.getAccess(), cfg.getVerify());
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
    	RPCConfiguration cfg = RPCConfiguration.instance();
        RPCClient c = new RPCClient(cfg.getHost(), cfg.getPort());

        try {
            c.login(cfg.getAccess(), cfg.getVerify());
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }

        try {
            c.context(CreateContext.CONTEXT_CPRS);
        } catch (IOException e) {
            Assert.fail("Socket IO shouldn't have failed");
        }
    }

}
