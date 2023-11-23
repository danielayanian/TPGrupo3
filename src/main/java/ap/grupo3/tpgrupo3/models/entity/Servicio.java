package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "servicio")
public class Servicio extends BaseEntity {

    @Column(name = "nombre")
    private NombreServicio nombre;

    /*@JoinTable(
            name = "servicio_cliente",
            joinColumns = @JoinColumn(name = "servicio_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "cliente_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY)*/
    @ManyToMany(mappedBy = "serviciosContratados")
    private Set<Cliente> clientes;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
    private List<IncidenteDetalle> detallesDelIncidente;

}