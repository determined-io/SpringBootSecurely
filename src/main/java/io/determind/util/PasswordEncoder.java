package io.determind.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by brianbahner on 1/7/16.
 */
public class PasswordEncoder {

    private static final String unencoded = "user";

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(unencoded);
        System.out.println("The encoded string is " + encoded);

        return;
    }
}
