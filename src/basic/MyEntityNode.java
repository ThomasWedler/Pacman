package basic;

import siris.pacman.graph.TileNode;

public class MyEntityNode extends MyNode implements siris.pacman.graph.EntityNode {
	
	public float x;
	public float y;
	public TileNode tileNode;

	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public float getPositionX() {
		return x;
	}

	@Override
	public float getPositionY() {
		return y;
	}

	@Override
	public TileNode getTileNode() {
		return tileNode;
	}

	@Override
	public void setTileNode(TileNode n) {
		//System.out.println("setTileNode: x = " + n.position().x());
		//System.out.println("setTileNode: y = " + n.position().y());
		this.connectTo((MyNode) n);
		this.tileNode = n;
	}

	@Override
	public void disconnect() {
		//System.out.println("disconnect");
		this.tileNode = null;
	}

}
