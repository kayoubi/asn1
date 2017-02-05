package cscie97.asn1.knowledge.engine.domain;

/**
 * A domain object that represents Subject / Object in the {@link Triple}
 *
 * @author khaled
 */
public class Node {
    private final String identifier;
    private final long createDate;

    public Node(String identifier) {
        this.identifier = identifier;
        this.createDate = System.currentTimeMillis();
    }

    public String getIdentifier() {
        return identifier;
    }

    public long getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return identifier.equals(node.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        return identifier;
    }
}
