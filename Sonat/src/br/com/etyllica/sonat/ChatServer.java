package br.com.etyllica.sonat;

import br.com.etyllica.sonat.mina.server.MinaServer;
import br.com.etyllica.sonat.netty.server.NettyServer;

public class ChatServer {

	public static void main(String[] args) throws Exception {
		//new NettyServer(8080).init();
		new MinaServer(8080).init();
	}
	
}
