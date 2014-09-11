package br.com.etyllica.network.examples.action;

import br.com.etyllica.network.examples.action.kryo.KryoActionServer;
import br.com.etyllica.network.server.Server;

public class ActionServerApplication {

	public static final int ACTION_TCP_PORT = 4455;
	public static final int ACTION_UDP_PORT = 4466;
	
	public static void main(String[] args) throws Exception {
		
		Server server = new KryoActionServer(ACTION_TCP_PORT, ACTION_UDP_PORT);
		
		server.init();
		server.prepare();
		server.bind();
	}
	
}
