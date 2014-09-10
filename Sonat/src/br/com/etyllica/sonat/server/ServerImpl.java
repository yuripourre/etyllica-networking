package br.com.etyllica.sonat.server;

public abstract class ServerImpl implements Server {

	protected int tcpPort;
	
	public ServerImpl(int port) {
		super();
		this.tcpPort = port;
	}

	public int getTcpPort() {
		return tcpPort;
	}
	
}
