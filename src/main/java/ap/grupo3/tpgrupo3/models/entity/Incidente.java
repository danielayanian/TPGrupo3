package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "incidente")
public class Incidente extends BaseEntity {

    @Column(name = "alias")
    private String alias;

    @Column(name = "fechaEstimada")
    private Date fechaEstimada;

    @Column(name = "fechaDesde")
    private Date fecheDesde;

    @Column(name = "fechaHasta")
    private Date fechaHasta;

    @Column(name = "resuelto")
    private int resuelto;

    @Column(name = "consideraciones")
    private String consideraciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @OneToMany(mappedBy = "incidente", fetch = FetchType.LAZY)
    private List<IncidenteDetalle> detallesDelIncidente;

}
