package basic;
import java.util.LinkedList;
import java.util.UUID;

public class MyNode implements siris.pacman.graph.Node {

	public UUID id = UUID.randomUUID();
	public LinkedList<MyNode> neighbors = new LinkedList<MyNode>();
	public int depth = 0;
	public MyNode parent = null;
	public int g = 0;
	
	public MyNode() {
	}

	public void connectTo(MyNode n) {
		if (!neighbors.contains(n))
			this.neighbors.add(n);
		if (!n.neighbors.contains(this))
			n.neighbors.add(this);
	}

	@Override
	public UUID id() {
		return id;
	}

	@Override
	public MyNode[] neighbors() {
		MyNode[] array = new MyNode[neighbors.size()];
		MyNode[] neighbours = neighbors.toArray(array);
		return neighbours;
	}

}
