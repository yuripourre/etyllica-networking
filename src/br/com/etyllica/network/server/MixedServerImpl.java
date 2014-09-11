package br.com.etyllica.network.server;

import br.com.etyllica.network.Network;

public abstract class MixedServerImpl extends ServerImpl {

	protected int udpPort = Network.UNDEFINED_PORT;
	
	public MixedServerImpl(int tcpPort, int udpPort) {
		super(tcpPort);
		this.udpPort = udpPort;
	}

	public int getUdpPort() {
		return udpPort;
	}
	
}
