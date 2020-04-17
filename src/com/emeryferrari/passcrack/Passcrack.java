package com.emeryferrari.passcrack;
import java.util.*;
import com.emeryferrari.passcrack.algorithms.*;
import java.io.*;
import java.security.*;
public class Passcrack {
	private Passcrack() {}
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		if (args.length == 2) {
			GVars.wordlistPath = args[0];
			GVars.inputPath = args[1];
			Passcrack.run();
		} else if (args.length == 3) {
			GVars.wordlistPath = args[0];
			GVars.inputPath = args[1];
			if (args[2].equals("-s")) {
				GVars.shouldPrint = true;
			}
			Passcrack.run(); // FIXED
		} else if (args.length == 4) {
			GVars.wordlistPath = args[0];
			GVars.inputPath = args[1];
			if (args[2].equals("-a")) {
				try {
					GVars.id = Integer.parseInt(args[3]);
				} catch (NumberFormatException ex) {
					Passcrack.printUsage();
				}
			}
			Passcrack.run();
		} else if (args.length == 5) {
			GVars.wordlistPath = args[0];
			GVars.inputPath = args[1];
			if (args[2].equals("-a")) {
				try {
					GVars.id = Integer.parseInt(args[3]);
				} catch (NumberFormatException ex) {
					Passcrack.printUsage();
				}
				if (args[4].equals("-s")) {
					GVars.shouldPrint = true;
				} else {
					Passcrack.printUsage();
				}
			} else if (args[2].equals("-s")) {
				GVars.shouldPrint = true;
				if (args[3].equals("-a")) {
					try {
						GVars.id = Integer.parseInt(args[4]);
					} catch (NumberFormatException ex) {
						Passcrack.printUsage();
					}
				} else {
					Passcrack.printUsage();
				}
			} else {
				Passcrack.printUsage();
			}
		} else {
			Passcrack.printUsage();
		}
	}
	private static void run() throws IOException, NoSuchAlgorithmException {
		if (GVars.shouldPrint) {
			System.out.println("Reading wordlist into memory...");
			int total = 0;
			System.out.print("\r" + total + " entries read...");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(GVars.wordlistPath))));
			ArrayList<String> wordlist = new ArrayList<String>();
			ArrayList<String> input = new ArrayList<String>();
			String line;
			while ((line = reader.readLine()) != null) {
				wordlist.add(line);
				total++;
				System.out.print("\r" + total + " entries read...          ");
			}
			System.out.print("\r" + total + " entries read... Done!   \n\n");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(GVars.inputPath))));
			total = 0;
			System.out.println("Reading input into memory...");
			System.out.print("\r" + total + " entries read...");
			while ((line = reader.readLine()) != null) {
				input.add(line);
				total++;
				System.out.print("\r" + total + " entries read...          ");
			}
			System.out.print("\r" + total + " entries read... Done!    \n\n");
			total = 0;
			System.out.println("Hashing wordlist...");
			int size = wordlist.size();
			System.out.print("\r" + total + "/" + size + " entries hashed...");
			ArrayList<String> hashes = new ArrayList<String>();
			for (int i = 0; i < wordlist.size(); i++) {
				hashes.add(HashController.hash(wordlist.get(i), GVars.id));
				total++;
				System.out.print("\r" + total + "/" + size + " entries hashed...                    ");
			}
			System.out.print("\r" + total + "/" + size + " entries hashed... Done!              \n\n");
			System.out.println("Performing equality checks...");
			boolean matched = false;
			for (int x = 0; x < hashes.size(); x++) {
				for (int y = 0; y < input.size(); y++) {
					String h = hashes.get(x);
					String i = input.get(y);
					if (h.equalsIgnoreCase(i)) {
						if (!matched) {
							System.out.println("Matches found:");
							matched = true;
						}
						System.out.println(h + " : " + wordlist.get(x));
					}
				}
			}
		} else {
			System.out.println("Reading wordlist into memory...");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(GVars.wordlistPath))));
			ArrayList<String> wordlist = new ArrayList<String>();
			ArrayList<String> input = new ArrayList<String>();
			String line;
			while ((line = reader.readLine()) != null) {
				wordlist.add(line);
			}
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(GVars.inputPath))));
			System.out.println("Reading input into memory...");
			while ((line = reader.readLine()) != null) {
				input.add(line);
			}
			ArrayList<String> hashes = new ArrayList<String>();
			System.out.println("Hashing wordlist...");
			for (int i = 0; i < wordlist.size(); i++) {
				hashes.add(HashMD5.hash(wordlist.get(i)));
			}
			boolean matched = false;
			System.out.println("Performing equality checks...\n");
			for (int x = 0; x < hashes.size(); x++) {
				for (int y = 0; y < input.size(); y++) {
					String h = hashes.get(x);
					String i = input.get(y);
					if (h.equalsIgnoreCase(i)) {
						if (!matched) {
							System.out.println("Matches found:");
							matched = true;
						}
						System.out.println(h + " : " + wordlist.get(x));
					}
				}
			}
		}
	}
	private static void printUsage() {
		System.out.println("Usage: java Passcrack wordlist input <options>");
		System.out.println("Options:");
		System.out.println("  -s: Enables use of CLI status indicators; makes calculation much slower");
		System.out.println("  -a algorithm_id: Uses a specific algorithm for bruteforcing, if not specified, program defaults to MD5\n");
		System.out.println("Algorithm IDs:");
		System.out.println("0: MD2");
		System.out.println("1: MD5");
		System.exit(1);
	}
}