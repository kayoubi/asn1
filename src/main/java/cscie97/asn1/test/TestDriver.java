package cscie97.asn1.test;

import cscie97.asn1.knowledge.engine.Administrator;
import cscie97.asn1.knowledge.engine.Agent;

import java.net.URL;

/**
 * The executor of the system, it has only one method that accept at least 2  params, the first is the name of the file
 * to load to the system, and the second is the query file, it makes sure that the files exist in the root directory, and complains otherwise
 *
 * @author khaled
 */
public class TestDriver {
    private final static String resourceLocation = "./../";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Missing arguments! usage: ".concat("java -cp . cscie97.asn1.test.TestDriver {input-file} {query-file}"));
            return;
        }

        try {
            String dataFile = getFile(args[0]);
            String queryFile = getFile(args[1]);

            Administrator administrator = new Administrator();
            administrator.importFile(dataFile);

            Agent agent = new Agent();
            agent.queryFile(queryFile);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static String getFile(String fileName) {
        URL data = TestDriver.class.getResource(resourceLocation.concat(fileName));
        if (data == null) {
            throw new IllegalArgumentException("can't find file: ".concat(fileName).concat(" make sure it exists under: ")
                    .concat(TestDriver.class.getResource(resourceLocation).getFile()));
        }
        return data.getFile();
    }
}
