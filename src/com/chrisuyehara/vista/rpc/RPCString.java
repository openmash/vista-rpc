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
