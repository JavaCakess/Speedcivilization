package org.fountanio.juancode.out;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.fountanio.juancode.eng.Engine;
import org.fountanio.juancode.eng.Sound;
import org.fountanio.juancode.eng.SpriteSheet;
import org.fountanio.juancode.msg.Button;
import org.fountanio.juancode.msg.MessageBox;
import org.fountanio.world.Item;
import org.fountanio.world.Tile;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
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
	public static boolean dir_exist = false;
	
	public static void main(String[] args) {
		// setup files
		try {
			setupFiles();
			console.println("Reading setup...");
			Settings.readSetup();
			console.println("Done");
		} catch (IOException e1) {
			console.errorln(e1.getMessage());
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
			glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1); // top down
			glMatrixMode(GL_MODELVIEW);
			
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
							Engine.play(intro_sound); console.println("play(intro_sound)");
							state = State.MAIN_MENU; ticks = 0; console.println("state set to MAIN_MENU");
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
						glColor3f(1.0f, 0f, 0f);
						glVertex2i(400, 0);
						glColor3f(1.0f, 0f, 0f);
						glVertex2i(800, 600);
						glColor3f(1.0f, 0f, 0f);
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
	
	static final String[] major_command_names = {"state", "intro", "hide"};
	static final String[] state_subs = {"intro", "main", "game"}; 
	static final String[] state_sub_use = {"INTRO state", "MAIN_MENU state", "GAME state"};
	static final String[] intro_subs = Tile.tileNames;
	static final String[] intro_sub_use = Tile.tileNames; // get the amt
	static final String[] hide_subs = {"ipwindow"};
	static final String[] hide_subs_use = {"IPWindow"};
	static void consoleInLogic() {
		String[] parsed = console.getInput().split(" ");
		for (int i = 0; i < Tile.AMOUNT_OF_TILES; i++) {
			intro_sub_use[i] = String.valueOf(i);
		}
		if (parsed.length == 3) {
			
		} else if (parsed.length == 2) { // 2 items
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
			}  else if (parsed[0].equalsIgnoreCase("hide")) {
				if (parsed[1].equalsIgnoreCase("ipwindow")) {
					win.setVisible(false);
					console.println("IPWindow is now hidden");
					console.println("You can show it again by typing \"show ipwindow\"");
				} else if (parsed[1].equalsIgnoreCase("help")) {
					console.printHelp(major_command_names[2], hide_subs, hide_subs_use); 
				}
				
			} else if (parsed[0].equalsIgnoreCase("show")) {
				if (parsed[1].equalsIgnoreCase("ipwindow")) {
					win.setVisible(true);
					console.println("IPWindow is now showing");
					console.println("You can hide it by typing \"hide ipwindow\"");
				} else if (parsed[1].equalsIgnoreCase("help")) {
					console.printHelp(major_command_names[2], hide_subs, hide_subs_use); 
				}
			}else if (parsed[0].equalsIgnoreCase("intro")) {
				try {
					if (Integer.parseInt(parsed[1]) > Tile.AMOUNT_OF_TILES) {
						console.println("Invalid Tile number: " + parsed[1]);
						console.println("Tile id CANNOT be over " + Tile.AMOUNT_OF_TILES);
					} else {
						rand_use = false;
					} 
				} catch (NumberFormatException e) {
					if (!parsed[1].equalsIgnoreCase("help")) {
						console.errorln("Item after \'intro\' is not a number!");
						console.println("Please use numbers!");
					}
				}
				if (parsed[1].equalsIgnoreCase("help")) {
					console.printHelp(major_command_names[1], intro_subs, intro_sub_use);
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
			} else if (parsed[0].equalsIgnoreCase("first_time")) {
				console.println("You can tell the game that this is NOT your second time playing with \"played_before\"");
				Settings.setFirstTimePlaying(true);
			} else if (parsed[0].equalsIgnoreCase("played_before")) {
				console.println("You can tell the game that this is your first time playing with \"first_time\"");
				Settings.setFirstTimePlaying(false);
			}
		}
		console.clearInput(); // reset to prevent repetition
	}
	
	static final MessageBox first_timer_messages = new MessageBox(
			"hi, i'm don!", "welcome to speed civilization!", 
			"i will inform you about everything happening at your territory", 
			"begin playing by clicking the map button."
			);
	static final Button main_button = new Button("MAP", Button.DEFAULT_BACKGROUND, 400, 200, 
			300, 150); 
	
	/** main menu stuff */
	private static void mainMenu() {
		// background
		Tile.drawTile(rand_choice, 0, 0, Display.getWidth(), Display.getHeight());
		// boxes
		Item.drawItem(Item.DON, 10, Display.getHeight() - 400, 300, 400); 
		if (Settings.firstTimePlaying()) {
			first_timer_messages.activate();
			if (first_timer_messages.isDone()) {
				Settings.setFirstTimePlaying(false);
			}
		} else {
			main_button.setFont(main_button.scaleFont(Button.DEFAULT_FONT));
			main_button.render();
			if (main_button.isSelected()) {
				
			}
		}
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
	
	private static File setup_file;
	/** Setup Files that are needed for the game 
	 * @throws IOException */
	private static void setupFiles() throws IOException {
		File file = new File(Engine.getAppDataDir() + "/sc");
		if ( !console.printExist(file, "Data dir") ) {
			console.println("Creating Data dir...");
			Engine.makeFolder(Engine.getAppDataDir(), "/sc");
			console.println("Done!");
			dir_exist = true;
		} else {
			dir_exist = true; // important!
		}
		if (dir_exist) {
			setup_file = new File(Engine.getAppDataDir() + "/sc/setup.launch");
			if ( !console.printExist(setup_file, "setup file") ) {
				Engine.makeFile(new File(Engine.getAppDataDir() + "/sc/setup.launch")); 
				console.println("setup.launch created!");
				console.println("Writing default contents to setup.launch");
				Settings.writeDefaultSetup();
			} else console.println("setup.launch exists"); 
		} 
	}
	
	public static File getSetupFile() {
		return setup_file;
	}
	
	// ss
	
	public static SpriteSheet getTiles() {
		return tiles_sheet;
	}
	
	public static SpriteSheet getObjects(){
		return items_sheet;
	}
	
	//cons
	public static Console getConsole() {
		return console;
	}
}