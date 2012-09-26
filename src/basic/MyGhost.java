package basic;

import java.util.LinkedList;

import pacman.Game;


public class MyGhost extends MyMovingEntityNode implements siris.pacman.graph.Ghost {

	private LinkedList<MyTileNode> desiredPath = new LinkedList<MyTileNode>(); 
	private int number = 0;
	private boolean seePacman = false;
	private boolean hearPacman = false;
	private boolean visionPacman = false;
	private boolean feelPacman = false;
	private int range = 3;
	private float timer = 0;

	public void setNr(int x) {
		number = x;
	}

	@Override
	public int getNr() {
		return number;
	}
	
	public boolean lookForPacman() {
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
	
	public boolean hearForPacman() {
		// to do
		return false;
	}
	
	public boolean visionForPacman(double elapsed) {
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
			int xMax = randomNode.position().x() + range;
			int xMin = randomNode.position().x() - range;
			int yMax = randomNode.position().y() + range;
			int yMin = randomNode.position().y() - range;
			for (MyTileNode node : Game.level.getTileNodes().keySet()) {
				if (node.position().x() <= xMax && node.position().x() >= xMin && node.position().y() <= yMax && node.position().y() >= yMin) {
					if (Game.pacman.getTileNode().position().equals(node.position())) {
						return true;
					}
				}
			}
			return false;	
		} else
			return false;
	}
	
	private boolean power = true;
	
	public boolean feelForPacman() {
		if (Game.pacman.getPowerLevel() > Game.maximumPowerLevel) {
			if (power) {
				System.out.println("Over 9000!!!");
				power = false;
			}
			return true;
		}
		return false;
	}

	public boolean seesPacman() {
		return seePacman;
	}

	public void setSeePacman(boolean seePacman) {
		this.seePacman = seePacman;
	}

	public boolean hearsPacman() {
		return hearPacman;
	}

	public void setHearPacman(boolean hearPacman) {
		this.hearPacman = hearPacman;
	}

	public boolean visionsPacman() {
		return visionPacman;
	}

	public void setVisionPacman(boolean visionPacman) {
		this.visionPacman = visionPacman;
	}

	public boolean feelsPacman() {
		return feelPacman;
	}

	public void setFeelPacman(boolean feelPacman) {
		this.feelPacman = feelPacman;
	}

	public LinkedList<MyTileNode> getDesiredPath() {
		return desiredPath;
	}

	public void setDesiredPath(LinkedList<MyTileNode> desiredPath) {
		this.desiredPath = desiredPath;
	}

}
