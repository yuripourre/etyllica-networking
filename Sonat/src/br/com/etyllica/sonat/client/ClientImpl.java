package br.com.etyllica.sonat.client;

public abstract class ClientImpl {

	public final static int UNDEFINED_PORT = -1;
	
	protected String host;

	protected int tcpPort = UNDEFINED_PORT;
	
	protected ClientListener listener;
	
	public ClientImpl(String host, int port) {
		super();

		this.host = host;
		this.tcpPort = port;
	}
	
	public ClientImpl(String host, int port, ClientListener listener) {
		super();

		this.host = host;
		this.tcpPort = port;

		this.listener = listener;
	}

	public ClientListener getListener() {
		return listener;
	}

	public void setListener(ClientListener listener) {
		this.listener = listener;
	}

	public String getHost() {
		return host;
	}

	public int getTcpPort() {
		return tcpPort;
	}	
	
}
