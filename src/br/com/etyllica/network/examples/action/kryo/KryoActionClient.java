package br.com.etyllica.network.examples.action.kryo;

import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.network.adapter.kryo.KryonetMixedClient;
import br.com.etyllica.network.examples.action.model.ActionClient;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.ClientActionListener;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;

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
		kryo.register(KeyAction.class);
		kryo.register(KeyState.class);

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
		client.sendUDP(state);
	}

	@Override
	public void sendMessage(String text) {
		
		Message message = new Message();
		message.text = text;
		
		client.sendTCP(message);
	}
	
	@Override
	public void sendKeyAction(KeyAction action) {
		client.sendTCP(action);
	}
	
}
