package br.com.etyllica.network.examples.chat.mina.server;

import java.nio.charset.Charset;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;

import br.com.etyllica.network.adapter.mina.MinaServer;

public class MinaChatServer extends MinaServer {

	public MinaChatServer(int port) {
		super(port);
	}

	@Override
	public void prepare() {
		
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		
		acceptor.setHandler(new MinaChatServerHandler());
	}
	
}
