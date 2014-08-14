package org.fountanio.juancode.msg;

import java.awt.Color;
import java.awt.Font;

import org.fountanio.juancode.eng.Engine;
import org.fountanio.world.Item;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class MessageBox {

	/** 
	 * Renders a message box on the bottom of the screen and displays the text.
	 * @param text - The message
	 * @param font - The Font for the message
	 */
	public static void say(String text, Font font) {
		Item.drawItem(Item.MESSAGE_BOX, 0, Display.getHeight() - (Display.getHeight() / 5)
				, Display.getWidth(), Display.getHeight() / 5);
		Engine.say(text, 50, Display.getHeight() - (Display.getHeight() / 5) + 5, font, 
				Color.black);
	}
	
	private String[] out;
	
	public MessageBox(String... out) {
		this.out = out;
	}
	
	private int next = 0;
	private Font font = new Font(Font.MONOSPACED, Font.BOLD, 40);
	private Color color = Color.black;
	private boolean done = false;
	
	public void activate() {
		if (next >= out.length) {
			done = true;
		} 
		if (!done) {
			
			// 36 is the char max for a line
			Item.drawItem(Item.MESSAGE_BOX, 0, Display.getHeight() - (Display.getHeight() / 5)
					, Display.getWidth(), Display.getHeight() / 5);
			if (out[next].toString().length() >= 36) {
				Engine.say(out[next].toUpperCase().substring(0, 36) 
						+ "\n" + out[next].toUpperCase().substring(36), 50, Display.getHeight() - (Display.getHeight() / 5) + 10, font, 
						color);
			} else {
				Engine.say(out[next].toUpperCase(), 50, Display.getHeight() - (Display.getHeight() / 5) + 10, font, color); 
			}
			/*Engine.say("enter->".toUpperCase(), Display.getWidth() - 160, 
					Display.getHeight() - (Display.getHeight() / 5) + 100, font, 
					color); */
			while (Keyboard.next()) { // should be last
				if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
					next++;
				} 
			}
		} 
	}
	
	public void setSlideNo(int slide) {
		if (slide > out.length) {
			throw new NullPointerException("Slide No. cannot be greater than out.length("
					+out.length+")!");
		} else {
			next = slide;
		}
	}
	
	public void setSlides(String... out) {
		this.out = out;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public int getSlide() {
		return next;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
}
