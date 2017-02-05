package cscie97.asn1.knowledge.engine.repository;

import cscie97.asn1.knowledge.engine.domain.Node;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Repository class to save {@link Node}
 *
 * @author khaled
 */
public class NodeRepository {
    private static NodeRepository instance = new NodeRepository();

    private final Map<String, Node> nodesMap = new HashMap<>(); // assuming concurrency is not an issue

    private NodeRepository() {
    }

    public static NodeRepository getInstance() {
        return instance;
    }


    /**
     * @param node the predicate to be saved
     * @return if the specified predicate doesn't exist, will create a new {@link Node} and return it, otherwise
     *         return the existing one
     */
    public Node save(String node) {
        return nodesMap.computeIfAbsent(node.toLowerCase(), s -> new Node(node));
    }

    /**
     * Note: in my design this is not really required but it's here for the requirement completion, and the fact that
     * Repositories in general should provide a way to retrieve data from it
     *
     * @param identifier the identifier of the {@link Node} to be returned
     * @return a {@link Node} that matches the identifier, otherwise null
     */
    public Node getPredicate(String identifier) {
        return nodesMap.get(identifier);
    }

}
