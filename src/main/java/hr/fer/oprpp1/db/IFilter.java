package hr.fer.oprpp1.db;

/**
 * {@code IFilter} interface.
 *
 * @author MatijaPav
 */

public interface IFilter {
    /**
     * Determines if passed {@code StudentRecord} instance is acceptable.
     * @param record {@code StudentRecord} passed for testing.
     * @return {@code true} if record is acceptable, {@code false} otherwise.
     */
    public boolean accepts(StudentRecord record);
}
