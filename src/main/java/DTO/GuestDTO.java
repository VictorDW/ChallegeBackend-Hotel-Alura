package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@Getter
public class GuestDTO {
    private Long id;
    private String cedula;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private String nationality;
    private String status;
}
