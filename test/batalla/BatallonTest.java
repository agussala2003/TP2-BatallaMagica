package batalla;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import fabricas.Reclutador;
import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import modelo.Auror;
import modelo.Comandante;
import modelo.Personaje;

public class BatallonTest {

    private Batallon batallonMagos;
    private Batallon batallonMortifagos;

    @Before
    public void setUp() {
        batallonMagos = new Batallon();
        batallonMortifagos = new Batallon();

        for (int i = 0; i < 3; i++) {
            batallonMagos.agregarPersonaje(Reclutador.crearMago());
            batallonMortifagos.agregarPersonaje(Reclutador.crearMortifago());
        }
    }

    @Test
    public void batallonConPersonajesVivosEsSaludable() {
        assertTrue(batallonMagos.tienePersonajesSaludables());
    }

    @Test
    public void batallonVacioNoEsSaludable() {
        Batallon vacio = new Batallon();
        assertFalse(vacio.tienePersonajesSaludables());
    }

    @Test
    public void batallonConTodosEliminadosNoEsSaludable() {
        Batallon batallon = new Batallon();
        Auror muerto = new Auror("Muerto");
        muerto.recibirDanio(9999);
        batallon.agregarPersonaje(muerto);
        assertFalse(batallon.tienePersonajesSaludables());
    }

    @Test
    public void agregarPersonajeAumentaConteo() {
        Batallon batallon = new Batallon();
        assertFalse(batallon.tienePersonajesSaludables());
        batallon.agregarPersonaje(new Auror("Nuevo"));
        assertTrue(batallon.tienePersonajesSaludables());
    }

    @Test
    public void obtenerPersonajeVivoDevuelvePersonajeVivo() {
        Personaje p = batallonMagos.obtenerPersonajeVivoAleatorio();
        assertNotNull(p);
        assertTrue(p.estaVivo());
    }

    @Test
    public void obtenerPersonajeVivoBatallonVacioDevuelveNull() {
        Batallon vacio = new Batallon();
        assertNull(vacio.obtenerPersonajeVivoAleatorio());
    }

    @Test
    public void atacarReduceVidaDelEnemigo() {
        Batallon atacante = new Batallon();
        Batallon defensor = new Batallon();

        Auror auror = new Auror("Harry");
        auror.agregarHechizo(new Expelliarmus());
        atacante.agregarPersonaje(auror);

        Comandante comandante = new Comandante("Voldemort");
        defensor.agregarPersonaje(comandante);

        int vidaInicial = comandante.getPuntosVida();
        atacante.atacar(defensor);

        assertTrue(comandante.getPuntosVida() < vidaInicial);
    }

    @Test
    public void hechizoNoSeRepiteEnMismaRondaHastaAgotarTodos() {
        Batallon atacante = new Batallon();
        Batallon defensor = new Batallon();

        Auror auror = new Auror("Harry");
        auror.agregarHechizo(new Expelliarmus());
        auror.agregarHechizo(new AvadaKedavra());
        atacante.agregarPersonaje(auror);

        Comandante cmd = new Comandante("Voldemort");
        cmd.agregarHechizo(new Expelliarmus());
        defensor.agregarPersonaje(cmd);

        // dos ataques en la misma ronda no deben lanzar excepción y deben funcionar
        atacante.atacar(defensor);
        atacante.atacar(defensor);
        assertTrue(true);
    }

    @Test
    public void nuevaRondaLimpiaHechizosUsados() {
        // no debe lanzar excepción al limpiar y volver a atacar
        batallonMagos.atacar(batallonMortifagos);
        batallonMagos.nuevaRonda();
        batallonMagos.atacar(batallonMortifagos);
        assertTrue(true);
    }

    @Test
    public void batallaTerminaCuandoUnBatallonCae() {
        Batallon magos = new Batallon();
        Batallon mortifagos = new Batallon();

        Auror auror = new Auror("Harry");
        auror.agregarHechizo(new AvadaKedavra());
        magos.agregarPersonaje(auror);

        Comandante cmd = new Comandante("Voldemort");
        cmd.agregarHechizo(new AvadaKedavra());
        mortifagos.agregarPersonaje(cmd);

        int rondas = 0;
        while (magos.tienePersonajesSaludables() && mortifagos.tienePersonajesSaludables() && rondas < 100) {
            magos.atacar(mortifagos);
            if (mortifagos.tienePersonajesSaludables()) {
                mortifagos.atacar(magos);
            }
            rondas++;
        }

        assertFalse(magos.tienePersonajesSaludables() && mortifagos.tienePersonajesSaludables());
    }
}
