package ap.grupo3.tpgrupo3.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "especialidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especialidad extends BaseEntity {

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "especialidades")
    private List<Tecnico> tecnicosDeLaEspecialidad;

    @ManyToMany(mappedBy = "especialidadesQueResuelvenElProblema", fetch = FetchType.EAGER)
    private List<TipoProblema> problemasDeLaEspecialidad;

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
