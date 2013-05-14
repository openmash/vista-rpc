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

import com.chrisuyehara.vista.rpc.procedures.XUS.GetUserInfo;
import com.chrisuyehara.vista.rpc.procedures.XWB.ImHere;
import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Date: 9/17/12
 * Time: 10:15 AM
 */
public class RPCTimerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCTimerTest.class);

    @Test
    public void testTimerLong() throws Exception {
        RPCClient rpcClient = new RPCClient(TestHarness.VISTA_HOSTNAME, TestHarness.VISTA_PORT);

        rpcClient.login(TestHarness.ACCESS_CODE, TestHarness.VERIFY_CODE);

        Thread.sleep(1000 * 60 * 10);

        Assert.assertTrue(rpcClient.isConnected());

        GetUserInfo userInfo = new GetUserInfo();
        rpcClient.call(userInfo);
        LOGGER.info(userInfo.getResponse());
    }

    @Test
    public void testTimerShort() throws Exception {
        RPCClient rpcClient = new RPCClient(TestHarness.VISTA_HOSTNAME, TestHarness.VISTA_PORT);

        rpcClient.login(TestHarness.ACCESS_CODE, TestHarness.VERIFY_CODE);

        Thread.sleep(1000 * 60 * 2);

        Assert.assertTrue(rpcClient.isConnected());

        GetUserInfo userInfo = new GetUserInfo();
        rpcClient.call(userInfo);
        LOGGER.info(userInfo.getResponse());
    }

    @Test
    public void testTimeout() throws Exception {
        RPCSocket rpcSocket = new RPCSocket();
        rpcSocket.connect(TestHarness.VISTA_HOSTNAME, TestHarness.VISTA_PORT);

        Assert.assertTrue(rpcSocket.isConnected());

        Thread.sleep(1000 * 60 * 5);

        ImHere rp = new ImHere();
        try {
            rpcSocket.call(rp);
        } catch (IOException e) {
            /* mock the rpcClient in that we'll disconnect the rpcSocket when we catch an IOException */
            rpcSocket.disconnect();
        }

        LOGGER.info("Response -> " + rp.getResponse());
        Assert.assertFalse(rpcSocket.isConnected());
        Assert.assertFalse(rp.isAlive());
    }
}