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
public class Cliente {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="razonSocial")
    private String razonSocial;

    @Column(name="CUIT")
    private String CUIT;

    @Column(name="email")
    private String email;

    @Column(name="telefono")
    private String telefono;

    @JoinTable(
            name = "servicio_cliente",
            joinColumns = @JoinColumn(name = "cliente_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="servicio_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Servicio> serviciosContratados;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Incidente> incidentes;

}
