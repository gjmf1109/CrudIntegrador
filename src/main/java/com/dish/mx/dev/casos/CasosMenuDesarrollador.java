package com.dish.mx.dev.casos;

import com.dish.mx.dev.daoimpl.DesarrolladorDAOImpl;
import com.dish.mx.dev.dto.DesarrolladorDTO;
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
public class CasosMenuDesarrollador {

    private DesarrolladorDTO desa;
    private List<DesarrolladorDTO> imprimir;
    private DesarrolladorDTO imprimir2;

    @Autowired
    @Qualifier("desarrolladorDAOImpl")
    private DesarrolladorDAOImpl desaDAO;

    private Scanner leer = new Scanner(System.in);

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
    
    public void insertar() {
        DesarrolladorDTO desaInsertar;
        desaInsertar = new DesarrolladorDTO();

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
        System.out.print("Ingresa el número de proyecto al cual esta designado el desarrollador: ");
        int proy = getLeer().nextInt();
        desaInsertar.setDesarrolladorId(desaNum);
        desaInsertar.setNumEmpleado(numEmp);
        desaInsertar.setNombre(nom);
        desaInsertar.setApPaterno(apPat);
        desaInsertar.setApMaterno(apMat);
        desaInsertar.setProyectoId(proy);
        getDesaDAO().insertarDesarrollador(desaInsertar);
        System.out.println("Desarrollador registrado!");
        setImprimir(getDesaDAO().encontrarTodos());
        System.out.println(getImprimir());
    }
    
    public void actualizar() {
        int numDesa;
        int resp = 0;

        System.out.print("¿Cuál es el número de desarrollador?: ");
        numDesa = getLeer().nextInt();
        setDesa(getDesaDAO().encontrarPorId(numDesa));

        System.out.print("\nNúmero actual del desarrollador: " + getDesa().getDesarrolladorId());
        System.out.print("\nNúmero nuevo del desarrollador (Digite el mismo número si no quiere cambiarlo): ");
        getDesa().setDesarrolladorId(getLeer().nextInt());
        
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
        
        System.out.print("\nNúmero actual de proyecto al cual esta designado el desarrollador: " + getDesa().getProyectoId());
        System.out.print("\nNuevo número de proyecto (Digite el mismo numero si no quiere cambiarlo): ");
        getDesa().setProyectoId(getLeer().nextInt());

        resp = getDesaDAO().actualizarDesarrollador(getDesa());
        System.out.println("Desarrollador actualizado!\n Se actualizaron " + resp + " líneas");
    }
    
    public void eliminar() {
        System.out.println("Eliminar todos los registros de la tabla --> 1");
        System.out.println("Eliminar un registro de la tabla por el número del desarrollador --> 2");
        //Se guarda la opción elegida por el usuario.
        int elim = getLeer().nextInt();
        if (elim == 1) {
            getDesaDAO().eliminarTodos();
            System.out.println("Todos los registros de desarrollador han sido eliminados!");
            setImprimir(getDesaDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingrese el número del desarrollador del registro a eliminar: ");
            //Lee el ID por consola para buscar el registro en la base de datos
            //y lo elimine
            int desa = getLeer().nextInt();
            getDesaDAO().eliminarPorID(desa);
            System.out.println("Desarrollador eliminado!");
            setImprimir(getDesaDAO().encontrarTodos());
            System.out.println(getImprimir());
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

}
