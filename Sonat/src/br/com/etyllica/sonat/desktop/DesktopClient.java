package br.com.etyllica.sonat.desktop;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.client.ClientListener;

public class DesktopClient extends Client implements ClientListener {

	public DesktopClient(String host, int port) {
		super(host, port);

		setListener(this);

	}

	public void init() throws Exception {
		super.init();

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				sendMessage(in.readLine());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

			finish();

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
		System.out.println(name+">> "+message);
	}

}
