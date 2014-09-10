package br.com.etyllica.sonat.adapter.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import br.com.etyllica.sonat.client.Client;
import br.com.etyllica.sonat.client.ClientImpl;
import br.com.etyllica.sonat.client.ClientListener;

public class NettyClient extends ClientImpl implements Client {

	private Channel channel;

	private EventLoopGroup group;
	
	protected Bootstrap bootstrap;

	public NettyClient(String host, int port) {
		super(host, port);
	}

	public NettyClient(String host, int port, ClientListener listener) {
		super(host, port, listener);
	}

	public void init() {
		group = new OioEventLoopGroup();
		
		bootstrap = new Bootstrap();
		bootstrap.group(group);
	}

	public void finish() {
		group.shutdownGracefully();
	}

	public void sendMessage(String message) {
		channel.writeAndFlush(message+"\r\n");
	}

	@Override
	public void prepare() {
		
	}

	@Override
	public void connect() throws Exception {
		channel = bootstrap.connect(host, tcpPort).sync().channel();
	}

}
