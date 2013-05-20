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

package com.chrisuyehara.vista.rpc.pool;

import com.chrisuyehara.vista.rpc.RPCConfiguration;

public class RPCConnectionPoolSource {

    private String hostName;
    private int port;
    private String accessCode;
    private String verifyCode;

    private int initialPoolSize;
    private int expandPoolSize;
    private int minPoolSize;
    private int maxPoolSize;

    public RPCConnectionPoolSource() {
    	this(RPCConfiguration.instance());
    }

    public RPCConnectionPoolSource(RPCConfiguration cfg) {
    	hostName = cfg.getHost();
    	port = cfg.getPort();
    	accessCode = cfg.getAccess();
    	verifyCode = cfg.getVerify();

        initialPoolSize = cfg.getInitialPoolSize();
        expandPoolSize = cfg.getExpandPoolSize();
        minPoolSize = cfg.getMinPoolSize();
        maxPoolSize = cfg.getMaxPoolSize();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public int getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(int initialPoolSize) {
        if (initialPoolSize < 1) {
            return;
        }
        this.initialPoolSize = initialPoolSize;
    }

    public int getExpandPoolSize() {
        return expandPoolSize;
    }

    public void setExpandPoolSize(int expandPoolSize) {
        if (expandPoolSize < 1) {
            return;
        }
        this.expandPoolSize = expandPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        if (maxPoolSize < 1) {
            return;
        }
        this.maxPoolSize = maxPoolSize;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }
}
