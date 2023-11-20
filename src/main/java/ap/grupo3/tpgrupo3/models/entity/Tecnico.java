package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="email")
    private String email;

    @Column(name="telefono")
    private String telefono;

    @JoinTable(
            name = "tecnico_especialidad",
            joinColumns = @JoinColumn(name = "tecnico_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="especialidad_id", nullable = false)
    )
    //@ManyToMany(cascade = CascadeType.ALL) //Ver si va o no lo de cascade
    @ManyToMany()
    private List<Especialidad> especialidades;

    @OneToMany(mappedBy="tecnico")
    private List<Incidente> incidentes;

}
