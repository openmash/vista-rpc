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

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.chrisuyehara.vista.rpc.RPCConfiguration;

/**
 * Date: 11/1/12
 * Time: 1:07 PM
 */
public class RPCConnectionPoolSourceTest {

    private static RPCConnectionPoolSource poolSource;

    @BeforeClass
    public static void beforeClass() {
        poolSource = new RPCConnectionPoolSource();
    }

    @Test
    public void testGetHostName() throws Exception {
        Assert.assertEquals(RPCConfiguration.instance().getHost(), poolSource.getHostName());
    }

    @Test
    public void testSetHostName() throws Exception {
        String hostName = poolSource.getHostName();
        String newHostName = "TEST.VISTAEHR.COM";

        poolSource.setHostName(newHostName);
        Assert.assertEquals(newHostName, poolSource.getHostName());

        poolSource.setHostName(hostName);
        Assert.assertEquals(hostName, poolSource.getHostName());
    }

    @Test
    public void testGetPort() throws Exception {
        Assert.assertEquals(RPCConfiguration.instance().getPort(), poolSource.getPort());
    }

    @Test
    public void testSetPort() throws Exception {
        int port = poolSource.getPort();
        int newPort = 1234;

        poolSource.setPort(newPort);
        Assert.assertEquals(newPort, poolSource.getPort());

        poolSource.setPort(port);
        Assert.assertEquals(port, poolSource.getPort());
    }

    @Test
    public void testGetAccessCode() throws Exception {
        Assert.assertEquals(RPCConfiguration.instance().getAccess(), poolSource.getAccessCode());
    }

    @Test
    public void testSetAccessCode() throws Exception {
        String accessCode = poolSource.getAccessCode();
        String newAccessCode = "abc123";

        poolSource.setAccessCode(newAccessCode);
        Assert.assertEquals(newAccessCode, poolSource.getAccessCode());

        poolSource.setAccessCode(accessCode);
        Assert.assertEquals(accessCode, poolSource.getAccessCode());
    }

    @Test
    public void testGetVerifyCode() throws Exception {
        Assert.assertEquals(RPCConfiguration.instance().getVerify(), poolSource.getVerifyCode());
    }

    @Test
    public void testSetVerifyCode() throws Exception {
        String verifyCode = poolSource.getVerifyCode();
        String newVerifyCode = "adsfasdf";

        poolSource.setVerifyCode(newVerifyCode);
        Assert.assertEquals(newVerifyCode, poolSource.getVerifyCode());

        poolSource.setVerifyCode(verifyCode);
        Assert.assertEquals(verifyCode, poolSource.getVerifyCode());
    }

    @Test
    public void testGetInitialPoolSize() throws Exception {
        Assert.assertEquals(RPCConfiguration.instance().getInitialPoolSize(), poolSource.getInitialPoolSize());
    }

    @Test
    public void testSetInitialPoolSize() throws Exception {
        int initialPoolSize = poolSource.getInitialPoolSize();
        int newPoolSize = 99;

        poolSource.setInitialPoolSize(newPoolSize);
        Assert.assertEquals(newPoolSize, poolSource.getInitialPoolSize());

        poolSource.setInitialPoolSize(initialPoolSize);
        Assert.assertEquals(initialPoolSize, poolSource.getInitialPoolSize());
    }

    @Test
    public void testGetExpandPoolSize() throws Exception {
        Assert.assertEquals(RPCConfiguration.instance().getExpandPoolSize(), poolSource.getExpandPoolSize());
    }

    @Test
    public void testSetExpandPoolSize() throws Exception {
        int expandPoolSize = poolSource.getExpandPoolSize();
        int newPoolSize = 123;

        poolSource.setExpandPoolSize(newPoolSize);
        Assert.assertEquals(newPoolSize, poolSource.getExpandPoolSize());

        poolSource.setExpandPoolSize(expandPoolSize);
        Assert.assertEquals(expandPoolSize, poolSource.getExpandPoolSize());
    }

    @Test
    public void testGetMinPoolSize() throws Exception {
        Assert.assertEquals(RPCConfiguration.instance().getMinPoolSize(), poolSource.getMinPoolSize());
    }

    @Test
    public void testSetMinPoolSize() throws Exception {
        int minPoolSize = poolSource.getMinPoolSize();
        int newPoolSize = 50;

        poolSource.setMinPoolSize(newPoolSize);
        Assert.assertEquals(newPoolSize, poolSource.getMinPoolSize());

        poolSource.setMinPoolSize(minPoolSize);
        Assert.assertEquals(minPoolSize, poolSource.getMinPoolSize());
    }

    @Test
    public void testGetMaxPoolSize() throws Exception {
        Assert.assertEquals(RPCConfiguration.instance().getMaxPoolSize(), poolSource.getMaxPoolSize());
    }

    @Test
    public void testSetMaxPoolSize() throws Exception {
        int maxPoolSize = poolSource.getMaxPoolSize();
        int newPoolSize = 50;

        poolSource.setMaxPoolSize(newPoolSize);
        Assert.assertEquals(newPoolSize, poolSource.getMaxPoolSize());

        poolSource.setMaxPoolSize(maxPoolSize);
        Assert.assertEquals(maxPoolSize, poolSource.getMaxPoolSize());
    }

}
