package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="alias")
    private String alias;

    @Column(name="fechaEstimada")
    private Date fechaEstimada;

    @Column(name="fechaDesde")
    private Date fecheDesde;

    @Column(name="fechaHasta")
    private Date fechaHasta;

    @Column(name="resuelto")
    private int resuelto;//O boolean?

    @Column(name="consideraciones")
    private String consideraciones;

    //@Column(name="cliente_id")
    //private int cliente_id;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    //@Column(name="tecnico_id")
    //private int tecnico_id;

    @ManyToOne
    @JoinColumn(name="tecnico_id")
    private Tecnico tecnico;

    @OneToMany(mappedBy="incidente")
    private List<IncidenteDetalle> detallesDelIncidente;

}
