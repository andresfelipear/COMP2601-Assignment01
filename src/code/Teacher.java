/**
 * Teacher
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Teacher extends Person
{
    final private String specialty;

    public Teacher(final Date born,
                   final Name name,
                   final String specialty)
    {
        super(born,
              name);
        validatesSpeciality(specialty);
        this.specialty = specialty;
    }

    private static void validatesSpeciality(final String speciality)
    {
        if(speciality.isBlank())
        {
            throw new IllegalPersonException("bad specialty");
        }
    }

    public String getSpecialty()
    {
        return specialty;
    }

    /**
     * Returns a string representation of a Teacher.
     *
     * @return a string representation of a Teacher.
     */
    public String toString()
    {
        return String.format("%s (specialty: %s) was born %s and %s",
                             getName().getPrettyName(),
                             specialty,
                             getDateOfBirth().getYyyyMmDd(),
                             getAliveStatusToPrint());
    }
}
