package basic;

import pacman.Game;
import search.AStar;
import siris.pacman.graph.EntityNode;
import siris.pacman.graph.MovingEntityNode;

public class MyPacmanAI implements siris.pacman.PacmanAI {

	private String behaviour = "NORMAL";

	@Override
	public void onSimulationStep(float deltaT) {
		checkBulletTime();

		for (MyGhost ghost : Game.level.getGhosts()) {
			ghost.sensePacman(deltaT);
		}

	}

	@Override
	public void onDecisionRequired(MovingEntityNode entityToDecideFor) {
		boolean directionIsSet = false;

		if (entityToDecideFor instanceof MyGhost) {
			MyGhost ghost = (MyGhost) entityToDecideFor;

			if (behaviour.equals("DUMB")) {
				randomDirection(ghost);
				directionIsSet = true;
			} else if (behaviour.equals("NORMAL")) {
				boolean crossing = false;
				String s = Game.level.getTileNodes().get(ghost.getTileNode());
				if (s.equals("X") || s.equals("P") || s.equals("G"))
					crossing = true;
				boolean found = checkSenses(ghost);
				if (!found && crossing) {
					randomDirection(ghost);
					directionIsSet = true;
				}
			} else if (behaviour.equals("ALLKNOWING"))
				ghost.setDesiredPath(new AStar(ghost, Game.pacman).getResult());

			if (!directionIsSet && ghost.getDesiredPath().size() > 0) {
				int x = 0;
				int y = 0;
				MyTileNode ghostPosition = (MyTileNode) ghost.getTileNode();
				MyTileNode nextStep = null;

				if (ghost.getDesiredPath().size() < 3)
					nextStep = (MyTileNode) Game.pacman.getTileNode();
				else {
					for (int i = 0; i < ghost.getDesiredPath().size(); i++) {
						if (ghost.getTileNode().position().x() == ghost.getDesiredPath().get(i).position().x() && ghost.getTileNode().position().y() == ghost.getDesiredPath().get(i).position().y()) {
							nextStep = ghost.getDesiredPath().get(i + 1);
						}
					}
				}

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
	}

	@Override
	public void onCollision(EntityNode e1, EntityNode e2) {
		if (e1 instanceof MyPacman) {
			if (e2 instanceof MyGhost) {
				System.out.println("Loser!");
				System.out.println("Your Score: " + Game.score);
				System.exit(0);
			} else if (e2 instanceof MyGoodie) {
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

	private void randomDirection(MyGhost ghost) {
		int x = 0;
		int y = 0;
		switch ((int) (Math.random() * (5 - 1) + 1)) {
			case 1:
				x = 1;
				break;
			case 2:
				y = 1;
				break;
			case 3:
				x = -1;
				break;
			case 4:
				y = -1;
				break;
		}
		ghost.setDesiredMovementDirection(x, y);
	}

	private boolean checkSenses(MyGhost ghost) {
		if (ghost.sensesPacman()) {
			System.out.println("sensed");
			ghost.setDesiredPath(new AStar(ghost, Game.pacman).getResult());
			ghost.setSensePacman(false);
			return true;
		}
		return false;
	}

	private void checkBulletTime() {
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
