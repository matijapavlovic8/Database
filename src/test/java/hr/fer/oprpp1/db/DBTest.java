package hr.fer.oprpp1.db;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DBTest {

    @Test
    public void testStudentDatabaseConst(){
        assertThrows(NullPointerException.class, () -> new StudentDatabase(null));
    }
    @Test
    public void testCondOp(){
        assertThrows(NullPointerException.class, ()-> new ConditionalExpression(null, null, null));
    }
}
