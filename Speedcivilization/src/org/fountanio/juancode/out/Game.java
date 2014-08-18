package org.fountanio.juancode.out;

import java.io.IOException;

import org.fountanio.world.World;

public class Game {

	public static GameState state = GameState.MAP;
	public static boolean map_loaded = false;
	private static World player_world = new World();
	private static World opponent_world = new World();
	
	public static void play() {
		if (state == GameState.MAP) {
			try {
			if (map_loaded) {
				player_world.render();
			} 
			else {
				player_world.loadWorld("C:\\Users\\MicroCode\\Desktop\\default_world.map"); 
				map_loaded = true;
			}
			} catch (IOException e) { e.printStackTrace(); } 
		} 
	}
}
