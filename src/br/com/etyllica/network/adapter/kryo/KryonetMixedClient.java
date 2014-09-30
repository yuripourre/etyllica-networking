package br.com.etyllica.network.adapter.kryo;


import br.com.etyllica.network.client.MixedClientImpl;

import com.esotericsoftware.kryonet.Client;

public class KryonetMixedClient extends MixedClientImpl {

	private static final int MAXIMUM_WAIT_TIME = 5000;
	
	protected Client client;
	
	public KryonetMixedClient(String host, int tcpPort, int udpPort) {
		super(host, tcpPort, udpPort);
	}

	public void init() {
		client = new Client();
		client.start();
	}
	
	public void initAsThread() {
		client = new Client();
		new Thread(client).start();
	}

	public void finish() {
		client.stop();
	}

	public void sendTCP(String message) {
		client.sendTCP(message);
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	}

	@Override
	public void connect() throws Exception {
		
		client.connect(MAXIMUM_WAIT_TIME, host, tcpPort, udpPort);		
	}

}
