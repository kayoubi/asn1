package cscie97.asn1.knowledge.engine.domain;

/**
 * Immutable domain object that represents a triple of subject {@link Node}, predicate {@link Predicate} and object {@link Node}
 *
 * @author khaled
 */
public class Triple {
    private final Node subject;
    private final Predicate predicate;
    private final Node object;
    private final long createDate;
    private long lastUpdatedDate;

    public Triple(Node subject, Predicate predicate, Node object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
        this.createDate = System.currentTimeMillis();
        this.lastUpdatedDate = createDate;
    }

    public long getCreateDate() {
        return createDate;
    }

    public long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Node getSubject() {
        return subject;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public Node getObject() {
        return object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triple triple = (Triple) o;

        if (!subject.equals(triple.subject)) return false;
        if (!predicate.equals(triple.predicate)) return false;
        return object.equals(triple.object);
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + predicate.hashCode();
        result = 31 * result + object.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return subject.getIdentifier()
                .concat(" ")
                .concat(predicate.getIdentifier())
                .concat(" ")
                .concat(object.getIdentifier())
                .concat(".");
    }

    public void refresh() {
        this.lastUpdatedDate = System.currentTimeMillis();
    }
}
