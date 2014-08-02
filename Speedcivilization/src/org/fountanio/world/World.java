package org.fountanio.world;

import java.util.ArrayList;

public class World {

	private ArrayList<Chunk> data = new ArrayList<Chunk>();

	public World(Chunk[] chunks) {
		for (int i =0; i < chunks.length; i ++) {
			data.add(chunks[i]);
		}
	}
	
	public void addChunks(Chunk[] chunks) {
		for (int i =0; i < chunks.length; i ++) {
			data.add(chunks[i]);
		}
	}
	
	public void setChunks(Chunk[] chunks) {
		data.clear();
		for (int i = 0; i < chunks.length; ++i) {
			data.add(chunks[i]);
		}
	}
	
	
}
