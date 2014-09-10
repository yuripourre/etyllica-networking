package br.com.etyllica.sonat.chat.netty.client;


import br.com.etyllica.sonat.chat.ClientChatListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyChatClientHandler extends SimpleChannelInboundHandler<String> {

	private ClientChatListener listener;
		
	private static final String COMMAND_USERS = "/users ";//whiteSpacing
	
	private static final String COMMAND_MESSAGE = "/msg ";//whiteSpacing
	
	public NettyChatClientHandler(ClientChatListener listener) {
		super();
		
		this.listener = listener;		
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
		if(msg.startsWith(COMMAND_USERS)) {
			
			String[] names = msg.substring(COMMAND_USERS.length()).split(" "); 
			
			listener.updateNames(names);
			
		} else if (msg.startsWith(COMMAND_MESSAGE)) {
			
			String message = msg.substring(COMMAND_MESSAGE.length());
			
			String[] parts = msg.split(" ");
			
			String userName = parts[1];
			
			message = message.substring(userName.length()+" ".length());
			
			listener.receiveMessage(userName, message);
			
		}
		
	}
	
}
