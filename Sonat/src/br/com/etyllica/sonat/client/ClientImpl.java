package br.com.etyllica.sonat.client;

import br.com.etyllica.sonat.Sonat;


public abstract class ClientImpl implements Client {
	
	protected String host;

	protected int tcpPort = Sonat.UNDEFINED_PORT;
		
	public ClientImpl(String host, int port) {
		super();

		this.host = host;
		this.tcpPort = port;
	}

	public String getHost() {
		return host;
	}

	public int getTcpPort() {
		return tcpPort;
	}	
	
}
