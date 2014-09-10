package br.com.etyllica.sonat.adapter.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import br.com.etyllica.sonat.examples.chat.mina.server.MinaChatServerHandler;
import br.com.etyllica.sonat.server.ServerImpl;

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
		
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		
		acceptor.setHandler(new MinaChatServerHandler());
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
