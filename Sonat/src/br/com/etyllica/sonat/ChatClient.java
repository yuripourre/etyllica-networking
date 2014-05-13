package br.com.etyllica.sonat;

import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.mina.client.MinaClient;
import br.com.etyllica.sonat.netty.client.NettyClient;
import br.com.etyllica.sonat.terminal.TerminalClient;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		
		//Client client = new NettyClient("127.0.0.1", 8080);
		Client client = new MinaClient("127.0.0.1", 8080);
		
		new TerminalClient(client).init();
		
	}

}
