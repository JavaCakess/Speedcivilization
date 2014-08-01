package org.fountanio.juancode.out;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

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
	
	
}
