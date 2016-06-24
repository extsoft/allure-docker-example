package com.extsoft.allure.docker.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TestClassRepository {

    private final String testsClassesRegex;

    public TestClassRepository(String testsClassesRegex) {
        this.testsClassesRegex = testsClassesRegex;
    }

    public Class[] loadClasses() {
        Set<String> classPathData;
        String[] data = System.getProperty("java.class.path").split(File.pathSeparator);
        /*
          ToDo:
                  There is an issue: when you place your artifacts inside root of docker image,
                  it takes too long to scan for test classes. "data.length < 1" is a temporary solution to fix it.
                  And it means that search for test classes will be only for the classpath.
         */
        if (data.length < 1) {
            classPathData = getConsoleRunClassPath();
        } else {
            classPathData = new HashSet<>(Arrays.asList(data));
        }
        Set<Class<?>> classes = new HashSet<>();
        for (String aCp : classPathData) {
            File f = new File(aCp);
            if (f.exists() && f.canRead()) {
                if (isJar(f)) {
                    searchJar(classes, f);
                } else {
                    searchFile(classes, f);
                }
            }
        }
        return classes.toArray(new Class[]{});
    }

    private Set<String> getConsoleRunClassPath() {
        try {
            final Set<String> files = new HashSet<>();
            Files.walkFileTree(Paths.get("."), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    files.add(file.toString());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
            return files;
        } catch (IOException e) {
            throw new TestClassRepositoryException("Unable to find tes classes: " + e.getMessage());
        }
    }

    private boolean isClass(String path) {
        return path.matches(".+\\.class$") && !path.contains("$");
    }

    private void searchFile(Set<Class<?>> classes, File f) {
        searchFile(classes, f, f.getPath());
    }

    private void searchFile(Set<Class<?>> classes, File f, String root) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                for (File file : files) {
                    searchFile(classes, file, root);
                }
            }
        } else if (isClass(f.getPath())) {
            String path = f.getPath().substring(root.length() + 1);
            if (mathPattern(path)) {
                classes.add(getClass(path));
            }
        }
    }

    private boolean mathPattern(String path) {
        return path.endsWith("class") && path.matches(testsClassesRegex) && !path.contains("$");
    }

    private Class<?> getClass(String name) {
        String className = name.replaceAll("[/\\\\]", ".").replaceFirst("^\\.", "").replace(".class", "");
        try {
            return Class.forName(className);
        } catch (Exception e) {
            return null;
        }
    }

    private void searchJar(Set<Class<?>> classes, File file) {
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(file))) {
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                String name = entry.getName();
                if (mathPattern(name)) {
                    classes.add(getClass(name));
                }
            }
        } catch (IOException ignored) {
        }
    }

    private boolean isJar(File f) {
        return f.getPath().endsWith(".jar");
    }
}
