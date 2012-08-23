package basic;

import siris.java.JavaInterface;
import siris.pacman.graph.Node;

public class MyGoalTestFunction implements siris.pacman.graph.GoalTestFunction {

//	public Node goal;
//
//	public GoalTestFunction(Node n) {
//		goal = n;
//	}
//	
//	@Override
//	public boolean testGoal(Node n) {
//		if (n.getClass().equals(goal.getClass()))
//			return true;
//		return false ;
//	}
	
	
	
// ------------ only for Testing --------------------
	

	public JavaInterface ji;
	
	public MyGoalTestFunction(JavaInterface javaInt){
		ji = javaInt;
	}
	
	@Override
	public boolean testGoal(Node n) {
		ji.swap(n.id());
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false ;
	}

}
