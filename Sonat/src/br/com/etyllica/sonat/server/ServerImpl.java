package br.com.etyllica.sonat.server;

import br.com.etyllica.sonat.Sonat;

public abstract class ServerImpl implements Server {

	protected int tcpPort = Sonat.UNDEFINED_PORT;
	
	public ServerImpl(int port) {
		super();
		this.tcpPort = port;
	}

	public int getTcpPort() {
		return tcpPort;
	}
	
}
