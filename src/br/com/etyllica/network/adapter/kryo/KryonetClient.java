package br.com.etyllica.network.adapter.kryo;


import com.esotericsoftware.kryonet.Client;

import br.com.etyllica.network.client.ClientImpl;

public class KryonetClient extends ClientImpl {

	private static final int MAXIMUM_WAIT_TIME = 5000;
	
	protected Client client;
	
	public KryonetClient(String host, int port) {
		super(host, port);
	}

	@Override
	public void init() {
		client = new Client();
	}

	@Override
	public void finish() {
		client.stop();
	}

	@Override
	public void sendTCP(String message) {
		client.sendTCP(message);
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	}

	@Override
	public void connect() throws Exception {
		client.start();
		client.connect(MAXIMUM_WAIT_TIME, host, tcpPort);
	}

}
