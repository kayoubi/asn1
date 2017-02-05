package cscie97.asn1.knowledge.engine;

import cscie97.asn1.knowledge.engine.domain.Triple;
import cscie97.asn1.knowledge.engine.exceptions.InvalidLineException;
import cscie97.asn1.knowledge.engine.service.QueryEngine;
import cscie97.asn1.knowledge.engine.util.FileProcessor;
import cscie97.asn1.knowledge.engine.util.LineParser;

import java.util.Set;

/**
 * A user for our system that can load data from a file, parse its data and query our system using {@link QueryEngine}
 *
 * @author khaled
 */
public class Agent {
    /**
     * @param fileName the name of the file to be processed, this method will try to load the file, parse it and send
     *        each line to the {@link QueryEngine} as a query param, and print out the result, it'll report any invalid line to the console
     */
    public void queryFile(String fileName) {
        System.out.println("========================");
        System.out.println("== Parsing Query File ==");
        System.out.println("========================\n");
        QueryEngine queryEngine = new QueryEngine();

        try  {
            FileProcessor.processFile(fileName, (idx, line) -> {
                try {
                    LineParser.validate(line, LineParser.TYPE.QUERY);

                    System.out.println(line);
                    Set<Triple> result = queryEngine.executeQuery(line);
                    if (result.isEmpty()) {
                        System.out.println("<null>");
                    } else {
                        result.forEach(System.out::println);
                    }
                    System.out.println("");

                } catch (InvalidLineException e) {
                    System.out.println("Error while parsing file: ".concat(fileName).concat(": (line: ")
                            .concat(Integer.toString(idx + 1)).concat(") \"").concat(line).concat("\" reason: ")
                            .concat(e.getMessage()).concat("\n"));
                }
            });
            System.out.println("=============================");
            System.out.println("== Done Parsing Query File ==");
            System.out.println("=============================\n");
        } catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Running out of memory, will stop processing now!! " +
                    "if this problem persists try to reduce the size of your input file or buy more RAM");
        }
    }
}
