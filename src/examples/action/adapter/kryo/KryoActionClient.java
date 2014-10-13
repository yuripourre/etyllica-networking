package examples.action.adapter.kryo;

import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.network.adapter.kryo.KryonetMixedClient;
import br.com.etyllica.network.realtime.ClientActionListener;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;
import br.com.etyllica.util.ReflectionUtils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import examples.action.model.ActionClient;
import examples.action.model.State;

public class KryoActionClient<S> extends KryonetMixedClient implements ActionClient {

	private State state = new State();

	private Class<?> stateClass;

	private Class<?> arrayStateClass;

	private ClientActionListener<S> listener;

	public KryoActionClient(String host, int tcpPort, int udpPort, ClientActionListener<S> listener) {
		super(host, tcpPort, udpPort);

		this.listener = listener;

		stateClass = listener.getStateClass();

		arrayStateClass = ReflectionUtils.getArrayClass(stateClass);
	}

	@Override
	public void prepare() {
		Kryo kryo = client.getKryo();
		//Generic State Class (and array class) 
		kryo.register(stateClass);
		kryo.register(arrayStateClass);
		//Other classes
		kryo.register(Message.class);
		kryo.register(KeyAction.class);
		kryo.register(KeyState.class);

		client.addListener(new Listener() {

			public void connected (Connection connection) {
				listener.playerJoin(connection.getID());
			}

			@Override
			public void received (Connection connection, Object object) {
				if (arrayStateClass.isInstance(object)) {
					listener.updateStates((S[])object);
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
