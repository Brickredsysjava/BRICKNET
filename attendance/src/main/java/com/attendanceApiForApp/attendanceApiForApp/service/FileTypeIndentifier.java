package com.attendanceApiForApp.attendanceApiForApp.service;

import java.util.HashMap;
import java.util.Map;

public class FileTypeIndentifier {

    private static final Map<String, String> FILE_TYPES = new HashMap<>();

    static {
        // Images
        FILE_TYPES.put("jpg", "Image");
        FILE_TYPES.put("jpeg", "Image");
        FILE_TYPES.put("png", "Image");

    }

    public static String identifyFileType(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            String extension = fileName.substring(lastDotIndex + 1).toLowerCase();
            return FILE_TYPES.getOrDefault(extension, "Unknown");
        }
        return "Unknown";
    }
}

