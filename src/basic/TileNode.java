package basic;

import siris.pacman.util.TilePosition;

public class TileNode extends Node implements siris.pacman.graph.TileNode {
	
	public TilePosition position;
	
	@Override
	public TilePosition position() {
		return position;
	}
	
	public void setPosition(TilePosition p) {
		this.position = p;
	}

}
