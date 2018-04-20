package com.dish.mx.dev.daoimpl;

import com.dish.mx.dev.dao.ProyectoDAO;
import com.dish.mx.dev.dto.ProyectoDTO;
import com.dish.mx.dev.mapper.ProyectoRowMapper;
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
@Repository("proyectoDAOImpl")
public class ProyectoDAOImpl implements ProyectoDAO {

    private static final Logger logger = LogManager.getLogger(DesarrolladorDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProyectoDTO> encontrarTodos() {
        return getJdbcTemplate().query("SELECT * FROM proyecto", new ProyectoRowMapper());
    }

    @Override
    public ProyectoDTO encontrarPorId(int id) {
        //Ejecuta la consulta y nos regresa un objeto del tipo indicad
        //En este caso del tipo de nuestra clase EmpleadoRowMapper
        return getJdbcTemplate().queryForObject("SELECT * FROM proyecto WHERE proyecto_id = ?", new Object[]{id},
                new ProyectoRowMapper());
    }

    @Override
    public void insertarProyecto(int proyectoId, String nombreProyecto, String descripcion, String fechaInicio, String fechaFin) {
        getJdbcTemplate().update("INSERT INTO proyecto (proyecto_id, nombre_proyecto, descripcion, fecha_inicio, fecha_fin) "
                + "VALUES(?,?,?,?,?)", proyectoId, nombreProyecto, descripcion, fechaInicio, fechaFin);
    }

    @Override
    public void insertarProyecto(ProyectoDTO proyecto) {
        getJdbcTemplate().update("INSERT INTO proyecto (proyecto_id, nombre_proyecto, descripcion, fecha_inicio, fecha_fin) "
                + "VALUES(?,?,?,?,?)", proyecto.getProyectoId(), proyecto.getNombreProyecto(), 
                proyecto.getDescripcion(), proyecto.getFechaInicio(), proyecto.getFechaFin());
    }

    @Override
    public int actualizarProyecto(ProyectoDTO proyecto) {
        int resp = 0;

        //Un update me regresa el número de filas actualizadas, es por eso
        //que el resultado es asignado a una variable de tipo int.
        return resp = getJdbcTemplate().update("UPDATE proyecto SET nombre_proyecto = ?, "
                + "descripcion = ?, fecha_inicio = ?, fecha_fin = ? "
                + "WHERE  proyecto_id = ?", new Object[]{proyecto.getNombreProyecto(), proyecto.getDescripcion(),
                    proyecto.getFechaInicio(), proyecto.getFechaFin(), proyecto.getProyectoId()});
    }

    @Override
    public int eliminarTodos() {
        return getJdbcTemplate().update("DELETE from proyecto");
    }

    @Override
    public int eliminarPorID(int id) {
        return getJdbcTemplate().update("DELETE FROM proyecto WHERE proyecto_id = ?", id);
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
