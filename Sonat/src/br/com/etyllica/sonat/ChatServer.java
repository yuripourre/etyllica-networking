package br.com.etyllica.sonat;

import br.com.etyllica.sonat.chat.mina.server.MinaChatServer;
import br.com.etyllica.sonat.chat.netty.server.NettyChatServer;
import br.com.etyllica.sonat.server.Server;

public class ChatServer {

	public static final int CHAT_PORT = 4444;
	
	public static void main(String[] args) throws Exception {
		
		//Server server = new NettyChatServer(CHAT_PORT);
		
		Server server = new MinaChatServer(CHAT_PORT);
		
		server.init();
		server.prepare();
		server.bind();
	}
	
}
