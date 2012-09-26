package basic;

import java.util.LinkedList;

import pacman.Game;
import search.AStar;
import siris.pacman.graph.EntityNode;
import siris.pacman.graph.MovingEntityNode;
import siris.pacman.util.TilePosition;

public class MyPacmanAI implements siris.pacman.PacmanAI {
	
	@Override
	public void onSimulationStep(float deltaT) {
		checkBulletTime();
		
		for (MyGhost ghost : Game.level.getGhosts()) {
			if (ghost.lookForPacman())
				ghost.setSeePacman(true);
			if (ghost.hearForPacman())
				ghost.setHearPacman(true);
			if (ghost.visionForPacman(deltaT))
				ghost.setVisionPacman(true);
			if (ghost.feelForPacman())
				ghost.setFeelPacman(true);
		}
				
	}

	@Override
	public void onDecisionRequired(MovingEntityNode entityToDecideFor) {
		boolean crossing = false;
		String s = Game.level.getTileNodes().get(entityToDecideFor.getTileNode());
		
		if (s.equals("X") || s.equals("P") || s.equals("G"))
			crossing = true;
		
		if (entityToDecideFor instanceof MyGhost) {
			MyGhost ghost = (MyGhost) entityToDecideFor;
			LinkedList<MyTileNode> aStarPath = new AStar(ghost, Game.pacman).getResult();
			aStarPath.removeFirst();
			if (ghost.seesPacman()) {
				System.out.println("seen");
				ghost.setDesiredPath(aStarPath);
				ghost.setSeePacman(false);
			} else if (ghost.hearsPacman()) {
				System.out.println("heard");
				ghost.setDesiredPath(aStarPath);
				ghost.setHearPacman(false);
			} else if (ghost.visionsPacman()) {
				System.out.println("visioned");
				ghost.setDesiredPath(aStarPath);
				ghost.setVisionPacman(false);
			} else if (ghost.feelsPacman()) {
				System.out.println("fealt");
				ghost.setDesiredPath(aStarPath);
				ghost.setFeelPacman(false);
			} else if (ghost.getDesiredPath().isEmpty() || crossing) {
				randomDirection(ghost);
			}
			
			MyTileNode ghostPosition = (MyTileNode) ghost.getTileNode();
			MyTileNode nextStep = ghost.getDesiredPath().peek();
			if (ghost.getDesiredPath().size() > 1)
				ghost.getDesiredPath().removeFirst();
			
			int x = 0;
			int y = 0;
			String direction = ghostPosition.getDifferenceBetweenPositions(nextStep);

			if (direction.equals("right"))
				x = 1;
			else if (direction.equals("left"))
				x = -1;
			else if (direction.equals("up"))
				y = 1;
			else if (direction.equals("down"))
				y = -1;
			
			entityToDecideFor.setDesiredMovementDirection(x, y);
		}
	}

	@Override
	public void onCollision(EntityNode e1, EntityNode e2) {
		if (e1 instanceof MyPacman) {
			if (e2 instanceof MyGhost) {
				System.out.println("Loser!");
				System.out.println("Your Score: " + Game.score);
				System.exit(0);
			}
			if (e2 instanceof MyGoodie) {
				Game.level.setGoodieCounter(Game.level.getGoodieCounter() - 1);
				Game.score += 100;
				Game.pacman.setPowerLevel(Game.pacman.getPowerLevel() + Game.goodiePower);
				if (Game.level.getGoodieCounter() == 0) {
					System.out.println("Winner!");
					System.out.println("Your Score: " + Game.score);
					System.exit(0);
				}
			}
		}
	}
	
	public void randomDirection(MyGhost ghost) {
		MyTileNode result = new MyTileNode();
		int random = (int) (Math.random() * (5 - 1) + 1);
		switch (random) {
			case 1:	result.setPosition(new TilePosition(1, 0));
					break;
			case 2:	result.setPosition(new TilePosition(0, 1));
					break;
			case 3:	result.setPosition(new TilePosition(-1, 0));
					break;
			case 4: result.setPosition(new TilePosition(0, -1));
					break;
		}
		ghost.getDesiredPath().clear();
		ghost.getDesiredPath().add(result);
	}
	
	public void checkBulletTime() {
		boolean close = false;
		MyTileNode pacmanNode = (MyTileNode) Game.pacman.getTileNode();
		
		for (MyNode n : pacmanNode.neighbors()) {
			if (n instanceof MyTileNode) {
				MyTileNode ghostNode = (MyTileNode) n;
				for (MyGhost ghost : Game.level.getGhosts()) {
					if (ghost.getTileNode().equals(ghostNode)) {
						close = true;
					}
				}
			}
		}
		
		if (close) {
			Game.pacman.setSpeed(0.5f);
			for (MyGhost ghost : Game.level.getGhosts()) {
				ghost.setSpeed(0.5f);
			}
		} else {
			Game.pacman.setSpeed(1f);
			for (MyGhost ghost : Game.level.getGhosts()) {
				ghost.setSpeed(1f);
			}
		}
	}

}
