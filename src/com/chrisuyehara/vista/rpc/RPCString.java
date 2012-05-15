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

public class RPCString {

    private String value;

    public RPCString(String s) {
        value = s;
    }

    @Override
    public String toString() {
        return value;
    }

    public String[] split(String delimiter) {
        return split(delimiter, value);
    }

    public static String[] split(String delimiter, String s) {
        return s.split(delimiter);
    }

    public String piece(String delimiter, int pieceNumber) {
        return piece(delimiter, value, pieceNumber);
    }

    public static String piece(String delimiter, String s, int pieceNumber) {
        String[] pieces = split(delimiter, s);
        if (pieceNumber > pieces.length) {
            return null;
        }

        try {
            return pieces[--pieceNumber];
        } catch (Exception e) {
            return null;
        }
    }

    public String packString() {
        return packString(value);
    }

    public static String packString(String s) {
        StringBuffer sb = new StringBuffer();
        sb.append((char) s.length());
        sb.append(s);

        return sb.toString();
    }

    public String packString(int number) {
        return packString(value, number);
    }

    public static String packString(String s, int number) {
        StringBuffer sb = new StringBuffer();
        sb.append(s.length());

        while (sb.length() < number) {
            sb.insert(0, "0");
        }

        sb.append(s);

        return sb.toString();
    }

    public String packVariable() {
        return packVariable(value);
    }

    public static String packVariable(String s) {
        if (null == s || s.length() == 0) {
            s = "0";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("|");
        sb.append((char) s.length());
        sb.append(s);

        return sb.toString();
    }

    public String packLiteral(int number) {
        return packLiteral(value, number);
    }

    public static String packLiteral(String s, int number) {
        StringBuffer sb = new StringBuffer();
        sb.append(s.length());

        while (sb.length() < number) {
            sb.insert(0, "0");
        }

        sb.append(s);

        return sb.toString();
    }
}
