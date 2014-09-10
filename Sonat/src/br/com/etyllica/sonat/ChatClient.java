package br.com.etyllica.sonat;

import br.com.etyllica.sonat.chat.mina.client.MinaChatClient;
import br.com.etyllica.sonat.chat.netty.client.NettyChatClient;
import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.terminal.TerminalClient;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		
		String host = "127.0.0.1";
		
		//Client client = new NettyChatClient(host, ChatServer.CHAT_PORT);
		Client client = new MinaChatClient(host, ChatServer.CHAT_PORT);
		
		new TerminalClient(client).init();
	}

}
