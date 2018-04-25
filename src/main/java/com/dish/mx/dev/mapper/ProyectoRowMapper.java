package com.dish.mx.dev.mapper;

import com.dish.mx.dev.dto.ProyectoDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Clase en la que se obtienen los datos de la clase Proyecto y los retorna para
 * poder utilizarlos en donde sean requeridos.
 *
 * @version 0.0.1
 *
 * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx%gt;
 *
 * @since 0.0.1
 *
 */
public class ProyectoRowMapper implements RowMapper<ProyectoDTO>{
    
    /**
     * El método recibe una variable del tipo ResultSet y un int, con estos
     * argumentos recorre la tabla de la base de datos y va recogiendo los datos.
     *
     * @param rs
     * @param rowNum
     *
     * @return Regresa un objeto del tipo ProyectoDTO con los registros
     * obtenidos de la base de datos.
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @throws java.sql.SQLException
     *
     * @since 0.0.1
     */
    @Override
    public ProyectoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProyectoDTO(rs.getInt("proyecto_id"), rs.getString("nombre_proyecto"), rs.getString("descripcion"),
                rs.getString("fecha_inicio"), rs.getString("fecha_fin"));
    }
}
