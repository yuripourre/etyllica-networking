package br.com.etyllica.sonat.adapter.kryo;


import br.com.etyllica.sonat.client.MixedClientImpl;

import com.esotericsoftware.kryonet.Client;

public class KryonetMixedClient extends MixedClientImpl {

	private static final int MAXIMUM_WAIT_TIME = 5000;
	
	protected Client client;
	
	public KryonetMixedClient(String host, int tcpPort, int udpPort) {
		super(host, tcpPort, udpPort);
	}

	public void init() {
		client = new Client();
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
		new Thread(client).start();		
		client.connect(MAXIMUM_WAIT_TIME, host, tcpPort, udpPort);		
	}

}
