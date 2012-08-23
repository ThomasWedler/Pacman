package pacman;
import siris.java.JavaInterface;
import siris.pacman.PacmanAI;
import siris.pacman.graph.EntityNode;
import siris.pacman.graph.JavaGraphWrapper;
import siris.pacman.graph.MovingEntityNode;
import basic.Node;

public class Pacman implements PacmanAI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JavaInterface test = new JavaInterface(true, false);
		test.startRenderer(800, 600);
		Node north = new Node();
		Node south = new Node();
		Node east = new Node();
		Node west = new Node();
		Node center = new Node(north, east, south, west);
		north.setNeighbourSouth(center);
		west.setNeighbourEast(center);
		south.setNeighbourNorth(center);
		east.setNeighbourWest(center);
		JavaGraphWrapper.drawGraph(center, test);
	}

	@Override
	public void onSimulationStep(float deltaT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDecisionRequired(MovingEntityNode entityToDecideFor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollision(EntityNode e1, EntityNode e2) {
		// TODO Auto-generated method stub
		
	}

}
