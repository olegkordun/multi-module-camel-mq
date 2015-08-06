package me.kordun.test.util;

import me.kordun.test.integration.DbUnitTest;
import org.apache.poi.util.IOUtils;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class FileUtil {
    private static final Logger log = LoggerFactory.getLogger(DbUnitTest.class);

    public static String getStringFromFile(String file) throws IOException {
        log.info("Loading  file {}", file);
        ClassPathResource resource = new ClassPathResource(file);
        if (!resource.exists()) {
            throw new IllegalStateException("Cannot load resource from " + file);
        }

        byte[] encoded = Files.readAllBytes(Paths.get(resource.getFile().getPath()));
        return new String(encoded, "UTF-8");

    }
    public static void writeStringToFile(String file, String content) throws IOException {
        log.info("Writing to file {}", file);

        Files.write(Paths.get(file).toAbsolutePath(), content.getBytes(), StandardOpenOption.CREATE);
    }
}
