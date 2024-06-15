/**
 * Name
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Name
{
    private final String first;
    private final String last;

    private static final int INITIAL;
    private static final int TO_LOWER_CASE;

    static
    {
        INITIAL = 0;
        TO_LOWER_CASE = 1;

    }
    public Name(final String first,
                final String last)
    {
        validateFirst(first);
        validateLast(last);
        this.first = first;
        this.last = last;
    }

    private static void validateLast(final String last)
    {
        if(last == null || last.isBlank())
        {
            throw new IllegalArgumentException("invalid last name");
        }
    }

    private static void validateFirst(final String first)
    {
        if(first == null || first.isBlank())
        {
            throw new IllegalArgumentException("invalid first name");
        }

    }

    public String getPrettyName()
    {
        final String firstTitleCase = first.toUpperCase().charAt(INITIAL) + first.substring(TO_LOWER_CASE).toLowerCase();
        final String lastTitleCase = last.toUpperCase().charAt(INITIAL) + last.substring(TO_LOWER_CASE).toLowerCase();
        return firstTitleCase + " " + lastTitleCase;
    }

    public String getInitials()
    {
        final String firstInitial;
        final String lastInitial;

        firstInitial = first.toUpperCase().charAt(INITIAL) + "";
        lastInitial = last.toUpperCase().charAt(INITIAL) + "";

        return String.format("%s.%s.", firstInitial, lastInitial);
    }
}
