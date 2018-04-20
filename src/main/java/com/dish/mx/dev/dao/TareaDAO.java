package com.dish.mx.dev.dao;

import com.dish.mx.dev.dto.TareaDTO;
import java.util.List;

/**
 *
 * @author gerardo.martinez
 */
public interface TareaDAO {
    
    public List<TareaDTO> encontrarTodos();

    public TareaDTO encontrarPorId(int id);

    public void insertarTarea(int tareaId, String nombreTarea, String descripcion, int proyectoId);

    public void insertarTarea(TareaDTO tarea);

    public int actualizarTarea(TareaDTO tarea);

    public int eliminarTodos();

    public int eliminarPorID(int id);
}
