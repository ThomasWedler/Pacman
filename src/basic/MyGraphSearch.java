package basic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import siris.java.JavaInterface;
import siris.pacman.graph.GoalTestFunction;
import siris.pacman.graph.Node;

public class MyGraphSearch implements siris.pacman.graph.GraphSearch {

	LinkedList<MyNode> visited;
	Stack<MyNode> sFringe;
	Queue<MyNode> qFringe;
	MyNode cutoff = null;

	// search start
	public MyNode search(MyNode n, GoalTestFunction goalTest) {
		visited = new LinkedList<MyNode>();
		sFringe = new Stack<MyNode>();
		qFringe = new LinkedList<MyNode>();
		recursiveDFS(n, goalTest);
		// recursiveBFS(n, goalTest);
		// recursiveDLS(n, goalTest, 0);
		// IDS(n, goalTest);
		return null;
	}

	// Depth-First-Search
	public MyNode recursiveDFS(MyNode n, GoalTestFunction goalTest) {
		if (goalTest.testGoal(n)) {
			return n;
		}
		visited.add(n);
		for (MyNode node : n.neighbors()) {
			if (!visited.contains(node)) {
				sFringe.addElement(node);
				visited.add(node);
			}
		}
		if (!sFringe.isEmpty()) {
			recursiveDFS(sFringe.pop(), goalTest);
		}
		return null;
	}

	// Breadth-First-Search
	public MyNode recursiveBFS(MyNode n, GoalTestFunction goalTest) {
		if (goalTest.testGoal(n)) {
			return n;
		}
		visited.add(n);
		for (MyNode node : n.neighbors()) {
			if (!visited.contains(node)) {
				qFringe.add(node);
				visited.add(node);
			}
		}
		if (!qFringe.isEmpty()) {
			recursiveBFS(qFringe.poll(), goalTest);
		}
		return null;
	}

	// Depth-Limited-Search
	public MyNode recursiveDLS(MyNode n, GoalTestFunction goalTest, int limit) {
		boolean cutoffocc = false;
		System.out.println("fringe: " + sFringe.size());
		System.out.println("vis: " + visited.size());
		visited.add(n);
		if (goalTest.testGoal(n)) {
			return n;
		} else if (n.depth == limit) {
			cutoff = n;
			if (!sFringe.isEmpty()) {
				recursiveDLS(sFringe.pop(), goalTest, limit);
			}
			return cutoff;
		} else {
			for (MyNode node : n.neighbors()) {
				if (!visited.contains(node)) {
					sFringe.addElement(node);
					node.depth = n.depth + 1;
				}
			}
			if (!sFringe.isEmpty()) {
				MyNode node = recursiveDLS(sFringe.pop(), goalTest, limit);
				if (node != null) {
					if (node.equals(cutoff))
						cutoffocc = true;
					else
						return node;
				}
			}
		}
		if (cutoffocc)
			return cutoff;
		return null;
	}

	// Iterative-Deepening-Search
	public MyNode IDS(MyNode n, GoalTestFunction goalTest) {
		for (int depth = 0;; depth++) {
			MyNode node = recursiveDLS(n, goalTest, depth);
			if (node != cutoff)
				return node;
			goalTest = new MyGoalTestFunction(new JavaInterface(true, true));
			visited = new LinkedList<MyNode>();
		}
	}

	@Override
	public Node search(Node startNode, GoalTestFunction goalTest) {
		MyNode n = search((MyNode) startNode, goalTest);
		return n;
	}

}
