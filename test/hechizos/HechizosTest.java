package hechizos;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import modelo.Auror;
import modelo.Comandante;
import modelo.Estudiante;

public class HechizosTest {

    private Auror auror;
    private Estudiante estudiante;
    private Comandante comandante;

    @Before
    public void setUp() {
        auror = new Auror("Harry");
        estudiante = new Estudiante("Ron");
        comandante = new Comandante("Voldemort");
    }

    // --- Expelliarmus ---

    @Test
    public void expelliarmusHaceDanio() {
        int vidaInicial = comandante.getPuntosVida();
        new Expelliarmus().ejecutar(auror, comandante);
        assertTrue(comandante.getPuntosVida() < vidaInicial);
    }

    @Test
    public void expelliarmusMortifagoHaceMasDanioQueMago() {
        Auror objetivo1 = new Auror("Obj1");
        Auror objetivo2 = new Auror("Obj2");

        new Expelliarmus().ejecutar(comandante, objetivo1);
        new Expelliarmus().ejecutar(estudiante, objetivo2);

        assertTrue(objetivo1.getPuntosVida() < objetivo2.getPuntosVida());
    }

    // --- Avada Kedavra ---

    @Test
    public void avadaKedavraHaceDanio() {
        int vidaInicial = auror.getPuntosVida();
        new AvadaKedavra().ejecutar(comandante, auror);
        assertTrue(auror.getPuntosVida() < vidaInicial);
    }

    @Test
    public void avadaKedavraHaceMasDanioQueExpelliarmus() {
        Auror objetivo1 = new Auror("Obj1");
        Auror objetivo2 = new Auror("Obj2");

        new AvadaKedavra().ejecutar(comandante, objetivo1);
        new Expelliarmus().ejecutar(comandante, objetivo2);

        assertTrue(objetivo1.getPuntosVida() < objetivo2.getPuntosVida());
    }

    // --- Expecto Patronum ---

    @Test
    public void expectoPatronumCuraAlLanzador() {
        auror.recibirDanio(50);
        int vidaAntes = auror.getPuntosVida();
        new ExpectoPatronum().ejecutar(auror, comandante);
        assertTrue(auror.getPuntosVida() > vidaAntes);
    }

    @Test
    public void expectoPatronumMagosCuraMasQueMortifago() {
        auror.recibirDanio(80);
        comandante.recibirDanio(80);

        int vidaMagoAntes = auror.getPuntosVida();
        int vidaMortifagoAntes = comandante.getPuntosVida();

        new ExpectoPatronum().ejecutar(auror, comandante);
        new ExpectoPatronum().ejecutar(comandante, auror);

        int curacionMago = auror.getPuntosVida() - vidaMagoAntes;
        int curacionMortifago = comandante.getPuntosVida() - vidaMortifagoAntes;

        assertTrue(curacionMago > curacionMortifago);
    }

    @Test
    public void expectoPatronumNoAfectaAlObjetivo() {
        int vidaObjetivoAntes = comandante.getPuntosVida();
        new ExpectoPatronum().ejecutar(auror, comandante);
        assertEquals(vidaObjetivoAntes, comandante.getPuntosVida());
    }

    // --- Protego ---

    @Test
    public void protegoAumentaDefensa() {
        assertEquals(0, auror.getDefensa());
        new Protego().ejecutar(auror, comandante);
        assertTrue(auror.getDefensa() > 0);
    }

    @Test
    public void protegoReduceDanioRecibido() {
        Auror sinProtego = new Auror("SinProtego");
        Auror conProtego = new Auror("ConProtego");

        new Protego().ejecutar(conProtego, comandante);

        new Expelliarmus().ejecutar(comandante, sinProtego);
        new Expelliarmus().ejecutar(comandante, conProtego);

        assertTrue(conProtego.getPuntosVida() >= sinProtego.getPuntosVida());
    }

    // --- Sectumsempra ---

    @Test
    public void sectumsempraHaceDanio() {
        int vidaInicial = auror.getPuntosVida();
        new Sectumsempra().ejecutar(comandante, auror);
        assertTrue(auror.getPuntosVida() < vidaInicial);
    }

    @Test
    public void sectumsempraAplicaSangrado() {
        new Sectumsempra().ejecutar(comandante, auror);
        int vidaTrasDanio = auror.getPuntosVida();

        auror.procesarEfectos();
        assertTrue(auror.getPuntosVida() < vidaTrasDanio);
    }
}
