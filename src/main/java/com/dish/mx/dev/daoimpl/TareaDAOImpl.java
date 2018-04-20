package com.dish.mx.dev.daoimpl;

import com.dish.mx.dev.dao.TareaDAO;
import com.dish.mx.dev.dto.TareaDTO;
import com.dish.mx.dev.mapper.TareaRowMapper;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gerardo.martinez
 */
@Repository("tareaDAOImpl")
public class TareaDAOImpl implements TareaDAO {
    
    private static final Logger logger = LogManager.getLogger(TareaDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TareaDAOImpl() {
    }

    @Override
    public List<TareaDTO> encontrarTodos() {
        return getJdbcTemplate().query("SELECT * FROM tarea", new TareaRowMapper());
    }

    @Override
    public TareaDTO encontrarPorId(int id) {
        //Ejecuta la consulta y nos regresa un objeto del tipo indicad
        //En este caso del tipo de nuestra clase EmpleadoRowMapper
        return getJdbcTemplate().queryForObject("SELECT * FROM tarea WHERE tarea_id = ?", new Object[]{id},
                new TareaRowMapper());
    }

    @Override
    public void insertarTarea(int tareaId, String nombreTarea, String descripcion, int proyectoId) {
        getJdbcTemplate().update("INSERT INTO tarea (tarea_id, nombre_tarea, descripcion, proyecto_id) "
                + "VALUES(?,?,?,?)", tareaId, nombreTarea, descripcion, proyectoId);
    }

    @Override
    public void insertarTarea(TareaDTO tarea) {
        getJdbcTemplate().update("INSERT INTO tarea (tarea_id, nombre_tarea, descripcion, proyecto_id) "
                + "VALUES(?,?,?,?)", tarea.getTareaId(), tarea.getNombreTarea(), tarea.getDescripcion(), tarea.getProyectoId());
    }

    @Override
    public int actualizarTarea(TareaDTO tarea) {
        int resp = 0;

        //Un update me regresa el número de filas actualizadas, es por eso
        //que el resultado es asignado a una variable de tipo int.
        return resp = getJdbcTemplate().update("UPDATE tarea SET nombre_tarea = ?, "
                + "descripcion = ?, proyecto_id = ?"
                + "WHERE  tarea_id = ?", new Object[]{tarea.getNombreTarea(), tarea.getDescripcion(),
                    tarea.getProyectoId(), tarea.getTareaId()});
    }

    @Override
    public int eliminarTodos() {
        return getJdbcTemplate().update("DELETE from tarea");
    }

    @Override
    public int eliminarPorID(int id) {
        return getJdbcTemplate().update("DELETE FROM tarea WHERE tarea_id = ?", id);
    }

    /**
     * @return the jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * @param jdbcTemplate the jdbcTemplate to set
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
}
