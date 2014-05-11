package br.com.etyllica.sonat;

import br.com.etyllica.sonat.client.Client;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		new Client("127.0.0.1", 8000).init();
	}

}
