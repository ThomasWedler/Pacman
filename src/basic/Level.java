package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import siris.pacman.util.TilePosition;

public class Level {

	public Level() {
	}

	public void renderLevel(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str;
		int col = 0;
		int row = 0;

		while ((str = br.readLine()) != null) {
			String[] parts = str.split("\\.");
			for (int i = 0; i < parts.length; i++) {
				createTile(parts[i], row, col);
				col++;
			}
			row++;
			col = 0;
		}
		
		br.close();
	}

	public void createTile(String s, int row, int col) {
		TileNode n = new TileNode();
		n.setPosition(new TilePosition(row, col));
		if (s.equals("-")) {

		}
		if (s.equals("I")) {
			
		}
		if (s.equals("G")) {

		}
		if (s.equals("P")) {

		}
		if (s.equals("X")) {

		}
	}

}
