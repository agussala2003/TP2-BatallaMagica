package modelo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import efectosProlongados.Sangrado;
import hechizos.Expelliarmus;

public class PersonajeTest {

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
    public void personajeNuevoEstaVivo() {
        assertTrue(auror.estaVivo());
    }

    @Test
    public void personajeConVidaCeroNoEstaVivo() {
        auror.recibirDanio(9999);
        assertFalse(auror.estaVivo());
    }

    @Test
    public void vidaNoBaraDeNegativo() {
        auror.recibirDanio(9999);
        assertEquals(0, auror.getPuntosVida());
    }

    @Test
    public void recibirDanioReduceVida() {
        int vidaInicial = auror.getPuntosVida();
        auror.recibirDanio(20);
        assertTrue(auror.getPuntosVida() < vidaInicial);
    }

    @Test
    public void defensaAbsorbeParteDanio() {
        int vidaInicial = auror.getPuntosVida();
        System.out.println("Hola: " + vidaInicial);
        auror.aumentarDefensa(30);
        auror.recibirDanio(10);
        System.out.println("Hola: " + auror.getPuntosVida());
        // defensa (30) > daño (10) → no recibe nada
        assertEquals(vidaInicial, auror.getPuntosVida());
    }

    @Test
    public void defensaSeResetearDespuésDeRecibirDanio() {
        auror.aumentarDefensa(15);
        auror.recibirDanio(5);
        assertEquals(0, auror.getDefensa());
    }

    @Test
    public void curacionAumentaVida() {
        auror.recibirDanio(30);
        int vidaAntes = auror.getPuntosVida();
        auror.curar(20);
        assertTrue(auror.getPuntosVida() > vidaAntes);
    }

    @Test
    public void curacionNoSuperaVidaMaxima() {
        int vidaMaxima = auror.getPuntosVida();
        auror.curar(9999);
        assertEquals(vidaMaxima, auror.getPuntosVida());
    }

    @Test
    public void sangradoReduceVidaEnProcesarEfectos() {
        int vidaAntes = auror.getPuntosVida();
        auror.agregarEfectoProlongado(new Sangrado(10, 10));
        auror.procesarEfectos();
        assertEquals(vidaAntes - 10, auror.getPuntosVida());
    }

    @Test
    public void magoModificaCuracionMejorQueMortifago() {
        int curacionMago = auror.modificarCuracion(20);
        int curacionMortifago = comandante.modificarCuracion(20);
        assertTrue(curacionMago > curacionMortifago);
    }

    @Test
    public void mortifagoModificaDanioMejorQueMago() {
        int danioMortifago = comandante.modificarDanioAtaque(20);
        int danioMago = estudiante.modificarDanioAtaque(20);
        assertTrue(danioMortifago > danioMago);
    }

    @Test
    public void personajePuedeAgregarHechizos() {
        auror.agregarHechizo(new Expelliarmus());
        assertEquals(1, auror.getHechizos().size());
    }

    @Test
    public void personajeMuertoNoPuedeLanzarHechizos() {
        Auror objetivo = new Auror("Objetivo");
        auror.recibirDanio(9999);
        // no debe lanzar excepción, solo imprime que está eliminado
        auror.lanzarHechizo(new Expelliarmus(), objetivo);
        // el objetivo no debería haber recibido daño
        assertTrue(objetivo.estaVivo());
    }
}
