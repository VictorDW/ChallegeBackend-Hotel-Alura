package DTO;


import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@Data
@ToString
public class GuestRequestDTO {
    private Long id;
    private String cedula;
    private String firsName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private NationalityRequestDTO nationality;

    public GuestRequestDTO(String cedula, String firsName, String lastName, LocalDate dateOfBirth, String phone, NationalityRequestDTO nationality) {
        this.cedula = cedula;
        this.firsName = firsName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.nationality = nationality;
    }

    public GuestRequestDTO(Long id, String cedula, String firsName, String lastName, LocalDate dateOfBirth, String phone, NationalityRequestDTO nationality) {
        this.id = id;
        this.cedula = cedula;
        this.firsName = firsName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.nationality = nationality;
    }
}
