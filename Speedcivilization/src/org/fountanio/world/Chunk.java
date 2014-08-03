package org.fountanio.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.fountanio.ent.Entity;
import org.fountanio.world.Tile;
import org.fountanio.juancode.out.Main;

public class Chunk extends Entity {

	
	
	public Chunk(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	@Override
	public void render() {
		
	}

	@Override
	public void update() {
		
	}

	private File map = null;
	private ArrayList<Integer> chunk = new ArrayList<Integer>();	
	public void read(String path) {
		map = new File(path);
		if (map.exists()) {
			Main.getConsole().println(path + "exists");
			// read shit
			try {
				
				String in = "";
				Reader r = new FileReader(map);
				BufferedReader reader = new BufferedReader(r);
				while (reader.ready()) {
					in += reader.readLine();
				}
				String[] parse = in.trim().split(",");
				for (int i = 0; i < parse.length; i ++) {
					chunk.add(Integer.parseInt(parse[i]));
				}
				r.close();
				reader.close();
			} catch (IOException e) {
				Main.getConsole().errorln(e.getStackTrace() + "\n" + e.getMessage());
			}
		} else Main.getConsole().errorln(path + "does not exist!");
	}
}
