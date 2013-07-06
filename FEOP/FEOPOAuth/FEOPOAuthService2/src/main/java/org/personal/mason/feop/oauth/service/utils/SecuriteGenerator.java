package org.personal.mason.feop.oauth.service.utils;

import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class SecuriteGenerator {

	public static String generateUniqueSecret(){
		StringKeyGenerator skg = KeyGenerators.string();
		return skg.generateKey();
	}
	
	public static void main(String[] args) {
		System.out.println(SecuriteGenerator.generateUniqueSecret());
	}
}
