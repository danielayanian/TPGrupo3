package ap.grupo3.tpgrupo3.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends BaseEntity {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "razonSocial")
    private String razonSocial;

    @Column(name = "CUIT")
    private String CUIT;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "servicio_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"), //nullable = false
            inverseJoinColumns = @JoinColumn(name = "servicio_id") //nullable = false
    )
    private List<Servicio> serviciosContratados;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
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
