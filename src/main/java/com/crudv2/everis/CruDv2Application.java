package com.crudv2.everis;

import javax.swing.JOptionPane;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CruDv2Application {

  public static void main(String[] args) {
    SpringApplication.run(CruDv2Application.class, args);
    System.setProperty("java.awt.headless", "false");
    JOptionPane.showMessageDialog(null, "¡Bienvenido al Gestor de Empleados!");
  }

}
