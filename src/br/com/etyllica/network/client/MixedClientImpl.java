package br.com.etyllica.network.client;

import br.com.etyllica.network.Network;


public abstract class MixedClientImpl extends ClientImpl {

	protected int udpPort = Network.UNDEFINED_PORT;
	
	public MixedClientImpl(String host, int tcpPort, int udpPort) {
		super(host, tcpPort);

		this.udpPort = udpPort;
	}

	public int getUdpPort() {
		return udpPort;
	}
	
}
