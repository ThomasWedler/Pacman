package pacman;
import java.io.IOException;

import siris.java.JavaInterface;
import siris.pacman.BasicPacman;
import siris.pacman.graph.JavaGraphWrapper;
import basic.MyGoalTestFunction;
import basic.MyGraphSearch;
import basic.MyLevel;
import basic.MyPacman;
import basic.MyPacmanAI;

public class Game {
	
	//
	// Martin:
	// goodies und powerups
	// sleep am anfang
	// desired und current movement direction
	
	// ToDos:
	// AStar aufruf
	// see / hear variablen
	//

	public static MyLevel level;
	public static int score = 0;
	public static MyPacman pacman;
	
	private String mode = "Normal";
	private String levelname = "src/Maps/testlevel.txt";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new Game();
	}
	
	public Game() throws IOException, InterruptedException {
		level = new MyLevel(levelname);
		pacman = level.getPacman();
		if (mode.equals("Normal"))
			start();
		if (mode.equals("TestSearch"))
			testSearch();
	}
	
	private void start() {
		MyGraphSearch gs = new MyGraphSearch();
		BasicPacman.startPacman(new MyPacmanAI(), level.getTileNodes().getFirst(), gs, true);
	}
	
	private void testSearch() throws InterruptedException {
		JavaInterface ji = new JavaInterface(true, true);
		ji.startRenderer(800, 600);
		MyGraphSearch gs = new MyGraphSearch();
		MyGoalTestFunction gtf = new MyGoalTestFunction();
		JavaGraphWrapper.drawGraph(level.getTileNodes().getFirst(), ji);
		Thread.sleep(10000);			
		gs.search(level.getTileNodes().getFirst(), gtf);
	}

}
