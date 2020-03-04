package ftpclient;

import java.util.Scanner;

public class User {
	
	private String user, pass;
	
	public User() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom d'utilisateur :");
		this.user = sc.nextLine();
		//this.pass = new String(System.console().readPassword("Mot de passe : "));
	}

}
