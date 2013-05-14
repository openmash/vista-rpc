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

package com.chrisuyehara.vista.rpc.pool;

import com.chrisuyehara.vista.rpc.TestHarness;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Date: 11/1/12
 * Time: 1:07 PM
 */
public class RPCConnectionPoolSourceTest {

    private static RPCConnectionPoolSource poolSource;

    @BeforeClass
    public static void beforeClass() {
        poolSource = new RPCConnectionPoolSource(TestHarness.VISTA_HOSTNAME, TestHarness.VISTA_PORT, TestHarness.ACCESS_CODE, TestHarness.VERIFY_CODE);
        poolSource.setInitialPoolSize(4);
        poolSource.setExpandPoolSize(2);
        poolSource.setMaxPoolSize(5);
    }

    @Test
    public void testGetHostName() throws Exception {
        Assert.assertTrue(poolSource.getHostName().equals(TestHarness.VISTA_HOSTNAME));
    }

    @Test
    public void testSetHostName() throws Exception {
        String hostName = poolSource.getHostName();
        String newHostName = "TEST.VISTAEHR.COM";
        poolSource.setHostName(newHostName);
        Assert.assertTrue(newHostName.equals(poolSource.getHostName()));
        poolSource.setHostName(hostName);
        Assert.assertTrue(TestHarness.VISTA_HOSTNAME.equals(hostName));
    }

    @Test
    public void testGetPort() throws Exception {
        Assert.assertTrue(poolSource.getPort() == TestHarness.VISTA_PORT);
    }

    @Test
    public void testSetPort() throws Exception {
        int port = poolSource.getPort();
        int newPort = 1234;
        poolSource.setPort(newPort);
        Assert.assertTrue(newPort == poolSource.getPort());
        poolSource.setPort(port);
        Assert.assertTrue(TestHarness.VISTA_PORT == port);
    }

    @Test
    public void testGetAccessCode() throws Exception {
        Assert.assertTrue(poolSource.getAccessCode().equals(TestHarness.ACCESS_CODE));
    }

    @Test
    public void testSetAccessCode() throws Exception {
        String accessCode = poolSource.getAccessCode();
        String newAccessCode = "abc123";
        poolSource.setAccessCode(newAccessCode);
        Assert.assertTrue(poolSource.getAccessCode().equals(newAccessCode));
        poolSource.setAccessCode(accessCode);
        Assert.assertTrue(TestHarness.ACCESS_CODE.equals(accessCode));
    }

    @Test
    public void testGetVerifyCode() throws Exception {
        Assert.assertTrue(poolSource.getVerifyCode().equals(TestHarness.VERIFY_CODE));
    }

    @Test
    public void testSetVerifyCode() throws Exception {
        String verifyCode = poolSource.getVerifyCode();
        String newVerifyCode = "adsfasdf";
        poolSource.setVerifyCode(newVerifyCode);
        Assert.assertTrue(newVerifyCode.equals(poolSource.getVerifyCode()));
        poolSource.setVerifyCode(verifyCode);
        Assert.assertTrue(TestHarness.VERIFY_CODE.equals(verifyCode));
    }

    @Test
    public void testGetInitialPoolSize() throws Exception {
        Assert.assertTrue(poolSource.getInitialPoolSize() == TestHarness.INITIAL_POOL_SIZE);
    }

    @Test
    public void testSetInitialPoolSize() throws Exception {
        int initialPoolSize = poolSource.getInitialPoolSize();
        int newPoolSize = 99;
        poolSource.setInitialPoolSize(newPoolSize);
        Assert.assertTrue(newPoolSize == poolSource.getInitialPoolSize());
        poolSource.setInitialPoolSize(initialPoolSize);
        Assert.assertTrue(initialPoolSize == poolSource.getInitialPoolSize());
    }

    @Test
    public void testGetExpandPoolSize() throws Exception {
        Assert.assertTrue(poolSource.getExpandPoolSize() == TestHarness.EXPAND_POOL_SIZE);
    }

    @Test
    public void testSetExpandPoolSize() throws Exception {
        int expandPoolSize = poolSource.getExpandPoolSize();
        int newPoolSize = 123;
        poolSource.setExpandPoolSize(newPoolSize);
        Assert.assertTrue(newPoolSize == poolSource.getExpandPoolSize());
        poolSource.setExpandPoolSize(expandPoolSize);
        Assert.assertTrue(TestHarness.EXPAND_POOL_SIZE == poolSource.getExpandPoolSize());
    }

    @Test
    public void testGetMaxPoolSize() throws Exception {
        Assert.assertTrue(poolSource.getMaxPoolSize() == TestHarness.MAX_POOL_SIZE);
    }

    @Test
    public void testSetMaxPoolSize() throws Exception {
        int maxPoolSize = poolSource.getMaxPoolSize();
        int newPoolSize = 50;
        poolSource.setMaxPoolSize(newPoolSize);
        Assert.assertTrue(newPoolSize == poolSource.getMaxPoolSize());
        poolSource.setMaxPoolSize(maxPoolSize);
        Assert.assertTrue(TestHarness.MAX_POOL_SIZE == poolSource.getMaxPoolSize());
    }
}
