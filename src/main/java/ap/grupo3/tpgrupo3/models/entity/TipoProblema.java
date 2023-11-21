package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tipo_problema")
public class TipoProblema extends BaseEntity {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tiempoEstimado")
    private int tiempoEstimado;//En dias

    /*@JoinTable(
            name = "problema_especialidad",
            joinColumns = @JoinColumn(name = "tipoProblema_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY)*/
    @ManyToMany(mappedBy = "problemasDeLaEspecialidad", fetch = FetchType.LAZY)
    private List<Especialidad> especialidadesQueResuelvenElProblema;

    @OneToMany(mappedBy = "tipoProblema", fetch = FetchType.LAZY)
    private List<IncidenteDetalle> detallesDelIncidente;

}
