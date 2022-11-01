package hr.fer.oprpp1.db;

/**
 * {@code QueryToken} represents a token created by lexical analysis of a query.
 *
 * @author MatijaPav
 */
public class QueryToken {
    private QueryTokenType type;
    private String value;

    /**
     * Creates an instance of {@code QueryToken}.
     * @param type type of the token.
     * @param value value of the token.
     */

    public QueryToken(QueryTokenType type, String value) {
        if(type != QueryTokenType.EOF && value == null)
            throw new NullPointerException("Can't create a token with value null unless it's EOF token.");
        this.type = type;
        this.value = value;
    }

    /**
     * Getter for the {@link QueryToken} type.
     * @return type of the token.
     */

    public QueryTokenType getType() {
        return type;
    }

    /**
     * Getter for the value of {@link QueryToken}
     * @return value of the token.
     */

    public String getValue() {
        return value;
    }
}
