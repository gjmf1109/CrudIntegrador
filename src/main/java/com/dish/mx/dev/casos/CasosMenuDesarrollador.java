package com.dish.mx.dev.casos;

import com.dish.mx.dev.daoimpl.AsignacionDesarrolloTareaDAOImpl;
import com.dish.mx.dev.daoimpl.DesarrolladorDAOImpl;
import com.dish.mx.dev.dto.DesarrolladorDTO;
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
public class CasosMenuDesarrollador {

    private DesarrolladorDTO desa;
    private List<DesarrolladorDTO> imprimir;
    private DesarrolladorDTO imprimir2;

    @Autowired
    @Qualifier("asignacionDesarrolloTareaDAOImpl")
    private AsignacionDesarrolloTareaDAOImpl asignaDAO;

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
    public CasosMenuDesarrollador() {
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
        System.out.println("Buscar por número de desarrollador --> 2");
        //Leemos la opción que introdujó el usuario en consola
        int eleccion = getLeer().nextInt();
        if (eleccion == 1) {
            setImprimir(getDesaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingresa el número de desarrollador: ");
            int id = getLeer().nextInt();
            setImprimir2(getDesaDAO().encontrarPorId(id));
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
        DesarrolladorDTO desaInsertar;
        desaInsertar = new DesarrolladorDTO();

        try {
            System.out.print("Ingresa el número del desarrollador: ");
            int desaNum = getLeer().nextInt();
            System.out.print("Ingresa el número de empleado: ");
            int numEmp = getLeer().nextInt();
            System.out.print("Ingresa el nombre del desarrollador: ");
            String nom = getLeer().next();
            System.out.print("Ingresa el apellido paterno del desarrollador: ");
            String apPat = getLeer().next();
            System.out.print("Ingresa el apellido materno del desarrollador: ");
            String apMat = getLeer().next();
            desaInsertar.setDesarrolladorId(desaNum);
            desaInsertar.setNumEmpleado(numEmp);
            desaInsertar.setNombre(nom);
            desaInsertar.setApPaterno(apPat);
            desaInsertar.setApMaterno(apMat);
            getDesaDAO().insertarDesarrollador(desaInsertar);
            System.out.println("Desarrollador registrado!");
            setImprimir(getDesaDAO().encontrarTodos());
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
        int numDesa;
        int resp = 0;

        try {
            System.out.print("¿Cuál es el número de desarrollador?: ");
            numDesa = getLeer().nextInt();
            setDesa(getDesaDAO().encontrarPorId(numDesa));

            System.out.print("\nNúmero de empleado actual de desarrollador: " + getDesa().getNumEmpleado());
            System.out.print("\nNúmero nuevo de empleado del desarrollador (Digite el mismo número si no quiere cambiarlo, debe ser de 4 dígitos): ");
            getDesa().setNumEmpleado(getLeer().nextInt());

            System.out.print("\nNombre actual del desarrollador: " + getDesa().getNombre());
            System.out.print("\nNombre nuevo del desarrollador (Digite el mismo nombre si no quiere cambiarlo): ");
            getDesa().setNombre(getLeer().next());

            System.out.print("\nApellido paterno actual del desarrollador: " + getDesa().getApPaterno());
            System.out.print("\nNuevo apellido paterno del desarrollador (Digite el mismo apellido si no quiere cambiarlo): ");
            getDesa().setApPaterno(getLeer().next());

            System.out.print("\nApellido materno actual del desarrollador: " + getDesa().getApMaterno());
            System.out.print("\nNuevo apellido materno del desarrollador (Digite el mismo apellido si no quiere cambiarlo): ");
            getDesa().setApMaterno(getLeer().next());

            resp = getDesaDAO().actualizarDesarrollador(getDesa());
            System.out.println("Desarrollador actualizado!\n Se actualizaron " + resp + " líneas");
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
        System.out.println("Eliminar un registro de la tabla por el número del desarrollador --> 2");
        //Se guarda la opción elegida por el usuario.
        int elim = getLeer().nextInt();

        try {
            if (elim == 1) {
                getAsignaDAO().eliminarTodos();
                getDesaDAO().eliminarTodos();
                System.out.println("Todos los registros de desarrollador han sido eliminados!");
                setImprimir(getDesaDAO().encontrarTodos());
                System.out.println(getImprimir());
            } else {
                System.out.print("Ingrese el número del desarrollador del registro a eliminar: ");
                int desa1 = getLeer().nextInt();
                getAsignaDAO().eliminarPorID2(desa1);
                getDesaDAO().eliminarPorID(desa1);
                System.out.println("Desarrollador eliminado!");
                setImprimir(getDesaDAO().encontrarTodos());
                System.out.println(getImprimir());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @return the desa
     */
    public DesarrolladorDTO getDesa() {
        return desa;
    }

    /**
     * @return the imprimir
     */
    public List<DesarrolladorDTO> getImprimir() {
        return imprimir;
    }

    /**
     * @return the imprimir2
     */
    public DesarrolladorDTO getImprimir2() {
        return imprimir2;
    }

    /**
     * @return the desaDAO
     */
    public DesarrolladorDAOImpl getDesaDAO() {
        return desaDAO;
    }

    /**
     * @return the leer
     */
    public Scanner getLeer() {
        return leer;
    }

    /**
     * @param desa the desa to set
     */
    public void setDesa(DesarrolladorDTO desa) {
        this.desa = desa;
    }

    /**
     * @param imprimir the imprimir to set
     */
    public void setImprimir(List<DesarrolladorDTO> imprimir) {
        this.imprimir = imprimir;
    }

    /**
     * @param imprimir2 the imprimir2 to set
     */
    public void setImprimir2(DesarrolladorDTO imprimir2) {
        this.imprimir2 = imprimir2;
    }

    /**
     * @param desaDAO the desaDAO to set
     */
    public void setDesaDAO(DesarrolladorDAOImpl desaDAO) {
        this.desaDAO = desaDAO;
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
