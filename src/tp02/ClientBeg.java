package tp02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientBeg {
	
	public static void main(String[] args) {
		try {
			new ClientBeg().client();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void client() throws IOException{
		Socket mySocket = new Socket("localhost", 9876);
		InputStream in = mySocket.getInputStream();
		OutputStream out = mySocket.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		PrintWriter writer = new PrintWriter(out, true);
		String msg = "";
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		while(!fin) {
			System.out.println("Entrer votre message :");
			msg = sc.nextLine();
			if(!msg.equals("FIN") && !msg.equals(".")) {
				System.out.println("Niveau de bégaiement : ");
				writer.println(sc.nextLine() + ":" + msg);
				receptionMessage(br.readLine());
				out.flush();
			} else fin = true;
		}
		mySocket.close();
		System.out.println("Au revoir");
	}
	
	private void receptionMessage(String reception) {
		String r = reception.substring(1);
		if(reception.charAt(0) != '0') {
			System.err.println(r);
		} else System.out.println(r);
	}

}
