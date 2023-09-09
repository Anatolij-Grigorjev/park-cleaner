package com.tiem625.parkcleaner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Reflections {

    private Reflections() {
        throw new UnsupportedOperationException("static helper");
    }

    public static Stream<String> classNamesInPackage(String packageName) {
        if (packageName == null || packageName.isBlank()) {
            return Stream.empty();
        }
        var packageContentsStream = Reflections.class.getClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        if (packageContentsStream == null) {
            throw new IllegalArgumentException("no stream made for package name " + packageName);
        }
        var reader = new BufferedReader(new InputStreamReader(packageContentsStream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(className -> asFullClassName(packageName, className));
    }

    private static String asFullClassName(String packageName, String className) {
        return packageName + '.' + className.substring(0, className.lastIndexOf('.'));
    }
}
