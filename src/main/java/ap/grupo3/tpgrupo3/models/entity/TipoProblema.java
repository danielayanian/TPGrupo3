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
public class TipoProblema {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="tiempoEstimado")
    private int tiempoEstimado;//En dias

    @JoinTable(
            name = "problema_especialidad",
            joinColumns = @JoinColumn(name = "tipoProblema_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="especialidad_id", nullable = false)
    )
    //@ManyToMany(cascade = CascadeType.ALL) //Ver si va o no lo de cascade
    @ManyToMany()
    private List<Especialidad> especialidadesQueResuelvenElProblema;

    @OneToMany(mappedBy="tipoProblema")
    private List<IncidenteDetalle> detallesDelIncidente;

}
