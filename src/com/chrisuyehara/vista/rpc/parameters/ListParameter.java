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
