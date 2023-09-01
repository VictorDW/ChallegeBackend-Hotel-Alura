package DTO;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
public class ReservationRequestDTO {

    private Long id;
    private String reservationCod;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private BigDecimal cost;
    private String methodPayment;

    public ReservationRequestDTO(Long id,
                                                 LocalDate checkIn,
                                                 LocalDate checkOut) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public ReservationRequestDTO(String reservationCod,
                                                 LocalDate checkIn,
                                                 LocalDate checkOut,
                                                 BigDecimal cost,
                                                 String methodPayment) {

        this.reservationCod = reservationCod;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cost = cost;
        this.methodPayment = methodPayment;
    }
}
