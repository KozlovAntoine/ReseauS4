package tp02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NIOEchoServer {

	public static void main(String[] args) {
		try {
			new NIOEchoServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public NIOEchoServer() throws IOException {
		ServerSocketChannel serveur = ServerSocketChannel.open();
		serveur.configureBlocking(false);
		SocketAddress socket = new InetSocketAddress(9876);
		serveur.bind(socket);
		Selector selector = Selector.open();
		serveur.register(selector, SelectionKey.OP_ACCEPT);
		SocketChannel client = null;
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		selector.select();
		Set<SelectionKey> listKeys = selector.selectedKeys();
		while(true) {
			for (SelectionKey key : listKeys) {
				if (key.isAcceptable()) {
					client = serveur.accept(); // a p p el non b l o q u a n t : )
					client.configureBlocking(false); // c a n al non b l o q u a n t
					client.register(selector, SelectionKey.OP_READ);
					System.out.println("Client");// e n r e g i s t r em e n t c l i e n t
				} else if (key.isReadable()) {
					System.out.println("Read");
					client.register(selector, SelectionKey.OP_WRITE);
					while(client.read(buffer) == 0);
				} else if (key.isWritable()) {
					System.out.println("Write");
					client.register(selector, SelectionKey.OP_READ);
					client.write(buffer);
					buffer.clear();
				}
				listKeys.remove(key);
				selector.select();
				listKeys = selector.selectedKeys();
			}
		}
	}

}
