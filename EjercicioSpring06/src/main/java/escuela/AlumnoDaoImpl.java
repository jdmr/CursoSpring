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
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository
public class AlumnoDaoImpl extends JdbcDaoSupport implements AlumnoDao {

    private static final String CREA_TABLA = "CREATE TABLE ALUMNOS("
            + "MATRICULA VARCHAR(32) PRIMARY KEY,"
            + "NOMBRE VARCHAR(32),"
            + "APELLIDO VARCHAR(32))";
    private static final String ELIMINA_TABLA = "DROP TABLE ALUMNOS";
    private static final String LISTA = "SELECT * FROM ALUMNOS";
    private List<Alumno> alumnos;

    @Autowired
    public AlumnoDaoImpl(DataSource dataSource) {
        setDataSource(dataSource);
        inicializa();
    }

    public void inicializa() {
        getJdbcTemplate().update(ELIMINA_TABLA);
        getJdbcTemplate().update(CREA_TABLA);
        getJdbcTemplate().update("INSERT INTO ALUMNOS(MATRICULA, NOMBRE, APELLIDO) "
                + "VALUES('0001','David','Mendoza')");
        getJdbcTemplate().update("INSERT INTO ALUMNOS(MATRICULA, NOMBRE, APELLIDO) "
                + "VALUES('0002','Dulce','Alvarado')");
        
        alumnos = new ArrayList<Alumno>();
        alumnos.add(new Alumno("0001", "David", "Mendoza"));
        alumnos.add(new Alumno("0002", "Dulce", "Alvarado"));
    }

    public List<Alumno> lista() {
        List<Alumno> resultados = getJdbcTemplate().query(LISTA, new RowMapper<Alumno>() {

            public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
                Alumno alumno = new Alumno();
                alumno.setMatricula(rs.getString("MATRICULA"));
                alumno.setNombre(rs.getString("NOMBRE"));
                alumno.setApellido(rs.getString("APELLIDO"));
                return alumno;
            }
        });
        return resultados;
    }

    public Alumno obtiene(String matricula) {
        Alumno resultado = null;
        for (Alumno alumno : alumnos) {
            if (alumno.getMatricula().equals(matricula)) {
                resultado = alumno;
            }
        }
        return resultado;
    }

    public Alumno crea(Alumno alumno) throws AlumnoNuloException {
        if (alumno != null) {
            alumnos.add(alumno);
            return alumno;
        } else {
            throw new AlumnoNuloException();
        }
    }

    public Alumno actualiza(Alumno nuevo) {
        Alumno resultado = null;
        for (Alumno alumno : alumnos) {
            if (alumno.getMatricula().equals(nuevo.getMatricula())) {
                alumno.setNombre(nuevo.getNombre());
                alumno.setApellido(nuevo.getApellido());
                resultado = alumno;
            }
        }
        return resultado;
    }

    public String elimina(String matricula) {
        Alumno resultado = null;
        for (Alumno alumno : alumnos) {
            if (matricula.equals(alumno.getMatricula())) {
                resultado = alumno;
                break;
            }
        }
        if (resultado != null) {
            alumnos.remove(resultado);
        }
        return matricula;
    }
}
