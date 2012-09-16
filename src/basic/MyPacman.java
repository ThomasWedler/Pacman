package basic;

public class MyPacman extends MyMovingEntityNode implements siris.pacman.graph.Pacman{

	private boolean poweredUp = false;

	public boolean isPoweredUp() {
		return poweredUp;
	}

	public void setPoweredUp(boolean poweredUp) {
		this.poweredUp = poweredUp;
	}
	
}
