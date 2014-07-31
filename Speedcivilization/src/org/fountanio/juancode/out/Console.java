package org.fountanio.juancode.out;

import org.fountanio.ent.Entity;
import org.fountanio.juancode.eng.Engine;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Console extends Entity {

	private Texture background = Engine.get("res/console_background.png");
	private Texture callout = Engine.get("res/text_bubble.png");
	private boolean field_selected = false;
	private boolean output_selected = false;
	private boolean visible = false;
	
	public Console(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	// width, height
	private int[] inputBox = {244, 11};
	private int[] outputBox = {245, 221};
	// coordinates
	private int inputBox_x = 7;
	private int inputBox_y= 236;
	private int outputBox_x = 7;
	private int outputBox_y = 8;
	@Override
	public void render() {
		/*
		 * Size of where text should go for output
		 * 245 x 221
		 * Size of where text should go for input
		 * 244 x 11
		 */
		if (visible) {
			Engine.beginShapeRendering();
			glBegin(GL_QUADS);
			//background
			glColor3d(.5, .5, .5);
			glVertex2i(x, y);
			glVertex2i(x + w, y);
			glVertex2i(x + w, y + h);
			glVertex2i(x, y + h);
			//outputbox
			glColor3d(.3, .3, .3);
			glVertex2i(x + 10, y + 10);
			glVertex2i(x + 10 + (w - 30), y + 10);
			glVertex2i(x + 10 + (w - 30), y + 10 + (h - 50));
			glVertex2i(x + 10, y + 10 + (h - 50));
			//inputbox
			glColor3d(.3, .3, .3);
			glVertex2i(x + 10, y + (h - 20));
			glVertex2i(x + 10 + (w - 20), y + (h - 20));
			glVertex2i(x + 10 + (w - 20), y + (h - 20) + 17);
			glVertex2i(x + 10 , y + (h - 20) + 17);
			glEnd();
			Engine.endShapeEndering();
		} else {}
	}

	private int cursor = 0;
	private String input = "";
	private int output_selection = 1;
	@Override
	public void update() {
		if (Mouse.isButtonDown(0)) {
			//input box
			if (Mouse.getX() >= x + 10 		&& Mouse.getX() <= x + 10 + (w - 20) &&
					(Display.getHeight() - Mouse.getY()) >= y + (h - 20)&& (Display.getHeight() - Mouse.getY()) <= y + (h - 20) + 17	) {
				field_selected = true;
			} // output box 
			else if ( 
					Mouse.getX() >= x + 10	&& Mouse.getX() <= x + 10 + (w - 0)&&
					(Display.getHeight() - Mouse.getY()) >= y + 10 	&& (Display.getHeight() - Mouse.getY()) <= y + 10 + (h - 50)
					) {
				field_selected = false;
			}
				
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F1)){ 
			visible = true;
		} else if (visible && Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			visible = false;
		}
		if (field_selected) { 
			while (Keyboard.next()) {
				input += "" + Keyboard.getEventCharacter(); 	
			}
			if (!(input.length() >= (240 / 10))) {
				if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
					input.replace(input.charAt(input.length() - 1), ' ');
				} // TODO: implement backspace and fix background color glitch on the display
				
			} 
		}
	}

}
