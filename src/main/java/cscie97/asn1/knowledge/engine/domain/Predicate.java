package cscie97.asn1.knowledge.engine.domain;

/**
 * A domain object that represents the relation between Subject & Object in the {@link Triple}
 *
 * @author khaled
 */
public class Predicate {
    private final String identifier;
    private final long createDate;

    public Predicate(String identifier) {
        this.identifier = identifier;
        this.createDate = System.currentTimeMillis();
    }

    public String getIdentifier() {
        return identifier;
    }

    long getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predicate predicate = (Predicate) o;

        return identifier.equals(predicate.identifier);
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
