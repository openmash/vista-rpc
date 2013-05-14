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

import com.chrisuyehara.vista.rpc.procedures.XWB.ImHere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.TimerTask;

/**
 * Date: 9/17/12
 * Time: 10:05 AM
 */
public class RPCTimerTask extends TimerTask {

    private RPCSocket rpcSocket;

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCTimerTask.class);

    public RPCTimerTask(RPCSocket rpcSocket) {
        this.rpcSocket = rpcSocket;
    }

    @Override
    public void run() {
        LOGGER.trace("RPCTimerTask::run enter");
        ImHere remoteProcedure = new ImHere();
        if (null != rpcSocket) {
            try {
                rpcSocket.call(remoteProcedure);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.trace("RPCTimerTask::run exit");
    }
}
