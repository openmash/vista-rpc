/*
 * Java VistA RPC Client
 * Copyright (C) 2012 Chris Uyehara / chris.uyehara@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
