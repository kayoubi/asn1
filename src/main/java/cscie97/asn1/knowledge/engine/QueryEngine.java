package cscie97.asn1.knowledge.engine;

import cscie97.asn1.knowledge.engine.domain.Triple;
import cscie97.asn1.knowledge.engine.exceptions.InvalidLineException;
import cscie97.asn1.knowledge.engine.service.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.util.FileProcessor;
import cscie97.asn1.knowledge.engine.util.LineParser;
import cscie97.asn1.knowledge.engine.util.Printer;

import java.util.Set;

/**
 * A user for our system that can load data from a file, parse its data and query our system using {@link KnowledgeGraph}
 *
 * @author khaled
 */
public class QueryEngine {
    /**
     * @param fileName the name of the file to be processed, this method will try to load the file, parse it and send
     *        each line to {@see executeQuery} as a query param, which will pint out the result, it'll report any invalid line to the console
     *        (including querying the whole DB)
     */
    public void executeQueryFile(String fileName) {
        Printer.printAction("Parsing Query File");

        try  {
            FileProcessor.processFile(fileName, (idx, line) -> {
                try {
                    if ("? ? ?.".equals(line)) {
                        System.out.println("Can't query the whole DB! (line ".concat(Integer.toString(idx)).concat(")\n"));
                    } else {
                        executeQuery(line);
                    }
                } catch (InvalidLineException e) {
                    System.out.println("Error while parsing file: ".concat(fileName).concat(": (line: ")
                            .concat(Integer.toString(idx + 1)).concat(") \"").concat(line).concat("\" reason: ")
                            .concat(e.getMessage()).concat("\n"));
                }
            });
        } catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Running out of memory, will stop processing now!! " +
                    "if this problem persists try to reduce the size of your input file or buy more RAM");
        }

        Printer.printAction("Done Parsing Query File");
    }

    /**
     * this method takes a query string, validate it, parse it, and pass the query elements to the {@link KnowledgeGraph}
     * then print the result to the console (<null> in case of empty result)
     *
     * @param query the query to be executed
     */
    public void executeQuery(String query) throws InvalidLineException {
        LineParser.validate(query, LineParser.TYPE.QUERY);

        String[] nodes = query.split(" ");
        Set<Triple> result = KnowledgeGraph.getInstance().query(nodes[0], nodes[1], nodes[2].substring(0, nodes[2].length() - 1));

        System.out.println(query);
        if (result.isEmpty()) {
            System.out.println("<null>");
        } else {
            result.forEach(System.out::println);
        }
        System.out.println("");
    }
}
