package com.dish.mx.dev.dao;

import com.dish.mx.dev.dto.ProyectoDTO;
import java.util.List;

/**
 *
 * @author gerardo.martinez
 */
public interface ProyectoDAO {

    public List<ProyectoDTO> encontrarTodos();

    public ProyectoDTO encontrarPorId(int id);

    public void insertarProyecto(int tareaId, String nombreTarea, String descripcion, int proyectoId);

    public void insertarProyecto(ProyectoDTO proyecto);

    public int actualizarProyecto(ProyectoDTO proyecto);

    public int eliminarTodos();

    public int eliminarPorID(int id);
}
