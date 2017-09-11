package com.epam.commons;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Roman_Iovlev on 9/2/2017.
 */
public class FileUtils {
    public static List<String> getFiles(String pathToDir) {
        try {
            return Files.walk(Paths.get(pathToDir))
                    .filter(Files::isRegularFile).map(Path::toString)
                    .collect(Collectors.toList());
        }
        catch (Exception ex) {
            return new ArrayList<>();
        }
    }
}
