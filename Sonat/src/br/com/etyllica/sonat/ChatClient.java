package br.com.etyllica.sonat;

import br.com.etyllica.sonat.desktop.DesktopClient;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		
		new DesktopClient("127.0.0.1", 8080).init();
		//new DesktopClient("10.42.0.1", 8080).init();
		
	}

}
