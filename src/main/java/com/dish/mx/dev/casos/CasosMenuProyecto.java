package com.dish.mx.dev.casos;

import com.dish.mx.dev.daoimpl.ProyectoDAOImpl;
import com.dish.mx.dev.dto.ProyectoDTO;
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
public class CasosMenuProyecto {
    
    private ProyectoDTO proyecto;
    private List<ProyectoDTO> imprimir;
    private ProyectoDTO imprimir2;

    @Autowired
    @Qualifier("proyectoDAOImpl")
    private ProyectoDAOImpl proyDAO;

    private Scanner leer = new Scanner(System.in);
    
    public void consultar() {
        //Le imprimimos al usuario las opciones
        System.out.println("Retornar todos los registros de la tabla --> 1");
        System.out.println("Buscar por número de proyecto --> 2");
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
    
    public void insertar() {
        ProyectoDTO proyInsertar;
        proyInsertar = new ProyectoDTO();

        System.out.print("Ingresa el número de proyecto: ");
        int proyNum = getLeer().nextInt();
        System.out.print("Ingresa el nombre del proyecto: ");
        String nomProy = getLeer().next();
        System.out.print("Ingresa la descripcion del proyecto (No debe rebasar los 100 caracteres): ");
        String des = getLeer().next();
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
    }
    
    public void actualizar() {
        int numProy;
        int resp = 0;

        System.out.print("¿Cuál es el número de proyecto?: ");
        numProy = getLeer().nextInt();
        setProyecto(getProyDAO().encontrarPorId(numProy));

        System.out.print("\nNombre actual del proyecto: " + getProyecto().getNombreProyecto());
        System.out.print("\nNuevo nombre del proyecto (Digite el mismo nombre si no quiere cambiarlo): ");
        getProyecto().setNombreProyecto(getLeer().next());

        System.out.print("\nDescripción actual del proyecto: " + getProyecto().getDescripcion());
        System.out.print("\nDescripción nueva del proyecto (Digite la misma descripción si no quiere cambiarla, no debe rebasar los 100 caracteres): ");
        getProyecto().setDescripcion(getLeer().next());

        System.out.print("\nFecha inicio actual del proyecto: " + getProyecto().getFechaInicio());
        System.out.print("\nNueva fecha inicio del proyecto (Digite la misma fecha si no quiere cambiarla, formato yyyy-mm-dd): ");
        getProyecto().setFechaInicio(getLeer().next());
        
        System.out.print("\nFecha final actual del proyecto: " + getProyecto().getFechaFin());
        System.out.print("\nNueva fecha final del proyecto (Digite la misma fecha si no quiere cambiarla, formato yyyy-mm-dd): ");
        getProyecto().setFechaFin(getLeer().next());
        
        resp = getProyDAO().actualizarProyecto(getProyecto());
        System.out.println("Proyecto actualizado!\n Se actualizaron " + resp + " líneas");
    }
    
    public void eliminar() {
        System.out.println("Eliminar todos los registros de la tabla --> 1");
        System.out.println("Eliminar un registro de la tabla por el número de proyecto --> 2");
        //Se guarda la opción elegida por el usuario.
        int elim = getLeer().nextInt();
        if (elim == 1) {
            getProyDAO().eliminarTodos();
            System.out.println("Todos los registros de proyecto han sido eliminados!");
            setImprimir(getProyDAO().encontrarTodos());
            System.out.println(getImprimir());
        } else {
            System.out.print("Ingrese el número de proyecto del registro a eliminar: ");
            //Lee el ID por consola para buscar el registro en la base de datos
            //y lo elimine
            int proy = getLeer().nextInt();
            getProyDAO().eliminarPorID(proy);
            System.out.println("Proyecto eliminado!");
            setImprimir(getProyDAO().encontrarTodos());
            System.out.println(getImprimir());
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
    
    
}
