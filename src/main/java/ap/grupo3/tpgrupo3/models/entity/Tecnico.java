package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tecnico")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
