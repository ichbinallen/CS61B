/* Date.java */

import java.io.*;

class Date {

    /* Put your private data fields here. */
    private int month;
    private int day;
    private int year;

    /** Constructs a date with the given month, day and year.   If the date is
     *  not valid, the entire program will halt with an error message.
     *  @param month is a month, numbered in the range 1...12.
     *  @param day is between 1 and the number of days in the given month.
     *  @param year is the year in question, with no digits omitted.
     */
    public Date(int month, int day, int year) {
        if (isValidDate(month, day, year)) {
            this.month = month;
            this.day = day;
            this.year = year;
        } else {
            System.out.println("Invalid Date.  Exiting program");
            System.exit(0);
        }
    }

    /** Constructs a Date object corresponding to the given string.
     *  @param s should be a string of the form "month/day/year" where month must
     *  be one or two digits, day must be one or two digits, and year must be
     *  between 1 and 4 digits.  If s does not match these requirements or is not
     *  a valid date, the program halts with an error message.
     */
    public Date(String s) {
        String[] split = s.split("/");
        int month = Integer.parseInt(split[0]);
        int day = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        if (isValidDate(month, day, year)) {
            this.month = month;
            this.day = day;
            this.year = year;
        } else {
            System.out.println("Invalid Date.  Exiting program");
            System.exit(0);
        }
    }

    /** Checks whether the given year is a leap year.
     *  @return true if and only if the input year is a leap year.
     */
    public static boolean isLeapYear(int year) {
        boolean isLeap = false;
        if (year % 4 == 0) {
            isLeap = true;
        }
        if (year % 100 == 0) {
            isLeap = false;
        }
        if (year % 400 == 0) {
            isLeap = true;
        }
        return isLeap;
    }

    /** Returns the number of days in a given month.
     *  @param month is a month, numbered in the range 1...12.
     *  @param year is the year in question, with no digits omitted.
     *  @return the number of days in the given month.
     */
    public static int daysInMonth(int month, int year) {
        int days = 31; // Jan, Mar, May, Jul, Aug, Oct, Dec
        if (month == 2) {
            if (isLeapYear(year)){
                days = 29;
            } else {
                days = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30; // Apr, Jun, Sep, Nov
        }
        return days;
    }

    /** Checks whether the given date is valid.
     *  @return true if and only if month/day/year constitute a valid date.
     *
     *  Years prior to A.D. 1 are NOT valid.
     */
    public static boolean isValidDate(int month, int day, int year) {
        boolean isValid = true;
        if (month < 1 || month > 12) {
            isValid = false;
        }
        if (day < 1 || day > daysInMonth(month, year)) {
            isValid = false;
        }
        if (year < 1) {
            isValid = false;
        }
        return isValid;
    }

    /** Returns a string representation of this date in the form month/day/year.
     *  The month, day, and year are expressed in full as integers; for example,
     *  12/7/2006 or 3/21/407.
     *  @return a String representation of this date.
     */
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /** Determines whether this Date is before the Date d.
     *  @return true if and only if this Date is before d.
     */
    public boolean isBefore(Date d) {
        int yearDif = this.year - d.year;
        if (yearDif < 0) {
            return true;
        } else if (yearDif > 0) {
            return false;
        } else {
            int monthDif = this.month - d.month;
            if (monthDif < 0) {
                return true;
            } else if (monthDif > 0) {
                return false;
            } else {
                int dayDif = this.day - d.day;
                if (dayDif < 0) {
                    return true;
                } else if (dayDif > 0) {
                    return false;
                } else {
                    return false;
                }
            }
        }
    }

    /** Determines whether this Date is after the Date d.
     *  @return true if and only if this Date is after d.
     */
    public boolean isAfter(Date d) {
        if (this.equals(d)) {
            return false;
        } else {
            return(! this.isBefore(d));
        }
    }

    /** Returns the number of this Date in the year.
     *  @return a number n in the range 1...366, inclusive, such that this Date
     *  is the nth day of its year.  (366 is used only for December 31 in a leap
     *  year.)
     */
    public int dayInYear() {
        int dayInYear = day;
        for (int mon = 1; mon < month; mon++) {
            dayInYear = dayInYear + daysInMonth(mon, year);
        }
        return dayInYear;
    }

    /** Determines the difference in days between d and this Date.  For example,
     *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
     *  If this Date occurs before d, the result is negative.
     *  @return the difference in days between d and this date.
     */
    public int difference(Date d) {
        if (isAfter(d)) {
            return -d.difference(this);
        }
        int dif = 0;
        int yeardif = d.year - this.year;
        for(int i = 1; i <= yeardif; i++) {
            dif += 365;
            if (isLeapYear(this.year + i)) {
                dif += 1;
            }
        }
        dif += (d.dayInYear() - this.dayInYear());

        return dif;
    }

    public static void main(String[] argv) {
        System.out.println("\nTesting constructors.");
        Date d1 = new Date(1, 1, 1);
        System.out.println("Date should be 1/1/1: " + d1);
        d1 = new Date("2/4/2");
        System.out.println("Date should be 2/4/2: " + d1);
        d1 = new Date("2/29/2000");
        System.out.println("Date should be 2/29/2000: " + d1);
        d1 = new Date("2/29/1904");
        System.out.println("Date should be 2/29/1904: " + d1);

        d1 = new Date(12, 31, 1975);
        System.out.println("Date should be 12/31/1975: " + d1);
        Date d2 = new Date("1/1/1976");
        System.out.println("Date should be 1/1/1976: " + d2);
        Date d3 = new Date("1/2/1976");
        System.out.println("Date should be 1/2/1976: " + d3);

        Date d4 = new Date("2/27/1977");
        Date d5 = new Date("8/31/2110");

        /* I recommend you write code to test the isLeapYear function! */
        boolean b2001, b2008, b1900, b2000;
        b2001 = isLeapYear(2001);
        b2008 = isLeapYear(2008);
        b1900 = isLeapYear(1900);
        b2000 = isLeapYear(2000);

        System.out.println("2001 " + b2001);
        System.out.println("2008 " + b2008);
        System.out.println("1900 " + b1900);
        System.out.println("2000 " + b2000);

        //Date dInvalid = new Date("2/29/1900");

        System.out.println("\nTesting before and after.");
        System.out.println(d2 + " after " + d1 + " should be true: " +
                d2.isAfter(d1));
        System.out.println(d3 + " after " + d2 + " should be true: " +
                d3.isAfter(d2));
        System.out.println(d1 + " after " + d1 + " should be false: " +
                d1.isAfter(d1));
        System.out.println(d1 + " after " + d2 + " should be false: " +
                d1.isAfter(d2));
        System.out.println(d2 + " after " + d3 + " should be false: " +
                d2.isAfter(d3));

        System.out.println(d1 + " before " + d2 + " should be true: " +
                d1.isBefore(d2));
        System.out.println(d2 + " before " + d3 + " should be true: " +
                d2.isBefore(d3));
        System.out.println(d1 + " before " + d1 + " should be false: " +
                d1.isBefore(d1));
        System.out.println(d2 + " before " + d1 + " should be false: " +
                d2.isBefore(d1));
        System.out.println(d3 + " before " + d2 + " should be false: " +
                d3.isBefore(d2));

        Date d6 = new Date("9/17/1991");
        Date d7 = new Date(9, 17, 1991);

        System.out.println(d6 + " before " + d7 + " should be false: " +
                d6.isBefore(d7));

        System.out.println(d6 + " after " + d7 + " should be false: " +
                d6.isAfter(d7));


        System.out.println("\nTesting dayInYear.");
        Date d8 = new Date("1/15/2000");

        Date d9 = new Date("2/15/2000");
        Date d10 = new Date("3/15/2000");
        Date d11 = new Date("3/15/2001");

        System.out.println(d8 + " is the number " + d8.dayInYear() + " day in the year.");
        System.out.println(d9 + " is the number " + d9.dayInYear() + " day in the year.");
        System.out.println(d10 + " is the number " + d10.dayInYear() + " day in the year.");
        System.out.println(d11 + " is the number " + d11.dayInYear() + " day in the year.");

        System.out.println("\nTesting difference.");
        Date Brian = new Date("03/10/1990");
        Date Allen = new Date("03/11/1992");

        Date one = new Date("01/01/0001");
        Date thou = new Date("01/01/1001");

        System.out.println(Brian.difference(Allen));
        System.out.println(Allen.difference(Brian));

        System.out.println(one.difference(thou));
        /*
        System.out.println(d1 + " - " + d1  + " should be 0: " +
                d1.difference(d1));
        System.out.println(d2 + " - " + d1  + " should be 1: " +
                d2.difference(d1));
        System.out.println(d3 + " - " + d1  + " should be 2: " +
                d3.difference(d1));
        System.out.println(d3 + " - " + d4  + " should be -422: " +
                d3.difference(d4));
        System.out.println(d5 + " - " + d4  + " should be 48762: " +
                d5.difference(d4));
        */
    }
}
