package com.emeryferrari.passcrack.algorithms;
import java.security.*;
import javax.xml.bind.*;
public class HashSHA256 {
	private HashSHA256() {}
	public static String hash(String input) throws NoSuchAlgorithmException {
		return DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(input.getBytes())).toLowerCase();
	}
}