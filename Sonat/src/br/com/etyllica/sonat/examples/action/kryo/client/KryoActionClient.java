package br.com.etyllica.sonat.examples.action.kryo.client;

import br.com.etyllica.sonat.adapter.kryo.KryonetMixedClient;
import br.com.etyllica.sonat.examples.action.model.ActionClient;
import br.com.etyllica.sonat.examples.action.model.ClientActionListener;
import br.com.etyllica.sonat.examples.action.model.Message;
import br.com.etyllica.sonat.examples.action.model.State;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoActionClient extends KryonetMixedClient implements ActionClient {

	private State state = new State();

	private ClientActionListener listener;

	public KryoActionClient(String host, int tcpPort, int udpPort, ClientActionListener listener) {
		super(host, tcpPort, udpPort);

		this.listener = listener;		
	}

	@Override
	public void prepare() {
		Kryo kryo = client.getKryo();
		kryo.register(State.class);
		kryo.register(State[].class);
		kryo.register(Message.class);

		client.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof State[]) {
					listener.updateStates((State[])object);
				}
			}
		});
	}

	@Override
	public void sendState() {
		System.out.println("trying to send state");
		client.sendTCP(state);
	}
}
