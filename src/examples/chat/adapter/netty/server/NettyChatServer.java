package examples.chat.adapter.netty.server;

import io.netty.channel.ChannelOption;

import java.util.HashMap;

import br.com.etyllica.network.adapter.netty.NettyServer;

public class NettyChatServer extends NettyServer {

	public NettyChatServer(int port) {
		super(port);
	}
	
	@Override
	public void prepare() {
		
		System.out.println("Starting Netty Chat Server..."+tcpPort);
		
		bootstrap
		.option(ChannelOption.TCP_NODELAY, true)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.childHandler(new NettyChatServerInitializer(new HashMap<String, String>()));
	}
	
}
