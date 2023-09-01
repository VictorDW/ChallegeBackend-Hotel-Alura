package service.util;

import DTO.ReservationRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public final class DataReservationTemporary {
    public static ReservationRequestDTO dataReserve(LocalDate dateOfEntry,
                                                    LocalDate departureDate,
                                                    String methodPayment) {

        Period period = Period.between(dateOfEntry,departureDate);
        BigDecimal cost = new BigDecimal(period.getDays() * 43300);

        return new ReservationRequestDTO(createReservationCod(),
                                                    dateOfEntry,
                                                    departureDate,
                                                    cost,
                                                    methodPayment);
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
