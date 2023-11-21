package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tecnico")
public class Tecnico extends BaseEntity {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    /*@JoinTable(
            name = "tecnico_especialidad",
            joinColumns = @JoinColumn(name = "tecnico_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY)*/
    @ManyToMany(mappedBy = "tecnicosDeLaEspecialidad", fetch = FetchType.LAZY)
    private List<Especialidad> especialidades;

    @OneToMany(mappedBy = "tecnico", fetch = FetchType.LAZY)
    private List<Incidente> incidentes;

}
