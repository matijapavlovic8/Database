package hr.fer.oprpp1.db;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code QueryParser} class parses the query from the user input.
 */
public class QueryParser {

    /**
     * Instance of {@link QueryLexer}.
     */
    private QueryLexer lexer;

    private boolean directQuery;

    /**
     * List of all conditional expressions in the query.
     */
    private List<ConditionalExpression> expressions;

    /**
     * Creates an instance of {@code QueryParser}.
     * @param query user input query.
     */
    public QueryParser(String query){
        if(query == null) throw new NullPointerException("Can't parse null!");
        this.lexer = new QueryLexer(query);
        this.directQuery = false;
        this.expressions = new ArrayList<>();
    }

    /**
     * Processes conjoined queries.
     */
    public void parse(){
        QueryToken token = lexer.nextToken();
        boolean conjunction = false;
        expressions.add(parseExp(token));
        token = lexer.nextToken();
        while(token.getType() != QueryTokenType.EOF){
            if(token.getType() == QueryTokenType.AND)
                conjunction = true;
            token = lexer.nextToken();
            switch(token.getType()){
                case JMBAG:
                case FIRST_NAME:
                case LAST_NAME:
                    if(!conjunction) throw new IllegalStateException("Conjunction is not present.");
                    break;
                default: throw new IllegalStateException("Incorrect conjunction!" + conjunction);

            }
            expressions.add(parseExp(token));
            conjunction = false;
            token = lexer.nextToken();
        }

        if(expressions.size() == 1 && expressions.get(0).getFieldGetter() == FieldValueGetters.JMBAG
            && expressions.get(0).getComparisonOperator() == ComparisonOperators.EQUALS)
            directQuery = true;
    }

    /**
     * Parses simple expressions.
     * @param token starting token of the expression.
     * @return {@link ConditionalExpression}.
     */
    private ConditionalExpression parseExp(QueryToken token){
        IFieldValueGetter getter;
        switch (token.getType()){
            case JMBAG -> getter = FieldValueGetters.JMBAG;
            case FIRST_NAME -> getter = FieldValueGetters.FIRST_NAME;
            case LAST_NAME -> getter = FieldValueGetters.LAST_NAME;
            default -> throw new IllegalArgumentException("Wrong token type!");
        }

        token = lexer.nextToken();

        if(token.getType() != QueryTokenType.OPERATOR)
            throw new IllegalArgumentException("Expected operator token!");

        IComparisonOperator operator;
        switch (token.getValue()){
            case ">" -> operator = ComparisonOperators.GREATER;
            case ">=" -> operator = ComparisonOperators.GREATER_OR_EQUALS;
            case "<" -> operator = ComparisonOperators.LESS;
            case "<=" -> operator = ComparisonOperators.LESS_OR_EQUALS;
            case "!=" -> operator = ComparisonOperators.NOT_EQUALS;
            case "LIKE" -> operator = ComparisonOperators.LIKE;
            case "=" -> operator = ComparisonOperators.EQUALS;
            default -> throw new IllegalArgumentException("Illegal operator!");
        }

        token = lexer.nextToken();
        if(token.getType() != QueryTokenType.STRING)
            throw new IllegalArgumentException("Operators must be followed by String literals!");

        return new ConditionalExpression(getter, operator, token.getValue());

    }




    public boolean isDirectQuery() {
        return directQuery;
    }

    public String getQueriedJMBAG(){
        if(!directQuery) throw new UnsupportedOperationException("Can't get JMBAG if query is not direct!");
        return expressions.get(0).getStringLiteral();

    }

    public List<ConditionalExpression> getQuery(){
        return expressions;
    }
}
