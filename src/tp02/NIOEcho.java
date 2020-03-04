package tp02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOEcho {

	public static void main(String[] args) {
		try {
			new NIOEcho();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static final int PORT_SERVICE = 9876;
	SocketChannel canal;

	public NIOEcho() throws IOException {
		SocketAddress remote = new InetSocketAddress("localhost",2121);
		canal = SocketChannel.open();
		canal.configureBlocking(false);
		if(canal.connect(remote)) 
			System.out.println("Connection terminée");
		while(!canal.finishConnect()) {}
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		String msg = "";
		Scanner sc = new Scanner(System.in);
		while (canal.isConnected() && !msg.equals("FIN")) {
			buffer.clear();
			System.out.println("Entrer votre message : ");
			msg = sc.nextLine();
			canal.write(ByteBuffer.wrap(msg.getBytes()));
			while(canal.read(buffer) == 0) {}
			String s = new String(buffer.array());
			System.out.print("Recu : " + s);
		}
		canal.close();
	}

}
