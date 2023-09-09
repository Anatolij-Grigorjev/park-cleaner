package com.tiem625.parkcleaner;

import java.io.IOException;
import java.net.URL;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Reflections {

    private Reflections() {
        throw new UnsupportedOperationException("static helper");
    }

    public static Stream<String> classNamesInPackage(String packageName) {
        if (packageName == null || packageName.isBlank()) {
            return Stream.empty();
        }
        return classNamesLocalToFolder(packageName.replaceAll("[.]", "/"))
                .map(className -> asFullClassName(packageName, className));
    }

    private static String asFullClassName(String packageName, String className) {
        return packageName + '.' + className.substring(0, className.lastIndexOf('.'));
    }

    private static Stream<String> classNamesLocalToFolder(String packageName) {
        try {
            var urls = Reflections.class.getClassLoader().getResources(packageName);

            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(urls.asIterator(), Spliterator.DISTINCT), false)
                    .map(URL::toExternalForm)
                    .filter(url -> url.endsWith(".class"))
                    .map(url -> url.substring(url.lastIndexOf("/")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
