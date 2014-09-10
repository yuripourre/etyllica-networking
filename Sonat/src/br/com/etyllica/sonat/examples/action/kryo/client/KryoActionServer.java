package br.com.etyllica.sonat.examples.action.kryo.client;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.sonat.adapter.kryo.KryonetMixedServer;
import br.com.etyllica.sonat.examples.action.model.Message;
import br.com.etyllica.sonat.examples.action.model.State;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoActionServer extends KryonetMixedServer {

	private List<State> states = new ArrayList<State>();
	
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
				System.out.println("Hey, we are connected!");
				states.add(new State());
			}
			public void received (Connection connection, Object object) {
				if (object instanceof State) {
					State state = (State)object;

					System.out.println("Receive");
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

}
