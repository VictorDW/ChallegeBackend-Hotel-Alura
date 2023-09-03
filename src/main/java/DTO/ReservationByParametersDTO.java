package DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class ReservationByParametersDTO {
    private String reservationCod;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
