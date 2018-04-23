package com.dish.mx.dev.daoimpl;

import com.dish.mx.dev.dao.AsignacionDesarrolloTareaDAO;
import com.dish.mx.dev.dto.AsignacionDesarrolloTareaDTO;
import com.dish.mx.dev.mapper.AsignacionDesarrolloTareaRowMapper;
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
@Repository("asignacionDesarrolloTareaDAOImpl")
public class AsignacionDesarrolloTareaDAOImpl implements AsignacionDesarrolloTareaDAO{
    
    private static final Logger logger = LogManager.getLogger(AsignacionDesarrolloTareaDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AsignacionDesarrolloTareaDAOImpl() {
    }

    @Override
    public List<AsignacionDesarrolloTareaDTO> encontrarTodos() {
        return getJdbcTemplate().query("SELECT * FROM asignacion_desarrollador_tarea", new AsignacionDesarrolloTareaRowMapper());
    }

    @Override
    public AsignacionDesarrolloTareaDTO encontrarPorId(int id) {
        //Ejecuta la consulta y nos regresa un objeto del tipo indicado
        //En este caso del tipo de nuestra clase TareaRowMapper
        return getJdbcTemplate().queryForObject("SELECT * FROM asignacion_desarrollador_tarea WHERE asignacion_id = ?", 
                new Object[]{id},
                new AsignacionDesarrolloTareaRowMapper());
    }

    @Override
    public void insertarAsignacionDesarrolloTarea(int asignacionId, int desarrolladorId, int tareaId) {
        getJdbcTemplate().update("INSERT INTO asignacion_desarrollador_tarea (asignacion_id, desarrollador_id, tarea_id) "
                + "VALUES(?,?,?)", asignacionId, desarrolladorId, tareaId);
    }

    @Override
    public void insertarAsignacionDesarrolloTarea(AsignacionDesarrolloTareaDTO asignacion) {
        getJdbcTemplate().update("INSERT INTO asignacion_desarrollador_tarea (asignacion_id, desarrollador_id, tarea_id) "
                + "VALUES(?,?,?)", asignacion.getAsignacionId(), asignacion.getDesarrolladorId(), asignacion.getTareaId());
    }

    @Override
    public int actualizarAsignacionDesarrolloTarea(AsignacionDesarrolloTareaDTO asignacion) {
        int resp = 0;

        //Un update me regresa el número de filas actualizadas, es por eso
        //que el resultado es asignado a una variable de tipo int.
        return resp = getJdbcTemplate().update("UPDATE asignacion_desarrollador_tarea SET desarrollador_id = ?, tarea_id = ?"
                + "WHERE  asignacion_id = ?", new Object[]{asignacion.getDesarrolladorId(),asignacion.getTareaId(),
                    asignacion.getAsignacionId()});
    }

    @Override
    public int eliminarTodos() {
        return getJdbcTemplate().update("DELETE from asignacion_desarrollador_tarea");
    }

    @Override
    public int eliminarPorID(int id) {
        return getJdbcTemplate().update("DELETE FROM asignacion_desarrollador_tarea WHERE asignacion_id = ?", id);
    }
    
    @Override
    public int eliminarPorID2(int id) {
        return getJdbcTemplate().update("DELETE FROM asignacion_desarrollador_tarea WHERE desarrollador_id = ?", id);
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
