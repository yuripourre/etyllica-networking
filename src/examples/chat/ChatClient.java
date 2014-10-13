package examples.chat;

import examples.chat.adapter.mina.client.MinaChatClient;
import examples.chat.adapter.netty.client.NettyChatClient;
import examples.chat.terminal.TerminalChatClient;
import br.com.etyllica.network.client.Client;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		
		String host = "127.0.0.1";
		
		TerminalChatClient chat = new TerminalChatClient();
		
		//Client client = new NettyChatClient(host, ChatServer.CHAT_PORT, chat);
		Client client = new MinaChatClient(host, ChatServer.CHAT_PORT, chat);
		client.init();
		client.prepare();
		client.connect();
		
		chat.setClient(client);
		chat.init();
	}

}
