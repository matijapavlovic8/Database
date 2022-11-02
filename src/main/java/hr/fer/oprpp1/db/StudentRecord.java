package hr.fer.oprpp1.db;

import java.util.Objects;

/**
 * Class {@code StudentRecord} represents records for each student in the emulated database.
 *
 * @author MatijaPav
 */

public class StudentRecord {
    private String jmbag;
    private String lastName;
    private String firstName;
    private int finalGrade;

    /**
     * Creates an instance of {@code StudentRecord} class.
     * @param jmbag Students JMBAG, 10 digits.
     * @param lastName Students last name.
     * @param firstName Students first name.
     * @param finalGrade Students final grade. {@code int} between 1 and 5.
     */

    public StudentRecord(String jmbag, String lastName, String firstName, int finalGrade) {
        this.jmbag = Objects.requireNonNull(jmbag, "JMBAG can't be null!");
        this.lastName = Objects.requireNonNull(lastName, "Last name can't be null!");
        this.firstName = Objects.requireNonNull(firstName, "Last name can't be null!");
        this.finalGrade = finalGrade;
    }

    public String getJmbag() {
        return jmbag;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentRecord)) {
            return false;
        }
        StudentRecord that = (StudentRecord) o;
        return jmbag.equals(that.jmbag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbag);
    }
}
