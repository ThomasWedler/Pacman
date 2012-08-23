package basic;
import java.util.LinkedList;
import java.util.UUID;

public class MyNode implements siris.pacman.graph.Node {

	public UUID id = UUID.randomUUID();
	LinkedList<MyNode> neighbors = new LinkedList<MyNode>();
	
//	public MyNode north = null;
//	public MyNode east = null;
//	public MyNode south = null;
//	public MyNode west = null;
	
	public MyNode() {
	}

//	public void setNeighbourNorth(MyNode n) {
//		north = n;
//	}
//	
//	public void setNeighbourSouth(MyNode n) {
//		south = n;
//	}
//	
//	public void setNeighbourWest(MyNode n) {
//		west = n;
//	}
//	
//	public void setNeighbourEast(MyNode n) {
//		east = n;
//	}
	
	public void connectTo(MyNode n) {
		if (!neighbors.contains(n))
			this.neighbors.add(n);
		if (!n.neighbors.contains(this))
			n.neighbors.add(this);
//		if (str.equals("north")){
//			this.setNeighbourNorth(n);
//			n.setNeighbourSouth(this);
//		} else if (str.equals("east")) {
//			this.setNeighbourEast(n);
//			n.setNeighbourWest(this);
//		} else if (str.equals("south")) {
//			this.setNeighbourSouth(n);
//			n.setNeighbourNorth(this);
//		} else if (str.equals("west")){
//			this.setNeighbourWest(n);
//			n.setNeighbourEast(this);
//		}
	}

	
	@Override
	public UUID id() {
		return id;
	}

	@Override
	public MyNode[] neighbors() {
		
//		if (north != null){
//			list.add(north);
//		}
//		if (east != null){
//			list.add(east);
//		}
//		if (south != null){
//			list.add(south);
//		}
//		if (west != null){
//			list.add(west);
//		}
		MyNode[] array = new MyNode[neighbors.size()];
		MyNode[] neighbours = neighbors.toArray(array);
		return neighbours;
	}

}
