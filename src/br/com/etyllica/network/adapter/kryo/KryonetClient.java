package br.com.etyllica.network.adapter.kryo;


import br.com.etyllica.network.client.ClientImpl;

import com.esotericsoftware.kryonet.Client;

public class KryonetClient extends ClientImpl {

	private static final int MAXIMUM_WAIT_TIME = 5000;
	
	protected Client client;
	
	public KryonetClient(String host, int port) {
		super(host, port);
	}

	@Override
	public void init() {
		client = new Client();
		client.start();
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
		client.connect(MAXIMUM_WAIT_TIME, host, tcpPort);
	}

}
