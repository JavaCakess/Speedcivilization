package org.fountanio.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.fountanio.world.Tile;
import org.fountanio.juancode.out.Main;

public class World  {
	
	private Tile[] world;
	public final int MAX_WIDTH = 120;
	public final int MAX_HEIGHT = 40;
	private int tick = 0;
	
	public void loadWorld(String path) throws IOException {
		File file = new File(path);
		Reader re = new FileReader(file);
		BufferedReader reader = new BufferedReader(re);
		if (file.exists()) {
			Main.getConsole().println("Reading map...");
			while (reader.ready()) {
				String parse[] = reader.readLine().trim().split(",");
				
				tick++; 
			}
			Main.getConsole().println("Done!");
			re.close();
			reader.close();
		} else {
			Main.getConsole().errorln(path + " was not found!"); 
		}
	}
	
	public void render() {
		
	}
}
