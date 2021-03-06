package examples.chat.adapter.mina.client;

import java.nio.charset.Charset;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;

import examples.chat.ClientChatListener;
import br.com.etyllica.network.adapter.mina.MinaClient;

public class MinaChatClient extends MinaClient {

	protected ClientChatListener listener;
	
	public MinaChatClient(String host, int port, ClientChatListener listener) {
		super(host, port);
		this.listener = listener;
	}
	
	@Override
	public void prepare() {
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		
		connector.setHandler(new MinaChatClientHandler(listener));
	}
	
}
