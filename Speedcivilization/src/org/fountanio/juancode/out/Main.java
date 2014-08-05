package org.fountanio.juancode.out;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.util.Random;

import org.fountanio.juancode.eng.Engine;
import org.fountanio.juancode.eng.Sound;
import org.fountanio.juancode.eng.SpriteSheet;
import org.fountanio.world.Tile;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;


public class Main {

	private static SpriteSheet tiles_sheet;
	private static SpriteSheet items_sheet;
	private static State state = State.INTRO;
	public static float tx = 0;
	public static float ty = 0;
	public static Console console = new Console();
	public static final java.awt.Font font = new java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.PLAIN, 64);
	public static boolean playing = true; // debug for now
	private static IPWindow win; private static boolean triangle_show_flag = false;
	
	public static void main(String[] args) {

		console.println("Checking if Data dir exists...");
		File file = new File(Engine.getAppDataDir() + "/sc");
		if ( file.exists() ) {
			console.println("Data dir exists...");
		} else {
			console.println("Data dir does NOT exist\nCreating new dir...");
			Engine.makeFolder(Engine.getAppDataDir(), "/sc");
			console.println("Done!");
		} 
		win = new IPWindow();
		console.println("IPWindow created...");	
		// playing
		if (playing) { console.println("playing = true");
			try {
				Display.setTitle("Speed Civilization");
				Display.setDisplayMode(new DisplayMode(970, 610));
				Display.create();
				AL.create();
				console.println("Display And AL created...");
			} catch (LWJGLException e) {
				e.printStackTrace();
				Display.destroy();
				AL.destroy();
				System.exit(1);
			}
			
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
			glMatrixMode(GL_MODELVIEW);
			glDisable(GL_DEPTH_TEST);
			console.println("OpenGL enabled...");
			// define
			tiles_sheet = new SpriteSheet("res/tiles.png", (512 / 32), 32, 32);
			items_sheet = new SpriteSheet("res/items_sheet.png", (4096/ 32), 32, 32);
			Texture logo = Engine.get("res/fountanio_logo.png");
			Sound intro_sound = new Sound("res/intro_sound.wav");
			int ticks = 0;
			
			console.println("Initializing Loop...");
			// loop
			while (!Display.isCloseRequested()) {
				glClear(GL_COLOR_BUFFER_BIT);
				glTranslatef(tx, ty, 0);
				
				glPushMatrix();
					if (state == State.INTRO) {
						ticks++;
						introChunk();
						Engine.draw(logo, Display.getWidth() / 5 - 50, Display.getHeight() / 7, 700, 600);
						if (ticks == 60) {
							Engine.play(intro_sound);
							state = State.MAIN_MENU; ticks = 0; 
						}
					} else if (state == State.MAIN_MENU) {
						mainMenu();
					}
					while (Keyboard.next()) {
						if (Keyboard.isKeyDown(Keyboard.KEY_GRAVE)) { // grave = `
							console.setVisible(true);
						}
					}
					// console logic
					consoleInLogic();
					
					// weird command:
					if ( triangle_show_flag ) {
						Engine.beginShapeRendering();
						glBegin(GL_TRIANGLES);
						glVertex2i(400, 0);
						glVertex2i(800, 600);
						glVertex2i(0, 600);
						glEnd();
						Engine.endShapeEndering();
					}
				glPopMatrix();
				
				Display.update();
				Display.sync(60);
			}
			Display.destroy();
			AL.destroy();
			/* release */
			Engine.release(intro_sound);
			System.exit(0);
		} 
		if (AL.isCreated()) AL.destroy();
		if (Display.isCreated()) Display.destroy();
	}
	
	static final String[] major_command_names = {"state", "intro"};
	static final String[] state_subs = {"intro", "main", "game"}; 
	static final String[] state_sub_use = {"INTRO state", "MAIN_MENU state", "GAME state"};
	static final String[] intro_subs = {String.valueOf(Tile.WATER), String.valueOf(Tile.GRASS), String.valueOf(Tile.STONE), String.valueOf(Tile.DIRT)
		,String.valueOf(Tile.LAVA), String.valueOf(Tile.BRICK)};
	static final String[] intro_sub_use = {"Water id", "Grass id", "Stone id", "Dirt id", "Lava id", "Brick id"}; // TODO: update when new tile is added to the game
	static void consoleInLogic() {
		String[] parsed = console.getInput().split(" ");
		if (parsed.length == 2) { // 2 items
			if (parsed[0].equalsIgnoreCase("state")) {
				if (parsed[1].equalsIgnoreCase("intro")) {
					state = State.INTRO;
					console.println("State set to INTRO");
				} else if (parsed[1].equalsIgnoreCase("main") || parsed[1].equalsIgnoreCase("main_menu")) {
					state = State.MAIN_MENU;
					console.println("State set to MAIN_MENU");
				} else if (parsed[1].equalsIgnoreCase("game")) {
					state = State.GAME;
					console.println("State set to GAME");
				} else if (parsed[1].equalsIgnoreCase("help")) {
					console.printHelp(major_command_names[0], state_subs, state_sub_use);
				}
				
			} else if (parsed[0].equalsIgnoreCase("intro")) {
				try {
					if (Integer.parseInt(parsed[1]) > Tile.AMOUNT_OF_TILES) {
						console.println("Invalid Tile number: " + parsed[1]);
						console.println("Tile id CANNOT be over " + Tile.AMOUNT_OF_TILES);
					} else {
						rand_use = false;
						rand_choice = Integer.parseInt(parsed[1]);
						console.println("rand_choice = " + rand_choice + "(" + intro_sub_use[rand_choice] + ")");
					} 
				} catch (NumberFormatException e) {
					console.errorln("Item after \'intro\' is not a number!");
					console.println("Please use numbers!");
				}
				if (parsed[1].equalsIgnoreCase("help")) {
					console.printHelp(major_command_names[1], intro_subs, intro_sub_use);
				}
			} else if (parsed[0].equalsIgnoreCase("hide")) {
				if (parsed[1].equalsIgnoreCase("ipwindow")) {
					win.setVisible(false);
					console.println("IPWindow is now hidden");
					console.println("You can show it again by typing \"show ipwindow\"");
				}
				
			} else if (parsed[0].equalsIgnoreCase("show")) {
				if (parsed[1].equalsIgnoreCase("ipwindow")) {
					win.setVisible(true);
					console.println("IPWindow is now showing");
					console.println("You can hide it by typing \"hide ipwindow\"");
				}
			}
			
			else {
				console.println("> " + parsed[0]);
				console.println("Did not understand command!"); 
			}
		} else if (parsed.length == 1) {
			if (parsed[0].equalsIgnoreCase("illuminati")) { // creepy comand
				triangle_show_flag = true;
				
				console.println("  ^ ");
				console.println("/_\\");
			} else if (parsed[0].equalsIgnoreCase("god_all_mighty")) {
				console.println("God Bless you!");
				triangle_show_flag = false;
			}
		}
		console.clearInput(); // reset to prevent repetition
	}
	
	private static void mainMenu() {
		
	}
	
	// v
	private static Random random = new Random();
	static boolean rand_use = true;
	static int rand_choice = 255;
	private static void introChunk() {
		if (rand_use){
			rand_choice = random.nextInt(Tile.AMOUNT_OF_TILES);
			rand_use = false;
		}
		Tile.drawTile(rand_choice, 0, 0, Display.getWidth(), Display.getHeight());
		
	}
	
	// ss
	
	public static SpriteSheet getItem() {
		return tiles_sheet;
	}
	
	public static SpriteSheet getTiles(){
		return items_sheet;
	}
	
	//cons
	public static Console getConsole() {
		return console;
	}
}