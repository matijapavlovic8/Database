package hr.fer.oprpp1.db;

/**
 * Implementation of a lexer used for lexical analysis of database queries.
 *
 * @author MatijaPav
 */
public class QueryLexer {
    /**
     * Query submitted for lexical analysis.
     */
    private char[] query;

    /**
     * Last created token.
     */
    private QueryToken token;

    /**
     * Index of the first unprocessed sub-string.
     */
    private int index;

    /**
     * Creates an instance of {@code QueryLexer}.
     * @param query user input query.
     */
    public QueryLexer(String query){
        if(query == null) throw new NullPointerException("Can't pass null as argument.");
        this.query = query.toCharArray();
        this.index = 0;
        this.token = null;
    }

    public QueryToken nextToken(){
        if(token.getType() == QueryTokenType.EOF)
            throw new UnsupportedOperationException("Can't create tokens after EOF");
        skipWhitespace();
        if(checkEOF())
            token = processEOF();
        skipWhitespace();
        token = createToken();
        return token;
    }


    /**
     * Used to check if char at the current index is a whitespace
     * @return {@code true} if char is a whitespace, {@code false} otherwise.
     */

    private boolean checkWhitespace() {
        char c = query[index];
        return (c == ' ' || c == '\r' || c == '\n' || c == '\t');
    }

    /**
     * Skips all whitespaces.
     */

    private void skipWhitespace(){
        while(checkWhitespace())
            this.index++;
    }

    /**
     * Checks if EOF is reached
     * @return {@code true} if EOF is reached, {@code false} otherwise.
     */
    private boolean checkEOF(){
        return index >= query.length;
    }

    /**
     * Creates an EOF token.
     * @return EOF token.
     */
    private QueryToken processEOF(){
        return new QueryToken(QueryTokenType.EOF, null);
    }

    /**
     * Checks if char at the current index is a letter.
     * @return {@code true} if char is a letter, {@code false} otherwise.
     */
    private boolean checkLetter(){
        return Character.isLetter(query[index]);
    }

    /**
     * Checks if char at current index is valid operator.
     * @return {@code true} if char is valid operator and {@code false} otherwise.
     */
    private boolean checkOperator(){
        return query[index] == '>' || query[index] == '<' || query[index] == '=' || query[index] == '!';
    }

    /**
     * Checks if LIKE operator is present.
     * @return {@code true} if LIKE operator is present and {@code false} otherwise.
     */

    private boolean checkLike(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < 4; i++){
            s.append(query[index + i]);
        }
        if(query[index + 4] != ' ' || query[index + 4] != '\t' || query[index + 4] != '\n' || query[index + 4] != '\r')
            return false;
        return s.toString().equalsIgnoreCase("like");
    }

    /**
     * Checks if AND operator is present.
     * @return {@code true} if AND operator is present and {@code false} otherwise.
     */
    private boolean checkAnd(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < 3; i++){
            s.append(query[index + i]);
        }
        if(query[index + 3] != ' ' || query[index + 3] != '\t' || query[index + 3] != '\n' || query[index + 3] != '\r')
            return false;
        return s.toString().equalsIgnoreCase("and");
    }

    /**
     * Creates an operator token.
     * @return instance of {@link QueryToken}
     */
    private QueryToken processOperator(){
        if(query[index] == '>' || query[index] == '<'){
            if(query[index + 1] == '='){
                String op = String.valueOf(query[index++] + query[index++]);
                return new QueryToken(QueryTokenType.OPERATOR, op);
            } else
                return new QueryToken(QueryTokenType.OPERATOR, String.valueOf(query[index++]));
        } else if(query[index] == '=') {
            index++;
            return new QueryToken(QueryTokenType.OPERATOR, "=");
        } else if(query[index] == '!') {
            if(query[index + 1] != '=')
                throw new IllegalArgumentException("Illegal operator!");
            String op = String.valueOf(query[index++] + query[index++]);
            return new QueryToken(QueryTokenType.OPERATOR, op);

        } else
            throw new IllegalArgumentException("Not an operator!");

    }

    /**
     * Creates an AND token.
     * @return instance of {@link QueryToken}
     */
    private QueryToken processAnd(){
        index += 3;
        return new QueryToken(QueryTokenType.AND, "AND");
    }

    /**
     * Creates an operator token.
     * @return instance of {@link QueryToken}
     */
    private QueryToken processLike(){
        index += 4;
        return new QueryToken(QueryTokenType.OPERATOR, "LIKE");
    }

    /**
     * Creates a String token.
     * @return instance of {@link QueryToken}
     */
    private QueryToken processLiteral(){
        StringBuilder sb = new StringBuilder();
        index++;
        while (index < query.length && query[index] != '"'){
            sb.append(query[index++]);
        }
        if(query[index] != '"')
            throw new IllegalArgumentException("Invalid string literal");
        index++;

        return new QueryToken(QueryTokenType.STRING, sb.toString());
    }
    /**
     * Creates a field value token.
     * @return instance of {@link QueryToken}
     */
    private QueryToken processFieldVal(){
        StringBuilder sb = new StringBuilder();
        while(checkLetter()){
            sb.append(query[index++]);
        }

        if(sb.toString().equalsIgnoreCase("jmbag"))
            return new QueryToken(QueryTokenType.JMBAG, sb.toString());
        if(sb.toString().equalsIgnoreCase("firstname"))
            return new QueryToken(QueryTokenType.FIRST_NAME, sb.toString());
        if(sb.toString().equalsIgnoreCase("lastname"))
            return new QueryToken(QueryTokenType.LAST_NAME, sb.toString());
        throw new IllegalArgumentException("Illegal field value!");

    }

    /**
     * Creates next token.
     * @return new token.
     */
    private QueryToken createToken(){
        if(checkOperator())
            return processOperator();
        if(checkAnd())
            return processAnd();
        if(checkLike())
           return processLike();
        if(query[index] == '"')
            return processLiteral();;
        if(checkLetter())
            return processFieldVal();
        throw new IllegalArgumentException("Can't create token!");
    }


}
