package org.fountanio.juancode.eng;

import java.io.File;

import org.cjaf.microcode.helper.GLHelper;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Engine {


	private static UnicodeFont fnt;
	
	public static void setUnicodeFont(java.awt.Font font) {
		 try {
	            fnt = new UnicodeFont(font);

	            fnt.addAsciiGlyphs();
	            fnt.addGlyphs(400, 600);
	            fnt.getEffects().add(new ColorEffect());
	            fnt.loadGlyphs();
	        } catch (SlickException eee) {
	            eee.printStackTrace();
	            Display.destroy(); AL.destroy();
	            System.exit(1);
	        }
	}
	
	public static void destroyUnicodeFont() {
		fnt.destroy();
	}
	
	@SuppressWarnings("unchecked")
	public static void say(String text, float x, float y, org.newdawn.slick.Color color) {
		if (fnt != null)
        fnt.drawString(x, y, text, color);
		else throw new NullPointerException("Null UnicodeFont! Call setUnicodeFont(java.awt.Font font) before!"); 
    }
	
	/**
     * Draws a texture
     * @param texture - org.newdawn.slick.opengl.Texture texture
     * @param x
     * @param y
     * @param w - width
     * @param h - height
     * @since 1.0 Beta
     */
    public static void draw(org.newdawn.slick.opengl.Texture texture, float x, float y, float w, float h) {
        GLHelper.drawTexture(texture, x, y, w, h);
    }

    /**
     * Draws a texture from a spritesheet
     * @param ss - spritesheet
     * @param xtile - the x coordinate of the section
     * @param ytile - the y coordinate of the section
     * @param x
     * @param y
     * @param w
     * @param h
     * @since 1.0 Beta
     */
    public static void draw(SpriteSheet ss, int xtile, int ytile, float x, float y,
                            float w, float h) {
        Texture tex = ss.tex;
        float xt = (xtile * ss.tW);
        float yt = (ytile * ss.tH);

        glBindTexture(GL_TEXTURE_2D, tex.getTextureID());
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glBegin(GL_QUADS);
        {
            glTexCoord2f((xt) / ss.tex.getTextureWidth(), (yt) / ss.tex.getTextureHeight());
            glVertex2f(x, y);
            glTexCoord2f((xt + ss.tW) / ss.tex.getTextureWidth(), (yt) / ss.tex.getTextureHeight());
            glVertex2f(x + w, y);
            glTexCoord2f((xt + ss.tW) / ss.tex.getTextureWidth(), (yt + ss.tH) / ss.tex.getTextureHeight());
            glVertex2f(x + w, y + h);
            glTexCoord2f((xt) / ss.tex.getTextureWidth(), (yt + ss.tH) / ss.tex.getTextureHeight());
            glVertex2f(x, y + h);
        }
        glEnd();
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public static Texture get(String texture) {
        return GLHelper.getTexture(texture);
    }

    /**
     * Gets the directory for AppData
     * @return - directory for AppData
     * @since 1.0 Beta
     */
    public static String getAppDataDir() {
        String osname = System.getProperty("os.name").toUpperCase();
        if (osname.contains("WIN")) { // if windows:
            return System.getenv("AppData");
        } else if (osname.contains("MAC")) { // if mac
            return System.getProperty("os.home");
        } else { // if linux
            return System.getProperty("os.home") + "/Library/Application Support";
        }
    }

    /**
     * Creates a folder in a directory
     * @param directory - directory in which you desire to create the folder
     * @param name - the name of the folder you want to make
     * @since 1.0 Beta
     */
    public static void makeFolder(String directory, String name) {
        try {
            if (directory == null) {
                throw new NullPointerException("directory cannot be null!");
            } else {
                // create the folder
                if (name == null) {
                    throw new NullPointerException("folder name cannot be null!" + "\n:" + directory);
                } else new File(directory + name).mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Display.destroy();
            AL.destroy();
            System.exit(1);
        }
    }

    public static void release(Sound sound) {
        AL10.alDeleteSources(sound.source());
    }

    public static void release(org.newdawn.slick.opengl.Texture texture) {
        if ( texture != null ) {
            texture.release();
        }
    }
    
    /**
     * Gets the directory in where the program is located
     * @return user.dir
     * @since 1.0.1 Beta
     */
    public static String getProgramDir() {
        return System.getProperty("user.dr");
    }
    
    /**
     * Allows rendering of OGL shapes
     */
    public static void beginShapeRendering() {
    	glDisable(GL_TEXTURE_2D); // render shapes
    }
    /**
     * Allows rendering of Textures
     */
    public static void endShapeEndering() {
    	glEnable(GL_TEXTURE_2D);
    }
    
    public static void play(Sound sound) {
    	AL10.alSourcePlay(sound.source());
    }
    
    public static void pause(Sound sound) {
    	AL10.alSourcePause(sound.source());
    }
    
    public static void stop(Sound sound) {
    	AL10.alSourceStop(sound.source());
    }
    
    public static void rewind(Sound sound) {
    	AL10.alSourceRewind(sound.source());
    }
    
}
