package pacman;
import java.io.File;
import java.io.IOException;

import siris.java.JavaInterface;
import siris.pacman.BasicPacman;
import siris.pacman.graph.JavaGraphWrapper;
import basic.MyGraphSearch;
import basic.MyLevel;
import basic.MyPacmanAI;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */

	public static void main(String[] args) throws IOException, InterruptedException {
		//testSearch();
		start();
	}
	
	public static void start() throws IOException {
		MyGraphSearch gs = new MyGraphSearch();
		MyLevel.renderLevel(new File("src/Maps/testlevel.txt"));
		BasicPacman.startPacman(new MyPacmanAI(), MyLevel.getTileNodes().getFirst(), gs, true);
	}
	
	public static void testSearch() throws InterruptedException, IOException {
		JavaInterface test = new JavaInterface(true, true);
		test.startRenderer(800, 600);
		MyLevel.renderLevel(new File("src/Maps/testlevel.txt"));
		MyGraphSearch gs = new MyGraphSearch();
		//MyGoalTestFunction gtf = new MyGoalTestFunction(test);
		JavaGraphWrapper.drawGraph(MyLevel.getTileNodes().getFirst(), test);
		Thread.sleep(10000);			
		//gs.search(MyLevel.getTileNodes().getFirst(), gtf);
	}


}
