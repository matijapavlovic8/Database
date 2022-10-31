package hr.fer.oprpp1.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class {@code StudentDatabase} emulates a simple database.
 */

public class StudentDatabase {
    /**
     * List of rows from input file.
     */
    private List<StudentRecord> records;

    /**
     * Map that allows quick retrieval of elements when the JMBAG value is known.
     */
    private Map<String, StudentRecord> index;

    /**
     * Creates an instance of {@code StudentDatabase}. Acts as a data structure of the database emulator.
     * @param records List of rows from the input file.
     */

    public StudentDatabase(List<String> records){
        if(records == null) throw new NullPointerException("Input can't be empty");
        records = new ArrayList<>();
        index = new HashMap<>();
        createRecordsAndIndex(records);

    }

    /**
     * Parses rows from the input file into instances of {@class StudentRecord}
     * Creates a record list and an index map.
     * @param records List of rows from the input file.
     * @throws IllegalArgumentException if finalGrade or jmbag aren't correctly passed.
     */

    private void createRecordsAndIndex(List<String> records){
        for(String s: records) {
            String[] splits = s.split("\\t");
            if(splits.length != 4) throw new IllegalArgumentException("Invalid input!");
            StudentRecord sr = new StudentRecord(splits[0], splits[1], splits[2], Integer.parseInt(splits[3]));
            if(index.put(sr.getJmbag(), sr) != null) throw new IllegalArgumentException("Database already contains this JMBAG!");
            if(sr.getFinalGrade() < 1 || sr.getFinalGrade() > 5) throw new IllegalArgumentException("Invalid final grade!");
        }
    }


    /**
     * Finds the StudentRecord with the given jmbag in O(1).
     * @param jmbag of the desired StudentRecord.
     * @return {@code StudentRecord}
     */
    public StudentRecord forJmbag(String jmbag){
        return index.get(jmbag);
    }

    /**
     * Iterates through the records list and ads all records that are acceptable to the temp list.
     * @param filter instance of IFilter interface.
     * @return {@code List<StudentRecord>}
     */

    public List<StudentRecord> filter(IFilter filter){
        List<StudentRecord> temp = new ArrayList<>();
        for(StudentRecord sr: records){
            if(filter.accepts(sr))
                temp.add(sr);
        }
        return temp;
    }




}
