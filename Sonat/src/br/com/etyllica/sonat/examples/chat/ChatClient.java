package br.com.etyllica.sonat.examples.chat;

import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.examples.chat.mina.client.MinaChatClient;
import br.com.etyllica.sonat.examples.chat.netty.client.NettyChatClient;
import br.com.etyllica.sonat.examples.chat.terminal.TerminalChatClient;

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
