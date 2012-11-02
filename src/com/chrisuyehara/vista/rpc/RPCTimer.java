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

package com.chrisuyehara.vista.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;

/**
 * Date: 9/17/12
 * Time: 9:41 AM
 */
public class RPCTimer {

    private RPCSocket rpcSocket;
    private long timeout;
    private String threadName = null;

    private Timer timer;

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCTimer.class);

    public RPCTimer(RPCSocket rpcSocket, long timeout) {
        this(rpcSocket, timeout, "RPCTimer." + System.currentTimeMillis());
    }

    public RPCTimer(RPCSocket rpcSocket, long timeout, String threadName) {
        LOGGER.trace("RPCTimer instantiated");
        this.rpcSocket = rpcSocket;
        this.timeout = timeout;
        this.threadName = threadName;

        restart();
    }

    /**
     * Pauses the timer so the scheduled task DOES NOT run; you cannot resume a timer once it has been paused.
     * Call the restart method to restart the timer.
     */
    public void pause() {
        LOGGER.trace("RPCTimer::pause enter");
        timer.cancel();
        LOGGER.trace("RPCTimer::pause exit");
    }

    /**
     * Restarts the timer so the scheduled task WILL run.
     */
    public void restart() {
        LOGGER.trace("RPCTimer::restart enter");
        timer = new Timer(threadName, true);
        timer.schedule(new RPCTimerTask(rpcSocket), timeout, timeout);
        LOGGER.trace("RPCTimer::restart exit");
    }
}
