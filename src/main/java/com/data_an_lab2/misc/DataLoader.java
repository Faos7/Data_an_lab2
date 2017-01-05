package com.data_an_lab2.misc;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DataLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    public static List<Double> load(File file) {
        LOGGER.debug("parsing file (in --> file)");
        return load(file.toPath());
    }

    private static List<Double> load(Path path) {
        LOGGER.debug("parsing file (in --> path)");
        try {

            return Files.lines(path)
                    .map(Double::valueOf)
                    .sorted().collect(toList());

        } catch (IOException e) {
            LOGGER.debug("error while parsing file (in --> path)");
            e.printStackTrace();
        }

        LOGGER.debug("load success");
        return Lists.newArrayList();
    }
}
