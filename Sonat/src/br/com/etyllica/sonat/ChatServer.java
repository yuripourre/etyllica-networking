package br.com.etyllica.sonat;

import br.com.etyllica.sonat.adapter.mina.server.MinaServer;
import br.com.etyllica.sonat.adapter.netty.server.NettyServer;

public class ChatServer {

	public static void main(String[] args) throws Exception {
		new NettyServer(8081).init();
		//new MinaServer(8081).init();
	}
	
}
