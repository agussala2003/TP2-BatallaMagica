package efectosProlongados;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import hechizos.AvadaKedavra;
import hechizos.Protego;
import modelo.Auror;
import modelo.Comandante;
import modelo.Estudiante;

public class RegeneracionTest {
    private Auror auror;
    private Estudiante estudiante;
    private Comandante comandante;
    
    @Before
    public void setUp() {
        auror = new Auror("Harry");
        estudiante = new Estudiante("Ron");
        comandante = new Comandante("Voldemort");
    }
    
    @Test
    public void regeneracion() {
        int puntosVida;
        new AvadaKedavra().ejecutar(comandante, estudiante);
        new Protego().ejecutar(estudiante, comandante);
        
        puntosVida = estudiante.getPuntosVida();
        
        for(int i = 0; i < 4; i++)
        	estudiante.procesarEfectos();
        
        assertEquals(estudiante.getPuntosVida(), puntosVida + 40);
    }
}
