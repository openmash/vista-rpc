package com.chrisuyehara.vista.rpc;

import junit.framework.Assert;
import org.junit.Test;

public class RPCStringTest {

    @Test
    public void testPackLiteralPass() {
        String s = RPCString.packLiteral("TEST", 3);

        Assert.assertTrue("004TEST".equals(s));
    }

    @Test
    public void testPackLiteralFail() {
        String s = RPCString.packLiteral("TEST", 3);

        Assert.assertFalse("005TEST".equals(s));
    }

    @Test
    public void testPackVariablePass() {
        String s = RPCString.packVariable("XWB");

        Assert.assertTrue("|3XWB".equals(s));
    }

    @Test
    public void testPackVariableFail() {
        String s = RPCString.packVariable("XWBB");

        Assert.assertFalse("|3XWB".equals(s));
    }

    @Test
    public void testPackStringPass1() {
        String s = RPCString.packString("XWB");

        Assert.assertTrue("\u0003XWB".equals(s));
    }

    @Test
    public void testPackStringPass2() {
        String s = RPCString.packString("XWB", 3);

        Assert.assertTrue("003XWB".equals(s));
    }
}
