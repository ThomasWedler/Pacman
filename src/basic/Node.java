package basic;
import java.util.UUID;

public class Node implements siris.pacman.graph.Node {

	public UUID id = UUID.randomUUID();
	public siris.pacman.graph.Node[] neighbours = {null, null, null, null};
	
	public Node() {
	}

	public Node(Node neighbourNorth, Node neighbourEast, Node neighbourSouth, Node neighbourWest) {
		if (neighbourNorth != null)
			neighbours[0] = neighbourNorth;
		if (neighbourEast != null)
			neighbours[1] = neighbourEast;
		if (neighbourSouth != null)
			neighbours[2] = neighbourSouth;
		if (neighbourWest != null)
			neighbours[3] = neighbourWest;
	}
	
	public void setNeighbourNorth(Node n) {
		neighbours[0] = n;
	}
	
	public void setNeighbourEast(Node n) {
		neighbours[1] = n;
	}
	
	public void setNeighbourSouth(Node n) {
		neighbours[2] = n;
	}
	
	public void setNeighbourWest(Node n) {
		neighbours[3] = n;
	}
	
	@Override
	public UUID id() {
		return id;
	}

	@Override
	public siris.pacman.graph.Node[] neighbors() {
		return neighbours;
	}

}
