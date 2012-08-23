package basic;


public class MyGhost extends MyMovingEntityNode implements siris.pacman.graph.Ghost {

	public int number = 0;
	
	public void setNr(int x) {
		number = x;
	}
	
	@Override
	public int getNr() {
		return number;
	}

}
