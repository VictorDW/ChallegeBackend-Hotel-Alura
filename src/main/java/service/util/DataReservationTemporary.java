package service.util;

import DTO.ReservationRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public final class DataReservationTemporary {
    public static ReservationRequestDTO CreateReservationRequestDTO() {


        return new ReservationRequestDTO(ConfigureDates.getCheckIn(),
                                                            ConfigureDates.getCheckOut(),
                                                            calculateReservationCost());
    }

    public static BigDecimal calculateReservationCost() {

        Integer periodOfDays =  ConfigureDates.getDaysReservation();
        int costDay = 43300;

        return new BigDecimal(
                periodOfDays != 0 ?
                        (periodOfDays) * costDay :
                         costDay
        );
    }

    public static String createReservationCod(){

        int[] numbers = {0,1,2,3,4,5,6,7,8,9};

        StringBuilder sequence = new StringBuilder("HAC");

        Random random = new Random();

        for (int i = 0; i < 3; i++) {
                int randomNumber = numbers[random.nextInt(numbers.length)];
                sequence.append(randomNumber);
        }

        return sequence.toString();
    }


}
