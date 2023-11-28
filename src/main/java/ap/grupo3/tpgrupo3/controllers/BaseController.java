package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    protected RolService rolService;

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected ServicioService servicioService;

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected TecnicoService tecnicoService;

    @Autowired
    protected TipoProblemaService tipoProblemaService;

    @Autowired
    protected EspecialidadService especialidadService;

    @Autowired
    protected IncidenteService incidenteService;

}
