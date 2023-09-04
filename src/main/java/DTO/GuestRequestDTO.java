package DTO;


import lombok.Getter;
import modelo.Nationality;

import java.time.LocalDate;
@Getter

public class GuestRequestDTO {
    private Long id;
    private String cedula;
    private String firsName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private Nationality nationality;

    public GuestRequestDTO(String cedula, String firsName, String lastName, LocalDate dateOfBirth, String phone, Nationality nationality) {
        this.cedula = cedula;
        this.firsName = firsName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.nationality = nationality;
    }

    public GuestRequestDTO(Long id, String cedula, String firsName, String lastName, LocalDate dateOfBirth, String phone) {
        this.id = id;
        this.cedula = cedula;
        this.firsName = firsName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
    }
}
