package com.dish.mx.dev.casos;

import com.dish.mx.dev.daoimpl.AsignacionDesarrolloTareaDAOImpl;
import com.dish.mx.dev.dto.AsignacionDesarrolloTareaDTO;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author gerardo.martinez
 */
@Component
public class CasosMenuAsignacionDesarrolloTarea {

    private AsignacionDesarrolloTareaDTO asignacion;
    private List<AsignacionDesarrolloTareaDTO> imprimir;
    private AsignacionDesarrolloTareaDTO imprimir2;

    @Autowired
    @Qualifier("asignacionDesarrolloTareaDAOImpl")
    private AsignacionDesarrolloTareaDAOImpl asignaDAO;

    private Scanner leer = new Scanner(System.in);

    public CasosMenuAsignacionDesarrolloTarea() {
    }

    public void consultar() {
        //Le imprimimos al usuario las opciones
        System.out.println("Retornar todos los registros de la tabla --> 1");
        System.out.println("Buscar por número de asignación --> 2");
        //Leemos la opción que introdujó el usuario en consola
        int eleccion = getLeer().nextInt();
        if (eleccion == 1) {
            setImprimir(getAsignaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingresa el número de asignación: ");
            int id = getLeer().nextInt();
            setImprimir2(getAsignaDAO().encontrarPorId(id));
            System.out.println(getImprimir2());
        }
    }

    public void insertar() {
        AsignacionDesarrolloTareaDTO asignacionInsertar;
        asignacionInsertar = new AsignacionDesarrolloTareaDTO();

        try {

            System.out.print("Ingresa el número de asignación: ");
            int asigna = getLeer().nextInt();
            System.out.print("Ingresa el número de desarrollador: ");
            int desarrollador = getLeer().nextInt();
            System.out.print("Ingresa el número de tarea: ");
            int tarea = getLeer().nextInt();
            asignacionInsertar.setAsignacionId(asigna);
            asignacionInsertar.setDesarrolladorId(desarrollador);
            asignacionInsertar.setTareaId(tarea);
            getAsignaDAO().insertarAsignacionDesarrolloTarea(asignacionInsertar);
            System.out.println("Empleado registrado!");
            setImprimir(getAsignaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void actualizar() {
        int numAsigna;
        int resp = 0;

        try {

            System.out.print("¿Cuál es el número de asignación?: ");
            numAsigna = getLeer().nextInt();
            setAsignacion(getAsignaDAO().encontrarPorId(numAsigna));

            System.out.print("\nNúmero actual de desarrollador: " + getAsignacion().getDesarrolladorId());
            System.out.print("\nNúmero nuevo de desarrollador (Digite el mismo número si no quiere cambiarlo): ");
            getAsignacion().setDesarrolladorId(getLeer().nextInt());

            System.out.print("\nNúmero actual de tarea: " + getAsignacion().getTareaId());
            System.out.print("\nNuevo número de tarea (Digite el mismo número si no quiere cambiarlo): ");
            getAsignacion().setTareaId(getLeer().nextInt());

            resp = getAsignaDAO().actualizarAsignacionDesarrolloTarea(getAsignacion());
            System.out.println("Asignación actualizada!\n Se actualizaron " + resp + " líneas");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminar() {
        System.out.println("Eliminar todos los registros de la tabla --> 1");
        System.out.println("Eliminar un registro de la tabla por el número de asignación --> 2");
        //Se guarda la opción elegida por el usuario.
        int elim = getLeer().nextInt();
        if (elim == 1) {
            getAsignaDAO().eliminarTodos();
            System.out.println("Todos los registros de asignación han sido eliminados!");
            setImprimir(getAsignaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingrese el número de asignación del registro a eliminar: ");
            //Lee el ID por consola para buscar el registro en la base de datos
            //y lo elimine
            int emp = getLeer().nextInt();
            getAsignaDAO().eliminarPorID(emp);
            System.out.println("Asignación eliminada!");
            setImprimir(getAsignaDAO().encontrarTodos());
            System.out.println(getImprimir());
        }
    }

    /**
     * @return the asignacion
     */
    public AsignacionDesarrolloTareaDTO getAsignacion() {
        return asignacion;
    }

    /**
     * @return the imprimir
     */
    public List<AsignacionDesarrolloTareaDTO> getImprimir() {
        return imprimir;
    }

    /**
     * @return the imprimir2
     */
    public AsignacionDesarrolloTareaDTO getImprimir2() {
        return imprimir2;
    }

    /**
     * @return the asignaDAO
     */
    public AsignacionDesarrolloTareaDAOImpl getAsignaDAO() {
        return asignaDAO;
    }

    /**
     * @return the leer
     */
    public Scanner getLeer() {
        return leer;
    }

    /**
     * @param asignacion the asignacion to set
     */
    public void setAsignacion(AsignacionDesarrolloTareaDTO asignacion) {
        this.asignacion = asignacion;
    }

    /**
     * @param imprimir the imprimir to set
     */
    public void setImprimir(List<AsignacionDesarrolloTareaDTO> imprimir) {
        this.imprimir = imprimir;
    }

    /**
     * @param imprimir2 the imprimir2 to set
     */
    public void setImprimir2(AsignacionDesarrolloTareaDTO imprimir2) {
        this.imprimir2 = imprimir2;
    }

    /**
     * @param asignaDAO the asignaDAO to set
     */
    public void setAsignaDAO(AsignacionDesarrolloTareaDAOImpl asignaDAO) {
        this.asignaDAO = asignaDAO;
    }

    /**
     * @param leer the leer to set
     */
    public void setLeer(Scanner leer) {
        this.leer = leer;
    }

}
