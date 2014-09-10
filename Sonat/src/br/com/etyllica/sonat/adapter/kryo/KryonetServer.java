package br.com.etyllica.sonat.adapter.kryo;

import br.com.etyllica.sonat.Sonat;
import br.com.etyllica.sonat.server.ServerImpl;

import com.esotericsoftware.kryonet.Server;

public class KryonetServer extends ServerImpl {

	protected Server server;
	
	public KryonetServer(int tcpPort) {
		super(tcpPort);
	}

	@Override
	public void init() {
		server = new Server(tcpPort, Sonat.UNDEFINED_PORT);
	    server.start();
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	}

	@Override
	public void bind() throws Exception {
		server.bind(tcpPort);
	}

	@Override
	public void stop() throws Exception {
		server.stop();
	}
	
}
