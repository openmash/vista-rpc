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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class RPCConfiguration {

	public static final String ENV_CONFIG_DIR = "VISTARPC_CONFIG_DIR";
	public static final String ENV_CONFIG_FILE = "vistarpc.properties";

	public static final String KEY_HOST = "vista.host";
	public static final String KEY_PORT = "vista.port";
	public static final String KEY_ACCESS = "vista.accesscode";
	public static final String KEY_VERIFY = "vista.verifycode";
	public static final String DEF_HOST = "localhost";
	public static final String DEF_PORT = "9220";
	public static final String DEF_ACCESS = "secret";
	public static final String DEF_VERIFY = "secret";
	
	public static void loadConfiguration(RPCConfiguration cfg, InputStream is) {
		if (is != null) {
	        try {
				Properties props = new Properties();
				props.load(is);
				
				cfg.setHost(props.getProperty(KEY_HOST));
				cfg.setPort(props.getProperty(KEY_PORT));
				cfg.setAccess(props.getProperty(KEY_ACCESS));
				cfg.setVerify(props.getProperty(KEY_VERIFY));
				
				is.close();
			} catch (IOException e) {
				// ignore, leave unmodified
				// TODO: log maybe?
			}
		}
	}
	public static RPCConfiguration instance() {
		return INSTANCE;
	}
	private static RPCConfiguration INSTANCE;
	
    static {
		RPCConfiguration cfg = new RPCConfiguration();  // default configuration;
		InputStream is = null;
        try {
        	String configDir = System.getenv(ENV_CONFIG_DIR);
        	// override configuration using a local file
        	File localFile = new File(".", ENV_CONFIG_FILE + ".local");
        	File configFile = configDir != null ? new File(configDir, ENV_CONFIG_FILE) :
    			localFile.exists() ? localFile : new File(".", ENV_CONFIG_FILE);
			is = configFile.exists() ? new FileInputStream(configFile) :
			    RPCConfiguration.class.getClassLoader().getResourceAsStream(ENV_CONFIG_FILE);

			loadConfiguration(cfg, is);

        } catch (FileNotFoundException e) {
			// ignore: should not happen, already checked if configFile.exists()
		}
        
		INSTANCE = cfg;
    }

	private String host = DEF_HOST;
	private String port = DEF_PORT;
	private String access = DEF_ACCESS;
	private String verify = DEF_VERIFY;

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		if (host != null) {
			this.host = host;
		}
	}

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		if (port != null) {
			this.port = port;
		}
	}

	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		if (access != null) {
			this.access = access;
		}
	}

	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		if (verify != null) {
			this.verify = verify;
		}
	}

}
