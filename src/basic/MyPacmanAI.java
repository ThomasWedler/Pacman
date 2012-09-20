package basic;

import java.util.LinkedList;

import pacman.Game;
import search.AStar;
import siris.pacman.graph.EntityNode;
import siris.pacman.graph.MovingEntityNode;

public class MyPacmanAI implements siris.pacman.PacmanAI {
	
	private String type = "AStar";
	private float powerUpTime = 0;

	@Override
	public void onSimulationStep(float deltaT) {
		if (Game.pacman.isPoweredUp()) {
			powerUpTime += deltaT;
			Game.pacman.setSpeed(2f);
		} else {
			Game.pacman.setSpeed(1f);
		}
		if (powerUpTime > 10000) {
			powerUpTime = 0;
			Game.pacman.setPoweredUp(false);
			Game.pacman.setSpeed(1f);
		}
	}

	@Override
	public void onDecisionRequired(MovingEntityNode entityToDecideFor) {
		if (type.equals("Random")) {
			if (entityToDecideFor instanceof MyGhost) {
				if (((MyGhost) entityToDecideFor).seesPacman()) {
					System.out.println("seen");
					//useAStar(entityToDecideFor);
				} else
					randomDirection(entityToDecideFor);
			}
		}
		if (type.equals("AStar")) {
			useAStar(entityToDecideFor);
		}
	}

	@Override
	public void onCollision(EntityNode e1, EntityNode e2) {
		if (e1.equals(Game.pacman) || e2.equals(Game.pacman)) {
			if (e1 instanceof MyGhost || e2 instanceof MyGhost) {
				System.out.println("Loser!");
				System.exit(0);
			}
			if (e1 instanceof MyGoodie || e2 instanceof MyGoodie) {
				Game.score += 100;
				if (Game.level.getGoodies().size() == 0) {
					System.out.println("Winner!");
					System.exit(0);
				}
			}
			if (e1 instanceof MyPowerUp || e2 instanceof MyPowerUp) {
				Game.pacman.setPoweredUp(true);
			}
		}
	}
	
	public void randomDirection(MovingEntityNode e) {
		double random = Math.random();
		if (random <= 0.25) {
			e.setDesiredMovementDirection(100, 0);
		}
		if (random > 0.25 && random <= 0.5) {
			e.setDesiredMovementDirection(0, 100);
		}
		if (random > 0.5 && random <= 0.75) {
			e.setDesiredMovementDirection(-100, 0);
		}
		if (random > 0.75) {
			e.setDesiredMovementDirection(0, -100);
		}
	}
	
	public void useAStar(MovingEntityNode entityToDecideFor) {
		MyEntityNode n = (MyEntityNode) entityToDecideFor;
		LinkedList<MyTileNode> path = new AStar(n, Game.pacman).getResult();	
		MyTileNode actual = (MyTileNode) n.getTileNode();
		if (path.size() > 1) {
			String direction = actual.getDifferenceBetweenPositions(path.get(1));
			System.out.println(direction);
			if (direction.equals("left"))
				entityToDecideFor.setDesiredMovementDirection(-100, 0);
			if (direction.equals("right"))
				entityToDecideFor.setDesiredMovementDirection(100, 0);
			if (direction.equals("up"))
				entityToDecideFor.setDesiredMovementDirection(0, 100);
			if (direction.equals("down"))
				entityToDecideFor.setDesiredMovementDirection(0, -100);
		}
	}

}
