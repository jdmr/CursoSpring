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

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository
public class AlumnoDaoImpl implements AlumnoDao {

    private List<Alumno> alumnos;

    public AlumnoDaoImpl() {
        inicializa();
    }

    public void inicializa() {
        alumnos = new ArrayList<Alumno>();
        alumnos.add(new Alumno("0001", "David", "Mendoza"));
        alumnos.add(new Alumno("0002", "Dulce", "Alvarado"));
    }

    public List<Alumno> lista() {
        return alumnos;
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
