package cscie97.asn1.knowledge.engine.service;

import cscie97.asn1.knowledge.engine.domain.Triple;
import cscie97.asn1.knowledge.engine.repository.TripleRepository;

import java.util.Set;

/**
 * A service class that provide an interface to the outside world to interact with our QueryEngine system this shield
 * the caller from the knowledge of our internal domain, I added {@see getAll} as an example
 *
 * @author khaled
 */
public class QueryEngine {
    /**
     * No validation, we are assuming the input is valid already (during parsing phase)
     *
     * @param query the query string
     * @return matching Set {@link Triple}
     */
    public Set<Triple> executeQuery(String query) {
        String[] nodes = query.split(" ");
        return TripleRepository.getInstance().query(nodes[0], nodes[1], nodes[2].substring(0, nodes[2].length() - 1));
    }

    /**
     * helper method to get all the data in our system (if something like this to be provided)
     * @return all {@link Triple} in the system
     */
    public Set<Triple> getAll() {
        return TripleRepository.getInstance().query("?", "?", "?");
    }
}

