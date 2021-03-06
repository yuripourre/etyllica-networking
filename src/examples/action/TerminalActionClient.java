package examples.action;

import examples.action.model.State;
import br.com.etyllica.network.client.Client;
import br.com.etyllica.network.realtime.ClientActionListener;
import br.com.etyllica.network.realtime.model.Message;

public class TerminalActionClient implements ClientActionListener<State> {

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
	public void receiveMessage(Message message) {
		System.out.println(message.sender+": "+message.text);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public void playerJoin(int id) {
		System.out.println("Player "+id+" joined.");
	}

	@Override
	public void playerLeft(int id) {
		System.out.println("Player "+id+" left.");
	}

	@Override
	public Class<?> getStateClass() {
		return State.class;
	}

}
