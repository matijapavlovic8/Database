package hr.fer.oprpp1.db;

/**
 * {@code FieldValueGetters} class provides strategies for retrieval of field values from
 * instances of {@link StudentRecord}
 */

public class FieldValueGetters {
    /**
     * Strategy that gets the first name field value.
     */
    public static final IFieldValueGetter FIRST_NAME = StudentRecord::getFirstName;

    /**
     * Strategy that gets the last name field value.
     */
    public static final IFieldValueGetter LAST_NAME = StudentRecord::getLastName;

    /**
     * Strategy that gets the JMBAG field value.
     */
    public static final IFieldValueGetter JMBAG = StudentRecord::getJmbag;


}
