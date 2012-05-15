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

public class Client {

    private Socket socket;
    private String hostName;
    private int port;

    private String accessCode;
    private String verifyCode;
    private String context;

    private boolean validAvCode = false;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void connect() throws ConnectException {
        if (!isConnected()) {
            try {
                socket = new Socket();
                socket.connect(hostName, port);

                Connect connect = new Connect(socket.getLocalIpAddress(), socket.getHostName());
                call(connect);

                if (!connect.isConnectionAccepted()) {
                    throw new ConnectException("VistA is not accepting new style connections");
                }

                call(new SignonSetup());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        if (null == socket) {
            return false;
        }
        return socket.isConnected();
    }

    public void disconnect() {
        if (isConnected()) {
            call(new Disconnect());
            socket.disconnect();
        }
    }

    public void login(String accessCode, String verifyCode) throws LoginException {
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

        this.accessCode = accessCode;
        this.verifyCode = verifyCode;
        this.validAvCode = true;
        this.context = "";
    }

    public void context(String context) {
        CreateContext cc = new CreateContext(context);
        call(cc);

        if (cc.isValidContext()) {
            this.context = context;
        } else {
            this.context = "";
        }
    }

    public synchronized void call(AbstractRemoteProcedure remoteProcedure) {
        socket.call(remoteProcedure);
    }

    public String getContext() {
        return context;
    }

}
