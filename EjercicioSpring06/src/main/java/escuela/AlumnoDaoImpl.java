/*
 * The MIT License
 *
 * Copyright 2012 Universidad de Montemorelos A. C.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package escuela;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository
public class AlumnoDaoImpl extends JdbcDaoSupport implements AlumnoDao {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoDaoImpl.class);

    private static final String CREA_TABLA = "CREATE TABLE ALUMNOS("
            + "MATRICULA VARCHAR(32) PRIMARY KEY,"
            + "NOMBRE VARCHAR(32),"
            + "APELLIDO VARCHAR(32))";
    private static final String ELIMINA_TABLA = "DROP TABLE ALUMNOS";
    private static final String LISTA = "SELECT * FROM ALUMNOS";
    private static final String OBTIENE = "SELECT * FROM ALUMNOS WHERE MATRICULA = ?";
    private static final String CREA = "INSERT INTO ALUMNOS(MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String ACTUALIZA = "UPDATE ALUMNOS SET NOMBRE = ?, APELLIDO = ? WHERE MATRICULA = ?";
    private static final String ELIMINA = "DELETE FROM ALUMNOS WHERE MATRICULA = ?";

    @Autowired
    public AlumnoDaoImpl(DataSource dataSource) {
        log.info("Nueva instancia de AlumnoDao creada.");
        setDataSource(dataSource);
        inicializa();
    }

    public void inicializa() {
        log.debug("Inicializando tablas");
        getJdbcTemplate().update(ELIMINA_TABLA);
        getJdbcTemplate().update(CREA_TABLA);
        getJdbcTemplate().update("INSERT INTO ALUMNOS(MATRICULA, NOMBRE, APELLIDO) "
                + "VALUES('0001','David','Mendoza')");
        getJdbcTemplate().update("INSERT INTO ALUMNOS(MATRICULA, NOMBRE, APELLIDO) "
                + "VALUES('0002','Dulce','Alvarado')");

    }

    public List<Alumno> lista() {
        log.debug("Buscando lista de alumnos");
        List<Alumno> resultados = getJdbcTemplate().query(LISTA, new AlumnoMapper());
        return resultados;
    }

    public Alumno obtiene(String matricula) {
        log.debug("Obtiene el alumno {}", matricula);
        Alumno resultado = null;

        try {
            resultado = getJdbcTemplate().queryForObject(OBTIENE, new AlumnoMapper(), matricula);

            return resultado;
        } catch(EmptyResultDataAccessException e) {
            log.error("No encontre al alumno con la matricula "+matricula, e);
            return null;
        }
    }

    public Alumno crea(Alumno alumno) throws AlumnoNuloException {
        log.debug("Creando alumno {}", alumno);
        if (alumno != null) {
            getJdbcTemplate().update(CREA, alumno.getMatricula(), alumno.getNombre(), alumno.getApellido());
            return alumno;
        } else {
            throw new AlumnoNuloException();
        }
    }

    public Alumno actualiza(Alumno alumno) {
        log.debug("Actualizando al alumno {}", alumno);
        getJdbcTemplate().update(ACTUALIZA, alumno.getNombre(), alumno.getApellido(), alumno.getMatricula());
        return alumno;
    }

    public String elimina(String matricula) {
        log.debug("Eliminando al alumno {}", matricula);
        getJdbcTemplate().update(ELIMINA, matricula);
        return matricula;
    }
}

class AlumnoMapper implements RowMapper<Alumno> {

    public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
        Alumno alumno = new Alumno();
        alumno.setMatricula(rs.getString("MATRICULA"));
        alumno.setNombre(rs.getString("NOMBRE"));
        alumno.setApellido(rs.getString("APELLIDO"));
        return alumno;
    }
}
