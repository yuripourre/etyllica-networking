package br.com.etyllica.sonat.server;

public abstract class MixedServerImpl extends ServerImpl {

	protected int udpPort;
	
	public MixedServerImpl(int tcpPort, int udpPort) {
		super(tcpPort);
		this.udpPort = udpPort;
	}

	public int getUdpPort() {
		return udpPort;
	}
	
}
