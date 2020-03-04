package tp02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientHTTP {
	
	public static void main(String[] args) {
		
		HttpURLConnection con;
		try {
			URL url = new URL("http://localhost:8000/");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
