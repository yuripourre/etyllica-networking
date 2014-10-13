package examples.chat;

import examples.chat.adapter.mina.server.MinaChatServer;
import examples.chat.adapter.netty.server.NettyChatServer;
import br.com.etyllica.network.server.Server;

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
