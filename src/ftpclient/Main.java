package ftpclient;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		ClientSocket server = null;
		User user = null;
		Scanner sc = new Scanner(System.in);
		
		if(args.length > 1) {
			if(ClientSocket.parsePort(args[1]) > 0) {
				server = new ClientSocket(args[0], ClientSocket.parsePort(args[1]));
			} else {
				System.exit(1);
			}
		} 
		if(server == null) 
			server = new ClientSocket();
		user = new User();
		server.connect(user);
	}

}
