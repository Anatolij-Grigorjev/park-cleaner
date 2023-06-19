package com.tiem625.parkcleaner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ReflectionsTests {

    @Test
    public void finds_classes_in_packages() {
        Assertions.assertTrue(
                Reflections.classNamesInPackage("com.tiem625.parkcleaner").findAny().isPresent()
        );
    }

    @Test
    public void throws_missing_packages() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Reflections.classNamesInPackage("com.tiem625.parkcleaner.missing").count()
        );
    }
}
