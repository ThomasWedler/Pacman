package basic;

public class MyMovingEntityNode extends MyEntityNode implements siris.pacman.graph.MovingEntityNode {

	public int cmdX = 0;
	public int cmdY = 0;
	public int dmdX = 0;
	public int dmdY = 0;
	public float speed = 1f;

	@Override
	public int getCurrentMovementDirectionX() {
		return cmdX;
	}

	@Override
	public int getCurrentMovementDirectionY() {
		return cmdY;
	}

	@Override
	public int getDesiredMovementDirectionX() {
		return dmdX;
	}

	@Override
	public int getDesiredMovementDirectionY() {
		return dmdY;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public void setCurrentMovementDirection(int x, int y) {
		cmdX = x;
		cmdY = y;
	}

	@Override
	public void setDesiredMovementDirection(int x, int y) {
		dmdX = x;
		dmdY = y;
	}


}
