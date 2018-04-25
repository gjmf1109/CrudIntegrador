package com.dish.mx.dev.casos;

import com.dish.mx.dev.daoimpl.AsignacionDesarrolloTareaDAOImpl;
import com.dish.mx.dev.daoimpl.TareaDAOImpl;
import com.dish.mx.dev.dto.TareaDTO;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Clase en la que definimos métodos para la obtención de datos introducidos por
 * consola por el usuario.
 *
 * @version 0.0.1
 *
 * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
 *
 * @since 0.0.1
 *
 */
@Component
public class CasosMenuTarea {

    private TareaDTO tarea;
    private List<TareaDTO> imprimir;
    private TareaDTO imprimir2;

    @Autowired
    @Qualifier("asignacionDesarrolloTareaDAOImpl")
    private AsignacionDesarrolloTareaDAOImpl asignaDAO;

    @Autowired
    @Qualifier("tareaDAOImpl")
    private TareaDAOImpl tareaDAO;

    private Scanner leer = new Scanner(System.in);

    /**
     * Constructor vacío de la clase
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    public CasosMenuTarea() {
    }

    /**
     * Método en el que se le da al usuario la opción de consultar todos los
     * elementos de la tabla de la base de datos o consultar alguno de acuerdo a
     * un id
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    public void consultar() {
        Scanner leer1 = new Scanner(System.in);

        //Le imprimimos al usuario las opciones
        System.out.println("Retornar todos los registros de la tabla --> 1");
        System.out.println("Buscar por número de tarea --> 2");
        //Leemos la opción que introdujó el usuario en consola
        //int eleccion = getLeer().nextInt();
        int eleccion = leer1.nextInt();
        if (eleccion == 1) {
            setImprimir(getTareaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingresa el número de tarea: ");
            //int id = getLeer().nextInt();
            int id = leer1.nextInt();
            setImprimir2(getTareaDAO().encontrarPorId(id));
            System.out.println(getImprimir2());
        }
    }

    /**
     * Método en el que se le piden al usuario datos para insertar un registro
     * en la tabla
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    public void insertar() {
        TareaDTO tareaInsertar;
        tareaInsertar = new TareaDTO();

        Scanner leer2 = new Scanner(System.in);

        try {
            System.out.print("Ingresa el número de tarea: ");
            int tareaNum = leer2.nextInt();
            System.out.print("Ingresa el nombre de la tarea: ");
            String nombre = leer2.next();
            System.out.print("Ingresa el número de proyecto al cual esta designada la tarea: ");
            int proy = leer2.nextInt();
            leer2.nextLine();//Limpiar buffer
            System.out.print("Ingresa la descripción de la tarea (Debe ser menor a 100 caracteres): ");
            String des = leer2.nextLine();
            tareaInsertar.setTareaId(tareaNum);
            tareaInsertar.setNombreTarea(nombre);
            tareaInsertar.setDescripcion(des);
            tareaInsertar.setProyectoId(proy);
            getTareaDAO().insertarTarea(tareaInsertar);
            System.out.println("Tarea registrada!");
            setImprimir(getTareaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Método en el que se le piden al usuario datos para actualizar todos o
     * algunos campos del registro.
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    public void actualizar() {
        int numTarea;
        int resp = 0;

        Scanner leer3 = new Scanner(System.in);

        try {
            System.out.print("¿Cuál es el número de tarea?: ");
            numTarea = leer3.nextInt();
            setTarea(getTareaDAO().encontrarPorId(numTarea));

            System.out.print("\nNombre actual de tarea: " + getTarea().getNombreTarea());
            System.out.print("\nNombre nuevo de tarea (Digite el mismo nombre si no quiere cambiarlo): ");
            String nomTarea = leer3.next();
            getTarea().setNombreTarea(nomTarea);
            leer3.nextLine();//Limpiar buffer
            System.out.print("\nNúmero actual de proyecto al cual esta designada la tarea: " + getTarea().getProyectoId());
            System.out.print("\nNuevo número de proyecto (Digite el mismo número si no quiere cambiarlo): ");
            int proyec = leer3.nextInt();
            getTarea().setProyectoId(proyec);
            leer3.nextLine();//Limpiar buffer
            System.out.print("\nDescripción actual de tarea: " + getTarea().getDescripcion());
            System.out.print("\nNueva descripción de tarea (La descripción no puede superar los 100 caracteres): ");
            String descrip = leer3.nextLine();
            getTarea().setDescripcion(descrip);

            resp = getTareaDAO().actualizarTarea(getTarea());
            System.out.println("Tarea actualizada!\n Se actualizaron " + resp + " líneas");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Método en el que se le da al usuario la opción de eliminar todos los
     * elementos de la tabla de la base de datos o eliminar alguno de acuerdo a
     * un id
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    public void eliminar() {
        Scanner leer4 = new Scanner(System.in);

        System.out.println("Eliminar todos los registros de la tabla --> 1");
        System.out.println("Eliminar un registro de la tabla por el número de tarea --> 2");
        //Se guarda la opción elegida por el usuario.
//        int elim = getLeer().nextInt();
        int elim = leer4.nextInt();

        try {
            if (elim == 1) {
                getAsignaDAO().eliminarTodos();
                getTareaDAO().eliminarTodos();
                System.out.println("Todos los registros de tarea han sido eliminados!");
                setImprimir(getTareaDAO().encontrarTodos());
                System.out.println(getImprimir());
            } else {
                System.out.print("Ingrese el número de tarea del registro a eliminar: ");
                //Lee el ID por consola para buscar el registro en la base de datos
                //y lo elimine
                int tarea1 = leer4.nextInt();
                getAsignaDAO().eliminarPorID3(tarea1);
                getTareaDAO().eliminarPorID(tarea1);
                System.out.println("Tarea eliminada!");
                setImprimir(getTareaDAO().encontrarTodos());
                System.out.println(getImprimir());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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

    /**
     * @return the asignaDAO
     */
    public AsignacionDesarrolloTareaDAOImpl getAsignaDAO() {
        return asignaDAO;
    }

    /**
     * @param asignaDAO the asignaDAO to set
     */
    public void setAsignaDAO(AsignacionDesarrolloTareaDAOImpl asignaDAO) {
        this.asignaDAO = asignaDAO;
    }

}
