package br.com.etyllica.network.adapter.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import br.com.etyllica.network.server.ServerImpl;

public class MinaServer extends ServerImpl {
	 
	protected IoAcceptor acceptor;
	
	public MinaServer(int port) {
		super(port);	
	}

	public void init() {
		
		acceptor = new NioSocketAcceptor();
		
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 600);		
	}
	
	public void prepare() {
		
	}

	@Override
	public void bind() throws Exception {
		acceptor.bind(new InetSocketAddress(tcpPort));
	}

	@Override
	public void stop() throws Exception {
		acceptor.dispose();
	}
	
}
