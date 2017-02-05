package cscie97.asn1.knowledge.engine.repository;

import cscie97.asn1.knowledge.engine.domain.Predicate;

import java.util.HashMap;
import java.util.Map;

/**
 * Repository class to save {@link Predicate}
 *
 * @author khaled
 */
public class PredicateRepository {
    private static final PredicateRepository instance = new PredicateRepository();

    private final Map<String, Predicate> predicatesMap = new HashMap<>(); // assuming concurrency is not an issue

    private PredicateRepository() {
    }

    public static PredicateRepository getInstance() {
        return instance;
    }

    /**
     * @param predicate the predicate to be saved
     * @return if the specified predicate doesn't exist, will create a new {@link Predicate} and return it, otherwise
     *         return the existing one
     */
    public Predicate save(String predicate) {
        return predicatesMap.computeIfAbsent(predicate.toLowerCase(), s -> new Predicate(predicate));
    }


    /**
     * Note: in my design this is not really required but it's here for the requirement completion, and the fact that
     * Repositories in general should provide a way to retrieve data from it
     *
     * @param identifier the identifier of the {@link Predicate} to be returned
     * @return a {@link Predicate} that matches the identifier, otherwise null
     */
    public Predicate getPredicate(String identifier) {
        return predicatesMap.get(identifier);
    }

}
