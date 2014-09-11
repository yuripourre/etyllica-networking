package br.com.etyllica.network.adapter.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import br.com.etyllica.network.client.ClientImpl;

public class MinaClient extends ClientImpl {

	protected IoConnector connector;
	
	private IoSession session;
	
	public MinaClient(String host, int port) {
		super(host, port);
	}

	public void init() {
		connector = new NioSocketConnector();
		connector.getSessionConfig().setReadBufferSize(2048);
	}
	
	public void finish() {
		connector.dispose();
	}

	@Override
	public void sendTCP(String message) {
		session.write(message);
	}

	@Override
	public void prepare() {
		
	}

	@Override
	public void connect() {

		ConnectFuture future = connector.connect(new InetSocketAddress(host, tcpPort));
		
		future.awaitUninterruptibly();

		if (!future.isConnected()) {
			return;
		}
		
		session = future.getSession();
	}

}
