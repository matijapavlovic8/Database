package hr.fer.oprpp1.db;

import java.util.List;

public class QueryFilter implements IFilter{

    private List<ConditionalExpression> expressionList;

    /**
     * Creates an instance of QueryFilter.
     * @param expressionList list of expressions passed for filtration.
     */

    public QueryFilter(List<ConditionalExpression> expressionList){
        if(expressionList == null) throw new IllegalArgumentException("Expression list can't be null!");
        this.expressionList = expressionList;
    }

    /**
     * Determines if passed {@code StudentRecord} instance is acceptable.
     * @param record {@code StudentRecord} passed for testing.
     * @return {@code true} if record is acceptable, {@code false} otherwise.
     */
    @Override
    public boolean accepts(StudentRecord record) {
        if(record == null) throw new NullPointerException("Can't process null record!");

        for(ConditionalExpression exp: expressionList){
            if(!exp.getComparisonOperator().satisfied(exp.getFieldGetter().get(record), exp.getStringLiteral()))
                return false;
        }
        return true;
    }
}
