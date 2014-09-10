package br.com.etyllica.sonat.adapter.kryonet;


import com.esotericsoftware.kryonet.Client;

import br.com.etyllica.sonat.client.ClientImpl;

public class KryonetClient extends ClientImpl {

	private static final int MAXIMUM_WAIT_TIME = 5000;
	
	protected Client client;
	
	public KryonetClient(String host, int port) {
		super(host, port);
	}

	public void init() {
		client = new Client();
		client.start();
	}

	public void finish() {
		client.stop();
	}

	public void sendMessage(String message) {
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
