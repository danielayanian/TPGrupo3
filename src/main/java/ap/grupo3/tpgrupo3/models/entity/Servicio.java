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
public class Servicio {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private NombreServicio nombre;

    @JoinTable(
            name = "servicio_cliente",
            joinColumns = @JoinColumn(name = "servicio_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="cliente_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL) //Ver si va o no lo de cascade
    private List<Cliente> clientes;

    @OneToMany(mappedBy="servicio")
    private List<IncidenteDetalle> detallesDelIncidente;

}