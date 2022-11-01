package hr.fer.oprpp1.db;

/**
 * {@code IFieldValueGetter} interface obtains
 * requested field value from {@link StudentRecord} using the given {@link FieldValueGetters} strategy.
 */

public interface IFieldValueGetter {
    /**
     * Obtains requested field value from the given {@link StudentRecord}
     * @param record {@link StudentRecord} whose field value is requested.
     * @return Field value determined by the given {@link FieldValueGetters} strategy.
     */
    public String get(StudentRecord record);
}
