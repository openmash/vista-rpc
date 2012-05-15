package com.chrisuyehara.vista.rpc.parameters;

import org.junit.Test;

public class OrderedListTest {

    @Test
    public void testOrderedList() {
        OrderedList o = new OrderedList();
        o.add(new KeyValuePair("chris", "uyehara"));
        o.add(new KeyValuePair("shanti", "wada"));

        for (KeyValuePair keyValuePair : o) {
            System.out.println(keyValuePair.getKey());
        }
    }
}
