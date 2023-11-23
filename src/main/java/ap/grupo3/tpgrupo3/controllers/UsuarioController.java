package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class UsuarioController {

    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping(value = "/verificarLogin")
    public String verificarLogin(@RequestParam("username") String username,
                                 @RequestParam("password") String password, Model model){
        model.addAttribute("nombre", username);
        //verificar login y si es valido devolver pagina correspondiente segun rol
        return "verificarLogin";

    }

}
