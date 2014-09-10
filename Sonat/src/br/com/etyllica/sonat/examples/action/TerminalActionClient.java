package br.com.etyllica.sonat.examples.action;

import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.examples.action.model.ClientActionListener;
import br.com.etyllica.sonat.examples.action.model.State;

public class TerminalActionClient implements ClientActionListener {

	private Client client;
	
	@Override
	public void updateStates(State[] states) {

		for(State state: states) {
			System.out.println("x: "+state.x);
	        System.out.println("y: "+state.y);
	        System.out.println("Act: "+state.action);	
		}
		
		System.out.println("-------------------------");        
	}

	@Override
	public void receiveMessage(String name, String message) {
		System.out.println(name+": "+message);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}