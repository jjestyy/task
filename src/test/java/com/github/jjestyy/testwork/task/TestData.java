package com.github.jjestyy.testwork.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestData {
    private final static String specPath = "src/test/java/com/github/jjestyy/testwork/task/examples/spec.json";
    private final static String oldPath = "src/test/java/com/github/jjestyy/testwork/task/examples/old.json";
    private final static String newPath = "src/test/java/com/github/jjestyy/testwork/task/examples/new.json";
    private final static String res1path = "src/test/java/com/github/jjestyy/testwork/task/examples/result1.json";
    private final static String res2path = "src/test/java/com/github/jjestyy/testwork/task/examples/result2.json";

    public static String getSpec(){
        try {
            return Files.readString(Paths.get(specPath));
        } catch (IOException e) {
            throw new RuntimeException("missed spec.json in examples");
        }
    }

    public static String getOldJSON() {
        try {
            return Files.readString(Paths.get(oldPath));
        } catch (IOException e) {
            throw new RuntimeException("missed old.json in examples");
        }
    }

    public static String getNewJSON() {
        try {
            return Files.readString(Paths.get(newPath));
        } catch (IOException e) {
            throw new RuntimeException("missed new.json in examples");
        }
    }

    public static String getRes1JSON() {
        try {
            return Files.readString(Paths.get(res1path));
        } catch (IOException e) {
            throw new RuntimeException("missed result1.json in examples");
        }
    }

    public static String getRes2JSON() {
        try {
            return Files.readString(Paths.get(res2path));
        } catch (IOException e) {
            throw new RuntimeException("missed result2.json in examples");
        }
    }

    public static List<String> getMarks() {
        return List.of("0000 0000 0000 0000 0000 0000 0000 0000",
                "1000 0000 0000 0000 0000 0000 0000 0000",
                "0100 0000 0000 0000 0000 0000 0000 0000",
                "1111 1111 1111 1111 1111 1111 1111 1111");
    }
}
