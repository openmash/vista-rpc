package com.chrisuyehara.vista.rpc;

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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Date: 10/26/12
 * Time: 11:29 AM
 */
public class TestHarness {

    public static final String VISTA_HOSTNAME = "";
    public static final int VISTA_PORT = 9300;
    public static final String ACCESS_CODE = "";
    public static final String VERIFY_CODE = "";

    public static final int INITIAL_POOL_SIZE = 4;
    public static final int MAX_POOL_SIZE = 10;
    public static final int MIN_POOL_SIZE = 5;
    public static final int EXPAND_POOL_SIZE = 10;

    // junit skeleton

    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @AfterClass
    public static void afterClass() {

    }
}
