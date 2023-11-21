package ap.grupo3.tpgrupo3.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "servicio_cliente",
            joinColumns = @JoinColumn(name = "cliente_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "servicio_id", nullable = false)
    )
    @OrderColumn(name = "id")
    private List<Servicio> serviciosContratados;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Incidente> incidentes;

}
