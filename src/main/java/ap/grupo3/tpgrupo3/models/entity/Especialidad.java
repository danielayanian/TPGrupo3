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
public class Especialidad {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private String nombre;

    @JoinTable(
            name = "tecnico_especialidad",
            joinColumns = @JoinColumn(name = "especialidad_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="tecnico_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tecnico> tecnicosDeLaEspecialidad;

    @JoinTable(
            name = "tecnico_especialidad",
            joinColumns = @JoinColumn(name = "especialidad_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="tipoProblema_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TipoProblema> problemasDeLaEspecialidad;
}
