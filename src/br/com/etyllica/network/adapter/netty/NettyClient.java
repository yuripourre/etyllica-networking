package br.com.etyllica.network.adapter.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import br.com.etyllica.network.client.ClientImpl;

public class NettyClient extends ClientImpl {

	private Channel channel;

	private EventLoopGroup group;
	
	protected Bootstrap bootstrap;

	public NettyClient(String host, int port) {
		super(host, port);
	}

	@Override
	public void init() {
		group = new OioEventLoopGroup();
		
		bootstrap = new Bootstrap();
		bootstrap.group(group);
	}

	@Override
	public void finish() {
		group.shutdownGracefully();
	}

	@Override
	public void sendTCP(String message) {
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
