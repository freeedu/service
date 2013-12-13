package org.personal.mason.feop.oauth.service.utils;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.UUID;

public class StringGenerator {

    public static String generateUniqueSecret() {
        StringKeyGenerator skg = KeyGenerators.string();
        return skg.generateKey();
    }

    public static String genereateUniqueToken() {
        return UUID.randomUUID().toString();
    }

    public static String generateCode() {
        return KeyGenerators.string().generateKey();
    }

    public static String generateUniqueString(){
        BytesKeyGenerator generator = KeyGenerators.secureRandom(32);
        return new String(Hex.encode(generator.generateKey()));
    }

    public static void main(String[] args) {
        // System.out.println(SecuriteGenerator.generateUniqueSecret());
        System.out.println(StringGenerator.genereateUniqueToken());
    }

}
