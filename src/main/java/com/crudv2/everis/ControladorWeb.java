package com.crudv2.everis;

import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.crudv2.everis.modelo.Empleado;
import com.crudv2.everis.repositorio.RepositorioEmpleado;

@Controller
public class ControladorWeb {

  String auxDNI, campo;

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
  // Fin Insertar empleado desde web

  // Paso previo a modificar
  @GetMapping("pasarelaMod")
  public RedirectView pasarelaMod() {
    System.setProperty("java.awt.headless", "false");
    String aux = JOptionPane.showInputDialog("Introduce ID de empleado a modificar:");
    campo = JOptionPane.showInputDialog("Introduce Campo de empleado a modificar:");
    return new RedirectView("/modificar/" + aux);
  }

  // Modificar por ID
  @GetMapping("/modificar/{id}")
  public String modificarEmpleadoGet(@PathVariable String id, Model model) {
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
  // Fin modificar por ID

  // Paso previo a eliminar por DNI
  @GetMapping("pasarelaDel")
  public RedirectView pasarelaDel() {
    System.setProperty("java.awt.headless", "false");
    auxDNI = JOptionPane.showInputDialog("Introduce dni de empleado a eliminar:");
    return new RedirectView("/eliminar");
  }

  // Eliminar empleado por DNI
  @GetMapping("/eliminar")
  public String Eliminar() {
    Query query = new Query();
    query.addCriteria(Criteria.where("dni").is(auxDNI));
    mongoTemplate.findAndRemove(query, Empleado.class, "empleados");
    return "redirect:/inicio";
  }
  // Fin Eliminar por DNI

  // Eliminar todos los empleados
  @GetMapping("/eliminarTodos")
  public String EliminarTodos() {
    repositorioEmpleado.deleteAll();
    return "redirect:/gestion/empleados";
  }
  // Fin Eliminar todos los empleados

  // Retorno a página principal
  @GetMapping("/inicio")
  public RedirectView retorno() {
    return new RedirectView("http://localhost:8080");
  }
}
