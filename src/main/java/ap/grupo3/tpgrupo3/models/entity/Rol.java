package ap.grupo3.tpgrupo3.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rol")
public class Rol extends BaseEntity {

    @Column(name = "rol")
    private String rol;

    @OneToMany(mappedBy="rol", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

}
