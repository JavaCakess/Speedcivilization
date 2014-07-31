package org.fountanio.juancode.out;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console extends JFrame {

	private JTextArea output = new JTextArea("Speed Civilization Console\n");
	private JScrollPane sp = new JScrollPane(output);
	private JTextField input = new JTextField();
	
	public Console() {
		super("SpeedCivilization Console");
	}
	
	public void println(String t) {
		output.append("\n" + t); 
	}
	
	public void print(String t) {
		output.append(t);
	}
	
	public void clear() {
		output.setText("Speed Civilization Console\n");
	}
	
	public String getInput() { String in = "";
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				in = input.getText();
			}
		});
		return in;
	}
	
}
