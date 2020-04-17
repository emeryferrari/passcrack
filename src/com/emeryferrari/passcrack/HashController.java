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
		} else {
			return null;
		}
	}
}