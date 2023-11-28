package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "incidente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidente extends BaseEntity {

    @Column(name = "alias")
    private String alias;

    @Column(name = "fechaEstimada")
    private Date fechaEstimada;

    @Column(name = "fechaDesde")
    private Date fechaDesde;

    @Column(name = "fechaHasta")
    private Date fechaHasta;

    @Column(name = "resuelto")
    private int resuelto;

    @Column(name = "consideraciones")
    private String consideraciones;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @OneToMany(mappedBy = "incidente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
