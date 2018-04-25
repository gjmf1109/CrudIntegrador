package com.dish.mx.dev.casos;

import com.dish.mx.dev.daoimpl.AsignacionDesarrolloTareaDAOImpl;
import com.dish.mx.dev.daoimpl.DesarrolladorDAOImpl;
import com.dish.mx.dev.daoimpl.ProyectoDAOImpl;
import com.dish.mx.dev.daoimpl.TareaDAOImpl;
import com.dish.mx.dev.dto.ProyectoDTO;
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
public class CasosMenuProyecto {

    private ProyectoDTO proyecto;
    private List<ProyectoDTO> imprimir;
    private ProyectoDTO imprimir2;

    @Autowired
    @Qualifier("proyectoDAOImpl")
    private ProyectoDAOImpl proyDAO;

    @Autowired
    @Qualifier("asignacionDesarrolloTareaDAOImpl")
    private AsignacionDesarrolloTareaDAOImpl asignaDAO;

    @Autowired
    @Qualifier("tareaDAOImpl")
    private TareaDAOImpl tareaDAO;

    @Autowired
    @Qualifier("desarrolladorDAOImpl")
    private DesarrolladorDAOImpl desaDAO;

    private Scanner leer = new Scanner(System.in);

    /**
     * Constructor vacío de la clase
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    public CasosMenuProyecto() {
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
        //Le imprimimos al usuario las opciones
        System.out.println("Retornar todos los registros de la tabla --> 1");
        System.out.println("Buscar por número de proyecto -------------> 2");
        //Leemos la opción que introdujó el usuario en consola
        int eleccion = getLeer().nextInt();
        if (eleccion == 1) {
            setImprimir(getProyDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingresa el número de proyecto: ");
            int id = getLeer().nextInt();
            setImprimir2(getProyDAO().encontrarPorId(id));
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
        ProyectoDTO proyInsertar;
        proyInsertar = new ProyectoDTO();

        try {
            getLeer().nextLine();
            System.out.print("Ingresa el número de proyecto: ");
            int proyNum = getLeer().nextInt();
            getLeer().nextLine(); //Limpiar buffer
            System.out.print("Ingresa el nombre del proyecto: ");
            String nomProy = getLeer().nextLine();
            System.out.print("Ingresa la descripcion del proyecto (No debe rebasar los 100 caracteres): ");
            String des = getLeer().nextLine();
            System.out.print("Ingresa la fecha de inicio del proyecto (Formato yyyy-mm-dd): ");
            String fechIni = getLeer().next();
            System.out.print("Ingresa la fecha final del proyecto (Formato yyyy-mm-dd): ");
            String fechFin = getLeer().next();
            proyInsertar.setProyectoId(proyNum);
            proyInsertar.setNombreProyecto(nomProy);
            proyInsertar.setDescripcion(des);
            proyInsertar.setFechaInicio(fechIni);
            proyInsertar.setFechaFin(fechFin);
            getProyDAO().insertarProyecto(proyInsertar);
            System.out.println("Proyecto registrado!");
            setImprimir(getProyDAO().encontrarTodos());
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
        int numProy;
        int resp = 0;

        getLeer().nextLine();
        try {
            System.out.print("¿Cuál es el número de proyecto?: ");
            numProy = getLeer().nextInt();
            setProyecto(getProyDAO().encontrarPorId(numProy));
            getLeer().nextLine();//Limpiar buffer
            System.out.print("\nNombre actual del proyecto: " + getProyecto().getNombreProyecto());
            System.out.print("\nNuevo nombre del proyecto (Digite el mismo nombre si no quiere cambiarlo): ");
            String proyNom = getLeer().nextLine();
            getProyecto().setNombreProyecto(proyNom);

            System.out.print("\nDescripción actual del proyecto: " + getProyecto().getDescripcion());
            System.out.print("\nDescripción nueva del proyecto (Digite la misma descripción si no quiere cambiarla, no debe rebasar los 100 caracteres): ");
            getProyecto().setDescripcion(getLeer().nextLine());

            System.out.print("\nFecha inicio actual del proyecto: " + getProyecto().getFechaInicio());
            System.out.print("\nNueva fecha inicio del proyecto (Digite la misma fecha si no quiere cambiarla, formato yyyy-mm-dd): ");
            getProyecto().setFechaInicio(getLeer().next());

            System.out.print("\nFecha final actual del proyecto: " + getProyecto().getFechaFin());
            System.out.print("\nNueva fecha final del proyecto (Digite la misma fecha si no quiere cambiarla, formato yyyy-mm-dd): ");
            getProyecto().setFechaFin(getLeer().next());

            resp = getProyDAO().actualizarProyecto(getProyecto());
            System.out.println("Proyecto actualizado!\n Se actualizaron " + resp + " líneas");
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
        System.out.println("Eliminar todos los registros de la tabla --> 1");
        System.out.println("Eliminar un registro de la tabla por el número de proyecto --> 2");
        //Se guarda la opción elegida por el usuario.
        int elim = getLeer().nextInt();
        try {
            if (elim == 1) {
                getAsignaDAO().eliminarTodos();
                getTareaDAO().eliminarTodos();
                getDesaDAO().eliminarTodos();
                getProyDAO().eliminarTodos();
                System.out.println("Todos los registros de proyecto han sido eliminados!");
                setImprimir(getProyDAO().encontrarTodos());
                System.out.println(getImprimir());
            } else {
                System.out.print("Ingrese el número de proyecto del registro a eliminar: ");
                //Lee el ID por consola para buscar el registro en la base de datos
                //y lo elimine
                int proy = getLeer().nextInt();
                getProyDAO().eliminarPorID2(proy);
                getProyDAO().eliminarPorID3(proy);
                getProyDAO().eliminarPorID4(proy);
                getProyDAO().eliminarPorID5(proy);
                getProyDAO().eliminarPorID(proy);
                System.out.println("Proyecto eliminado!");
                setImprimir(getProyDAO().encontrarTodos());
                System.out.println(getImprimir());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @return the proyecto
     */
    public ProyectoDTO getProyecto() {
        return proyecto;
    }

    /**
     * @return the imprimir
     */
    public List<ProyectoDTO> getImprimir() {
        return imprimir;
    }

    /**
     * @return the imprimir2
     */
    public ProyectoDTO getImprimir2() {
        return imprimir2;
    }

    /**
     * @return the proyDAO
     */
    public ProyectoDAOImpl getProyDAO() {
        return proyDAO;
    }

    /**
     * @return the leer
     */
    public Scanner getLeer() {
        return leer;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(ProyectoDTO proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * @param imprimir the imprimir to set
     */
    public void setImprimir(List<ProyectoDTO> imprimir) {
        this.imprimir = imprimir;
    }

    /**
     * @param imprimir2 the imprimir2 to set
     */
    public void setImprimir2(ProyectoDTO imprimir2) {
        this.imprimir2 = imprimir2;
    }

    /**
     * @param proyDAO the proyDAO to set
     */
    public void setProyDAO(ProyectoDAOImpl proyDAO) {
        this.proyDAO = proyDAO;
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

    /**
     * @return the tareaDAO
     */
    public TareaDAOImpl getTareaDAO() {
        return tareaDAO;
    }

    /**
     * @param tareaDAO the tareaDAO to set
     */
    public void setTareaDAO(TareaDAOImpl tareaDAO) {
        this.tareaDAO = tareaDAO;
    }

    /**
     * @return the desaDAO
     */
    public DesarrolladorDAOImpl getDesaDAO() {
        return desaDAO;
    }

    /**
     * @param desaDAO the desaDAO to set
     */
    public void setDesaDAO(DesarrolladorDAOImpl desaDAO) {
        this.desaDAO = desaDAO;
    }

}
