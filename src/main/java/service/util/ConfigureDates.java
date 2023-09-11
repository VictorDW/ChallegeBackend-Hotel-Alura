package service.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public final class ConfigureDates {

    private static LocalDate checkIn;
    private static LocalDate checkOut;
    private static LocalDate dateOfBirth;

    public static void mapperDataToLocalDate(Date checkIn, Date checkOut) throws NullPointerException {

        Instant instantCheckIn = checkIn.toInstant();
        Instant instantCheckOut= checkOut.toInstant();

        ConfigureDates.checkIn  = instantCheckIn.atZone(ZoneId.systemDefault()).toLocalDate();
        ConfigureDates.checkOut= instantCheckOut.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static void mapperDataToLocalDate(Date dateOfBirth) throws NullPointerException {

        Instant instantDateBirth = dateOfBirth.toInstant();
        ConfigureDates.dateOfBirth= instantDateBirth.atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public static Date mapperLocalDateToData(LocalDate dateOfBirth) {
       return Date.from(dateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static  void resetDates() {
        checkIn = null;
        checkOut = null;
    }

    public static boolean validateDateOrder() {

        /*Se valida de esta manera para que de verificar que la fecha sea el mismo o antes
        * true: si es antes o el mismo día
        * false: si es mayor al día de salida
        */
         return !checkIn.isAfter(checkOut);
    }

    public static  boolean validateDateCheckIn(LocalDate dateComparation) {

        /*Se valida de esta manera para que de verificar que la fecha sea el mismo o despues
         * true: si es después o el mismo día
         * false: si es menor a la ficha actual
         */
        return !checkIn.isBefore(dateComparation);
    }

    public static boolean isUnderAge() throws NullPointerException {

        LocalDate fechaActual = LocalDate.now();
        Period period = Period.between(dateOfBirth, fechaActual);
        return period.getYears() < 18;
    }

    public static LocalDate getCheckIn() {
        return checkIn;
    }

    public static LocalDate getCheckOut() {
        return checkOut;
    }

    public static LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
