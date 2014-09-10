package br.com.etyllica.sonat.examples.action;

import br.com.etyllica.sonat.examples.action.kryo.client.KryoActionClient;
import br.com.etyllica.sonat.examples.action.model.ActionClient;

public class ActionClientApplication {

	public static void main(String[] args) throws Exception {
		
		String host = "127.0.0.1";
		
		TerminalActionClient action = new TerminalActionClient();
		
		ActionClient client = new KryoActionClient(host, ActionServerApplication.ACTION_TCP_PORT, ActionServerApplication.ACTION_UDP_PORT, action);
		client.init();
		client.prepare();
		client.connect();
						
		action.setClient(client);
		
		client.sendState();
	}

}
