package com.emeryferrari.passcrack.algorithms;
import java.security.*;
import javax.xml.bind.*;
public class HashSHA512 {
	private HashSHA512() {}
	public static String hash(String input) throws NoSuchAlgorithmException {
		return DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-512").digest(input.getBytes())).toLowerCase();
	}
}