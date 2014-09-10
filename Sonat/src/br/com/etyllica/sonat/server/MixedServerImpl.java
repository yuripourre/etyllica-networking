package br.com.etyllica.sonat.server;

import br.com.etyllica.sonat.Sonat;

public abstract class MixedServerImpl extends ServerImpl {

	protected int udpPort = Sonat.UNDEFINED_PORT;
	
	public MixedServerImpl(int tcpPort, int udpPort) {
		super(tcpPort);
		this.udpPort = udpPort;
	}

	public int getUdpPort() {
		return udpPort;
	}
	
}
