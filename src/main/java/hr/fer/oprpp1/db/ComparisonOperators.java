package hr.fer.oprpp1.db;

/**
 * {@code ComparisonOperator} offers static variables of {@link IComparisonOperator}
 * which act as comparison operators in database queries.
 */

public class ComparisonOperators {
    /**
     * Implementation of operator "<" (less than).
     */
    public static final IComparisonOperator LESS = ((a, b) -> a.compareTo(b) < 0);

    /**
     * Implementation of operator "<=" (less or equals than).
     */
    public static final IComparisonOperator LESS_OR_EQUALS = ((a, b) -> a.compareTo(b) <= 0);

    /**
     * Implementation of operator ">" (greater than).
     */
    public static final IComparisonOperator GREATER = ((a, b) -> a.compareTo(b) > 0);

    /**
     * Implementation of operator ">=" (greater or equals than).
     */
    public static final IComparisonOperator GREATER_OR_EQUALS = ((a, b) -> a.compareTo(b) >= 0);

    /**
     * Implementation of equals.
     */
    public static final IComparisonOperator EQUALS = (String::equals);

    /**
     * Implementation of not equals.
     */
    public static final IComparisonOperator NOT_EQUALS = ((a, b) -> !a.equals(b));

    public static final IComparisonOperator LIKE = ((a, b) ->{
        if(a == null || b == null) throw new NullPointerException("Can't pass null as arguments!");
        if(b.indexOf('*') != b.lastIndexOf('*'))
            throw new IllegalArgumentException("Can't have more than one wildcard element.");

        return a.matches(b.replace("*", ".*"));

    });

}
