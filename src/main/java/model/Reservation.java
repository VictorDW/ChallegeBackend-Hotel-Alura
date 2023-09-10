package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_reservation")
    private String reservationCod;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private  LocalDate checkOut;

    @Column(name = "cost_reservation")
    private BigDecimal costOfReservation;

    @Column(name = "method_payment")
    private String methodPayment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id")
    private Guest guest;

}
