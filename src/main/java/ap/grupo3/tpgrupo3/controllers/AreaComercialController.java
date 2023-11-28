package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Servicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AreaComercialController extends BaseController {

    @GetMapping(value = "/areaComercialPage")
    public String areaComercialPage(Model model) {
        return "areaComercial/areaComercial";
    }

    @GetMapping(value = "/altaClientePage")
    public String altaClientePage(Model model) {

        List<Servicio> servicios = servicioService.buscarTodosLosServicios();

        model.addAttribute("servicios", servicios);

        return "areaComercial/altaCliente";

    }

    @GetMapping(value = "/bajaClientePage")
    public String bajaClientePage(Model model) {
        return "areaComercial/bajaCliente";
    }

    @GetMapping(value = "/updateClientePage")
    public String updateClientePage(Model model) {

        List<Servicio> servicios = servicioService.buscarTodosLosServicios();

        model.addAttribute("servicios", servicios);

        return "areaComercial/updateCliente";

    }

    @PostMapping(value = "/altaCliente")
    public String altaCliente(@RequestParam(name = "nombre") String nombre,
                              @RequestParam(name = "razonSocial") String razonSocial,
                              @RequestParam(name = "cuit") String cuit,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "telefono") String telefono,
                              @RequestParam(name = "idChecked", required = false) List<String> serviciosMarcados,
                              Model model) {

        List<Servicio> serviciosContratados = new ArrayList<>();

        if (serviciosMarcados != null) {

            Long idLong = Long.parseLong("0");
            for (String nombreServicio : serviciosMarcados) {
                Servicio s = new Servicio();
                if (nombreServicio.equals("SAP")) {
                    idLong = Long.parseLong("1");
                }
                if (nombreServicio.equals("TANGO")) {
                    idLong = Long.parseLong("2");
                }
                if (nombreServicio.equals("WINDOWS")) {
                    idLong = Long.parseLong("3");
                }
                if (nombreServicio.equals("MAC")) {
                    idLong = Long.parseLong("4");
                }
                if (nombreServicio.equals("LINUX")) {
                    idLong = Long.parseLong("5");
                }
                s.setId(idLong);
                serviciosContratados.add(s);
            }

        }

        Cliente cliente = new Cliente(nombre, razonSocial, cuit, email, telefono, serviciosContratados, null);

        clienteService.altaCliente(cliente);

        model.addAttribute("mensaje", "Cliente ingresado con éxito");

        return "areaComercial/mensajeCliente";

    }

    @PostMapping(value = "/bajaCliente")
    public String bajaCliente(@RequestParam("id") String id, Model model) {

        Long idLong = Long.parseLong(id);

        Cliente cliente = clienteService.buscarCliente(idLong);
        if (cliente == null) {

            model.addAttribute("mensaje", "ERROR: Cliente no existe");

            return "areaComercial/mensajeCliente";

        } else {

            clienteService.bajaCliente(cliente);

            model.addAttribute("mensaje", "Cliente eliminado con éxito");

            return "areaComercial/mensajeCliente";
        }

    }

    @PostMapping(value = "/updateCliente")
    public String updateCliente(@RequestParam(name = "id") String id,
                                @RequestParam(name = "nombre") String nombre,
                                @RequestParam(name = "razonSocial") String razonSocial,
                                @RequestParam(name = "cuit") String cuit,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "telefono") String telefono,
                                @RequestParam(name = "idChecked", required = false) List<String> serviciosMarcados,
                                Model model) {

        List<Servicio> serviciosContratados = new ArrayList<>();

        if (serviciosMarcados != null) {

            Long idLong = Long.parseLong("0");
            for (String nombreServicio : serviciosMarcados) {
                Servicio s = new Servicio();
                if (nombreServicio.equals("SAP")) {
                    idLong = Long.parseLong("1");
                }
                if (nombreServicio.equals("TANGO")) {
                    idLong = Long.parseLong("2");
                }
                if (nombreServicio.equals("WINDOWS")) {
                    idLong = Long.parseLong("3");
                }
                if (nombreServicio.equals("MAC")) {
                    idLong = Long.parseLong("4");
                }
                if (nombreServicio.equals("LINUX")) {
                    idLong = Long.parseLong("5");
                }
                s.setId(idLong);
                serviciosContratados.add(s);
            }

        }

        Long idLong = Long.parseLong(id);

        Cliente cliente = clienteService.buscarCliente(idLong);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setRazonSocial(razonSocial);
            cliente.setCUIT(cuit);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setServiciosContratados(serviciosContratados);

            clienteService.updateCliente(cliente);

            model.addAttribute("mensaje", "Cliente actualizado con éxito");

            return "areaComercial/mensajeCliente";
        }

        model.addAttribute("mensaje", "ERROR: Cliente no existe");

        return "areaComercial/mensajeCliente";

    }

}
