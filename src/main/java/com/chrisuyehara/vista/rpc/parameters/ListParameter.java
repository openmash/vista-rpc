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

import java.util.LinkedHashMap;
import java.util.Map;

import com.chrisuyehara.vista.rpc.RPCString;

public class ListParameter implements Parameter {
    public static final String BROKER_VALUE = "2";
    private Map<String, String> params = new LinkedHashMap<String, String>();

    public ListParameter() {
    }

    public ListParameter(final ListParameter other) {
    	this(other != null ? other.getParams() : null);
    }

    public ListParameter(Map<String, String> params) {
    	if (params != null) {
    		this.params.putAll(params);
    	}
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
    	this.params.clear();
        this.params.putAll(params);
    }

    public String toParameter() {
        StringBuffer sb = new StringBuffer();
        sb.append(BROKER_VALUE);

        synchronized (params) {
            if (params.size() == 0) {
                sb.append(RPCString.packString("", PACK_STRING_LENGTH));
                sb.append("f");
            } else {
                for (Map.Entry<String, String> kvPair : params.entrySet()) {
                    String value = kvPair.getValue();

                    if (value == null || value.length() == 0) {
                        value = "\u0001";
                    }
                    sb.append(RPCString.packString(kvPair.getKey(), PACK_STRING_LENGTH));
                    sb.append(RPCString.packString(value, PACK_STRING_LENGTH));
                    sb.append("t");
                }
            }
        }

        sb.replace(sb.length() - 1, sb.length(), "f");

        return sb.toString();
    }
}
