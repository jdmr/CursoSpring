package escuela;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jdmr
 */
@Repository
public class AlumnoDao extends JdbcDaoSupport {

    private static final String CREAR_TABLA = "CREATE TABLE ALUMNOS("
            + "ID BIGINT NOT NULL AUTO_INCREMENT, "
            + "NOMBRE VARCHAR(32), "
            + "APELLIDO VARCHAR(32),"
            + "PRIMARY KEY(ID)"
            + ")";
    private static final String ELIMINA_TABLA = "DROP TABLE IF EXISTS ALUMNOS";
    private static final String CREAR_ALUMNO = "INSERT INTO ALUMNOS(NOMBRE, APELLIDO) VALUES(?,?)";
    private static final String ACTUALIZAR_ALUMNO = "UPDATE ALUMNOS SET NOMBRE = ?, APELLIDO = ? WHERE ID = ?";
    private static final String OBTIENE_ALUMNO = "SELECT ID, NOMBRE, APELLIDO FROM ALUMNOS WHERE ID = ?";

    @Autowired
    public AlumnoDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Alumno creaAlumno(final Alumno alumno) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        CREAR_ALUMNO, new String[]{"id"});
                ps.setString(1, alumno.getNombre());
                ps.setString(2, alumno.getApellido());
                return ps;
            }
        }, keyHolder);
        alumno.setId(keyHolder.getKey().intValue());
        return alumno;
    }

    public Alumno obtieneAlumno(Integer key) {
        RowMapper<Alumno> mapper = new RowMapper<Alumno>() {

            public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
                Alumno alumno = new Alumno();
                alumno.setId(rs.getInt("ID"));
                alumno.setNombre(rs.getString("NOMBRE"));
                alumno.setApellido(rs.getString("APELLIDO"));
                return alumno;
            }
        };
        return getJdbcTemplate().queryForObject(OBTIENE_ALUMNO, new Object[]{key}, mapper);
    }

    public Alumno actualizaAlumno(Alumno alumno) {
        getJdbcTemplate().update(ACTUALIZAR_ALUMNO, alumno.getNombre(), alumno.getApellido(), alumno.getId());
        return alumno;
    }

    public void inicializa() {
        System.out.println("Inicializando...");
        getJdbcTemplate().update(ELIMINA_TABLA);
        getJdbcTemplate().update(CREAR_TABLA);
    }
}
