package githubCommits;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fetcher {	// Should be able to serve many different Visuals with only one class
	
	ArrayList<Day> fetch(String username){	// returns list of Days from username
	    String html;
	    html = fetchHTML(username);
		return regexHTML(html);
	}
	
	String fetchHTML(String username){
	    String content = null;
	    URLConnection connection = null;
	    try {
	      connection =  new URL("https://github.com/users/"+username+"/contributions").openConnection();
	      Scanner scanner = new Scanner(connection.getInputStream());
	      scanner.useDelimiter("\\Z");
	      content = scanner.next();
	      scanner.close();
	    }catch ( MalformedURLException ex ) {
	    	System.out.println("Could not retrieve Github data: Bad URL, try removing URL-unsafe characters from your username!");
	        ex.printStackTrace();
	        content = "";
	    } catch (IOException e) {
	    	System.out.println("Could not retrieve Github data: Bad IO, ensure proper connectivity and resource allocation!");
	        e.printStackTrace();
	        content = "";
		}
		return content;
	}
	
	ArrayList<Day> regexHTML(String html) {
		Day day;
		ArrayList<Day> days = new ArrayList<Day>();
		String color;
		int commits;
		GregorianCalendar date = new GregorianCalendar();
		
		Pattern p = Pattern.compile("fill=\"(\\#[0-9a-e]{6})\".*data-count=\"(\\d+)\".*(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d)\"");
		/*
		 * group 0 sample: fill="#eeeeee" data-count="0" data-date="2015-01-07"
		 * group 1 - HTML color code
		 * group 2 - commit count
		 * group 3 - year
		 * group 4 - month (1-12)
		 * group 5 - day   (1-32)
		 */
		Matcher m = p.matcher(html);
		while(m.find()) {
			color = m.group(1);
			commits = Integer.parseInt(m.group(2));
			date.set(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)));
			day = new Day(date, commits, color);
			days.add(day);
		}
		return days;	// should return 365 days
	}
}
