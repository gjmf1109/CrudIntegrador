package com.dish.mx.dev.dao;

import com.dish.mx.dev.dto.DesarrolladorDTO;
import java.util.List;

/**
 *
 * @author gerardo.martinez
 */
public interface DesarrolladorDAO {
    
    public List<DesarrolladorDTO> encontrarTodos();

    public DesarrolladorDTO encontrarPorId(int id);

    public void insertarDesarrollador(int desarrolladorId, int numEmpleado, String nombre,
            String apPaterno, String apMaterno, int proyectoId);

    public void insertarDesarrollador(DesarrolladorDTO tarea);

    public int actualizarDesarrollador(DesarrolladorDTO tarea);

    public int eliminarTodos();

    public int eliminarPorID(int id);
    
}
