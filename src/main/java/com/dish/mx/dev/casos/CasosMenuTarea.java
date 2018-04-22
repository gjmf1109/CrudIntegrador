package com.dish.mx.dev.casos;

import com.dish.mx.dev.daoimpl.TareaDAOImpl;
import com.dish.mx.dev.dto.TareaDTO;
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
public class CasosMenuTarea {

    private TareaDTO tarea;
    private List<TareaDTO> imprimir;
    private TareaDTO imprimir2;

    @Autowired
    @Qualifier("tareaDAOImpl")
    private TareaDAOImpl tareaDAO;

    private Scanner leer = new Scanner(System.in);

    public CasosMenuTarea() {
    }

    public void consultar() {
        //Le imprimimos al usuario las opciones
        System.out.println("Retornar todos los registros de la tabla --> 1");
        System.out.println("Buscar por número de tarea --> 2");
        //Leemos la opción que introdujó el usuario en consola
        int eleccion = getLeer().nextInt();
        if (eleccion == 1) {
            setImprimir(getTareaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingresa el número de tarea: ");
            int id = getLeer().nextInt();
            setImprimir2(getTareaDAO().encontrarPorId(id));
            System.out.println(getImprimir2());
        }
    }

    public void insertar() {
        TareaDTO tareaInsertar;
        tareaInsertar = new TareaDTO();

        System.out.print("Ingresa el número de tarea: ");
        int tareaNum = getLeer().nextInt();
        System.out.print("Ingresa el nombre de la tarea: ");
        String nombre = getLeer().next();
        System.out.print("Ingresa la descripción de la tarea (Debe ser menor a 100 caracteres): ");
        String des = getLeer().next();
        System.out.print("Ingresa el número de proyecto al cual esta designada la tarea: ");
        int proy = getLeer().nextInt();
        tareaInsertar.setTareaId(tareaNum);
        tareaInsertar.setNombreTarea(nombre);
        tareaInsertar.setDescripcion(des);
        tareaInsertar.setProyectoId(proy);
        getTareaDAO().insertarTarea(tareaInsertar);
        System.out.println("Tarea registrada!");
        setImprimir(getTareaDAO().encontrarTodos());
        System.out.println(getImprimir());
    }

    public void actualizar() {
        int numTarea;
        int resp = 0;

        System.out.print("¿Cuál es el número de tarea?: ");
        numTarea = getLeer().nextInt();
        setTarea(getTareaDAO().encontrarPorId(numTarea));

        System.out.print("\nNúmero actual de tarea: " + getTarea().getTareaId());
        System.out.print("\nNúmero nuevo de tarea (Digite el mismo número si no quiere cambiarlo): ");
        getTarea().setTareaId(getLeer().nextInt());

        System.out.print("\nNombre actual de tarea: " + getTarea().getNombreTarea());
        System.out.print("\nNombre nuevo de tarea (Digite el mismo nombre si no quiere cambiarlo): ");
        getTarea().setNombreTarea(getLeer().next());

        System.out.print("\nDescripción actual de tarea: " + getTarea().getDescripcion());
        System.out.print("\nNueva descripción de tarea (La descripción no puede superar los 100 caracteres): ");
        getTarea().setDescripcion(getLeer().next());
        
        System.out.print("\nNúmero actual de proyecto al cual esta designada la tarea: " + getTarea().getProyectoId());
        System.out.print("\nNuevo número de proyecto (Digite el mismo nombre si no quiere cambiarlo): ");
        getTarea().setProyectoId(getLeer().nextInt());

        resp = getTareaDAO().actualizarTarea(getTarea());
        System.out.println("Tarea actualizada!\n Se actualizaron " + resp + " líneas");
    }
    
    public void eliminar() {
        System.out.println("Eliminar todos los registros de la tabla --> 1");
        System.out.println("Eliminar un registro de la tabla por el número de tarea --> 2");
        //Se guarda la opción elegida por el usuario.
        int elim = getLeer().nextInt();
        if (elim == 1) {
            getTareaDAO().eliminarTodos();
            System.out.println("Todos los registros de tarea han sido eliminados!");
            setImprimir(getTareaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingrese el número de tarea del registro a eliminar: ");
            //Lee el ID por consola para buscar el registro en la base de datos
            //y lo elimine
            int tarea = getLeer().nextInt();
            getTareaDAO().eliminarPorID(tarea);
            System.out.println("Tarea eliminada!");
            setImprimir(getTareaDAO().encontrarTodos());
            System.out.println(getImprimir());
        }
    }

    /**
     * @return the tarea
     */
    public TareaDTO getTarea() {
        return tarea;
    }

    /**
     * @return the imprimir
     */
    public List<TareaDTO> getImprimir() {
        return imprimir;
    }

    /**
     * @return the imprimir2
     */
    public TareaDTO getImprimir2() {
        return imprimir2;
    }

    /**
     * @return the tareaDAO
     */
    public TareaDAOImpl getTareaDAO() {
        return tareaDAO;
    }

    /**
     * @return the leer
     */
    public Scanner getLeer() {
        return leer;
    }

    /**
     * @param tarea the tarea to set
     */
    public void setTarea(TareaDTO tarea) {
        this.tarea = tarea;
    }

    /**
     * @param imprimir the imprimir to set
     */
    public void setImprimir(List<TareaDTO> imprimir) {
        this.imprimir = imprimir;
    }

    /**
     * @param imprimir2 the imprimir2 to set
     */
    public void setImprimir2(TareaDTO imprimir2) {
        this.imprimir2 = imprimir2;
    }

    /**
     * @param tareaDAO the tareaDAO to set
     */
    public void setTareaDAO(TareaDAOImpl tareaDAO) {
        this.tareaDAO = tareaDAO;
    }

    /**
     * @param leer the leer to set
     */
    public void setLeer(Scanner leer) {
        this.leer = leer;
    }

}
