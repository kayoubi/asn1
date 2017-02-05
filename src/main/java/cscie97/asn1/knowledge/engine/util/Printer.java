package cscie97.asn1.knowledge.engine.util;

import java.util.Collections;

/**
 * A util class for cosmetic printing
 *
 * @author khaled
 */
public class Printer {
    private Printer() {

    }

    /**
     *
     * @param action the String to be printed to the console with some decoration around it
     */
    public static void printAction(String action) {
        String title = "=== ".concat(action).concat(" ===");
        String dashes = String.join("", Collections.nCopies(title.length(), "="));

        System.out.println(dashes);
        System.out.println(title);
        System.out.println(dashes);
        System.out.println();
    }
}
