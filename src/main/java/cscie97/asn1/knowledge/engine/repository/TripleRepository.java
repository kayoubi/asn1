package cscie97.asn1.knowledge.engine.repository;

import cscie97.asn1.knowledge.engine.domain.Node;
import cscie97.asn1.knowledge.engine.domain.Predicate;
import cscie97.asn1.knowledge.engine.domain.Triple;

import java.util.*;

/**
 * Repository class to save {@link Triple}
 *
 * @author khaled
 */
public class TripleRepository {
    private static final TripleRepository instance = new TripleRepository();

    private final Map<String, Set<Triple>> queryMapSet = new HashMap<>();
    private final Map<String, Triple> triplesMap = new HashMap<>();

    private TripleRepository() {
    }

    public static TripleRepository getInstance() {
        return instance;
    }

    /**
     * @param subject the subject of the {@link Triple} to be returned
     * @param predicate the predicate of the {@link Triple} to be returned
     * @param object the object of the {@link Triple} to be returned
     * @return a {@link Triple} that matches the params, otherwise null
     *
     */
    public Triple getTriple(Node subject, Predicate predicate, Node object) {
        Set<Triple> t = query(subject.getIdentifier(), predicate.getIdentifier(), object.getIdentifier());
        if (!t.isEmpty()) {
            return t.iterator().next();
        }
         return null;

    }

    /**
     * first this method will try to find a {@link Triple} that matches that passed params, if non found it'll create
     * a new one and save it under multiple keys to make search more efficient
     *
     * @param subject the subject of the {@link Triple} to be saved
     * @param predicate the predicate of the {@link Triple} to be saved
     * @param object the object of the {@link Triple} to be saved
     *
     */
    public void save(Node subject, Predicate predicate, Node object) {
        String id = buildKey(subject.getIdentifier(), predicate.getIdentifier(), object.getIdentifier());
        if (triplesMap.containsKey(id)) {
            triplesMap.get(id).refresh();
        } else {
            String[] keys = buildKeys(subject.getIdentifier(), predicate.getIdentifier(), object.getIdentifier());
            Triple triple = new Triple(subject, predicate, object);

            Arrays.stream(keys).forEach(key -> queryMapSet.computeIfAbsent(key, t -> new HashSet<>()).add(triple));
            triplesMap.put(id, triple);
        }
    }

    /**
     * helper method to generate varies keys based on the passed params, to make searching for {@link Triple} more
     * efficient
     *
     * @return arrays of generated keys
     */
    private String[] buildKeys(String subject, String predicate, String object) {
        String[] keys = new String[8];
        keys[0] = buildKey(subject, predicate, object);
        keys[1] = buildKey(subject, predicate, "?");
        keys[2] = buildKey(subject, "?", object);
        keys[3] = buildKey("?", predicate, object);
        keys[4] = buildKey("?", "?", object);
        keys[5] = buildKey("?", predicate, "?");
        keys[6] = buildKey(subject, "?", "?");
        keys[7] = buildKey("?", "?", "?");
        return keys;
    }

    /**
     * helper method to build a key in a consistent way for a Triple, based on the param passed, this method will be
     * used when saving and retrieving Triple from the underlying data storage
     *
     * @return unique key based on the passed params
     */
    private String buildKey(String subject, String predicate, String object) {
        return subject.toLowerCase().concat(":").concat(predicate.toLowerCase()).concat(":").concat(object.toLowerCase());
    }

    /**
     * @param subject the subject of the {@link Triple} to be returned
     * @param predicate the predicate of the {@link Triple} to be returned
     * @param object the object of the {@link Triple} to be returned
     * @return a Set of {@link Triple} that matches the passed params, otherwise an empty {@link Set}
     */
    public Set<Triple> query(String subject, String predicate, String object) {
        Set<Triple> result = queryMapSet.get(buildKey(subject, predicate, object));
        return result == null ? new HashSet<>() : result;
    }
}
