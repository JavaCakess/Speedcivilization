package org.fountanio.world;

import org.fountanio.ent.Entity;
import org.fountanio.juancode.eng.Engine;
import org.fountanio.juancode.out.Main;

public class Tile extends Entity {
	
	private boolean active;
	private int id;

	public Tile(int id, boolean active, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.active = active;
		this.id = id;
	}
	
	public static final String []tileNames = {"water", "grass", "stone", "dirt", "lava", "brick", "invalid", "burnt dirt", "water2"};
	public static final int AMOUNT_OF_TILES = tileNames.length; 
	public static final int WATER 		= 0;
	public static final int GRASS 		= 1;
	public static final int STONE 		= 2;
	public static final int DIRT  		= 3;
	public static final int LAVA  		= 4;
	public static final int BRICK 		= 5;
	public static final int INVALID		= 6;
	public static final int BURNT_DIRT	= 7;
	public static final int WATER2 		= 8;
	@Override
	public void render() {
		Tile.drawTile(this.id, x, y, w, h); 
	}
	
	public int getID() {
		return this.id;
	}
	
	public static final void drawTile(int id, int x, int y, int w, int h) {
		switch (id) {
		case WATER:
			Engine.draw(Main.getTiles(), WATER, 0, x, y, w, h);
			break;
		case GRASS:
			Engine.draw(Main.getTiles(), GRASS, 0, x, y, w, h);
			break;
		case STONE:
			Engine.draw(Main.getTiles(), STONE, 0, x, y, w, h);
			break;
		case DIRT:
			Engine.draw(Main.getTiles(), DIRT, 0, x, y, w, h);
			break;
		case LAVA:
			Engine.draw(Main.getTiles(), LAVA, 0, x, y, w, h);
			break;
		case BRICK:
			Engine.draw(Main.getTiles(), BRICK, 0, x, y, w, h);
			break;
		case INVALID:
			Engine.draw(Main.getTiles(), INVALID, 0, x, y, w, h);
			break;
		case BURNT_DIRT:
			Engine.draw(Main.getTiles(), BURNT_DIRT, 0, x, y, w, h);
			break;
		case WATER2:
			Engine.draw(Main.getTiles(), WATER2, 0, x, y, w, h);
			break;
			default:
				Engine.draw(Main.getTiles(), INVALID, 0, x, y, w, h);
				break;
		}
	}
	private int ticks = 0;
	@Override
	public void update() {
		ticks++;
		if (id == WATER) {
			if (ticks == 60) {
				// Let me guess. Reset ticks?
				resetTicks();
			}
		}
	}
	
	public void resetTick() {
		ticks = 0;
	}
	
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
	
	public int getTick() {
		return ticks;
	}

	public boolean isActive() {
		return active;
	}
	
}
