/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.mx.dev.main;

import com.dish.mx.dev.casos.CasosMenuAsignacionDesarrolloTarea;
import com.dish.mx.dev.casos.CasosMenuDesarrollador;
import com.dish.mx.dev.casos.CasosMenuProyecto;
import com.dish.mx.dev.casos.CasosMenuTarea;
import com.dish.mx.dev.crudintegrador.AppConfig;
import com.dish.mx.dev.daoimpl.AsignacionDesarrolloTareaDAOImpl;
import com.dish.mx.dev.daoimpl.DesarrolladorDAOImpl;
import com.dish.mx.dev.daoimpl.ProyectoDAOImpl;
import com.dish.mx.dev.daoimpl.TareaDAOImpl;
import com.dish.mx.dev.dto.AsignacionDesarrolloTareaDTO;
import com.dish.mx.dev.dto.DesarrolladorDTO;
import com.dish.mx.dev.dto.ProyectoDTO;
import com.dish.mx.dev.dto.TareaDTO;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Clase principal que muestra al usuario las opciones que puede
 * elegir para realizar operaciones en la base de datos.
 *
 * @version 0.0.1
 *
 * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx%gt;
 *
 * @since 0.0.1
 *
 */
public class MainApp {

    /**
     * Este es el método principal con el que corre la aplicación para el manejo
     * de la base de datos.
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     * 
     * @param args
     *
     * @since 0.0.1
     */
    public static void main(String args[]) {
        AsignacionDesarrolloTareaDAOImpl asigDAO;
        DesarrolladorDAOImpl desaDAO;
        TareaDAOImpl tareaDAO;
        ProyectoDAOImpl proyDAO;

        CasosMenuAsignacionDesarrolloTarea casosAsignacion;
        CasosMenuDesarrollador casosDesarrollador;
        CasosMenuTarea casosTarea;
        CasosMenuProyecto casosProyecto;

        List<AsignacionDesarrolloTareaDTO> imprimir1;
        List<TareaDTO> imprimir2;
        List<DesarrolladorDTO> imprimir3;
        List<ProyectoDTO> imprimir4;

        //Instanciamos el contexto de spring
        ApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfig.class);

        asigDAO = ctx.getBean("asignacionDesarrolloTareaDAOImpl", AsignacionDesarrolloTareaDAOImpl.class);
        desaDAO = ctx.getBean("desarrolladorDAOImpl", DesarrolladorDAOImpl.class);
        tareaDAO = ctx.getBean("tareaDAOImpl", TareaDAOImpl.class);
        proyDAO = ctx.getBean("proyectoDAOImpl", ProyectoDAOImpl.class);

        casosAsignacion = ctx.getBean(CasosMenuAsignacionDesarrolloTarea.class);
        casosDesarrollador = ctx.getBean(CasosMenuDesarrollador.class);
        casosTarea = ctx.getBean(CasosMenuTarea.class);
        casosProyecto = ctx.getBean(CasosMenuProyecto.class);
        
        Scanner leer = new Scanner(System.in);

        int a = 1;

        do {
            System.out.println("¿Sobre que tabla quieres operar en la base de datos?");
            System.out.println("1.- Asignacion_Desarrollo_Tarea");
            System.out.println("2.- Tarea");
            System.out.println("3.- Desarrollador");
            System.out.println("4.- Proyecto");
            System.out.println("5.- Salir");

            int opcion = leer.nextInt();

            switch (opcion) {

                case 1:
                    int b = 1;
                    
                    Scanner leer1 = new Scanner(System.in);

                    do {

                        System.out.println("¿Que quieres realizar en la tabla de la base de datos?");
                        System.out.println("1.- Consultar a la tabla");
                        System.out.println("2.- Insertar una asignación a la tabla");
                        System.out.println("3.- Actualizar los datos de una asignacion");
                        System.out.println("4.- Eliminar una asignación de la tabla");
                        System.out.println("5.- Salir");

                        int opcion1 = leer1.nextInt();

                        switch (opcion1) {
                            case 1:
                                casosAsignacion.consultar();
                                b = 1;
                                break;
                            case 2:
                                casosAsignacion.insertar();
                                b = 1;
                                break;
                            case 3:
                                casosAsignacion.actualizar();
                                imprimir1 = asigDAO.encontrarTodos();
                                System.out.println(imprimir1);
                                b = 1;
                                break;
                            case 4:
                                casosAsignacion.eliminar();
                                b = 1;
                                break;
                            case 5:
                                System.out.println("Saliendo del menú... \n");
                                b = 2;
                                break;
                            default:
                                System.out.println("La opción elegida es incorrecta");
                                b = 1;
                                break;
                        }
                    } while (b == 1);
                    a = 1;
                    break;

                case 2:
                    int c = 1;

                    Scanner leer2 = new Scanner(System.in);
                    
                    do {

                        System.out.println("¿Que quieres realizar en la tabla de la base de datos?");
                        System.out.println("1.- Consultar a la tabla");
                        System.out.println("2.- Insertar una tarea a la tabla");
                        System.out.println("3.- Actualizar los datos de una tarea");
                        System.out.println("4.- Eliminar una tarea de la tabla");
                        System.out.println("5.- Salir");

                        int opcion2 = leer2.nextInt();

                        switch (opcion2) {
                            case 1:
                                casosTarea.consultar();
                                c = 1;
                                break;
                            case 2:
                                casosTarea.insertar();
                                c = 1;
                                break;
                            case 3:
                                casosTarea.actualizar();
                                imprimir2 = tareaDAO.encontrarTodos();
                                System.out.println(imprimir2);
                                c = 1;
                                break;
                            case 4:
                                casosTarea.eliminar();
                                c = 1;
                                break;
                            case 5:
                                System.out.println("Saliendo del menú... \n");
                                c = 2;
                                break;
                            default:
                                System.out.println("La opción elegida es incorrecta");
                                c = 1;
                                break;
                        }
                    } while (c == 1);
                    a = 1;
                    break;

                case 3:
                    int d = 1;
                    
                    Scanner leer3 = new Scanner(System.in);

                    do {

                        System.out.println("¿Que quieres realizar en la tabla de la base de datos?");
                        System.out.println("1.- Consultar a la tabla");
                        System.out.println("2.- Insertar un desarrollador a la tabla");
                        System.out.println("3.- Actualizar los datos de un desarrollador");
                        System.out.println("4.- Eliminar un desarrollador de la tabla");
                        System.out.println("5.- Salir");

                        int opcion3 = leer3.nextInt();

                        switch (opcion3) {
                            case 1:
                                casosDesarrollador.consultar();
                                d = 1;
                                break;
                            case 2:
                                casosDesarrollador.insertar();
                                d = 1;
                                break;
                            case 3:
                                casosDesarrollador.actualizar();
                                imprimir3 = desaDAO.encontrarTodos();
                                System.out.println(imprimir3);
                                d = 1;
                                break;
                            case 4:
                                casosDesarrollador.eliminar();
                                d = 1;
                                break;
                            case 5:
                                System.out.println("Saliendo del menú... \n");
                                d = 2;
                                break;
                            default:
                                System.out.println("La opción elegida es incorrecta");
                                d = 1;
                                break;
                        }
                    } while (d == 1);
                    a = 1;
                    break;

                case 4:
                    int e = 1;
                    
                    Scanner leer4 = new Scanner(System.in);

                    do {

                        System.out.println("¿Que quieres realizar en la tabla de la base de datos?");
                        System.out.println("1.- Consultar a la tabla");
                        System.out.println("2.- Insertar un proyecto a la tabla");
                        System.out.println("3.- Actualizar los datos de un proyecto");
                        System.out.println("4.- Eliminar un proyecto de la tabla");
                        System.out.println("5.- Salir");

                        int opcion4 = leer4.nextInt();

                        switch (opcion4) {
                            case 1:
                                casosProyecto.consultar();
                                d = 1;
                                break;
                            case 2:
                                casosProyecto.insertar();
                                d = 1;
                                break;
                            case 3:
                                casosProyecto.actualizar();
                                imprimir4 = proyDAO.encontrarTodos();
                                System.out.println(imprimir4);
                                d = 1;
                                break;
                            case 4:
                                casosProyecto.eliminar();
                                d = 1;
                                break;
                            case 5:
                                System.out.println("Saliendo del menú... \n");
                                d = 2;
                                break;
                            default:
                                System.out.println("La opción elegida es incorrecta");
                                d = 1;
                                break;
                        }
                    } while (d == 1);
                    a = 1;
                    break;

                case 5:
                    System.out.println("Saliendo del programa... \n");
                    a = 2;
                    break;

                default:
                    System.out.println("La opción elegida es incorrecta");
                    a = 1;
                    break;
            }

        } while (a == 1);

    }
}
