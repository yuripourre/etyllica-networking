package br.com.etyllica.sonat.adapter.kryonet;


import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.client.ClientImpl;

public class KryonetClient extends ClientImpl implements Client {

	com.esotericsoftware.kryonet.Client client;
	
	public KryonetClient(String host, int port) {
		super(host, port);
	}

	public void init() {
		client = new com.esotericsoftware.kryonet.Client();
		client.start();		
	}

	public void finish() {
		client.stop();
	}

	public void sendMessage(String message) {
		//channel.writeAndFlush(message+"\r\n");
		client.sendTCP(message);
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect() throws Exception {
		client.connect(5000, host, tcpPort);
	}

}
