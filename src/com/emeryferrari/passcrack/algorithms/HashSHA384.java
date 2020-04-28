package com.emeryferrari.passcrack.algorithms;
import java.security.*;
import javax.xml.bind.*;
public class HashSHA384 {
	private HashSHA384() {}
	public static String hash(String input) throws NoSuchAlgorithmException {
		return DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-384").digest(input.getBytes())).toLowerCase();
	}
}