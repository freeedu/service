package org.personal.mason.feop.oauth.contact.utils;

import java.util.UUID;

public class AccountUidGenerator {

    public static String generateUid() {
        return UUID.randomUUID().toString();
    }
}
