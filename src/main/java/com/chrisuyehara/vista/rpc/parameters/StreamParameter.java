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

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StreamParameter implements Parameter {

    public static final String BROKER_VALUE = "5";

    @Override
    public String toParameter() {
        /**
         * Not implemented as documented in PRS5+37^XWBPRS
         **/
        throw new NotImplementedException();
    }

}