package DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
public class ReservationRequestDTO {

    private Long id;
    @Setter
    private String reservationCod;
    @Setter
    private LocalDate checkIn;
    @Setter
    private LocalDate checkOut;
    private BigDecimal cost;
    @Setter
    private String methodPayment;

    public ReservationRequestDTO(Long id,
                                                 LocalDate checkIn,
                                                 LocalDate checkOut,
                                                 String methodPayment) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.methodPayment = methodPayment;
    }

    public ReservationRequestDTO(LocalDate checkIn, LocalDate checkOut, BigDecimal cost) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cost = cost;
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
