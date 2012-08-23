package basic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

public class MyAStar {

	public TreeMap<MyNode, Integer> openlist = new TreeMap<MyNode, Integer>();
	public Set<MyNode> closedlist = new HashSet<MyNode>();
	public LinkedList<MyNode> shortestPath = new LinkedList<MyNode>();
	public int tentative_g = 0;
	
	public boolean aStar(MyNode n) {
		openlist.put(n, 0);
		do {
			MyNode currentNode = openlist.pollFirstEntry().getKey();
			if (currentNode.getClass().equals(MyPacman.class)) {
				return true;
			}
			expandNode(currentNode);
			closedlist.add(currentNode);
		} while(!openlist.isEmpty());
		return false;
	}
	
	public void expandNode(MyNode n) {
		for (MyNode node : n.neighbors()) {
			if (!closedlist.contains(node)) {
				tentative_g = node.g + 1;
			}
			if (!openlist.containsKey(node) && !(tentative_g >= node.g)) {
				node.parent = n;
				node.g = tentative_g;
				int f = tentative_g + 1;
					openlist.put(node, f);
			}
		}
	}
	
}
