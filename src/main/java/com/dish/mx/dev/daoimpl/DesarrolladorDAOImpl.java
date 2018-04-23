package com.dish.mx.dev.daoimpl;

import com.dish.mx.dev.dao.DesarrolladorDAO;
import com.dish.mx.dev.dto.DesarrolladorDTO;
import com.dish.mx.dev.mapper.DesarrolladorRowMapper;
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
@Repository("desarrolladorDAOImpl")
public class DesarrolladorDAOImpl implements DesarrolladorDAO {

    private static final Logger logger = LogManager.getLogger(DesarrolladorDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DesarrolladorDAOImpl() {
    }

    @Override
    public List<DesarrolladorDTO> encontrarTodos() {
        return getJdbcTemplate().query("SELECT * FROM desarrollador", new DesarrolladorRowMapper());
    }

    @Override
    public DesarrolladorDTO encontrarPorId(int id) {
        //Ejecuta la consulta y nos regresa un objeto del tipo indicad
        //En este caso del tipo de nuestra clase EmpleadoRowMapper
        return getJdbcTemplate().queryForObject("SELECT * FROM desarrollador WHERE desarrollador_id = ?", new Object[]{id},
                new DesarrolladorRowMapper());
    }

    @Override
    public void insertarDesarrollador(int desarrolladorId, int numEmpleado, String nombre, String apPaterno,
            String apMaterno, int proyectoId) {
        getJdbcTemplate().update("INSERT INTO desarrollador (desarrollador_id, num_empleado, nombre, ap_paterno, "
                + "ap_materno, proyecto_id) "
                + "VALUES(?,?,?,?,?,?)", desarrolladorId, numEmpleado, nombre, apPaterno, apMaterno, proyectoId);
    }

    @Override
    public void insertarDesarrollador(DesarrolladorDTO tarea) {
        getJdbcTemplate().update("INSERT INTO desarrollador (desarrollador_id, num_empleado, nombre, ap_paterno, "
                + "ap_materno, proyecto_id) "
                + "VALUES(?,?,?,?,?,?)", tarea.getDesarrolladorId(), tarea.getNumEmpleado(), tarea.getNombre(),
                tarea.getApPaterno(), tarea.getApMaterno(), tarea.getProyectoId());
    }

    @Override
    public int actualizarDesarrollador(DesarrolladorDTO tarea) {
        int resp = 0;

        //Un update me regresa el número de filas actualizadas, es por eso
        //que el resultado es asignado a una variable de tipo int.
        return resp = getJdbcTemplate().update("UPDATE desarrollador SET num_empleado = ?, "
                + "nombre = ?, ap_paterno = ?, ap_materno = ?, proyecto_id = ? "
                + "WHERE  desarrollador_id = ?", new Object[]{tarea.getNumEmpleado(), tarea.getNombre(), tarea.getApPaterno(),
                    tarea.getApMaterno(), tarea.getProyectoId(), tarea.getDesarrolladorId()});
    }

    @Override
    public int eliminarTodos() {
        return getJdbcTemplate().update("DELETE from desarrollador");
    }

    @Override
    public int eliminarPorID(int id) {
        return getJdbcTemplate().update("DELETE FROM desarrollador WHERE desarrollador_id = ?", id);
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
