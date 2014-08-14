package org.fountanio.juancode.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Settings {

	private static boolean first_time_playing = true;
	private static String record = "";
	private static final String[] pre = {"first"};
	private static String default_contents = "first=true\n";
	/* FINAL WRITE CONSTANTS */
	private static boolean final_first = false;
	
	public static void readSetup() throws IOException {
		Reader r = new FileReader(Main.getSetupFile());
		BufferedReader reader = new BufferedReader(r);
		while (reader.ready()) {
			record += reader.readLine() + "\n";
		}
		reader.close();
		r.close();
		/* parse */
		String parse[] = record.split("\n"); // split lines
		for (int i = 0; i < pre.length; i++) {
			if (!record.contains(pre[i])) {
				Main.getConsole().println("A prefix does not exist in setup.launch!");
				Main.getConsole().println("Re-Writing contents of setup.launch...");
				writeDefaultSetup();
				break;
			} else {
				continue;
			}
		}
		for (int i = 0; i < parse.length; i++) {
			String[] line = parse[i].split("=");
			if (line.length == 2) {
				if (line[0].equals("first")) {
					first_time_playing = Boolean.parseBoolean(line[1]);
				}
			}
		}
	}
	
	public static void writeDefaultSetup() throws IOException {

		Writer w = new FileWriter(Main.getSetupFile());
		BufferedWriter writer = new BufferedWriter(w);
		String[] toOut = default_contents.split("\n");
		for (int i = 0; i < toOut.length; i ++) {
			writer.append(toOut[i]);
		}
		writer.close(); 
		w.close();
		Main.getConsole().println("Default contents written!"); 
	}
	/** Write before the program terminates */
	public static void writeFinalSetup() throws IOException {
		Writer w = new FileWriter(Main.getSetupFile());
		BufferedWriter writer = new BufferedWriter(w);
	}
	
	public static final boolean firstTimePlaying() {
		return first_time_playing;
	}
	
	public static final void setFirstTimePlaying(boolean f) {
		first_time_playing = f;
	}
}
