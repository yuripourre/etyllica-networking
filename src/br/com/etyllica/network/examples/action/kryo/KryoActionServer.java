package br.com.etyllica.network.examples.action.kryo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.network.adapter.kryo.KryonetMixedServer;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.ServerActionListener;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoActionServer extends KryonetMixedServer {

	private int count = 0;
		
	private Map<Integer, State> states = new LinkedHashMap<Integer, State>();
	
	private Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
	
	private Map<Integer, String> names = new HashMap<Integer, String>();
	
	private ServerActionListener listener;
	
	public KryoActionServer(int tcpPort, int udpPort, ServerActionListener listener) {
		super(tcpPort, udpPort);
		
		this.listener = listener;
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
		sendStates();
	}
	
	private void sendStates() {
		State[] array = states.values().toArray(new State[states.size()]);
		server.sendToAllTCP(array);
	}
	
	private void handleMessage(Connection connection, Message message) {
		
		String sender = names.get(connection.getID());
		
		message.sender = sender;
		
		listener.handleMessage(connection.getID(), message);		
		
		server.sendToAllTCP(message);
	}
	
	private void handleKeyAction(Connection connection, KeyAction action) {
		listener.handleKey(connection.getID(), action);
	}
	
	private void handleJoin(Connection connection) {
		ids.put(connection.getID(), count);
		names.put(connection.getID(), "Player "+count+1);
		
		State state = new State();
		state.id = connection.getID();
		state.y = 60;
		state.x = 20+60*count;
		
		if(count >= 1) {
			state.action = "WAITING";
		}
		
		states.put(connection.getID(), state);
		
		count++;
		
		listener.join(connection.getID());
		sendStates();
	}
	
	private void handleLeft(Connection connection) {
		listener.left(connection.getID());
		sendStates();
	}

}
