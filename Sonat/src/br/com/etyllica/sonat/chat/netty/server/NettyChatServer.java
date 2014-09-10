package br.com.etyllica.sonat.chat.netty.server;

import io.netty.channel.ChannelOption;

import java.util.HashMap;

import br.com.etyllica.sonat.adapter.netty.NettyServer;

public class NettyChatServer extends NettyServer {

	public NettyChatServer(int port) {
		super(port);
	}
	
	@Override
	public void prepare() {
		
		System.out.println("Starting Netty Chat Server..."+port);
		
		bootstrap
		.option(ChannelOption.TCP_NODELAY, true)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.childHandler(new NettyChatServerInitializer(new HashMap<String, String>()));
	}
	
}
