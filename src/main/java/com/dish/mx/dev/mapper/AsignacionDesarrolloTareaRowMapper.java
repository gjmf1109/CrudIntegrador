package com.dish.mx.dev.mapper;

import com.dish.mx.dev.dto.AsignacionDesarrolloTareaDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author gerardo.martinez
 */
public class AsignacionDesarrolloTareaRowMapper implements RowMapper<AsignacionDesarrolloTareaDTO> {
    
    /**
     * El método recibe una variable del tipo ResultSet y un int, con estos 
     * argumentos recorre la tabla de la base de datos y va recogiendo los datos.
     *
     * @param rs
     * @param rowNum
     * 
     * @return Regresa un objeto del tipo EmpleadoDTO con los registros obtenidos
     * de la base de datos.
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     * 
     * @throws java.sql.SQLException
     *
     * @since 0.0.1
     */
    @Override
    public AsignacionDesarrolloTareaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AsignacionDesarrolloTareaDTO(rs.getInt("asignacion_id"), rs.getInt("desarrollador_id"), rs.getInt("tarea_id"));
    }
}
