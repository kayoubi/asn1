package cscie97.asn1.knowledge.engine.util;

import cscie97.asn1.knowledge.engine.exceptions.ImportException;

import java.util.Arrays;

/**
 * A helper class to process input / query lines
 *
 * @author khaled
 */
public class LineParser {

    public enum TYPE {
        INPUT,
        QUERY
    }

    private LineParser() {
    }


    /**
     * a helper method to parse a line, and validate it based on the TYPE
     *
     * @param line the line to be parsed
     * @param type the type of of the line (this will affect validation)
     * @return array of String in a form of [subject, predicate, object] the line will be stripped from its last character '.'
     * @throws ImportException if the line is invalid
     */
    public static String[] parse(String line, TYPE type) throws ImportException{
        validate(line, type);

        String[] result = line.split(" ");
        result[2] = result[2].substring(0, result[2].length() - 1);

        return result;
    }


    /**
     * a helper method to validate a line based on its type, note that this method will stop validating after first failure
     * if we want to catch all errors, we need to keep track of all the errors in a Set, at the end of the method if the Set
     * is not empty throw ImportException (its constructor will accept the Set and it will provide access to it)
     *
     * @param line the line to be parsed
     * @param type the type of of the line
     * @throws ImportException if the line is invalid
     */
    public static void validate(String line, TYPE type) throws ImportException {

        String [] result = line.split(" ");
        if (result.length != 3) {
            throw new ImportException("line has to have three tokens");
        }

        Arrays.stream(result).filter(s -> s.length() == 0).findAny().ifPresent(s -> {
            throw new ImportException("Can't have empty node");
        });

        if (result[2].charAt(result[2].length() - 1) != '.') {
            throw new ImportException("line should end with a '.'");
        }

        if (TYPE.INPUT.equals(type)) {
            Arrays.stream(result).filter(s -> s.equals("?")).findAny().ifPresent(s -> {
                throw new ImportException("? is reserved keyword");
            });
        }
    }
}
