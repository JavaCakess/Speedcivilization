package org.fountanio.juancode.out;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.fountanio.juancode.eng.Engine;

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
		if (!Main.playing) {
			ipinput.setBorder(topborder);
			gotoip.setBorder(topborder);
			gotoipf.setBorder(bottomborder);
			friendlist.setBorder(bottomborder);
			
			add(ipinput, BorderLayout.CENTER);
			add(gotoip, BorderLayout.EAST);
			// actions
			
		} else {
			JLabel msg = new JLabel("Currently Playing\nDO NOT CLOSE WHILE PLAYING!");
			add(msg, BorderLayout.CENTER);
		}
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO: add action listener to the gotoip button
	}
	
	public void refreshList() {
		try {
			File file = new File(Engine.getAppDataDir() + "/sc/friends.txt"); 
			if (!file.exists()) {
				File f = new File(Engine.getAppDataDir() + "/sc/friends.txt");
				f.createNewFile(); Main.getConsole().println("Friends.txt created!");
			}
			Reader r = new FileReader(file);
			BufferedReader reader = new BufferedReader(r);
			String parsed = "";
			while (reader.ready()) {
				parsed += reader.readLine();
			}
			reader.close();	
			r.close();
			// parse
			String[] full = parsed.split("\n");
			Main.getConsole().println("Parsing Friend List Information...");
			friendlist.setListData(full); 
			Main.getConsole().println("Done!");
		} catch (IOException e) {
			Main.getConsole().errorln("Data directory NOT found!\nPerhaps you may want to restart the game?");
		}
	}
	
	public void addToList(String name, String ip) {
		
	}
}
