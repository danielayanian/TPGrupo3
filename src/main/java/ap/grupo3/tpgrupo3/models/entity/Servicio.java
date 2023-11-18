package ap.grupo3.tpgrupo3.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Servicio {
    private int id;
    private NombreServicio nombre;
}