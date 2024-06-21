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

    /**
     * Constructs a new Name object with the specified first and last names.
     *
     * @param first the first name
     * @param last the last name
     * @throws IllegalArgumentException if the first or last name is null or blank
     */
    public Name(final String first,
                final String last)
    {
        validateFirst(first);
        validateLast(last);
        this.first  = first;
        this.last   = last;
    }

    /**
     * Validates the last name.
     *
     * @param last the last name to validate
     * @throws IllegalArgumentException if the last name is null or blank
     */
    private static void validateLast(final String last)
    {
        if(last == null || last.isBlank())
        {
            throw new IllegalArgumentException("invalid last name");
        }
    }

    /**
     * Validates the first name.
     *
     * @param first the first name to validate
     * @throws IllegalArgumentException if the first name is null or blank
     */
    private static void validateFirst(final String first)
    {
        if(first == null || first.isBlank())
        {
            throw new IllegalArgumentException("invalid first name");
        }

    }

    /**
     * Returns the name in a pretty format, with the first letter of each name capitalized.
     *
     * @return the name in a pretty format
     */
    public String getPrettyName()
    {
        final String firstTitleCase = first.toUpperCase().charAt(INITIAL) + first.substring(TO_LOWER_CASE).toLowerCase();
        final String lastTitleCase = last.toUpperCase().charAt(INITIAL) + last.substring(TO_LOWER_CASE).toLowerCase();
        return firstTitleCase + " " + lastTitleCase;
    }

    /**
     * Returns the initials of the first and last names.
     *
     * @return the initials of the first and last names
     */
    public String getInitials()
    {
        final String firstInitial;
        final String lastInitial;

        firstInitial = first.toUpperCase().charAt(INITIAL) + "";
        lastInitial = last.toUpperCase().charAt(INITIAL) + "";

        return String.format("%s.%s.", firstInitial, lastInitial);
    }
}
