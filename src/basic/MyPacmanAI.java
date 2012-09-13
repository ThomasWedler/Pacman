package basic;

import siris.pacman.graph.EntityNode;
import siris.pacman.graph.MovingEntityNode;

public class MyPacmanAI implements siris.pacman.PacmanAI {

	@Override
	public void onSimulationStep(float deltaT) {

	}

	@Override
	public void onDecisionRequired(MovingEntityNode entityToDecideFor) {
		/*
		 * MyMovingEntityNode n = (MyMovingEntityNode) entityToDecideFor;
		 * MyPacman pacman = new MyPacman(); pacman.setPosition(4, 4);
		 * for(MyTileNode node : MyLevel.nodes) { if(node.position().x() == 4 &&
		 * node.position().y() == 4) { pacman.connectTo(node); } } new
		 * AStar((MyEntityNode)n, (MyEntityNode)pacman);
		 */
	}

	@Override
	public void onCollision(EntityNode e1, EntityNode e2) {
		if (e1.getClass().getName().equals("basic.MyPacman") || e2.getClass().getName().equals("basic.MyPacman")) {
			if (e1.getClass().getName().equals("basic.MyGhost") || e2.getClass().getName().equals("basic.MyGhost")) {
				System.out.println("Loser!");
				System.exit(0);
			}
		}
	}

}
