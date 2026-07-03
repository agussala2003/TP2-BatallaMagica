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

    @Test
    public void regeneracionMultiplosTurnos() {
        int puntosVidaInicial = estudiante.getPuntosVida();
        new AvadaKedavra().ejecutar(comandante, estudiante);
        new Protego().ejecutar(estudiante, comandante);
        
        for(int i = 0; i < 4; i++)
            estudiante.procesarEfectos();
        
        // Debe dejar de regenerar después de 4 turnos (solo 40 total)
        assertEquals(estudiante.getPuntosVida(), puntosVidaInicial - 39);
    }

    @Test
    public void regeneracionNoExcedeVidaMaxima() {
        estudiante.agregarPuntosVida(20); // Casi con vida máxima
        int vidaMaxima = estudiante.getVidaMaxima();
        
        Regeneracion regeneracion = new Regeneracion(5, 100); // Intenta regenerar mucho
        estudiante.agregarEfectoProlongado(regeneracion);
        
        for(int i = 0; i < 5; i++)
            estudiante.procesarEfectos();
        
        assertEquals(estudiante.getPuntosVida(), vidaMaxima);
    }

    @Test
    public void regeneracionTurnosInfinitos() {
        int puntosVidaInicial = estudiante.getPuntosVida();
        estudiante.quitarPuntosVida(60);
        Regeneracion regeneracion = new Regeneracion(-1, 5);
        estudiante.agregarEfectoProlongado(regeneracion);
        
        for(int i = 0; i < 10; i++)
            estudiante.procesarEfectos();
        
        assertEquals(estudiante.getPuntosVida(), puntosVidaInicial - 10);
    }

    @Test
    public void sangradoYRegeneracionJuntos() {
        int puntosVidaInicial = estudiante.getPuntosVida();
        Sangrado sangrado = new Sangrado(3, 8);
        Regeneracion regeneracion = new Regeneracion(3, 5);
        
        estudiante.agregarEfectoProlongado(sangrado);
        estudiante.agregarEfectoProlongado(regeneracion);
        
        for(int i = 0; i < 3; i++)
            estudiante.procesarEfectos();
        
        // Net effect: -3 por turno durante 3 turnos = -9
        assertEquals(estudiante.getPuntosVida(), puntosVidaInicial - 9);
    }
}
