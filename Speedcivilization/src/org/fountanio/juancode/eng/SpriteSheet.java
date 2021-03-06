package org.fountanio.juancode.eng;

import org.newdawn.slick.opengl.Texture;

import cjaf.tools.NewGLHandler;

public class SpriteSheet {

	public final int tilesAcross, tW, tH;
	public String path;
	public Texture tex;
	
	public SpriteSheet(String path, int tA, int tW, int tH) {
		tex = NewGLHandler.loadTexture(path); //Use NewGLHandler to load our spritesheet.
		tilesAcross = tA;
		this.tW = tW;
		this.tH = tH;
	}
}
