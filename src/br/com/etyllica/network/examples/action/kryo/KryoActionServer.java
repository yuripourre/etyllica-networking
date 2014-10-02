package br.com.etyllica.network.examples.action.kryo;

import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.network.adapter.kryo.KryonetMixedServer;
import br.com.etyllica.network.examples.action.Sender;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.ServerActionListener;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoActionServer<S> extends KryonetMixedServer implements Sender {
		
	private ServerActionListener<S> listener;
	
	public KryoActionServer(int tcpPort, int udpPort, ServerActionListener<S> listener) {
		super(tcpPort, udpPort);
		
		this.listener = listener;
		listener.setSender(this);
	}

	@Override
	public void prepare() {
		System.out.println("Starting Action Server...");

		Kryo kryo = server.getKryo();
		kryo.register(State.class);
		kryo.register(State[].class);
		kryo.register(Message.class);
		kryo.register(KeyAction.class);
		kryo.register(KeyState.class);		

		server.addListener(new Listener() {
			
			public void connected(Connection con) {
				handleJoin(con);
			}
			
			public void disconnected(Connection con) {
				handleLeft(con);				
			}
			
			public void received (Connection connection, Object object) {
				if (object instanceof State) {
					State state = (State)object;

					handleState(connection, state);
				}
				
				if (object instanceof Message) {
					Message message = (Message)object;

					handleMessage(connection, message);
				}
				
				if (object instanceof KeyAction) {
					KeyAction action = (KeyAction)object;
					System.out.println("Received KEY"+action.key);
					
					handleKeyAction(connection, action);
				}
			}
		});
	}
	
	private void handleState(Connection connection, State state) {
		
		listener.handleState(connection.getID(), state);
		
		//Response with all states		
		sendStates(listener.getStates());
	}
	
	private void sendStates(S[] states) {
		
		server.sendToAllUDP(states);
	}
	
	private void handleMessage(Connection connection, Message message) {
				
		message.sender = connection.getID();
		
		listener.handleMessage(connection.getID(), message);		
		
		server.sendToAllTCP(message);
	}
	
	private void handleKeyAction(Connection connection, KeyAction action) {
		listener.handleKey(connection.getID(), action);
	}
	
	private void handleJoin(Connection connection) {
		
		listener.join(connection.getID());
		sendStates(listener.getStates());
	}
	
	private void handleLeft(Connection connection) {
		listener.left(connection.getID());
		sendStates(listener.getStates());
	}

	@Override
	public void sendToAllTCP(Object object) {
		server.sendToAllTCP(object);
	}

	@Override
	public void sendToAllUDP(Object object) {
		server.sendToAllUDP(object);
	}

}
