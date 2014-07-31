package org.fountanio.world;

import org.fountanio.ent.Entity;

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

	
}
