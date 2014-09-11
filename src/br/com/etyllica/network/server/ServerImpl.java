package br.com.etyllica.network.server;

import br.com.etyllica.network.Network;

public abstract class ServerImpl implements Server {

	protected int tcpPort = Network.UNDEFINED_PORT;
	
	public ServerImpl(int port) {
		super();
		this.tcpPort = port;
	}

	public int getTcpPort() {
		return tcpPort;
	}
	
}
