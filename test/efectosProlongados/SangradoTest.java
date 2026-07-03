package efectosProlongados;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import hechizos.Sectumsempra;
import modelo.Auror;
import modelo.Comandante;

public class SangradoTest {
    private Auror auror;
    private Comandante comandante;

    @Before
    public void setUp() {
        auror = new Auror("Harry");
        comandante = new Comandante("Voldemort");
    }
    
    @Test
    public void sangrado() {
        int puntosVida;
        new Sectumsempra().ejecutar(auror, comandante);
        
        puntosVida = comandante.getPuntosVida();
        
        for(int i = 0; i < 4; i++)
        	comandante.procesarEfectos();
        
        assertEquals(comandante.getPuntosVida(), puntosVida - 30);
    }

    @Test
    public void sangradoMultiplosTurnos() {
        int puntosVidaInicial = comandante.getPuntosVida();
        new Sectumsempra().ejecutar(auror, comandante);
        
        for(int i = 0; i < 10; i++)
            comandante.procesarEfectos();
        
        assertEquals(comandante.getPuntosVida(), puntosVidaInicial - 91);
    }

    @Test
    public void sangradoTurnosInfinitosMenoUno() {
        int puntosVidaInicial = comandante.getPuntosVida();
        Sangrado sangrado = new Sangrado(-1, 7);
        comandante.agregarEfectoProlongado(sangrado);
        
        for(int i = 0; i < 20; i++)
            comandante.procesarEfectos();
        
        assertEquals(comandante.getPuntosVida(), 0);
    }

}
