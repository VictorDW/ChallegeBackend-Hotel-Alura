package modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nationality")
public class Nationality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "gentilico_man")
    private String gentilicioMan;
    @Column(name = "gentilico_woman")
    private String gentilicioWoman;
}
