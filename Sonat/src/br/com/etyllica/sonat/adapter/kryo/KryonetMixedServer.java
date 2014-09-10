package br.com.etyllica.sonat.adapter.kryo;

import br.com.etyllica.sonat.server.MixedServerImpl;

import com.esotericsoftware.kryonet.Server;

public class KryonetMixedServer extends MixedServerImpl {

	protected Server server;
	
	public KryonetMixedServer(int tcpPort, int udpPort) {
		super(tcpPort, udpPort);
	}

	@Override
	public void init() {
		server = new Server(tcpPort, udpPort);
	    server.start();
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	}

	@Override
	public void bind() throws Exception {
		server.bind(tcpPort, udpPort);    
	}

	@Override
	public void stop() throws Exception {
		server.stop();
	}
	
}
