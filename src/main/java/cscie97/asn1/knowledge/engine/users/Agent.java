package cscie97.asn1.knowledge.engine.users;

import cscie97.asn1.knowledge.engine.domain.Triple;
import cscie97.asn1.knowledge.engine.exceptions.ImportException;
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
    public void queryFile(String fileName) {
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

                } catch (ImportException e) {
                    System.out.println("Error while parsing file: ".concat(fileName).concat(": (line: ")
                            .concat(Integer.toString(idx + 1)).concat(") \"").concat(line).concat("\" reason: ")
                            .concat(e.getMessage()).concat("\n"));
                }
            });

        } catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Running out of memory, will stop processing now!! " +
                    "if this problem persists try to reduce the size of your input file or buy more RAM");
        }
    }
}