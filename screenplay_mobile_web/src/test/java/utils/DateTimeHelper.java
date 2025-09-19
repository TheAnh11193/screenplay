package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateTimeHelper {

    public static LocalDate workingDaysBefore(int n) {
        LocalDate date = LocalDate.now();
        int daysFound = 0;

        while (daysFound < n) {
            date = date.minusDays(1); // step back 1 day
            DayOfWeek day = date.getDayOfWeek();

            if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
                daysFound++;
            }
        }
        return date;
    }
}
