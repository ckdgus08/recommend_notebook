package com.github.ckdgus08.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class Utils {

    public FileWriter getWriter(String fileName) throws IOException {
        File new_file = new File(fileName);

        if (!new_file.exists())
            new_file.createNewFile();

        return new FileWriter(new_file, true);
    }

    public List<String> getFileLines(String fileName) throws IOException {
        File file = new File(fileName);
        return Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
    }

}