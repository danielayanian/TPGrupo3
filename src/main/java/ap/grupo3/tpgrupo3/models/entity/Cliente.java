package ap.grupo3.tpgrupo3.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Cliente {
    private int id;
    private String nombre;
    private String razonSocial;
    private String CUIT;
    private String email;
    private String telefono;
    private List<Servicio> serviciosContratados;
    private List<Incidente> incidentes;
}
