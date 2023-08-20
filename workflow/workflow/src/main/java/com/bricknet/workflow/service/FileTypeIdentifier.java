package com.bricknet.workflow.service;

import java.util.HashMap;
import java.util.Map;

public class FileTypeIdentifier {
    private static final Map<String, String> FILE_TYPES = new HashMap<>();

    static {
        // Images
        FILE_TYPES.put("apng", "Image");
        FILE_TYPES.put("avif", "Image");
        FILE_TYPES.put("gif", "Image");
        FILE_TYPES.put("jpg", "Image");
        FILE_TYPES.put("jpeg", "Image");
        FILE_TYPES.put("jfif", "Image");
        FILE_TYPES.put("pjpeg", "Image");
        FILE_TYPES.put("pjp", "Image");
        FILE_TYPES.put("png", "Image");
        FILE_TYPES.put("svg", "Image");
        FILE_TYPES.put("webp", "Image");
        FILE_TYPES.put("bmp", "Image");
        FILE_TYPES.put("ico", "Image");
        FILE_TYPES.put("cur", "Image");
        FILE_TYPES.put("tif", "Image");
        FILE_TYPES.put("tiff", "Image");

        // Videos
        FILE_TYPES.put("webm", "Video");
        FILE_TYPES.put("mkv", "Video");
        FILE_TYPES.put("flv", "Video");

        // Documents
        FILE_TYPES.put("txt", "Document");
        FILE_TYPES.put("zip", "Document");
        FILE_TYPES.put("ppt", "Document");
        FILE_TYPES.put("pptx", "Document");
        FILE_TYPES.put("odt", "Document");
        FILE_TYPES.put("html", "Document");
        FILE_TYPES.put("doc", "Document");
        FILE_TYPES.put("docx", "Document");

        // Excel
        FILE_TYPES.put("xls", "Excel");
        FILE_TYPES.put("xlsx", "Excel");

        //Pdf
        FILE_TYPES.put("pdf", "Pdf");
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

