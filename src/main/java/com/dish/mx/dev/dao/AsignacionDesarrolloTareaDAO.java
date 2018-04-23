package com.dish.mx.dev.dao;

import com.dish.mx.dev.dto.AsignacionDesarrolloTareaDTO;
import java.util.List;

/**
 *
 * @author gerardo.martinez
 */
public interface AsignacionDesarrolloTareaDAO {

    public List<AsignacionDesarrolloTareaDTO> encontrarTodos();

    public AsignacionDesarrolloTareaDTO encontrarPorId(int id);

    public void insertarAsignacionDesarrolloTarea(int asignacionId, int desarrolladorId, int tareaId);

    public void insertarAsignacionDesarrolloTarea(AsignacionDesarrolloTareaDTO asignacion);

    public int actualizarAsignacionDesarrolloTarea(AsignacionDesarrolloTareaDTO asignacion);

    public int eliminarTodos();

    public int eliminarPorID(int id);
    
    public int eliminarPorID2(int id);

}
