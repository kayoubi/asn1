package cscie97.asn1.test;

import cscie97.asn1.knowledge.engine.users.Administrator;
import cscie97.asn1.knowledge.engine.users.Agent;

import java.net.URL;

/**
 * The executor of the system, it has only one method that accept at least 2 string params, the first is the name of the file
 * to load the system, and the second is the query file, it makes sure that the files exist in the same directory where this
 * class file exist, and complains otherwise
 *
 * @author khaled
 */
public class TestDriver {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Missing arguments! usage: ".concat("java -cp . cscie97.asn1.test.TestDriver inputTriples.nts sampleQuery.nt"));
            return;
        }
        String dataFile = getFile(args[0]);
        String queryFile = getFile(args[1]);

        if (dataFile != null && queryFile != null) {
            Administrator administrator = new Administrator();
            administrator.importFile(TestDriver.class.getResource("./" + args[0]).getFile());

            Agent agent = new Agent();
            agent.queryFile(TestDriver.class.getResource("./" + args[1]).getFile());
        }
    }

    private static String getFile(String fileName) {
        URL data = TestDriver.class.getResource("./" + fileName);
        if (data == null) {
            System.out.println("can't find file: ".concat(fileName).concat(" make sure it exists under: ").concat(TestDriver.class.getResource(".").getFile()));
            return null;
        }
        return data.getFile();
    }
}
