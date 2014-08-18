package org.fountanio.world;

import org.fountanio.ent.Entity;
import org.fountanio.juancode.eng.Engine;
import org.fountanio.juancode.out.Main;

public class Item extends Entity {

	private int id;

	public Item(int id, int x, int y, int w, int h) { 
		super(x, y, w, h);
	}

	@Override
	public void render() { 
		Item.drawItem(id, x, y, w, h); 
	}


	@Override
	public void update() { }

	public static final String[] item_names = {"exclemation", "danger exclemation", "cloud", 
		"storm cloud", "cross", "options", "war supplies", "medical supplies", "engineering supplies"
		, "hammer", "moving truck", "security truck", "toxic transfer truck", "don", "toxic puddle"
		, "dam", "toxic dam", "damaged dam", "toxic", "cracks", "burning", "message box"};
	public static final int EXCLEMATION = 0;
	public static final int FIRE_EXCLEMATION = 1;
	public static final int CLOUD  = 2;
	public static final int DARK_CLOUD = 3;
	public static final int CROSS = 4;
	public static final int OPTIONS = 5;
	public static final int WAR_SUPPLIES = 6;
	public static final int MEDIC_SUPPLIES = 7;
	public static final int ENGINEERING_SUPPLIES = 8;
	public static final int HAMMER = 9;
	public static final int MOVING_TRUCK = 10;
	public static final int SECURE_TRUCK = 11;;
	public static final int TOXIC_TRUCK = 12;
	public static final int DON = 13;
	public static final int TOXIC_PUDDLE = 14;
	public static final int DAM = 15;
	public static final int TOXIC_DAM = 16;
	public static final int CRACKED_DAM = 17;
	public static final int TOXIC_SIGN = 18;
	public static final int CRACKED = 19;
	public static final int FIRE = 20;
	public static final int MESSAGE_BOX = 21;
	public static final int DEFAULT_BUTTON_BACKGROUND = 22;
	public static final int DEFAULT_BUTTON_DISABLED_BACKGROUND = 23;

	public static void drawItem(int id, int x, int y, int w, int h) {
		switch (id) {
		case EXCLEMATION:
			Engine.draw(Main.getObjects(), EXCLEMATION, 0, x, y, w, h);
			break;
		case FIRE_EXCLEMATION:
			Engine.draw(Main.getObjects(), FIRE_EXCLEMATION, 0, x, y, w, h);
			break;
		case CLOUD:
			Engine.draw(Main.getObjects(), CLOUD, 0, x, y, w, h);
			break;
		case DARK_CLOUD:
			Engine.draw(Main.getObjects(), DARK_CLOUD, 0, x, y, w, h);
			break;
		case CROSS:
			Engine.draw(Main.getObjects(), CROSS, 0, x, y, w, h);
			break;
		case OPTIONS:
			Engine.draw(Main.getObjects(), OPTIONS, 0, x, y, w, h);
			break;
		case WAR_SUPPLIES:
			Engine.draw(Main.getObjects(), WAR_SUPPLIES, 0, x, y, w, h);
			break;
		case MEDIC_SUPPLIES:
			Engine.draw(Main.getObjects(), MEDIC_SUPPLIES, 0, x, y, w, h);
			break;
		case ENGINEERING_SUPPLIES:
			Engine.draw(Main.getObjects(), ENGINEERING_SUPPLIES, 0, x, y, w, h);
			break;
		case HAMMER:
			Engine.draw(Main.getObjects(), HAMMER, 0, x, y, w, h);
			break;
		case MOVING_TRUCK:
			Engine.draw(Main.getObjects(), MOVING_TRUCK, 0, x, y, w, h);
			break;
		case SECURE_TRUCK:
			Engine.draw(Main.getObjects(), SECURE_TRUCK, 0, x, y, w, h);
			break;
		case TOXIC_TRUCK:
			Engine.draw(Main.getObjects(), TOXIC_TRUCK, 0, x, y, w, h);
			break;
		case DON:
			Engine.draw(Main.getObjects(), DON, 0, x, y, w, h);
			break;
		case TOXIC_PUDDLE:
			Engine.draw(Main.getObjects(), TOXIC_PUDDLE, 0, x, y, w, h);
			break;
		case DAM:
			Engine.draw(Main.getObjects(), DAM, 0, x, y, w, h);
			break;
		case TOXIC_DAM:
			Engine.draw(Main.getObjects(), TOXIC_DAM, 0, x, y, w, h);
			break;
		case CRACKED_DAM:
			Engine.draw(Main.getObjects(), CRACKED_DAM, 0, x, y, w, h);
			break;
		case TOXIC_SIGN:
			Engine.draw(Main.getObjects(), TOXIC_SIGN, 0, x, y, w, h);
			break;
		case CRACKED:
			Engine.draw(Main.getObjects(), CRACKED, 0, x, y, w, h);
			break;
		case FIRE:
			Engine.draw(Main.getObjects(), FIRE, 0, x, y, w, h);
			break;
		case MESSAGE_BOX:
			Engine.draw(Main.getObjects(), MESSAGE_BOX, 0, x, y, w, h);
			break;
		case DEFAULT_BUTTON_BACKGROUND:
			Engine.draw(Main.getObjects(), DEFAULT_BUTTON_BACKGROUND, 0, x, y, w, h);
			break;
		case DEFAULT_BUTTON_DISABLED_BACKGROUND:
			Engine.draw(Main.getObjects(), DEFAULT_BUTTON_DISABLED_BACKGROUND, 0, x, y, w, h);
			break;
		default:
			Tile.drawTile(Tile.INVALID, x, y, w, h); 
			break;
		}
	}

}