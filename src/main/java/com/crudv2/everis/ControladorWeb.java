package com.crudv2.everis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.crudv2.everis.modelo.Empleado;
import com.crudv2.everis.repositorio.RepositorioEmpleado;

@Controller
public class ControladorWeb {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  RepositorioEmpleado repositorioEmpleado;

  // Insertar empleado desde web
  @GetMapping("/insertar")
  public String insertarEmpleado(Model model) {
    model.addAttribute("empleado", new Empleado());
    return "agregar_empleado";
  }

  @PostMapping("/insertar")
  public String insertarEmpleadoPost(@ModelAttribute Empleado empleado, Model model, RedirectAttributes redirectAttrs) {
    mongoTemplate.insert(empleado, "empleados");
    redirectAttrs
        .addFlashAttribute("mensaje", "Empleado agregado")
        .addFlashAttribute("clase", "success");
    return "redirect:/gestion/empleados";
  }

  // @GetMapping("/pasarela")
  // public String pasarela(Model model) {
  // // System.setProperty("java.awt.headless", "false");
  // // auxiliar = Integer.parseInt(JOptionPane.showInputDialog("Introduce ID del empleado a eliminar:"));
  // return "pasarela_modificar";
  // }

  @GetMapping("/modificar/{id}")
  public String modificarEmpleado(@PathVariable String id, Model model) {
    model.addAttribute("empleado", repositorioEmpleado.findById(id));
    return "modificar_empleado";
  }

  @PostMapping("/modificar")
  public String modificarEmpleadoPost(@ModelAttribute Empleado empleado, RedirectAttributes redirectAttrs) {
    repositorioEmpleado.save(empleado);
    redirectAttrs
        .addFlashAttribute("mensaje", "Empleado modificado")
        .addFlashAttribute("clase", "success");
    return "redirect:/gestion/empleados";
  }

  // public void Modificar() {
  // System.out.print("Introduce DNI del Empleado a modificar:");
  // String dni = in.nextLine();
  // System.out.print("Campo a modificar:");
  // String campo = in.nextLine();
  // System.out.print("Introduce el nuevo valor:");
  // String modificacion = in.nextLine();
  // Query query = new Query();
  // query.addCriteria(Criteria.where("dni").is(dni));
  // Update update = Update.update(campo, modificacion);
  // mongoTemplate.findAndModify(query, update, Empleado.class, "empleados");
  // }

}
