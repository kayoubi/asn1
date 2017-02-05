package cscie97.asn1.knowledge.engine.service;

import cscie97.asn1.knowledge.engine.domain.Node;
import cscie97.asn1.knowledge.engine.domain.Predicate;
import cscie97.asn1.knowledge.engine.domain.Triple;
import cscie97.asn1.knowledge.engine.repository.NodeRepository;
import cscie97.asn1.knowledge.engine.repository.PredicateRepository;
import cscie97.asn1.knowledge.engine.repository.TripleRepository;

/**
 * A service class that provide an interface to the outside world to interact with our KnowledgeGraph system
 * (adding data for now), this shield the caller from the knowledge of our internal domain, and could provide
 * a place for any business logic in the future
 *
 * @author khaled
 */
public class KnowledgeGraph {
    private final static KnowledgeGraph instance = new KnowledgeGraph();


    private KnowledgeGraph() {
    }

    public static KnowledgeGraph getInstance() {
        return instance;
    }

    /**
     * @param subject the subject of the {@link Triple} to be saved
     * @param predicate the predicate of the {@link Triple} to be saved
     * @param object the object of the {@link Triple} to be saved
     */
    public void importTriple(String subject, String predicate, String object) {
        Node subjectNode = NodeRepository.getInstance().save(subject);
        Predicate predicateNode = PredicateRepository.getInstance().save(predicate);
        Node objectNode = NodeRepository.getInstance().save(object);

        TripleRepository.getInstance().save(subjectNode, predicateNode, objectNode);
    }
}
