package br.com.etyllica.sonat;

import br.com.etyllica.sonat.chat.mina.client.MinaChatClient;
import br.com.etyllica.sonat.chat.netty.client.NettyChatClient;
import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.terminal.TerminalClient;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		
		String host = "127.0.0.1";
		
		TerminalClient chat = new TerminalClient();
		
		//Client client = new NettyChatClient(host, ChatServer.CHAT_PORT, chat);
		Client client = new MinaChatClient(host, ChatServer.CHAT_PORT, chat);
		
		chat.setClient(client);
		chat.init();
	}

}
