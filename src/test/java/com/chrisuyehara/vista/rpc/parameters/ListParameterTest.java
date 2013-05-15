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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;

import org.junit.Test;

public class ListParameterTest {

    @Test
    public void testListParameter() {
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("chris", "uyehara");
        params.put("shanti", "wada");
        params.put("hadrian", "hadrian");
        params.put("ed", "ost");
        params.remove("hadrian");
        params.put("mike", "stark");
        params.put("hadrian", "hadrian");
        params.remove("shanti");

        ListParameter lp = new ListParameter(params);
        Iterator<String> it = lp.getParams().values().iterator();

        Assert.assertEquals(4, lp.getParams().size());
        Assert.assertEquals("uyehara", it.next());
        Assert.assertEquals("ost", it.next());
        Assert.assertEquals("stark", it.next());
        Assert.assertEquals("hadrian", it.next());
    }
}
