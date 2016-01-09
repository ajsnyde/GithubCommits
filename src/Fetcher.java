import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Fetcher {

	String fetch(String username){
	    String html;
	    html = fetchHTML(username);
		return username;	// needs changed
	}
	
	String fetchHTML(String username){
	    String content = null;
	    URLConnection connection = null;
	    try {
	      connection =  new URL("https://github.com/users/"+username+"/contributions").openConnection();
	      Scanner scanner = new Scanner(connection.getInputStream());
	      scanner.useDelimiter("\\Z");
	      content = scanner.next();
	    }catch ( Exception ex ) {
	        ex.printStackTrace();
	    }
		return content;
	}
}
