package org.fountanio.juancode.ui;

import java.awt.Font;

import org.fountanio.ent.Entity;
import org.fountanio.juancode.eng.Engine;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public final class TextInputUI {

	
	public static String getUserInput() {
		return "" + Keyboard.getEventCharacter();
	}
	
	public static class TextField extends Entity {

		// default
		private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 32);
		private Color col = Color.white;
		private Texture background;
		private boolean visible = false;
		private boolean selected = false;
		private String text = "";
		
		public TextField(int x, int y, int w, int h) {
			super(x, y, w, h);
		}

		public void setColor(Color col) {
			this.col = col;
		}
		
		public void setFont(Font font) {
			this.font = font;
		}
		
		public void setVisible(boolean flag) {
			visible = flag;
		}
		
		public boolean isVisible() {
			return visible;
		}
		
		public Font getFont() {
			return font;
		}
		
		public boolean isSelected() {
			return selected;
		}
		
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
		public boolean isEntered() {
			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
				return true;
			} 
			return false;
		}
		
		public String getText() {
			return text;
		}
		/** why in the world would you want to do with this anyway? **/
		public void setText(String text) {
			this.text = text;
		}
		
		@Override
		public void render() {
			Engine.setUnicodeFont(font);
			
		}
		
		public void setBackground(Texture background) {
			this.background = background;
		}
		
		

		@Override
		public void update() {
			if (visible && selected) {
				text += getUserInput();
				if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
					// TODO: implement deletion
				}
			}
		}
		
	}
}
