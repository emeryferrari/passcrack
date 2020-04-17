package com.emeryferrari.passcrack;
import java.util.*;
import java.io.*;
import java.security.*;
public class Passcrack {
	private Passcrack() {}
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		if (args.length == 2) {
			GVars.wordlistPath = args[0];
			GVars.inputPath = args[1];
			Passcrack.run();
		} else {
			Passcrack.printUsage();
		}
	}
	private static void run() throws IOException, NoSuchAlgorithmException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(GVars.wordlistPath))));
		ArrayList<String> wordlist = new ArrayList<String>();
		ArrayList<String> input = new ArrayList<String>();
		String line;
		while ((line = reader.readLine()) != null) {
			wordlist.add(line);
		}
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(GVars.inputPath))));
		while ((line = reader.readLine()) != null) {
			input.add(line);
		}
		ArrayList<String> hashes = new ArrayList<String>();
		for (int i = 0; i < wordlist.size(); i++) {
			hashes.add(HashMD5.hash(wordlist.get(i)));
		}
		for (int x = 0; x < hashes.size(); x++) {
			for (int y = 0; y < input.size(); y++) {
				String h = hashes.get(x);
				String i = input.get(y);
				if (h.equalsIgnoreCase(i)) {
					System.out.println(h + " : " + wordlist.get(x));
				}
			}
		}
	}
	private static void printUsage() {
		System.out.println("Usage: java Passcrack wordlist input\n");
		System.exit(1);
	}
}