package com.chrisuyehara.vista.rpc.procedures;

import org.junit.Test;
import com.chrisuyehara.vista.rpc.parameters.KeyValuePair;
import com.chrisuyehara.vista.rpc.parameters.ListParameter;
import com.chrisuyehara.vista.rpc.parameters.LiteralParameter;
import com.chrisuyehara.vista.rpc.parameters.OrderedList;

public class RemoteProcedureTest {

    @Test
    public void test_RPC_TIU_UPDATE_RECORD() {
        RemoteProcedure r = new RemoteProcedure("TIU UPDATE RECORD");
        LiteralParameter literal = new LiteralParameter("3");

        OrderedList o = new OrderedList();
        o.add(new KeyValuePair(".01", "20"));
        o.add(new KeyValuePair("1201", "1"));
        o.add(new KeyValuePair("1301", "3120513.1724"));
        o.add(new KeyValuePair("1701", ""));

        ListParameter list = new ListParameter(o);

        r.addParameter(literal);
        r.addParameter(list);

        System.out.println(r.toProcedure());
        char[] chars = r.toProcedure().toCharArray();
        for (char c : chars) {
            System.out.print(Integer.toHexString((int) c) + " ");
        }
    }
}
