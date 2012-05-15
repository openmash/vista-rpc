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
