package br.com.etyllica.sonat;

import br.com.etyllica.sonat.adapter.mina.server.MinaServer;
import br.com.etyllica.sonat.adapter.netty.server.NettyServer;

public class ChatServer {

	private static final int DEFAULT_PORT = 4444;
	
	public static void main(String[] args) throws Exception {
		new NettyServer(DEFAULT_PORT).init();
		//new MinaServer(DEFAULT_PORT).init();
	}
	
}
