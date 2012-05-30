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

import com.chrisuyehara.vista.rpc.procedures.AbstractRemoteProcedure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class Socket {

    private java.net.Socket socket = new java.net.Socket();

    public Socket() {
    }

    public void connect(String hostname, int port) throws IOException, SocketTimeoutException {
        socket.connect(new InetSocketAddress(hostname, port));
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                socket.close();
            } catch (IOException e) {
                /* ignore exception */
            }
        }
    }

    public boolean isConnected() {
        if (socket.isClosed())
            return false;
        return socket.isConnected();
    }

    public String getHostName() {
        if (socket.isConnected()) {
            return socket.getLocalAddress().getCanonicalHostName();
        }
        return null;
    }

    public String getLocalIpAddress() {
        if (socket.isConnected()) {
            return socket.getLocalAddress().getHostAddress();
        }
        return null;
    }

    public synchronized void call(AbstractRemoteProcedure remoteProcedure) {
        String procedure = remoteProcedure.toProcedure();

        InputStream inputStream;
        OutputStream outputStream;

        try {
            byte[] procedureBytes = procedure.getBytes();

            outputStream = socket.getOutputStream();
            outputStream.write(procedureBytes);
            outputStream.flush();

            List<Byte> byteList = new ArrayList<Byte>(); /* bucket of bytes */

            boolean nullHeader = true;
            int read;
            inputStream = socket.getInputStream();

            while ((read = inputStream.read()) != -1) {
                if (4 == read) {
                    break;
                }
                if (nullHeader && 0 == read) {
                    continue;
                }

                byteList.add((byte) read);
                nullHeader = false;
            }

            byte[] byteResponse = toByteArray(byteList);
            remoteProcedure.setResponse(new String(byteResponse));
        } catch (IOException e) {
            remoteProcedure.setResponse(null);
        }
    }

    /**
     * boo - java cannot convert List of primitives to array
     *
     * @param list
     * @return
     */
    private byte[] toByteArray(List<Byte> list) {
        byte[] rv = new byte[list.size()];
        for (int i = 0; i < rv.length; i++) {
            rv[i] = list.get(i);
        }
        return rv;
    }
}
