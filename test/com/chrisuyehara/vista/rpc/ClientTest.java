package com.chrisuyehara.vista.rpc;

import junit.framework.Assert;
import org.junit.Test;
import com.chrisuyehara.vista.rpc.exceptions.ConnectException;
import com.chrisuyehara.vista.rpc.exceptions.LoginException;
import com.chrisuyehara.vista.rpc.models.NewPerson;
import com.chrisuyehara.vista.rpc.procedures.XUS.GetUserInfo;

public class ClientTest {

    @Test
    public void testConnect() {
        Client c = new Client("192.168.10.3", 9200);
        try {
            c.connect();
        } catch (ConnectException e) {
            Assert.fail("Connect failed...");
        }

        Assert.assertTrue(c.isConnected());
    }

    @Test
    public void testDisconnect() {
        Client c = new Client("192.168.10.3", 9200);
        try {
            c.connect();
        } catch (ConnectException e) {
            Assert.fail("Connect failed...");
        }
        c.disconnect();

        Assert.assertFalse(c.isConnected());
    }

    @Test
    public void testLoginPass() {
        Client c = new Client("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "rfvtgbyhn!UJM7");
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        }
    }

    @Test
    public void testLoginFail() {
        Client c = new Client("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "abc");
        } catch (LoginException e) {
            return;
        }

        Assert.fail("Login should HAVE failed.");
    }

    @Test
    public void testLoginPass2() {
        Client c = new Client("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "rfvtgbyhn!UJM7");
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        }

        GetUserInfo userInfo = new GetUserInfo();
        c.call(userInfo);

        NewPerson newPerson = userInfo.getNewPerson();
        Assert.assertTrue(newPerson.getFullName().length() > 0);
        Assert.assertTrue(newPerson.getIen() > 0);
    }

    @Test
    public void testContextPass() {
        Client c = new Client("192.168.10.3", 9200);

        try {
            c.login("!QAZ1qaz!QAZ", "rfvtgbyhn!UJM7");
        } catch (LoginException e) {
            Assert.fail("Login shouldn't have failed");
        }

        c.context("OR CPRS GUI CHART");
    }
}
