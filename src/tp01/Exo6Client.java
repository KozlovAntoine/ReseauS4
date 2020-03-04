package tp01;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Exo6Client {
	
	public static void main(String[] args) {
		try {
			InetAddress adresse = InetAddress.getByName(args[0]);
			int port = Integer.valueOf(args[1]);
			new Exo6Client(adresse, port);
		} catch (IOException e) {
			System.err.println("Arguments manquant(s) : <IP> et/ou <PORT>");
			System.exit(1);
		}
	}
	
	private MulticastSocket socketEmission;
	private InetAddress adresse;
	private int port;
	
	public Exo6Client(InetAddress adresse, int port) throws IOException {
		this.adresse = adresse;
		this.port = port;
		socketEmission = new MulticastSocket();
	}
	
	public void go() {
	    BufferedReader entreeClavier;
	    
	    try {
	       entreeClavier = new BufferedReader(new InputStreamReader(System.in));
	       while(true) {
				  String texte = entreeClavier.readLine();
				  emettre(texte);
	       }
	    }
	    catch (Exception exc) {
	       System.out.println(exc);
	    }
	  } 

	  void emettre(String texte) throws Exception {
			byte[] contenuMessage;
			DatagramPacket message;
		
			ByteArrayOutputStream sortie = new ByteArrayOutputStream(); 
			texte =  adresse.getHostAddress().toString() + " : " + texte ;
			(new DataOutputStream(sortie)).writeUTF(texte); 
			contenuMessage = sortie.toByteArray();
			message = new DatagramPacket(contenuMessage, contenuMessage.length, adresse, port);
			socketEmission.send(message);
	  }

}
