package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    private String reservationCod;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal cost;
    private String methodPayment;
    private String status;
    private String guestDni;
}
