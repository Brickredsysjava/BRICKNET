package org.example.service;

import java.util.Base64;

public class Base64Convertor {

    public static String convertToBase64(byte[] imageBytes) {

        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
