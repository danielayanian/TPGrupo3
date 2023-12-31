package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "servicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio extends BaseEntity {

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "serviciosContratados")
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
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