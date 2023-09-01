package modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="dni", unique = true)
    private String cedula;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_birth")
    private LocalDate dateOfBirth;

    private String phone;

    @ManyToOne()
    private Nationality nationality;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

}
