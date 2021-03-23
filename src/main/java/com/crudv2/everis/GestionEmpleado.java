package com.crudv2.everis;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crudv2.everis.modelo.Empleado;
import com.crudv2.everis.repositorio.RepositorioEmpleado;

@RestController
@RequestMapping("gestion")
public class GestionEmpleado {

  Scanner in = new Scanner(System.in);

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  RepositorioEmpleado repositorioEmpleado;

  // // Agregar nuevo empleado
  // @GetMapping("/nuevo")
  // public void CrearEmpleado() {
  //
  // Empleado _empleado = new Empleado();
  //
  // System.out.print("\nDNI de empleado: ");
  // _empleado.setDni(in.nextLine());
  // System.out.print("\nNombre de empleado: ");
  // _empleado.setNombre(in.nextLine());
  // System.out.print("\nApellidos de empleado: ");
  // _empleado.setApellidos(in.nextLine());
  // System.out.print("\nPosición de empleado: ");
  // _empleado.setPosicion(in.nextLine());
  //
  // /*
  // * _empleado.setDni("46791328D"); _empleado.setNombre("Ana"); _empleado.setApellidos("Molina Díaz");
  // * _empleado.setPosicion("Directora Marketing");
  // */
  //
  // mongoTemplate.insert(_empleado, "empleados");
  // }

  // Modifica empleado
  // @GetMapping("/modificar")
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

  // Eliminar empleado por DNI
  // @GetMapping("/eliminar")
  // public void Eliminar() {
  // System.out.print("Introduce dni a eliminar:");
  // String dni = in.nextLine();
  // Query query = new Query();
  // query.addCriteria(Criteria.where("dni").is(dni));
  // mongoTemplate.findAndRemove(query, Empleado.class, "empleados");
  // }

  // Buscar empleado específico por DNI
  @GetMapping("/empleado")
  public List<Empleado> getPost() {
    System.out.print("Nombre a buscar:");
    String empleado = in.nextLine();
    Query query = new Query();
    query.addCriteria(Criteria.where("dni").is(empleado));
    return mongoTemplate.find(query, Empleado.class);
  }

  // Mostrar todos los empleados
  @GetMapping("/empleados")
  public List<Empleado> EmpleadosTodos() {
    return mongoTemplate.findAll(Empleado.class);
  }

}
