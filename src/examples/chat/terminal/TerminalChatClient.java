package examples.chat.terminal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import examples.chat.ClientChatListener;
import br.com.etyllica.network.client.Client;

public class TerminalChatClient implements ClientChatListener {

	private Client client;
	
	public TerminalChatClient() {
		super();
	}

	public void init() throws Exception {
		
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				client.sendTCP(in.readLine());
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
