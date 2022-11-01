package hr.fer.oprpp1.db;

/**
 * {@code IComparisonOperator} interface defines a comparison strategy for twogiven String literals.
 * @author MatijaPav
 */

public interface IComparisonOperator {
    /**
     * Determines whether the given arguments satisfy a given comparison strategy.
     * @param value1 String literal that is being compared.
     * @param value2 String literal to which the value1 is being compared to.
     * @return {@code true} if strings satisfy the strategy, {@code false} otherwise.
     */
    public boolean satisfied(String value1, String value2);
}
