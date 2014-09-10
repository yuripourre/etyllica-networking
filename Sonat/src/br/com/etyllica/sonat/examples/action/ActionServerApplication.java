package br.com.etyllica.sonat.examples.action;

import br.com.etyllica.sonat.examples.action.kryo.client.KryoActionServer;
import br.com.etyllica.sonat.server.Server;

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
