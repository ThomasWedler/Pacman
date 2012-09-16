package basic;


public class MyGhost extends MyMovingEntityNode implements siris.pacman.graph.Ghost {

	private int number = 0;

	public void setNr(int x) {
		number = x;
	}

	@Override
	public int getNr() {
		return number;
	}
	
	public boolean seesPacman() {
		MyTileNode position = (MyTileNode) this.getTileNode();
		boolean result = lookForPacman(position);
		return result;
	}

	private boolean lookForPacman(MyTileNode position) {
		for (MyNode n : position.getNeighbors()) {
			if (n instanceof MyPacman) {
				return true;
			} else if (n instanceof MyTileNode) {
				MyTileNode node = (MyTileNode) n;
				String direction = position.getDifferenceBetweenPositions(node);
				if (checkDirection(direction))
					return lookForPacman(node);
			}
		}
		return false;
	}

	private boolean checkDirection(String direction) {
		if (this.getDirection().equals("left") && direction.equals("left")) {
			return true;
		}
		if (this.getDirection().equals("right") && direction.equals("right")) {
			return true;
		}
		if (this.getDirection().equals("up") && direction.equals("up")) {
			return true;
		}
		if (this.getDirection().equals("down") && direction.equals("down")) {
			return true;
		}
		return false;
	}

}
