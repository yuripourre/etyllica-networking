package br.com.etyllica.sonat.examples.action.kryo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.etyllica.sonat.adapter.kryo.KryonetMixedServer;
import br.com.etyllica.sonat.examples.action.model.Message;
import br.com.etyllica.sonat.examples.action.model.State;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoActionServer extends KryonetMixedServer {

	private int count = 0;
		
	private List<State> states = new ArrayList<State>();
	
	private Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
	
	public KryoActionServer(int tcpPort, int udpPort) {
		super(tcpPort, udpPort);
	}

	@Override
	public void prepare() {
		System.out.println("Starting Action Server...");

		Kryo kryo = server.getKryo();
		kryo.register(State.class);
		kryo.register(State[].class);
		kryo.register(Message.class);

		server.addListener(new Listener() {
			
			public void connected(Connection con) {
				System.out.println("New player connected! "+count);
				
				join(con);
			}
			
			public void received (Connection connection, Object object) {
				if (object instanceof State) {
					State state = (State)object;

					System.out.println("Receive from "+connection.getID());
					System.out.println("x: "+state.x);
					System.out.println("y: "+state.y);
					System.out.println("Act: "+state.action);
										
					//Response with all states
					State[] array = states.toArray(new State[states.size()]);
					connection.sendTCP(array);
				}
			}
		});
	}
	
	private void join(Connection connection) {
		ids.put(connection.getID(), count);
		
		State state = new State(); 
		state.y = count;
		
		if(count>=1) {
			state.action = "WAITING";
		}
		states.add(state);
		
		count++;
	}	

}
