import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * School
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class School
{
    private final List<Person> references;
    private static final String FILE_NAME;

    static
    {
       FILE_NAME = "people.txt";
    }

    {
        references = new ArrayList<Person>();
    }

    /**
     * Registers a person in the school.
     *
     * @param p the person to register
     * @throws IllegalPersonException if the person is null
     */
    public void register(final Person p)
    {
        if(p == null)
        {
            throw new IllegalPersonException("cannot register a non-person");
        }
        references.add(p);
    }

    /**
     * Prints the roster of registered persons.
     */
    public void printRoster()
    {
        references.forEach(System.out::println);
    }

    /**
     * Prints the ages and years of registered persons.
     */
    public void printAgesAndYears()
    {
        final Writeable w;
        w = (fullName, yearBorn, maxYear) ->
        {
            int age = 0;
            for(int i = yearBorn; i <= maxYear; i++)
            {
                System.out.printf("%s: %d (age %d)%n",
                                  fullName,
                                  i,
                                  age);
                age++;
            }
        };

        references.forEach((p ->
        {
            final String fullName;
            final int    yearBorn;
            final int    maxYear;

            fullName = p.getName().getPrettyName();
            yearBorn = p.getDateOfBirth().getYear();

            if(p.isAlive())
            {
                maxYear = Date.CURRENT_YEAR;
            }
            else
            {
                maxYear = p.getDateOfDeath().getYear();
            }

            w.printData(fullName,
                        yearBorn,
                        maxYear);
        }));
    }

    /**
     * Saves the details of registered persons to a file.
     * Following the format (e.g. "Tiger Woods(T.W.) was born on Tuesday 1975-12-30.")
     * If the person is death add "and died on Monday 1955-04-18." to the end of the string.
     */
    public void saveDetails()
    {
        FileWriter fw;

        fw = null;

        try
        {
            fw = new FileWriter(FILE_NAME);

            final FileWriter finalFw = fw;
            references.forEach(
                    person ->
                    {
                        try
                        {
                            finalFw.write(person.getDetailsForWriteFile() + System.lineSeparator());
                        }
                        catch(IOException e)
                        {
                            throw new RuntimeException(e);
                        }
                    });
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fw != null)
                {
                    fw.close();
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
