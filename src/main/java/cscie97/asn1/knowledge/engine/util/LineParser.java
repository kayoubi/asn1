package cscie97.asn1.knowledge.engine.util;

import cscie97.asn1.knowledge.engine.exceptions.InvalidLineException;

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
     * @throws InvalidLineException if the line is invalid
     */
    public static String[] parse(String line, TYPE type) throws InvalidLineException {
        validate(line, type);

        String[] result = line.split(" ");
        result[2] = result[2].substring(0, result[2].length() - 1);

        return result;
    }


    /**
     * a helper method to validate a line based on its type, note that this method will stop validating after first failure
     * if we want to catch all errors, we need to keep track of all the errors in a Set, at the end of the method if the Set
     * is not empty throw InvalidLineException (its constructor will accept the Set and it will provide access to it)
     *
     * @param line the line to be parsed
     * @param type the type of of the line
     * @throws InvalidLineException if the line is invalid
     */
    public static void validate(String line, TYPE type) throws InvalidLineException {

        String [] tokens = line.split(" ");
        if (tokens.length != 3) {
            throw new InvalidLineException("line has to have three tokens");
        }

        Arrays.stream(tokens).filter(s -> s.length() == 0).findAny().ifPresent(s -> {
            throw new InvalidLineException("Can't have empty node");
        });

        if (tokens[2].charAt(tokens[2].length() - 1) != '.') {
            throw new InvalidLineException("line should end with a '.'");
        }

        if (TYPE.INPUT.equals(type)) {
            tokens[2] = tokens[2].substring(0, tokens[2].length() - 1);
            Arrays.stream(tokens).filter(s -> s.equals("?")).findAny().ifPresent(s -> {
                throw new InvalidLineException("? is reserved keyword");
            });
        }
    }
}
