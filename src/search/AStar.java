package search;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import basic.MyEntityNode;
import basic.MyNode;

public class AStar {

	private HashSet<MyEntityNode> closedSet = new HashSet<MyEntityNode>();
	private PriorityQueue<MyEntityNode> openSet = new PriorityQueue<MyEntityNode>(10, new NodeComparator());
	private HashMap<MyEntityNode, MyEntityNode> cameFrom = new HashMap<MyEntityNode, MyEntityNode>();
	private LinkedList<MyEntityNode> result = new LinkedList<MyEntityNode>();
	private float tentativeG = 0;

	public AStar(MyEntityNode start, MyEntityNode goal) {
		start.g = 0;
		start.f = start.g + h(start, goal);
		openSet.add(start);
		start(start, goal);
	}

	private LinkedList<MyEntityNode> start(MyEntityNode start, MyEntityNode goal) {
		while (!openSet.isEmpty()) {
			MyEntityNode current = openSet.poll();

			if (current.equals(goal))
				return reconstructPath(cameFrom, goal);

			closedSet.add(current);
			for (MyNode n : current.neighbors) {
				MyEntityNode node = (MyEntityNode) n;
				if (!closedSet.contains(node)) {
					tentativeG = current.g + 1;
				}
				if (!openSet.contains(node) || tentativeG < node.g) {
					if (!openSet.contains(node)) {
						openSet.add(node);
					}
					cameFrom.put(node, current);
					node.g = tentativeG;
					node.f = node.g + h(node, goal);
				}
			}
		}
		return null;
	}

	private LinkedList<MyEntityNode> reconstructPath(HashMap<MyEntityNode, MyEntityNode> cameFrom, MyEntityNode node) {
		if (cameFrom.containsKey(node)) {
			LinkedList<MyEntityNode> path = reconstructPath(cameFrom, cameFrom.get(node));
			path.add(node);
			return path;
		}
		result.add(node);
		return result;
	}

	private float h(MyEntityNode n1, MyEntityNode n2) {
		float a, b, c;
		float diffX, diffY;
		diffX = n1.x - n2.x;
		diffY = n1.y - n2.y;
		a = Math.abs(diffX);
		b = Math.abs(diffY);
		c = (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		return c;
	}

	private class NodeComparator implements Comparator<MyEntityNode> {
		public int compare(MyEntityNode n1, MyEntityNode n2) {
			float f1 = n1.f;
			float f2 = n2.f;
			if (f1 < f2)
				return -1;
			if (f1 > f2)
				return 1;
			return 0;
		}
	}

}
