package basic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyGraphSearch implements siris.pacman.graph.GraphSearch {

	LinkedList<MyNode> visited;
	Stack<MyNode> sFringe;
	Queue<MyNode> qFringe;

	public MyNode search(MyNode n, siris.pacman.graph.GoalTestFunction goalTest) {
		visited = new LinkedList<MyNode>();
		sFringe = new Stack<MyNode>();
		// qFringe = new LinkedList<Node>();
		if (goalTest.testGoal(n)) {
			return n;
		}
		visited.add(n);
		recursiveDFS(n, goalTest);
		// recursiveBFS(n, goalTest);
		return null;
	}

	public MyNode recursiveDFS(MyNode n, siris.pacman.graph.GoalTestFunction goalTest) {
		for (MyNode node : n.neighbors()) {
			if (!visited.contains(node)) {
				sFringe.addElement(node);
				visited.add(node);
			}
		}
		if (!sFringe.isEmpty()) {
			MyNode node = sFringe.pop();
			if (goalTest.testGoal(node)) {
				return node;
			}
			recursiveDFS(node, goalTest);
		}
		return null;
	}

	public MyNode recursiveBFS(MyNode n, MyGoalTestFunction goalTest) {
		visited.add(n);
		for (MyNode node : n.neighbors()) {
			if (!visited.contains(node)) {
				qFringe.add(node);
			}
		}
		if (!qFringe.isEmpty()) {
			MyNode node = qFringe.poll();
			if (goalTest.testGoal(node)) {
				return node;
			}
			recursiveBFS(node, goalTest);
		}
		return null;
	}

	@Override
	public siris.pacman.graph.Node search(siris.pacman.graph.Node startNode,
			siris.pacman.graph.GoalTestFunction goalTest) {
		MyNode n = search((MyNode) startNode, goalTest);
		System.out.println(n);
		return n;
	}

}
