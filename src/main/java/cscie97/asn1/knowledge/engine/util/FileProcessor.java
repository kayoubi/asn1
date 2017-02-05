package cscie97.asn1.knowledge.engine.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A helper class to process files and custom-process them
 *
 * @author khaled
 */
public class FileProcessor {

    private FileProcessor() {
    }

    /**
     * If an error happend during the file reading, the processor will stop and the report the error to the console
     *
     * @param fileName the name of the file to processed
     * @param consumer consumer that will be passed both the index of the current line and the current to be processed
     */
    public static void processFile(String fileName, BiConsumer<Integer, String> consumer) {
        try  {
            List<String> lines = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
            IntStream.range(0, lines.size())
                .forEach(idx -> {
                    String line = lines.get(idx);
                    consumer.accept(idx, line);
                });
        } catch (IOException e) {
            System.out.println("Error while parsing file: " + fileName);
        }
    }
}
