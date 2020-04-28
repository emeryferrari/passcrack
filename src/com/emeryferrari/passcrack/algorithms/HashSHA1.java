package com.emeryferrari.passcrack.algorithms;
import java.security.*;
import javax.xml.bind.*;
public class HashSHA1 {
	private HashSHA1() {}
	public static String hash(String input) throws NoSuchAlgorithmException {
		return DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-1").digest(input.getBytes())).toLowerCase();
	}
}