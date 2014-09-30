package br.com.etyllica.network.examples.action;

import br.com.etyllica.network.examples.action.kryo.KryoActionClient;
import br.com.etyllica.network.examples.action.model.ActionClient;

public class ActionClientApplication {

	public static void main(String[] args) throws Exception {
		
		String host = "127.0.0.1";
		
		TerminalActionClient terminal = new TerminalActionClient();
		
		ActionClient client = new KryoActionClient(host, ActionServerApplication.ACTION_TCP_PORT, ActionServerApplication.ACTION_UDP_PORT, terminal);
		//client.init();
		((KryoActionClient)client).initAsThread();
		client.prepare();
		
		client.connect();
		
		//Responsible to show client states
		terminal.setClient(client);
		
		client.sendState();
	}

}
