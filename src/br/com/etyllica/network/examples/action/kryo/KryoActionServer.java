package br.com.etyllica.network.examples.action.kryo;

import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.network.Sender;
import br.com.etyllica.network.adapter.kryo.KryonetMixedServer;
import br.com.etyllica.network.realtime.ServerActionListener;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;
import br.com.etyllica.util.ReflectionUtils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoActionServer<S> extends KryonetMixedServer implements Sender {
		
	private Class<?> stateClass;
	
	private ServerActionListener<S> listener;
	
	public KryoActionServer(int tcpPort, int udpPort, ServerActionListener<S> listener) {
		super(tcpPort, udpPort);
		
		this.listener = listener;
		listener.setSender(this);
		
		stateClass = listener.getStateClass();
	}

	@Override
	public void prepare() {
		System.out.println("Starting Action Server...");

		Kryo kryo = server.getKryo();
		//Generic State Class (and array class) 
		kryo.register(stateClass);
		kryo.register(ReflectionUtils.getArrayClass(stateClass));
		//Other classes
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
								
				if (stateClass.isInstance(object)) {
					//S state = (S)(stateClass.cast(object));
					handleState(connection, (S)object);
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
		
	private void handleState(Connection connection, S state) {
		
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
