package org.fountanio.world;

import java.io.File;

import org.fountanio.ent.Entity;
import org.fountanio.juancode.out.Main;

public class Chunk extends Entity {

	private Tile[] tiles = {
			null, null, null, null,
			null, null, null, null,
			null, null, null, null,
			null, null, null, null
	};
	
	public Chunk(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void setChunk(Tile[] tiles) {
		this.tiles = tiles;
	}
	
	@Override
	public void render() {
		for (int i = 0; i < tiles.length; i ++) {
			if (i > 0 || i < 4 || i > 4 || i < 8 || i > 8 || i < 12 || i > 12) {
				
			} else {
				
			}
		}
	}

	@Override
	public void update() {
		
	}

	private File map = null;
	public void read(String path) {
		map = new File(path);
		if (map.exists()) {
			Main.getConsole().println(path + "exists");
			// read shit
		} else Main.getConsole().errorln(path + "does not exist!");
	}
}
