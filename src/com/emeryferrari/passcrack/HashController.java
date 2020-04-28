package com.emeryferrari.passcrack;
import java.security.*;
import com.emeryferrari.passcrack.algorithms.*;
class HashController {
	private HashController() {}
	static String hash(String input, int algorithm) throws NoSuchAlgorithmException {
		if (algorithm == 0) {
			return HashMD2.hash(input);
		} else if (algorithm == 1) {
			return HashMD5.hash(input);
		} else if (algorithm == 2) {
			return HashSHA1.hash(input);
		} else if (algorithm == 3) {
			return HashSHA256.hash(input);
		} else if (algorithm == 4) {
			return HashSHA384.hash(input);
		} else if (algorithm == 5) {
			return HashSHA512.hash(input);
		} else {
			Passcrack.printUsage();
			return null;
		}
	}
}