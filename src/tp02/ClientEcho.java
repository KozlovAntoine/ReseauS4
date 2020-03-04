package tp02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEcho {
	
	public static void main(String[] args) {
		ClientEcho ce = new ClientEcho();
		try {
			ce.client();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void client() throws IOException{
		Socket mySocket = new Socket("AYOU02", 7777);
		InputStream in = mySocket.getInputStream();
		OutputStream out = mySocket.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		PrintWriter writer = new PrintWriter(out, true);
		String msg = "";
		Scanner sc = new Scanner(System.in);
		while(!msg.equals("FIN") && !msg.equals(".")) {
			System.out.println("Entrer msg : ");
			msg = sc.nextLine();
			
			writer.println(msg);
			
			System.out.println("Reception : " + br.readLine());
			
			out.flush();
		}
		mySocket.close();
	}

}
