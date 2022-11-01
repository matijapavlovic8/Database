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
    private List<ConditionalExpression> expressions = new ArrayList<>();

    /**
     * Creates an instance of {@code QueryParser}.
     * @param query user input query.
     */
    public QueryParser(String query){
        if(query == null) throw new NullPointerException("Can't parse null!");
        this.lexer = new QueryLexer(query);
        this.directQuery = false;
    }

    public boolean isDirectQuery() {
        return directQuery;
    }

    public String getQueriedJMBAG(){
        if(!directQuery) throw new UnsupportedOperationException("Can't get JMBAG if query is not direct!");
    }

    public List<ConditionalExpression> getQuery(){
        return expressions;
    }
}
