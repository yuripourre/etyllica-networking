package br.com.etyllica.sonat;

import br.com.etyllica.sonat.server.Server;

public class ChatServer {

	public static void main(String[] args) throws Exception {
		new Server(8080).init();
	}
	
}
