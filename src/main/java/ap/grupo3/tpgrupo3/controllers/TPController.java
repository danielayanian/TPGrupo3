package ap.grupo3.tpgrupo3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class TPController {

    @GetMapping()
    public String index(Model model){
        model.addAttribute("tstamp", LocalDateTime.now());
        return "index";
    }

}
