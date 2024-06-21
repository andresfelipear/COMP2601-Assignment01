/**
 * Date of Bank
 *
 * @author Andres Arevalo & Dustin & Shamin
 * @version 1.0
 */
public class Date implements Comparable<Date>, Orderable
{

    private int day;
    private int month;
    private int year;

    // CONSTANTS - DAYS OF THE WEEK
    private static final int SATURDAY;
    private static final int SUNDAY;
    private static final int MONDAY;
    private static final int TUESDAY;
    private static final int WEDNESDAY;
    private static final int THURSDAY;
    private static final int FRIDAY;

    // CONSTANTS - MONTHS
    private static final int JANUARY;
    private static final int FEBRUARY;
    private static final int MARCH;
    private static final int APRIL;
    private static final int MAY;
    private static final int JUNE;
    private static final int JULY;
    private static final int AUGUST;
    private static final int SEPTEMBER;
    private static final int OCTOBER;
    private static final int NOVEMBER;
    private static final int DECEMBER;
    private static final int DEFAULT_MONTH_CODE;

    // CONSTANTS - YEARS
    private static final int INITIAL_YEAR;
    private static final int FIRST_DAY_MONTH;
    public static final int CURRENT_YEAR;

    private static final int EXACT_DIVISION;

    // CONSTANTS - MONTHS CODES
    private static final int MONTH_CODE_JAN_OCT;
    private static final int MONTH_CODE_FEB_MARCH_NOV;
    private static final int MONTH_CODE_APRIL_JULY;
    private static final int MONTH_CODE_MAY;
    private static final int MONTH_CODE_JUNE;
    private static final int MONTH_CODE_AUGUST;
    private static final int MONTH_CODE_SEP_DEC;

    // CONSTANT - SPECIAL OFFSETS
    private static final int OFFSET_LEAP_YEAR_JAN_OR_FEB;
    private static final int OFFSET_SIXTEEN_AND_TWENTIETH_CENTURY;
    private static final int OFFSET_SEVENTEEN_AND_TWENTIETH_ONE_CENTURY;
    private static final int OFFSET_EIGHTEEN__CENTURY;
    private static final int DEFAULT_OFFSET;

    private static final int DAYS_OF_THE_WEEK;
    private static final int MONTHS_OF_THE_YEAR;
    private static final int FOUR_CENTURIES;
    private static final int CENTURIES;

    // CONSTANTS - CENTURIES
    private static final int START_SIXTEEN_CENTURY;
    private static final int END_SIXTEEN_CENTURY;
    private static final int START_SEVENTEEN_CENTURY;
    private static final int END_SEVENTEEN_CENTURY;
    private static final int START_EIGHTEEN_CENTURY;
    private static final int END_EIGHTEEN_CENTURY;
    private static final int START_TWENTIETH_CENTURY;
    private static final int END_TWENTIETH_CENTURY;
    private static final int START_TWENTY_ONE_CENTURY;
    private static final int END_TWENTY_ONE_CENTURY;

    private static final int NUMBER_OF_DAYS_FEBRUARY_LEAP_YEAR;
    private static final int NUMBER_OF_DAYS_FEBRUARY_REGULAR;
    private static final int NUMBER_OF_DAYS_APRIL_JUN_SEP_NOV;
    private static final int NUMBER_OF_DAYS_OTHER_MONTHS;

    private static final int HUNDREDS_DIGITS;
    private static final int FRECUENCY_LEAP_YEAR;
    private static final int STEP3_DIVISION;

    static
    {
        // DAYS OF THE WEEK
        SATURDAY  = 0;
        SUNDAY    = 1;
        MONDAY    = 2;
        TUESDAY   = 3;
        WEDNESDAY = 4;
        THURSDAY  = 5;
        FRIDAY    = 6;

        // MONTHS
        JANUARY            = 1;
        FEBRUARY           = 2;
        MARCH              = 3;
        APRIL              = 4;
        MAY                = 5;
        JUNE               = 6;
        JULY               = 7;
        AUGUST             = 8;
        SEPTEMBER          = 9;
        OCTOBER            = 10;
        NOVEMBER           = 11;
        DECEMBER           = 12;
        DEFAULT_MONTH_CODE = -1;

        // YEARS
        INITIAL_YEAR    = 1;
        FIRST_DAY_MONTH = 1;
        CURRENT_YEAR     = 2022;

        // MONTHS CODES
        MONTH_CODE_JAN_OCT       = 1;
        MONTH_CODE_FEB_MARCH_NOV = 4;
        MONTH_CODE_APRIL_JULY    = 0;
        MONTH_CODE_MAY           = 2;
        MONTH_CODE_JUNE          = 5;
        MONTH_CODE_AUGUST        = 3;
        MONTH_CODE_SEP_DEC       = 6;

        // SPECIAL OFFSETS
        OFFSET_LEAP_YEAR_JAN_OR_FEB                = 6;
        OFFSET_SIXTEEN_AND_TWENTIETH_CENTURY       = 6;
        OFFSET_SEVENTEEN_AND_TWENTIETH_ONE_CENTURY = 4;
        OFFSET_EIGHTEEN__CENTURY                   = 2;
        DEFAULT_OFFSET                             = 0;

        DAYS_OF_THE_WEEK   = 7;
        MONTHS_OF_THE_YEAR = 12;
        FOUR_CENTURIES     = 400;
        CENTURIES          = 100;

        // CENTURIES
        START_SIXTEEN_CENTURY    = 1600;
        END_SIXTEEN_CENTURY      = 1699;
        START_SEVENTEEN_CENTURY  = 1700;
        END_SEVENTEEN_CENTURY    = 1799;
        START_EIGHTEEN_CENTURY   = 1800;
        END_EIGHTEEN_CENTURY     = 1899;
        START_TWENTIETH_CENTURY  = 2000;
        END_TWENTIETH_CENTURY    = 2099;
        START_TWENTY_ONE_CENTURY = 2100;
        END_TWENTY_ONE_CENTURY   = 2199;

        // MONTH DAYS
        NUMBER_OF_DAYS_FEBRUARY_LEAP_YEAR = 29;
        NUMBER_OF_DAYS_FEBRUARY_REGULAR   = 28;
        NUMBER_OF_DAYS_APRIL_JUN_SEP_NOV  = 30;
        NUMBER_OF_DAYS_OTHER_MONTHS       = 31;

        HUNDREDS_DIGITS     = 100;
        FRECUENCY_LEAP_YEAR = 4;
        STEP3_DIVISION      = 4;
        EXACT_DIVISION      = 0;
    }

    /**
     * Constructs a new Date object with the specified day, month, and year.
     *
     * @param day the day of the month
     * @param month the month of the year
     * @param year the year
     * @throws IllegalArgumentException if the day, month, or year is invalid
     */
    public Date(final int day,
                final int month,
                final int year)
    {
        validateYear(year);
        validateMonth(month);
        validateDay(day, month, year);

        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Validates the day of the month.
     *
     * @param day the day to validate
     * @param month the month of the year
     * @param year the year
     * @throws IllegalArgumentException if the day is invalid for the given month and year
     */
    private static void validateDay(final int day, final int month, final int year)
    {
        final int numberOfDaysInMonth;
        numberOfDaysInMonth = getNumberOfDaysPerMonth(month, year);

        if(day > numberOfDaysInMonth)
        {
            throw new IllegalArgumentException("invalid day of the month");
        }

    }

    /**
     * Returns the number of days in the specified month and year.
     *
     * @param month the month
     * @param year the year
     * @return the number of days in the specified month and year
     */
    private static int getNumberOfDaysPerMonth(final int month, final int year)
    {
        if(month == FEBRUARY)
        {
            if(isLeapYear(year))
            {
                return NUMBER_OF_DAYS_FEBRUARY_LEAP_YEAR;
            }
            else
            {
                return NUMBER_OF_DAYS_FEBRUARY_REGULAR;
            }
        }
        if(month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) // month with 30 days.
        {
            return NUMBER_OF_DAYS_APRIL_JUN_SEP_NOV;
        }
        else
        {
            return NUMBER_OF_DAYS_OTHER_MONTHS;
        }
    }

    /**
     * Validates the month of the year.
     *
     * @param month the month to validate
     * @throws IllegalArgumentException if the month is not between JANUARY and DECEMBER
     */
    private static void validateMonth(final int month)
    {
        if(month < JANUARY || month > DECEMBER)
        {
            throw new IllegalArgumentException("invalid month");
        }
    }

    /**
     * Validates the year.
     *
     * @param year the year to validate
     * @throws IllegalArgumentException if the year is not between the INITIAL_YEAR and the CURRENT_YEAR
     */
    private static void validateYear(final int year)
    {
        if(year < INITIAL_YEAR)
        {
            throw new IllegalArgumentException("invalid year");
        }
    }

    /**
     * @return day of the year in format DD
     */
    public int getDay()
    {
        return day;
    }

    /**
     * @return month of the year in format MM
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * @return year in format YYYY
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @return date in format YYYY-MM-DD
     */
    public String getYyyyMmDd()
    {
        return String.format("%d-%02d-%02d",
                             year,
                             month,
                             day);
    }

    /**
     * Calculates and returns the name of the day of the week for a given date (month, day, year).
     * Following the next steps:
     * 1) get the last two digits of the year and get how many twelves fit in it.
     * 2) determine the remainder of step 1.
     * 3) determine how many fours fit into the remainder.
     * 4) add the day of the month.
     * 5) add the month code from the table below:
     *      Jan = 1  May = 2  Sep = 6
     *      Feb = 4  Jun = 5  Oct = 1
     *      Mar = 4  Jul = 0  Nov = 4
     *      Apr = 0  Aug = 3  Dec = 6
     * 6) add the result of all the previous steps. Then mod by 7.
     * Note: some dates require special offsets to add after step 5:
     * January and February dates in leap years: add 6 to step 5
     *      All dates in the 1600s: add 6 to step 5
     *      All dates in the 1700s: add 4 to step 5
     *      All dates in the 1800s: add 2 to step 5
     *      All dates in the 2000s: add 6 to step 5
     *      All dates in the 2100s: add 4 to step 5
     * @return The name of the day of the week as a String.
     */
    public String getDayOfTheWeek()
    {
        final int lastTwoDigitsYear;
        final int step1;
        final int step2;
        final int step3;
        final int step4;
        int       step5;
        final int step6;

        lastTwoDigitsYear = year % HUNDREDS_DIGITS;
        step1 = lastTwoDigitsYear / MONTHS_OF_THE_YEAR;
        step2 = lastTwoDigitsYear % MONTHS_OF_THE_YEAR;
        step3 = step2 / STEP3_DIVISION;
        step4 = day;
        step5 = getMonthCode(month);

        if((month == JANUARY || month == FEBRUARY) && isLeapYear(year))
        {
            step5 += OFFSET_LEAP_YEAR_JAN_OR_FEB;
        }

        step5 += specialOffsetCenturies(year);

        step6 = (step1 + step2 + step3 + step4 + step5) % DAYS_OF_THE_WEEK;

        return dayOfWeek(step6);
    }

    /**
     * Checks if the given year is a leap year. Follow these rules:
     * If the year is evenly divisible by FRECUENCY_LEAP_YEAR and not by 100, or is evenly divisible by 400, the year is a leap year.
     * If none of these conditions are met, the year is not a leap year.
     *
     * @param year The year to check.
     * @return True if it's a leap year, False otherwise.
     */
    public static boolean isLeapYear(int year)
    {
        return (year % FRECUENCY_LEAP_YEAR == EXACT_DIVISION && year % CENTURIES != EXACT_DIVISION) ||
                year % FOUR_CENTURIES == EXACT_DIVISION;
    }

    /**
     * Retrieves the month code for the given month.
     *
     * @param month The month for which to get the code.
     * @return The month code as an integer.
     */
    public static int getMonthCode(int month)
    {
        if(month == JANUARY || month == OCTOBER)
        {
            return MONTH_CODE_JAN_OCT;
        }
        else if(month == FEBRUARY || month == MARCH || month == NOVEMBER)
        {
            return MONTH_CODE_FEB_MARCH_NOV;
        }
        else if(month == APRIL || month == JULY)
        {
            return MONTH_CODE_APRIL_JULY;
        }
        else if(month == MAY)
        {
            return MONTH_CODE_MAY;
        }
        else if(month == JUNE)
        {
            return MONTH_CODE_JUNE;
        }
        else if(month == AUGUST)
        {
            return MONTH_CODE_AUGUST;
        }
        else if(month == SEPTEMBER || month == DECEMBER)
        {
            return MONTH_CODE_SEP_DEC;
        }
        else
        {
            return DEFAULT_MONTH_CODE; // Invalid month
        }
    }

    /**
     * Applies special offsets for centuries based on the given year.
     *
     * @param year The year for which to calculate the offset.
     * @return The special offset as an integer.
     */
    public static int specialOffsetCenturies(int year)
    {
        if(year >= START_SIXTEEN_CENTURY && year <= END_SIXTEEN_CENTURY)
        {
            return OFFSET_SIXTEEN_AND_TWENTIETH_CENTURY;
        }
        else if(year >= START_SEVENTEEN_CENTURY && year <= END_SEVENTEEN_CENTURY)
        {
            return OFFSET_SEVENTEEN_AND_TWENTIETH_ONE_CENTURY;
        }
        else if(year >= START_EIGHTEEN_CENTURY && year <= END_EIGHTEEN_CENTURY)
        {
            return OFFSET_EIGHTEEN__CENTURY;
        }
        else if(year >= START_TWENTIETH_CENTURY && year <= END_TWENTIETH_CENTURY)
        {
            return OFFSET_SIXTEEN_AND_TWENTIETH_CENTURY;
        }
        else if(year >= START_TWENTY_ONE_CENTURY && year <= END_TWENTY_ONE_CENTURY)
        {
            return OFFSET_SEVENTEEN_AND_TWENTIETH_ONE_CENTURY;
        }
        else // any other century
        {
            return DEFAULT_OFFSET;
        }
    }

    /**
     * Converts the step number to the corresponding day of the week.
     *
     * @param step6 The step number calculated in the day of the week algorithm.
     * @return The name of the day of the week as a String.
     */
    public static String dayOfWeek(int step6)
    {
        if(step6 == SATURDAY)
        {
            return "Saturday";
        }
        else if(step6 == SUNDAY)
        {
            return "Sunday";
        }
        else if(step6 == MONDAY)
        {
            return "Monday";
        }
        else if(step6 == TUESDAY)
        {
            return "Tuesday";
        }
        else if(step6 == WEDNESDAY)
        {
            return "Wednesday";
        }
        else if(step6 == THURSDAY)
        {
            return "Thursday";
        }
        else if(step6 == FRIDAY)
        {
            return "Friday";
        }
        else
        {
            return "Invalid day";
        }
    }

    /**
     * @return the full date in the format dayWeek, FullMonth dd, yyyy!
     * (e.g "friday, March 14, 1879")
     */
    public String getFullDate()
    {
        final String fullDate;

        fullDate = String.format("%s, %s %s, %s",
                                 getDayOfTheWeek(),
                                 getFullMonth(),
                                 getDay(),
                                 getYear());

        return fullDate;
    }

    /**
     * Get the month in format MM and return it as a String
     *
     * @return Full month as a string (e.g. 01 -> January)
     */
    private String getFullMonth()
    {
        final int month;
        month = getMonth();

        if(month == JANUARY)
        {
            return "January";
        }
        else if(month == FEBRUARY)
        {
            return "February";
        }
        else if(month == MARCH)
        {
            return "March";
        }
        else if(month == APRIL)
        {
            return "April";
        }
        else if(month == MAY)
        {
            return "May";
        }
        else if(month == JUNE)
        {
            return "June";
        }
        else if(month == JULY)
        {
            return "July";
        }
        else if(month == AUGUST)
        {
            return "August";
        }
        else if(month == SEPTEMBER)
        {
            return "September";
        }
        else if(month == OCTOBER)
        {
            return "October";
        }
        else if(month == NOVEMBER)
        {
            return "November";
        }
        else if(month == DECEMBER)
        {
            return "December";
        }
        else
        {
            return "Invalid month";
        }
    }

    /**
     * Returns the date in YYYY-MM-DD format.
     *
     * @return the date as a string in YYYY-MM-DD format
     */
    @Override
    public String toString()
    {
        return getYyyyMmDd();
    }

    /**
     * Returns the previous date.
     *
     * @return a new Date object representing the previous date
     */
    @Override
    public Date previous()
    {
        final Date previusDate;

        previusDate = new Date(day, month, year);
        previusDate.decreesDay();

        return previusDate;
    }

    /**
     * Returns the next date.
     *
     * @return a new Date object representing the next date
     */
    @Override
    public Date next()
    {
        final Date nextDate;

        nextDate = new Date(day, month, year);
        nextDate.incrementDay();

        return nextDate;
    }

    /**
     * Decreases the year by one.
     */
    public void decreesYear()
    {
        year--;
    }

    /**
     * Decreases the month by one, adjusting the year if necessary.
     */
    public void decreesMonth()
    {
        if(month == JANUARY)
        {
            decreesYear();
            month = DECEMBER;
        }
        else
        {
            month--;
        }
    }

    /**
     * Decreases the day by one, adjusting the month and year if necessary.
     */
    public void decreesDay()
    {
        if(day == FIRST_DAY_MONTH)
        {
            decreesMonth();
            day = getNumberOfDaysPerMonth(month, year);
        }
        else
        {
            day--;
        }
    }

    /**
     * Increases the year by one.
     */
    public void incrementYear()
    {
        year++;
    }

    /**
     * Increases the month by one, adjusting the year if necessary.
     */
    public void incrementMonth()
    {
        if(month == DECEMBER)
        {
            incrementYear();
            month = JANUARY;
        }
        else
        {
            month++;
        }
    }

    /**
     * Increases the day by one, adjusting the month and year if necessary.
     */
    public void incrementDay()
    {
        if(day == getNumberOfDaysPerMonth(month, year))
        {
            incrementMonth();
            day = FIRST_DAY_MONTH;
        }
        else
        {
            day++;
        }
    }

    /**
     * Compares this date with another date.
     *
     * @param that the other date to compare to
     * @return a negative integer, zero, or a positive integer if this date is less than, equal to, or greater than the specified date
     */
    @Override
    public int compareTo(final Date that)
    {
        if(this.year == that.getYear())
        {
            if(this.month == that.getMonth())
            {
                return this.day - that.getDay();
            }
            else
            {
                return this.month - that.getMonth();
            }
        }
        else
        {
            return this.year - that.getYear();
        }
    }
}
