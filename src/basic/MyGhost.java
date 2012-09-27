package basic;

import java.util.LinkedList;
import pacman.Game;

public class MyGhost extends MyMovingEntityNode implements siris.pacman.graph.Ghost {

	private LinkedList<MyTileNode> desiredPath = new LinkedList<MyTileNode>();
	private int number = 0;
	private boolean sensePacman = false;
	private int hearRange = 1;
	private int visionRange = 2;
	private float timer = 0;

	public void setNr(int x) {
		number = x;
	}

	@Override
	public int getNr() {
		return number;
	}

	public void sensePacman(float elapsed) {
		if (lookForPacman() || hearForPacman() || visionForPacman(elapsed) || feelForPacman())
			sensePacman = true;
	}

	private boolean lookForPacman() {
		MyTileNode position = (MyTileNode) this.getTileNode();
		boolean result = lookOut(position);
		return result;
	}

	private boolean lookOut(MyTileNode position) {
		for (MyNode n : position.getNeighbors()) {
			if (n instanceof MyPacman) {
				return true;
			} else if (n instanceof MyTileNode) {
				MyTileNode node = (MyTileNode) n;
				String direction = position.getDifferenceBetweenPositions(node);
				if (this.getDirection().equals("left") && direction.equals("left"))
					return lookOut(node);
				else if (this.getDirection().equals("right") && direction.equals("right"))
					return lookOut(node);
				else if (this.getDirection().equals("up") && direction.equals("up"))
					return lookOut(node);
				else if (this.getDirection().equals("down") && direction.equals("down"))
					return lookOut(node);
			}
		}
		return false;
	}

	private boolean hearForPacman() {
		int xMax = this.getTileNode().position().x() + hearRange;
		int xMin = this.getTileNode().position().x() - hearRange;
		int yMax = this.getTileNode().position().y() + hearRange;
		int yMin = this.getTileNode().position().y() - hearRange;
		for (MyTileNode node : Game.level.getTileNodes().keySet()) {
			if (node.position().x() <= xMax && node.position().x() >= xMin && node.position().y() <= yMax && node.position().y() >= yMin) {
				if (Game.pacman.getTileNode().position().equals(node.position()))
					return true;
			}
		}
		return false;
	}

	private boolean visionForPacman(float elapsed) {
		timer += elapsed;
		if (timer > 10) {
			timer = 0;
			int counter = 0;
			int random = (int) (Math.random() * Game.level.getTileNodes().keySet().size());
			MyTileNode randomNode = null;
			for (MyTileNode node : Game.level.getTileNodes().keySet()) {
				if (counter == random) {
					randomNode = node;
					break;
				} else
					counter++;
			}
			int xMax = randomNode.position().x() + visionRange;
			int xMin = randomNode.position().x() - visionRange;
			int yMax = randomNode.position().y() + visionRange;
			int yMin = randomNode.position().y() - visionRange;
			for (MyTileNode node : Game.level.getTileNodes().keySet()) {
				if (node.position().x() <= xMax && node.position().x() >= xMin && node.position().y() <= yMax && node.position().y() >= yMin) {
					if (Game.pacman.getTileNode().position().equals(node.position()))
						return true;
				}
			}
			return false;
		} else
			return false;
	}

	private boolean power = true;

	private boolean feelForPacman() {
		if (Game.pacman.getPowerLevel() > Game.maximumPowerLevel) {
			if (power) {
				System.out.println("Over 9000!!!");
				power = false;
			}
			return true;
		}
		return false;
	}

	public LinkedList<MyTileNode> getDesiredPath() {
		return desiredPath;
	}

	public void setDesiredPath(LinkedList<MyTileNode> desiredPath) {
		this.desiredPath = desiredPath;
	}

	public boolean sensesPacman() {
		return sensePacman;
	}

	public void setSensePacman(boolean sensePacman) {
		this.sensePacman = sensePacman;
	}

}
