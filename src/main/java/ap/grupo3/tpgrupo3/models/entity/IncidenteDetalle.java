package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteDetalle {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="servicio_id")
    private int servicio_id;

    @Column(name="incidente_id")
    private int incidente_id;

    @Column(name="tipoProblema_id")
    private int tipoProblema_id;

}
