package service.util;

import DTO.ReservationRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public final class DataReservationTemporary {
    public static ReservationRequestDTO dataOfReserve(LocalDate dateOfEntry, LocalDate departureDate) {


        return new ReservationRequestDTO(dateOfEntry,
                                                            departureDate,
                                                            calculateReservationCost(dateOfEntry, departureDate));
    }

    public static BigDecimal calculateReservationCost(LocalDate dateOfEntry, LocalDate departureDate) {

        Period period =  Period.between(dateOfEntry,departureDate);
        int costDay = 43300;

        return new BigDecimal(
                period.getDays() != 0 ?
                        (period.getDays()+1) * costDay :
                        costDay
        );
    }

    public static String createReservationCod(){

        char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','N','M','O','P','Q','R','S','T','V','W','K','Y','Z'};
        int[] numbers = {0,1,2,3,4,5,6,7,8,9};

        StringBuilder sequence = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int choice = random.nextInt(2); // Choose between letters and numbers
            if (choice == 0) {
                char randomLetter = letters[random.nextInt(letters.length)];
                sequence.append(randomLetter);
            } else {
                int randomNumber = numbers[random.nextInt(numbers.length)];
                sequence.append(randomNumber);
            }
        }

        return sequence.toString();
    }


}
