package com.emeryferrari.passcrack.algorithms;
import java.security.*;
import javax.xml.bind.*;
public class HashMD2 {
	private HashMD2() {}
	public static String hash(String input) throws NoSuchAlgorithmException {
		return DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD2").digest(input.getBytes())).toLowerCase();
	}
}