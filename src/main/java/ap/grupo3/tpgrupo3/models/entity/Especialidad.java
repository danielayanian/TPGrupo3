package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "especialidad")
public class Especialidad extends BaseEntity {

    @Column(name = "nombre")
    private String nombre;

    @JoinTable(
            name = "tecnico_especialidad",
            joinColumns = @JoinColumn(name = "especialidad_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="tecnico_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tecnico> tecnicosDeLaEspecialidad;

    @JoinTable(
            name = "problema_especialidad",
            joinColumns = @JoinColumn(name = "especialidad_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tipoProblema_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TipoProblema> problemasDeLaEspecialidad;

}
