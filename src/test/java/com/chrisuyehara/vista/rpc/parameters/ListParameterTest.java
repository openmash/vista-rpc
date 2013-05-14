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
