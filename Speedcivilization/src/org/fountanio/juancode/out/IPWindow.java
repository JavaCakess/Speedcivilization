package org.fountanio.juancode.out;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.Border;

import org.fountanio.juancode.eng.Engine;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class IPWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton gotoip = new JButton("Go");
	private JButton gotoipf = new JButton("Go");
	private Border topborder = BorderFactory.createTitledBorder("Connect");
	private Border bottomborder = BorderFactory.createTitledBorder("Friends");
	private JTextField ipinput = new JTextField();
	private JList<String> friendlist = new JList(); 
	
	public IPWindow() { 
		super("Speed Civilization");
		setSize(400, 340);
		setLayout(new BorderLayout());
		ipinput.setBorder(topborder);
		gotoip.setBorder(topborder);
		gotoipf.setBorder(bottomborder);
		friendlist.setBorder(bottomborder);
		// actions
		
	}
	
	void refreshList() {
		try {
			File file = new File(Engine.getAppDataDir() + "\\fountanio\\speedcivilization\\friends.fls");		
			Reader r = new FileReader(file);
			BufferedReader reader = new BufferedReader(r);
		} catch (FileNotFoundException e) {
			Main.getConsole().errorln("Data directory NOT found!\nPerhaps you may want to restart the game?");
		}
	}
}
