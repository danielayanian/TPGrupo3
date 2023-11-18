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
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String alias;
    private Date fechaEstimada;
    private Date fecheDesde;
    private Date fechaHasta;
    private int resuelto;//O boolean?
    private String consideraciones;
    private int cliente_id;
    private int tecnico_id;
    private List<IncidenteDetalle> detallesDelIncidente;
}
