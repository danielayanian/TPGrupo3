package ap.grupo3.tpgrupo3.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cliente")
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "servicio_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"), //nullable = false
            inverseJoinColumns = @JoinColumn(name = "servicio_id") //nullable = false
    )
    private Set<Servicio> serviciosContratados;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Incidente> incidentes;

}
