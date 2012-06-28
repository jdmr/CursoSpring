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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository
@Transactional
public class AlumnoDaoJdbc extends JdbcDaoSupport implements AlumnoDao {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoDaoJdbc.class);

    private Map<String, Alumno> alumnos = new TreeMap<>();
    private static final String CREAR_TABLA = "CREATE TABLE ALUMNOS("
            + "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
            + "MATRICULA VARCHAR(32) UNIQUE NOT NULL,"
            + "NOMBRE VARCHAR(32) NOT NULL,"
            + "APELLIDO VARCHAR(32) NOT NULL,"
            + "CONSTRAINT ALUMNOS_PK PRIMARY KEY(ID)"
            + ")";
    private static final String ELIMINA_TABLA = "DROP TABLE ALUMNOS";
    private static final String CREAR_ALUMNO = "INSERT INTO ALUMNOS(MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String ACTUALIZAR_ALUMNO = "UPDATE ALUMNOS SET NOMBRE = ?, APELLIDO = ? WHERE ID = ?";
    private static final String OBTIENE_ALUMNO = "SELECT ID, MATRICULA, NOMBRE, APELLIDO FROM ALUMNOS WHERE ID = ?";
    private static final String OBTIENE_ALUMNO_X_MATRICULA = "SELECT ID, MATRICULA, NOMBRE, APELLIDO FROM ALUMNOS WHERE MATRICULA = ?";
    private static final String OBTIENE_ALUMNOS = "SELECT * FROM ALUMNOS";
    private static final String ELIMINA_ALUMNO = "DELETE FROM ALUMNOS WHERE ID = ?";
    private static final String ELIMINA_ALUMNO_X_MATRICULA = "DELETE FROM ALUMNOS WHERE MATRICULA = ?";

    @Autowired
    public AlumnoDaoJdbc(DataSource dataSource) {
        log.info("Se ha creado una nueva instancia de AlumnoDaoJdbc");
        setDataSource(dataSource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> lista() {
        log.debug("Regresando lista de alumnos");
        return getJdbcTemplate().query(OBTIENE_ALUMNOS, new AlumnoMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno obtiene(final String matricula) {
        log.debug("Obteniendo alumno por matricula: {}", matricula);
        return getJdbcTemplate().queryForObject(OBTIENE_ALUMNO_X_MATRICULA, new Object[]{matricula}, new AlumnoMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno obtiene(final Long id) {
        log.debug("Obteniendo alumno por id: {}", id);
        return getJdbcTemplate().queryForObject(OBTIENE_ALUMNO, new Object[]{id}, new AlumnoMapper());
    }

    @Override
    public Alumno crea(final Alumno alumno) {
        log.debug("Creando alumno: {}", alumno);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        CREAR_ALUMNO, new String[]{"id"});
                ps.setString(1, alumno.getMatricula());
                ps.setString(2, alumno.getNombre());
                ps.setString(3, alumno.getApellido());
                return ps;
            }
        };

        getJdbcTemplate().update(psCreator, keyHolder);
        alumno.setId(keyHolder.getKey().longValue());
        return alumno;
    }

    @Override
    public Alumno actualiza(Alumno alumno) {
        log.debug("Actualizando alumno: {}", alumno);
        getJdbcTemplate().update(ACTUALIZAR_ALUMNO, alumno.getNombre(), alumno.getApellido(), alumno.getId());
        return alumno;
    }

    @Override
    public void elimina(String matricula) {
        log.debug("Eliminando alumno por matricula {}", matricula);
        getJdbcTemplate().update(ELIMINA_ALUMNO_X_MATRICULA, matricula);
    }

    @Override
    public void elimina(Long id) {
        log.debug("Eliminando alumno por id {}", id);
        getJdbcTemplate().update(ELIMINA_ALUMNO, id);
    }

    @Override
    public void inicializa() {
        log.debug("Inicializando ALUMNOS");
        getJdbcTemplate().update(ELIMINA_TABLA);
        getJdbcTemplate().update(CREAR_TABLA);
    }
}

class AlumnoMapper implements RowMapper<Alumno> {

    @Override
    public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
        Alumno alumno = new Alumno();
        alumno.setId(rs.getLong("ID"));
        alumno.setMatricula("MATRICULA");
        alumno.setNombre(rs.getString("NOMBRE"));
        alumno.setApellido(rs.getString("APELLIDO"));
        return alumno;
    }
}