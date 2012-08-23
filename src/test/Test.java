package test;

import java.io.File;
import java.io.IOException;

import basic.Level;

public class Test {

	public static void main(String[] args) throws IOException {
		Level level = new Level();
		level.renderLevel(new File("/Users/Thomas/Documents/workspace/Pacman/src/Maps/level.txt"));
	}
	
}
