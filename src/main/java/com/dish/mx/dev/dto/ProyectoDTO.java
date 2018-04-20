package com.dish.mx.dev.dto;

/**
 *
 * @author gerardo.martinez
 */
public class ProyectoDTO {

    private int proyectoId;
    private String nombreProyecto;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;

    public ProyectoDTO() {
    }

    public ProyectoDTO(int proyectoId, String nombreProyecto, String descripcion, String fechaInicio, String fechaFin) {
        this.proyectoId = proyectoId;
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /**
     * @return the proyectoId
     */
    public int getProyectoId() {
        return proyectoId;
    }

    /**
     * @return the nombreProyecto
     */
    public String getNombreProyecto() {
        return nombreProyecto;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the fechaInicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * @param proyectoId the proyectoId to set
     */
    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    /**
     * @param nombreProyecto the nombreProyecto to set
     */
    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "proyectoId=" + proyectoId + "\tnombreProyecto=" + nombreProyecto + "\tdescripcion=" + descripcion + "\tfechaInicio=" + fechaInicio + "\tfechaFin=" + fechaFin + "\n";
    }

}
