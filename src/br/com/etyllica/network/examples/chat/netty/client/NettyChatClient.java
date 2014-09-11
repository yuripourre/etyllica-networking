package br.com.etyllica.network.examples.chat.netty.client;

import io.netty.channel.ChannelOption;
import io.netty.channel.socket.oio.OioSocketChannel;
import br.com.etyllica.network.adapter.netty.NettyClient;
import br.com.etyllica.network.examples.chat.ClientChatListener;

public class NettyChatClient extends NettyClient {

	protected ClientChatListener listener;
	
	public NettyChatClient(String host, int port, ClientChatListener listener) {
		super(host, port);
		this.listener = listener;
	}

	@Override
	public void prepare() {
		
		bootstrap.channel(OioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY, true)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.handler(new NettyChatClientInitializer(listener));
	}
	
}
