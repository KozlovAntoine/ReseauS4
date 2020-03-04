package tp02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurBegaiement {

	public static void main(String[] args) {
		try {
			new ServeurBegaiement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static final int PORT_SERVICE = 9876;
	private ServerSocket serverSocket;

	public ServeurBegaiement() throws IOException {
		serverSocket = new ServerSocket(PORT_SERVICE);
		this.attenteClient();
	}

	private void attenteClient() {
		Socket client = null;
		while (true) {
			try {
				client = serverSocket.accept();
				new ClientResponse(client).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}			
	}

	private String multiPhrase(String phrase) {
		String nbreStr = phrase.split(":")[0];
		int nbre;
		try {
			nbre = Integer.valueOf(nbreStr);
		} catch (NumberFormatException e) {
			return "1Erreur : multiplicateur manquant.";
		}
		phrase = phrase.split(":")[1];
		if(phrase.matches(".*\\d.*")) return "2: Erreur nombre";
		String[] mots = phrase.split(" ");
		String res = "";
		for (String s : mots) {
			for (int i = 0; i < nbre; i++)
				res += s + " ";
		}
		return "0" + res;
	}
	class ClientResponse extends Thread {
		private Socket socket;
		private PrintWriter pw = null;
		private BufferedReader br = null;
		
		public ClientResponse(Socket socket) throws IOException {
			this.socket = socket;
			this.pw = new PrintWriter(socket.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Connection nouveau client");
		}
		
		public boolean dialogue() {
			try {
				String arrive = br.readLine();
				if(arrive == null) return false;
				System.out.println("Arrivé : " + arrive);
				String sortie = multiPhrase(arrive);
				System.out.println("Sortie : " + sortie);
				pw.println(sortie);
				pw.flush();
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		
		public void run() {
			while(dialogue() && socket.isConnected());
			
			try {
				socket.close();
				System.out.println("Fermeture de la connection du client.");
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}



