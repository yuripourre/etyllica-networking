package br.com.etyllica.sonat.terminal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import br.com.etyllica.sonat.chat.ClientChatListener;
import br.com.etyllica.sonat.client.Client;

public class TerminalClient implements ClientChatListener {

	private Client client;
	
	public TerminalClient() {
		super();
	}

	public void init() throws Exception {
		client.init();
		client.prepare();
		client.connect();

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				client.sendMessage(in.readLine());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

			client.finish();

		}

	}

	@Override
	public void updateNames(String[] names) {
		
		String text = "Names: ";
				
		text+= names[0];
		
		for(int i=1;i<names.length;i++) {
			text += " ";
			text += names[i];
		}
		
		System.out.println(text+".");
		
	}
	
	@Override
	public void receiveMessage(String name, String message) {
		System.out.println(name+" >> "+message);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	

}
