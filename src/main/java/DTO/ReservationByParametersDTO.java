package DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReservationByParametersDTO {
    private String reservationCod;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
