package com.chrisuyehara.vista.rpc.parameters;

import org.junit.Test;

public class ListParameterTest {

    @Test
    public void testListParameter() {
        ListParameter l = new ListParameter();

        OrderedList o = new OrderedList();
        o.add(new KeyValuePair("chris", "uyehara"));
        o.add(new KeyValuePair("shanti", "wada"));

        l.setOrderedList(o);

        System.out.println(l.toParameter());
    }
}
