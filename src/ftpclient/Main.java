package ftpclient;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Server server = null;
		User user = null;
		Scanner sc = new Scanner(System.in);
		
		if(args.length > 1) {
			if(Server.parsePort(args[1]) > 0) {
				server = new Server(args[0], Server.parsePort(args[1]));
			} else {
				System.exit(1);
			}
		} 
		if(server == null) 
			server = new Server();
		user = new User();
		server.connect(user);
	}

}
