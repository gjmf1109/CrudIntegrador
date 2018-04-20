package com.dish.mx.dev.crudintegrador;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
//Ruta donde buscará el archivo properties para  poder obtener los datos necesarios
//para conectarse a la base de datos.
@PropertySource("classpath:db.properties")
@ComponentScan("com.dish.mx.dev")

/**
 * Clase en la que definimos los beans que vamos a cargar al contexto de spring.
 *
 * @version 0.0.1
 *
 * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
 *
 * @since 0.0.1
 *
 */
/**
 *
 * @author gerardo.martinez
 */
public class AppConfig {

    /**
     * El método recoge los datos necesarios para poder realizar la conexión a
     * la base de datos cuando se necesite.
     *
     * @return Regresa un objeto con los certificados para la conexión.
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    @Bean//(destroyMethod = "close")
    DataSource dataSource(Environment env) {
        //Creamos el objeto para poder utilizar sus métodos y conectarnos a la base de datos
        //ComboPooledDataSource ds = new ComboPooledDataSource();
        DriverManagerDataSource ds = new DriverManagerDataSource();
        try {
            //Obtiene la dependencia donde se encuentra el driver para conectarse a la base de datos MySQL
            ds.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        } catch (Exception ex) {
            throw new RuntimeException(
                    "error while setting the driver class name in the datasource", ex);
        }
        //Obtengo los datos de mi archivo properties
        ds.setUrl(env.getRequiredProperty("jdbc.url"));
        ds.setUsername(env.getRequiredProperty("jdbc.username"));
        ds.setPassword(env.getRequiredProperty("jdbc.password"));

        return ds;
    }

    /**
     * Método para poder utilizar la conexión de la base con jdbcTemplate.
     *
     * @param dataSource
     * @return Regresa la instancia jdbcTemplate con los datos obtenidos por el
     * DataSource.
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * Método para poder tener el acceso a los datos de la base
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
    @Bean
    DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Método para poder utilizar la variable jdbcTemplate en mi clase
     * EmpleadoDAO y realizar las acividades necesarias con dicha variable.
     *
     * @param jdbcTemplate
     * @return Regresa una instancia de EmpleadoDAO pasándole como argumento la
     * variable jdbcTemplate para poder interactuar con la base de datos.
     *
     * @author Gerardo Martinez &lt;gerardo.martinez@dish.com.mx&gt;
     *
     * @since 0.0.1
     */
//    @Bean("empleadoDAOImpl2")
//    EmpleadoDAO empleadoDAOImpl(JdbcTemplate jdbcTemplate) {
//        EmpleadoDAOImpl dao = new EmpleadoDAOImpl();
//        dao.setJdbcTemplate(jdbcTemplate);
//        return dao;
//    }
}

