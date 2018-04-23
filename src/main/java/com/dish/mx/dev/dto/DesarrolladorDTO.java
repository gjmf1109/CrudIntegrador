package com.dish.mx.dev.dto;

/**
 *
 * @author gerardo.martinez
 */
public class DesarrolladorDTO {

    private int desarrolladorId;
    private int numEmpleado;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private int proyectoId;

    public DesarrolladorDTO() {
    }

    public DesarrolladorDTO(int desarrolladorId, int numEmpleado, String nombre,
            String apPaterno, String apMaterno, int proyectoId) {

        this.desarrolladorId = desarrolladorId;
        this.numEmpleado = numEmpleado;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.proyectoId = proyectoId;
    }

    /**
     * @return the desarrolladorId
     */
    public int getDesarrolladorId() {
        return desarrolladorId;
    }

    /**
     * @return the numEmpleado
     */
    public int getNumEmpleado() {
        return numEmpleado;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apPaterno
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * @return the apMaterno
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * @return the proyectoId
     */
    public int getProyectoId() {
        return proyectoId;
    }

    /**
     * @param desarrolladorId the desarrolladorId to set
     */
    public void setDesarrolladorId(int desarrolladorId) {
        this.desarrolladorId = desarrolladorId;
    }

    /**
     * @param numEmpleado the numEmpleado to set
     */
    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param apPaterno the apPaterno to set
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    /**
     * @param apMaterno the apMaterno to set
     */
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    /**
     * @param proyectoId the proyectoId to set
     */
    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    @Override
    public String toString() {
        return "desarrolladorId=" + desarrolladorId + "\tnumEmpleado=" + numEmpleado + "\tnombre=" + nombre + "\tapPaterno=" + apPaterno + "\tapMaterno=" + apMaterno + "\tproyectoId=" + proyectoId + "\n";
    }

}
