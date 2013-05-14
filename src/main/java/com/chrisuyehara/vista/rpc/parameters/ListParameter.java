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

import com.chrisuyehara.vista.rpc.RPCString;

public class ListParameter implements Parameter {

    public static final String BROKER_VALUE = "2";

    OrderedList orderedList;

    public ListParameter() {
        orderedList = new OrderedList();
    }

    public ListParameter(OrderedList orderedList) {
        this.orderedList = orderedList;
    }

    public OrderedList getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(OrderedList orderedList) {
        this.orderedList = orderedList;
    }

    @Override
    public String toParameter() {
        StringBuffer sb = new StringBuffer();
        sb.append(BROKER_VALUE);

        synchronized (orderedList) {
            if (null == orderedList || 0 == orderedList.size()) {
                sb.append(RPCString.packString("", PACK_STRING_LENGTH));
                sb.append("f");
            } else {
                for (KeyValuePair keyValuePair : orderedList) {
                    String key = keyValuePair.getKey();
                    String value = keyValuePair.getValue();

                    if (null == value || "".equalsIgnoreCase(value)) {
                        value = "\u0001";
                    }
                    sb.append(RPCString.packString(key, PACK_STRING_LENGTH));
                    sb.append(RPCString.packString(value, PACK_STRING_LENGTH));
                    sb.append("t");
                }
            }
        }

        sb.replace(sb.length() - 1, sb.length(), "f");

        return sb.toString();
    }
}
