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

import java.util.ArrayList;
import java.util.Iterator;

public class OrderedList implements Iterable<KeyValuePair> {

    private ArrayList<KeyValuePair> keyValuePairs;

    public OrderedList() {
        keyValuePairs = new ArrayList<KeyValuePair>();
    }

    public void add(KeyValuePair keyValuePair) {
        keyValuePairs.add(keyValuePair);
    }

    public KeyValuePair get(int index) throws IndexOutOfBoundsException {
        return keyValuePairs.get(index);
    }

    public void set(int index, KeyValuePair keyValuePair) {
        keyValuePairs.set(index, keyValuePair);
    }

    public int size() {
        return keyValuePairs.size();
    }

    @Override
    public Iterator<KeyValuePair> iterator() {
        return keyValuePairs.iterator();
    }
}
