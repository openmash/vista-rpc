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
