package br.com.etyllica.network.examples.action;

import br.com.etyllica.network.client.Client;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.examples.action.model.listener.ClientActionListener;

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
