package hr.fer.oprpp1.db;

/**
 * {@code ConditionalExpression} represents a database emulator query.
 */
public class ConditionalExpression {
    /**
     * Determines the attribute that will be compared.
     */
    private IFieldValueGetter fieldGetter;
    /**
     * Determines comparison strategy.
     */
    private IComparisonOperator comparisonOperator;
    /**
     * String literal to which the attribute is compared.
     */
    private String stringLiteral;

    /**
     * Creates an instance of {@code ConditionalExpression}.
     * @param fieldGetter Determines the attribute that will be compared.
     * @param comparisonOperator Determines comparison strategy.
     * @param stringLiteral String literal to which the attribute is compared.
     */

    public ConditionalExpression(IFieldValueGetter fieldGetter, IComparisonOperator comparisonOperator, String stringLiteral) {
        if(fieldGetter == null || comparisonOperator == null || stringLiteral == null)
            throw new NullPointerException("ConditionalExpression can't accept null as argument!");
        this.fieldGetter = fieldGetter;
        this.comparisonOperator = comparisonOperator;
        this.stringLiteral = stringLiteral;
    }

    /**
     * Gets the field value of the expression.
     * @return subject of comparison.
     */

    public IFieldValueGetter getFieldGetter() {
        return fieldGetter;
    }

    /**
     * Gets the comparator of the expression.
     * @return comparator of the expression.
     */

    public IComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    /**
     * Gets the string literal the attribute is being compared to.
     * @return comparator of the expression.
     */

    public String getStringLiteral() {
        return stringLiteral;
    }
}
