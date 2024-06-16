/**
 * Student
 *
 * @author user
 * @version 1.0
 */
public class Student extends Person
{
    private final String studentNumber;

    private static final int STUDENT_NUMBER_LENGTH = 9;

    /**
     * Constructs a new Student object with the specified date of birth, name, and student number.
     *
     * @param born the date of birth
     * @param name the name
     * @param studentNumber the student number
     * @throws IllegalPersonException if the student number is invalid
     */
    public Student(final Date born,
                   final Name name,
                   final String studentNumber)
    {
        super(born,
              name);

        validatesStudentNumber(studentNumber);
        this.studentNumber = studentNumber;
    }

    /**
     * Validates the student number.
     *
     * @param studentNumber the student number to validate
     * @throws IllegalPersonException if the student number is null, blank, or not the correct length
     */
    private static void validatesStudentNumber(final String studentNumber)
    {
        if(studentNumber == null || studentNumber.isBlank() || studentNumber.length() != STUDENT_NUMBER_LENGTH)
        {
            throw new IllegalPersonException("bad student number");
        }
    }

    /**
     * Returns the student number.
     *
     * @return the student number
     */
    public String getStudentNumber()
    {
        return studentNumber;
    }

    /**
     * Returns a string representation of the student.
     *
     * @return a string representation of the student
     */
    public String toString()
    {
        return String.format("%s (student number: %s) was born %s and %s",
                             getName().getPrettyName(),
                             studentNumber,
                             getDateOfBirth().getYyyyMmDd(),
                             getAliveStatusToPrint());
    }

}
