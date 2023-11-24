package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.services.RolService;
import ap.grupo3.tpgrupo3.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TPController {

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public String index(Model model){
        return "varias/index";
    }

}
