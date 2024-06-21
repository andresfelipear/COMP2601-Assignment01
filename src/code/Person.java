import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Person
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Person implements Comparable<Person>
{
    private final Date born;
    private final Name name;

    private static final int POSITIVE_NUMBER;

    static
    {
        POSITIVE_NUMBER = 0;
    }

    Date died;
    {
        died = null;
    }

    /**
     * Constructs a new Person object with the specified date of birth and name.
     *
     * @param born the date of birth
     * @param name the name
     * @throws IllegalPersonException if the date of birth or name is invalid
     */
    public Person(final Date born,
                  final Name name)
    {
        validateBorn(born);
        validateName(name);
        this.born = born;
        this.name = name;
    }

    /**
     * Validates the name.
     *
     * @param name the name to validate
     * @throws IllegalPersonException if the name is null
     */
    private static void validateName(final Name name)
    {
        if(name == null)
        {
            throw new IllegalPersonException("invalid name");
        }
    }

    /**
     * Validates the date of birth.
     *
     * @param born the date of birth to validate
     * @throws IllegalPersonException if the date of birth is null
     */
    private static void validateBorn(final Date born)
    {
        if(born == null)
        {
            throw new IllegalPersonException("invalid date of birth");
        }
    }

    /**
     * Validates the date of death.
     *
     * @param dateOfDeath the date of death to validate
     * @param dateOfBirth the date of birth to compare against
     * @throws IllegalPersonException if the date of death is before the date of birth
     */

    private static void validateDie(final Date dateOfDeath, final Date dateOfBirth)
    {
        if(dateOfDeath.compareTo(dateOfBirth) < POSITIVE_NUMBER)
        {
            throw new IllegalPersonException("invalid date of death. It cannot be before de date of birth");
        }
    }

    /**
     * Returns the date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth()
    {
        return born;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Returns the date of death, or null if the person is still alive.
     *
     * @return the date of death or null
     */
    public Date getDateOfDeath()
    {
        return died;
    }

    /**
     * Sets the date of death.
     *
     * @param dateOfDeath the date of death to set
     * @throws IllegalPersonException if the date of death is before the date of birth
     */
    public void die(final Date dateOfDeath)
    {
        validateDie(dateOfDeath, born);
        died = dateOfDeath;
    }

    /**
     * Checks if the person is still alive.
     *
     * @return true if the person is still alive, false otherwise
     */
    public boolean isAlive()
    {
        return died == null;
    }

    /**
     * Returns a string indicating the person's alive status.
     *
     * @return a string indicating if the person is still alive or the date they died
     */
    public String getAliveStatusToPrint()
    {
        if(isAlive())
        {
            return "is still alive";
        }
        else
        {
            return "died " + died.getYyyyMmDd();
        }
    }

    /**
     * Compares this person with another person based on their date of birth.
     *
     * @param that the other person to compare to
     * @return a negative integer, zero, or a positive integer if this person's date of birth is before, equal to, or after the specified person's date of birth
     */
    @Override
    public int compareTo(final Person that)
    {
        return this.born.compareTo(that.born);
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString()
    {
        return String.format("%s was born %s and %s",
                              name.getPrettyName(),
                              born.getYyyyMmDd(),
                              getAliveStatusToPrint());
    }

    public String getDetailsForWriteFile()
    {
        if(isAlive())
        {
            return String.format("%s (%s) was born on %s %s.",
                                 name.getPrettyName(),
                                 name.getInitials(),
                                 born.getDayOfTheWeek(),
                                 born.getYyyyMmDd());
        }
        else
        {
            return String.format("%s (%s) was born on %s %s and died on %s %s.",
                                 name.getPrettyName(),
                                 name.getInitials(),
                                 born.getDayOfTheWeek(),
                                 born.getYyyyMmDd(),
                                 died.getDayOfTheWeek(),
                                 died.getYyyyMmDd());
        }

    }
}
