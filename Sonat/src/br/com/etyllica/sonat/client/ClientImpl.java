package br.com.etyllica.sonat.client;


public abstract class ClientImpl {

	public final static int UNDEFINED_PORT = -1;
	
	protected String host;

	protected int tcpPort = UNDEFINED_PORT;
		
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
