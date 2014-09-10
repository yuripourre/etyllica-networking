package br.com.etyllica.sonat.client;

import br.com.etyllica.sonat.Sonat;


public abstract class MixedClientImpl extends ClientImpl {

	protected int udpPort = Sonat.UNDEFINED_PORT;
	
	public MixedClientImpl(String host, int tcpPort, int udpPort) {
		super(host, tcpPort);

		this.udpPort = udpPort;
	}

	public int getUdpPort() {
		return udpPort;
	}
	
}
