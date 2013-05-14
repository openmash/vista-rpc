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

import com.chrisuyehara.vista.rpc.exceptions.ConnectException;
import com.chrisuyehara.vista.rpc.exceptions.LoginException;
import com.chrisuyehara.vista.rpc.procedures.AbstractRemoteProcedure;
import com.chrisuyehara.vista.rpc.procedures.XUS.AvCode;
import com.chrisuyehara.vista.rpc.procedures.XUS.SignonSetup;
import com.chrisuyehara.vista.rpc.procedures.XWB.Connect;
import com.chrisuyehara.vista.rpc.procedures.XWB.CreateContext;
import com.chrisuyehara.vista.rpc.procedures.XWB.Disconnect;

import java.io.IOException;

public class RPCClient {

    private RPCSocket rpcSocket;
    private String hostName;
    private int port;

    private RPCTimer rpcTimer;
    private long timeout = 1000 * 60 * 2; /* 1000 ms * 60 sec * 2 = 2 minutes */
    private boolean managedRPCClient = true;

    private String context;

    private boolean validAvCode = false;

    /**
     * Create a managed RPC Client that will connect to the given VistA instance using the hostName and port. The
     * default RPC Broker timeout will be set to 2 minutes.
     *
     * @param hostName
     * @param port
     */
    public RPCClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    /**
     * Create a managed RPC Client that will connect to the given VistA instance using the hostName, port and a
     * specific RPC Broker timeout. Managed means the RPC Client will keep the socket connection alive by issuing the
     * "XWB IM HERE" RPC before the RPC Broker reaches the M level read timeout.
     *
     * @param hostName the RPC Broker hostname or IP address to connect to
     * @param port     the RPC Broker port number to connect to
     * @param timeout  the number of milliseconds to wait before resetting the RPC Broker read timeout
     */
    public RPCClient(String hostName, int port, long timeout) {
        this(hostName, port);
        this.timeout = timeout;
    }

    /**
     * Create a 1) UNMANAGED or 2) managed RPC Client that will connect to the given VistA instance using the hostName
     * and port. UNMANAGED means the RPC Client will allow the socket connection to
     *
     * @param hostName
     * @param port
     * @param managedRPCClient set to true if the programmer wants the RPCClient to reset the RPC Broker read timeouts;
     *                         otherwise set to false to disable the feature. Default read timeout is set to 2 minutes.
     */
    public RPCClient(String hostName, int port, boolean managedRPCClient) {
        this(hostName, port);
        this.managedRPCClient = managedRPCClient;
    }

    public boolean isManagedRPCClient() {
        return managedRPCClient;
    }

    /**
     * @throws ConnectException
     * @throws IOException
     */
    public void connect() throws ConnectException, IOException {
        if (!isConnected()) {
            rpcSocket = new RPCSocket();
            rpcSocket.connect(hostName, port);

            if (isManagedRPCClient()) {
                rpcTimer = new RPCTimer(rpcSocket, timeout);
            }

            Connect connect = new Connect(rpcSocket.getLocalIpAddress(), rpcSocket.getHostName());
            call(connect);

            if (!connect.isConnectionAccepted()) {
                throw new ConnectException("VistA is not accepting new style connections");
            }

            call(new SignonSetup());
        }
    }

    /**
     * @return
     */
    public boolean isConnected() {
        if (null == rpcSocket) {
            return false;
        }
        return rpcSocket.isConnected();
    }

    /**
     * Logout and disconnect the RPC socket
     */
    public void disconnect() {
        if (isConnected()) {
            try {
                call(new Disconnect());
            } catch (IOException e) {
                /* if the call to disconnect fails then ignore the exception */
            }
        }
        rpcSocket.disconnect();
    }

    /**
     * @param accessCode unencrypted form of the access code
     * @param verifyCode unencrypted form of the verify code
     * @throws LoginException
     * @throws IOException
     */
    public void login(String accessCode, String verifyCode) throws LoginException, IOException {
        try {
            connect();
        } catch (ConnectException e) {
            throw new LoginException(e.getMessage(), e.getCause());
        }

        AvCode avCode = new AvCode(accessCode, verifyCode);
        call(avCode);
        if (!avCode.isLoggedIn()) {
            validAvCode = false;
            throw new LoginException("Invalid access or verify code.");
        }

        this.validAvCode = true;
        this.context = "";
    }

    /**
     * Logout and disconnect the RPC socket; equivalent call to disconnect();
     */
    public void logout() {
        disconnect();
    }

    /**
     * Change the broker context to the supplied context. Numerous context's have been built in; they are
     * available as constant strings in the class CreateContext.
     *
     * @param context the context to change to
     * @throws IOException when the rpc client is unable to communicate with the rpc broker.
     */
    public void context(String context) throws IOException {
        CreateContext cc = new CreateContext(context);
        call(cc);

        if (cc.isValidContext()) {
            this.context = context;
        } else {
            this.context = "";
        }
    }

    public synchronized void call(AbstractRemoteProcedure remoteProcedure) throws IOException {
        if (isManagedRPCClient()) {
            rpcTimer.pause();
        }

        try {
            rpcSocket.call(remoteProcedure);

            if (isManagedRPCClient()) {
                rpcTimer.restart();
            }
        } catch (IOException e) {
            /* when an ioexception occurs we'll first disconnect the socket */
            rpcSocket.disconnect();
            /* then it is up to the programmer to handle the exception after the call is made */
            throw e;
        }
    }

    public String getContext() {
        return context;
    }

}
