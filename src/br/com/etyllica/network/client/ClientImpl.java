package br.com.etyllica.network.client;

import br.com.etyllica.network.Network;


public abstract class ClientImpl implements Client {
	
	protected String host;

	protected int tcpPort = Network.UNDEFINED_PORT;
		
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
