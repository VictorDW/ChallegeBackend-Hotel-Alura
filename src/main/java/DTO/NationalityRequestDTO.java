package DTO;


import lombok.Getter;

@Getter

public class NationalityRequestDTO {

    private Long id;
    private final String gentilicioMan;
    private final String gentilicioWoman;

    public NationalityRequestDTO(Long id, String gentilicioMan, String gentilicioWoman) {
        this.id = id;
        this.gentilicioMan = gentilicioMan;
        this.gentilicioWoman = gentilicioWoman;
    }

    public NationalityRequestDTO(String gentilicioMan, String gentilicioWoman) {
        this.gentilicioMan = gentilicioMan;
        this.gentilicioWoman = gentilicioWoman;
    }

    @Override
    public String toString() {
        return gentilicioMan +" : "+ gentilicioWoman;
    }
}
