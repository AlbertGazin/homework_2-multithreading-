package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {
    @Override
    public Stream<String> readLines(File file) {
        Path path = Paths.get(file.getPath());
        Stream<String> allLines;
        try {
            allLines = Files.lines(path);
        } catch (IOException e) {
            return Stream.empty();
        }
        return allLines;
    }
}