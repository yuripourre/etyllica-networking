package br.com.etyllica.sonat.netty.client;


import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.client.ClientImpl;
import br.com.etyllica.sonat.client.ClientListener;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.oio.OioSocketChannel;

public class NettyClient extends ClientImpl implements Client {

	private Channel channel;

	private EventLoopGroup group;

	public NettyClient(String host, int port) {
		super(host, port);
	}

	public NettyClient(String host, int port, ClientListener listener) {
		super(host, port, listener);
	}

	public void init() throws Exception {

		group = new OioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap()
		.group(group)
		.channel(OioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY, true)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.handler(new NettyChatClientInitializer(listener));

		channel = bootstrap.connect(host, port).sync().channel();

	}

	public void finish() {
		group.shutdownGracefully();
	}

	public void sendMessage(String message) {
		channel.writeAndFlush(message+"\r\n");
	}

}
