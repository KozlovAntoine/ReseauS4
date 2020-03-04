package tp02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientTCP1 {
	
	public static void main(String[] args) {
		ClientTCP1 clt = new ClientTCP1();
		try {
			clt.client1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void client1() throws IOException{
		Socket mySocket = new Socket("AYOU02", 1313);
		InputStream in = mySocket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}
		mySocket.close();
	}

}
