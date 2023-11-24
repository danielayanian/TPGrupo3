package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tipo_problema")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity that)) return false;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}
