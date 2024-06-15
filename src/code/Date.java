/**
 * Date of Bank
 *
 * @author Andres Arevalo & Dustin & Shamin
 * @version 1.0
 */
public class Date implements Comparable<Date>, Orderable
{

    int day;
    int month;
    int year;

    // CONSTANTS - DAYS OF THE WEEK
    private static final int SATURDAY = 0;
    private static final int SUNDAY = 1;
    private static final int MONDAY = 2;
    private static final int TUESDAY = 3;
    private static final int WEDNESDAY = 4;
    private static final int THURSDAY = 5;
    private static final int FRIDAY = 6;

    // CONSTANTS - MONTHS
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    private static final int DEFAULT_MONTH_CODE = -1;

    // CONSTANTS - YEARS
    private static final int INITIAL_YEAR = 1;
    private static final int FIRST_DAY_MONTH = 1;
    public static final int CURRENT_YEAR = 2024;

    private static final int EXACT_DIVISION = 0;

    // CONSTANTS - MONTHS CODES
    private static final int MONTH_CODE_JAN_OCT = 1;
    private static final int MONTH_CODE_FEB_MARCH_NOV = 4;
    private static final int MONTH_CODE_APRIL_JULY = 0;
    private static final int MONTH_CODE_MAY = 2;
    private static final int MONTH_CODE_JUNE = 5;
    private static final int MONTH_CODE_AUGUST = 3;
    private static final int MONTH_CODE_SEP_DEC = 6;

    // CONSTANT - SPECIAL OFFSETS
    private static final int OFFSET_LEAP_YEAR_JAN_OR_FEB = 6;
    private static final int OFFSET_SIXTEEN_AND_TWENTIETH_CENTURY = 6;
    private static final int OFFSET_SEVENTEEN_AND_TWENTIETH_ONE_CENTURY = 4;
    private static final int OFFSET_EIGHTEEN__CENTURY = 2;
    private static final int DEFAULT_OFFSET = 0;

    private static final int DAYS_OF_THE_WEEK = 7;
    private static final int MONTHS_OF_THE_YEAR = 12;
    private static final int FOUR_CENTURIES = 400;
    private static final int CENTURIES = 100;

    // CONSTANTS - CENTURIES
    private static final int START_SIXTEEN_CENTURY = 1600;
    private static final int END_SIXTEEN_CENTURY = 1699;
    private static final int START_SEVENTEEN_CENTURY = 1700;
    private static final int END_SEVENTEEN_CENTURY = 1799;
    private static final int START_EIGHTEEN_CENTURY = 1800;
    private static final int END_EIGHTEEN_CENTURY = 1899;
    private static final int START_TWENTIETH_CENTURY = 2000;
    private static final int END_TWENTIETH_CENTURY = 2099;
    private static final int START_TWENTY_ONE_CENTURY = 2100;
    private static final int END_TWENTY_ONE_CENTURY = 2199;

    private static final int NUMBER_OF_DAYS_FEBRUARY_LEAP_YEAR = 29;
    private static final int NUMBER_OF_DAYS_FEBRUARY_REGULAR = 28;
    private static final int NUMBER_OF_DAYS_APRIL_JUN_SEP_NOV = 30;
    private static final int NUMBER_OF_DAYS_OTHER_MONTHS = 31;

    private static final int HUNDREDS_DIGITS = 100;
    private static final int FRECUENCY_LEAP_YEAR = 4;
    private static final int STEP3_DIVISION = 4;

    public static void main(String[] args)
    {
        Date d3 = new Date(1, 0, 1);
    }

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

    private static void validateDay(final int day, final int month, final int year)
    {
        final int numberOfDaysInMonth;
        numberOfDaysInMonth = getNumberOfDaysPerMonth(month, year);

        if(day > numberOfDaysInMonth)
        {
            throw new IllegalArgumentException("invalid day of the month");
        }

    }

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

    private static void validateMonth(final int month)
    {
        if(month < JANUARY || month > DECEMBER)
        {
            throw new IllegalArgumentException("invalid month");
        }
    }

    private static void validateYear(final int year)
    {
        if(year < INITIAL_YEAR || year > CURRENT_YEAR)
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
        return switch(month)
        {
            case JANUARY, OCTOBER -> MONTH_CODE_JAN_OCT;
            case FEBRUARY, MARCH, NOVEMBER -> MONTH_CODE_FEB_MARCH_NOV;
            case APRIL, JULY -> MONTH_CODE_APRIL_JULY;
            case MAY -> MONTH_CODE_MAY;
            case JUNE -> MONTH_CODE_JUNE;
            case AUGUST -> MONTH_CODE_AUGUST;
            case SEPTEMBER, DECEMBER -> MONTH_CODE_SEP_DEC;
            default -> DEFAULT_MONTH_CODE; // Invalid month
        };
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
        return switch(step6)
        {
            case SATURDAY -> "Saturday";
            case SUNDAY -> "Sunday";
            case MONDAY -> "Monday";
            case TUESDAY -> "Tuesday";
            case WEDNESDAY -> "Wednesday";
            case THURSDAY -> "Thursday";
            case FRIDAY -> "Friday";
            default -> "Invalid day";
        };
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
        return switch(getMonth())
        {
            case JANUARY -> "January";
            case FEBRUARY -> "February";
            case MARCH -> "March";
            case APRIL -> "April";
            case MAY -> "May";
            case JUNE -> "June";
            case JULY -> "July";
            case AUGUST -> "August";
            case SEPTEMBER -> "September";
            case OCTOBER -> "October";
            case NOVEMBER -> "November";
            case DECEMBER -> "December";
            default -> "Invalid day";
        };
    }

    @Override
    public String toString()
    {
        return getYyyyMmDd();
    }

    @Override
    public Date previous()
    {
        final Date previusDate;

        previusDate = new Date(day, month, year);
        previusDate.decreesDay();

        return previusDate;
    }

    @Override
    public Date next()
    {
        final Date nextDate;

        nextDate = new Date(day, month, year);
        nextDate.incrementDay();

        return nextDate;
    }

    public void decreesYear()
    {
        year--;
    }

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

    public void incrementYear()
    {
        year++;
    }

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
