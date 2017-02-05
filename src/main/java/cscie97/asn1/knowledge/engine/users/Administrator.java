package cscie97.asn1.knowledge.engine.users;

import cscie97.asn1.knowledge.engine.exceptions.InvalidLineException;
import cscie97.asn1.knowledge.engine.service.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.util.FileProcessor;
import cscie97.asn1.knowledge.engine.util.LineParser;

/**
 * A user for our system that can load data from a file, parse its data and insert it to {@link KnowledgeGraph}
 *
 * @author khaled
 */
public class Administrator {

    /**
     * @param fileName the name of the file to be processed, this method will try to load the file, parse it and insert
     *        each line into the {@link KnowledgeGraph} it'll report any invalid line to the console
     */
    public void importFile(String fileName) {
        KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
        try  {
            FileProcessor.processFile(fileName, (idx, line) -> {
                try {
                    String[] input = LineParser.parse(line, LineParser.TYPE.INPUT);
                    knowledgeGraph.importTriple(input[0], input[1], input[2]);
                } catch (InvalidLineException e) {
                    System.out.println("Error while parsing file: ".concat(fileName).concat(": (line: ")
                            .concat(Integer.toString(idx + 1)).concat(") \"").concat(line).concat("\" reason: ").concat(e.getMessage()));
                }
            });
        } catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Running out of memory, will stop loading data, and will try to run some queries for you! The chances are we won't be " +
                    "go too far! if this problem persists try to reduce the size of your input file or buy more RAM");
        }
    }
}
