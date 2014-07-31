package org.fountanio.world;

import org.fountanio.ent.Entity;
import org.fountanio.juancode.eng.Engine;
import org.fountanio.juancode.eng.SpriteSheet;
import org.fountanio.juancode.out.Main;

public class Tile extends Entity {
	
	private boolean collidable;
	private boolean active;
	private int id;

	public Tile(int id, boolean collidable, boolean active, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.active = active;
		this.collidable = collidable;
		this.id = id;
	}
	
	public static final int AMOUNT_OF_TILES = 6; // TODO: change when new tile is invented 
	public static final int WATER = 0; 
	public static final int GRASS = 1;
	public static final int STONE = 2;
	public static final int DIRT = 3;
	public static final int LAVA = 4;
	public static final int BRICK = 5;
	public static final int INVALID = 6;

	@Override
	public void render() {
		switch (id) {
		case WATER: // water
			Engine.draw(Main.getItem(), WATER, 0, x, y, w, h);
			break;
		case GRASS: // grass
			Engine.draw(Main.getItem(), GRASS, 0, x, y, w, h);
			break;
		case STONE: // stone
			Engine.draw(Main.getItem(), STONE, 0, x, y, w, h);
			break;
		case DIRT: // dirt 
			Engine.draw(Main.getItem(), DIRT, 0, x, y, w, h);
			break;
		case LAVA: // lava
			Engine.draw(Main.getItem(), LAVA, 0, x, y, w, h);
			break;
		case BRICK: // brick
			Engine.draw(Main.getItem(), BRICK, 0, x, y, w, h);
			break;
		case INVALID: // n/a
			Engine.draw(Main.getItem(), INVALID, 0, x, y, w, h);
			break;
			default:
				Engine.draw(Main.getItem(), BRICK + 1, 0, x, y, w, h);
				break;
		}
	}
	
	public static final void drawTile(int id, int x, int y, int w, int h) {
		switch (id) {
		case WATER: // water
			Engine.draw(Main.getItem(), WATER, 0, x, y, w, h);
			break;
		case GRASS: // grass
			Engine.draw(Main.getItem(), GRASS, 0, x, y, w, h);
			break;
		case STONE: // stone
			Engine.draw(Main.getItem(), STONE, 0, x, y, w, h);
			break;
		case DIRT: // dirt 
			Engine.draw(Main.getItem(), DIRT, 0, x, y, w, h);
			break;
		case LAVA: // lava
			Engine.draw(Main.getItem(), LAVA, 0, x, y, w, h);
			break;
		case BRICK: // brick
			Engine.draw(Main.getItem(), BRICK, 0, x, y, w, h);
			break;
			default:
				Engine.draw(Main.getItem(), BRICK + 1, 0, x, y, w, h);
				break;
		}
	}

	@Override
	public void update() {
		
	}

	public boolean isActive(){
		return active;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
}
