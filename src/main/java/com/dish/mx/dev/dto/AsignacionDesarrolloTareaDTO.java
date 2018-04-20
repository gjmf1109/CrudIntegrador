package com.dish.mx.dev.dto;

/**
 *
 * @author gerardo.martinez
 */
public class AsignacionDesarrolloTareaDTO {

    private int asignacionId;
    private int desarrolladorId;
    private int tareaId;

    public AsignacionDesarrolloTareaDTO() {
    }

    public AsignacionDesarrolloTareaDTO(int asignacionId, int desarrolladorId, int tareaId) {
        this.asignacionId = asignacionId;
        this.desarrolladorId = desarrolladorId;
        this.tareaId = tareaId;
    }

    /**
     * @return the asignacionId
     */
    public int getAsignacionId() {
        return asignacionId;
    }

    /**
     * @return the desarrolladorId
     */
    public int getDesarrolladorId() {
        return desarrolladorId;
    }

    /**
     * @return the tareaId
     */
    public int getTareaId() {
        return tareaId;
    }

    /**
     * @param asignacionId the asignacionId to set
     */
    public void setAsignacionId(int asignacionId) {
        this.asignacionId = asignacionId;
    }

    /**
     * @param desarrolladorId the desarrolladorId to set
     */
    public void setDesarrolladorId(int desarrolladorId) {
        this.desarrolladorId = desarrolladorId;
    }

    /**
     * @param tareaId the tareaId to set
     */
    public void setTareaId(int tareaId) {
        this.tareaId = tareaId;
    }

    @Override
    public String toString() {
        return "\tasignacionId=" + asignacionId + "\tdesarrolladorId=" + desarrolladorId + "\ttareaId=" + tareaId + "\n";
    }

}
