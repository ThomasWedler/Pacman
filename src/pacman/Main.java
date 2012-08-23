package pacman;
import java.io.File;
import java.io.IOException;

import siris.pacman.BasicPacman;
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
		//JavaInterface test = new JavaInterface(true);
		//test.startRenderer(800, 600);
		MyLevel.renderLevel(new File("src/Maps/level2.txt"));	
		
		
		//GoalTestFunction gtf = new GoalTestFunction(test);
		//JavaGraphWrapper.drawGraph(Level.getTileNodes().getFirst(), test);
	
		//Thread.sleep(20000);
			
		MyGraphSearch gs = new MyGraphSearch();
		//gs.search(Level.getTileNodes().getFirst(), gtf);
		
		BasicPacman.startPacman(new MyPacmanAI(), MyLevel.getTileNodes().getFirst(), gs, true);
		
	}


}
