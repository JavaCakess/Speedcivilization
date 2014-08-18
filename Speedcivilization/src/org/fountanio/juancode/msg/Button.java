package org.fountanio.juancode.msg;

import java.awt.Color;
import java.awt.Font;

import org.fountanio.ent.Entity;
import org.fountanio.juancode.eng.Engine;
import org.fountanio.world.Item;
import org.lwjgl.input.Mouse;

public class Button extends Entity {

	private int item_tex_id;
	private String text;
	private boolean enabled = true;
	private Font font = new Font(Font.MONOSPACED, Font.BOLD, 35);
	private Color color = Color.black;
	public static final int DEFAULT_BACKGROUND = Item.DEFAULT_BUTTON_BACKGROUND;
	public static final Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.BOLD, 35);
	public static final int DEFAULT_DISABLED_BACKGROUND = Item.DEFAULT_BUTTON_DISABLED_BACKGROUND;
	private int item_dis_tex_id = Button.DEFAULT_DISABLED_BACKGROUND;
	public static final Font DEFAULT_DISABLED_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 35);
	public static final Color DEFAULT_DISABLED_COLOR = Color.red.darker();
	private boolean animation = true;
	
	public Button(String text, int item_tex_id, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.text = text;
		this.item_tex_id = item_tex_id;
	}

	@Override
	public void render() {
		if (!enabled) {
			Item.drawItem(item_dis_tex_id, x, y, w, h);
			Engine.say(text, x + 20, y + 10, scaleFont(Button.DEFAULT_DISABLED_FONT), Button.DEFAULT_DISABLED_COLOR);
		} else {
			if (!animation) {
				Item.drawItem(item_tex_id, x, y, w, h); 
				Engine.say(text, x + 20, y + 10, scaleFont(font), color); 
			} else {
				if (isSelected()) {
					Item.drawItem(item_dis_tex_id, x, y, w, h); 
					Engine.say(text, x + 20, y + 10, scaleFont(Button.DEFAULT_DISABLED_FONT), color);
				} else {
					Item.drawItem(item_tex_id, x, y, w, h);
					Engine.say(text, x + 20, y + 10, scaleFont(font), color);
				}
			}
		}
	}
	
	public void setAnimations(boolean animation){ 
		this.animation = animation;
	}
	
	public void setDisabledBackground(int item_dis_tex_id) {
		this.item_dis_tex_id = item_dis_tex_id;
	}
	
	public void setFont(Font font){ 
		this.font = font;
	}
	/** Sets the color of the font */
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isSelected() {
		if (Mouse.isButtonDown(0)) {
			if (Mouse.getX() >= x 	&&Mouse.getX() <= x+w&&
				Mouse.getY() >= y	&&Mouse.getY() <= y+h) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public Font scaleFont(Font font) { 
		return font.deriveFont(Font.BOLD, w / this.text.length() + 10);
	} 
	
	@Override
	public void update() { }
	
	
}
