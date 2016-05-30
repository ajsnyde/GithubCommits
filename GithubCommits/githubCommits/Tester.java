package githubCommits;

public class Tester {

	public static void main(String[] args) {
		Visual vis = new Visual();
		Thread thread = new Thread(vis);
		thread.start();
	}

}
