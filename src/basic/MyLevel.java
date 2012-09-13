package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import siris.pacman.util.TilePosition;

public class MyLevel {

	static LinkedList<MyTileNode> nodes = new LinkedList<MyTileNode>();

	public MyLevel(String filename) throws IOException {
		File file = new File(filename);
		renderLevel(file);
	}

	public static LinkedList<MyTileNode> getTileNodes() {
		return nodes;
	}

	public void renderLevel(File file) throws IOException {
		int col = 0;
		int row = countRows(file, -1);
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str;
		
		while ((str = br.readLine()) != null) {
			String[] parts = str.split("(?<=\\G.)");
			for (int i = 0; i < parts.length; i++) {
				if (!parts[i].equals(" ")) {
					createTile(parts[i], row, col);
				}
				col++;
			}
			row--;
			col = 0;
		}
		
		br.close();
	}

	public void createTile(String s, int row, int col) {
		MyTileNode n = new MyTileNode();
		n.setPosition(new TilePosition(col, row));
		nodes.add(n);

		TilePosition posleft = new TilePosition(col - 1, row);
		TilePosition posup = new TilePosition(col, row + 1);
		for (MyTileNode node : nodes) {
			if (node.position().x() == posleft.x()
					&& node.position().y() == posleft.y()) {
				n.connectTo(node);
			}
			if (node.position().x() == posup.x()
					&& node.position().y() == posup.y()) {
				n.connectTo(node);
			}
		}

		if (s.equals("G")) {
			MyGhost ghost = new MyGhost();
			ghost.setTileNode(n);
			ghost.setPosition(col, row);
		}

		if (s.equals("P")) {
			MyPacman man = new MyPacman();
			man.setTileNode(n);
			man.setPosition(col, row);
		}
	}

	public int countRows(File file, int row) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		while ((br.readLine()) != null) {
			row++;
		}
		
		br.close();
		return row;
	}

}
