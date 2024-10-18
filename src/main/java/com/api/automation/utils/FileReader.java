package com.api.automation.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static String readJsonFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/"+fileName+".json")));
    }
}
