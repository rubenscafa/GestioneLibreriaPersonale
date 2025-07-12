package glp.test;

import glp.controller.LibreriaController;
import glp.facade.LibreriaFacade;
import glp.model.*;
import glp.strategy.*;
import glp.utils.FiltraggioEVisualizzazione;
import glp.command.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestLibreria {

    private LibreriaController controller;
    private CatalogoLibri catalogo;

    @BeforeEach
    void setup() {
        catalogo = new CatalogoLibri();
        CommandManager cmdManager = new CommandManager();
        OrdinamentoImpl ordinamento = new OrdinamentoImpl();
        LibreriaFacade facade = new LibreriaFacade(catalogo, cmdManager, ordinamento);
        FiltraggioEVisualizzazione filtro = new FiltraggioEVisualizzazione();
        controller = new LibreriaController(facade, filtro);
    }

    @Test
    void testAggiuntaLibro() {
        controller.aggiungiLibro("Titolo", "Autore", "ISBN123", "Fantasy", "letto", 4);
        assertEquals(1, controller.facade.getCatalogo().getLibri().size());
    }

    @Test
    void testRimozioneLibro() {
        Libro libro = creaLibroBase("ISBN123");
        controller.facade.getCatalogo().aggiungiLibro(libro);
        controller.rimuoviLibro(libro);
        assertTrue(controller.facade.getCatalogo().getLibri().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Fantasy", "Horror", "Giallo"})
    void testFiltroPerGenere(String genere) {
        controller.aggiungiLibro("Titolo", "Autore", "ISBN123", genere, "letto", 3);
        List<Libro> risultati = controller.filtroPerStringa(genere);
        assertFalse(risultati.isEmpty());
        assertEquals(genere.toLowerCase(), risultati.get(0).getGenere().toLowerCase());
    }

    @RepeatedTest(3)
    void testModificaRipetuta() {
        Libro l = creaLibroBase("ISBN999");
        controller.facade.getCatalogo().aggiungiLibro(l);

        Libro nuovo = new Libro.LibroBuilder()
                .setTitolo("Modificato")
                .setAutore("Autore")
                .setISBN("ISBN999")
                .setGenere("Fantasy")
                .setStatus("letto")
                .setValutazione(5)
                .build();

        controller.modificaLibro(l, nuovo);
        assertEquals("Modificato", controller.facade.getCatalogo().getLibri().get(0).getTitolo());
    }

    @TestFactory
    Stream<DynamicTest> testFiltraggioTitoloDinamico() {
        controller.aggiungiLibro("Il Re Leone", "Autore", "I1", "Animazione", "letto", 4);
        controller.aggiungiLibro("Il Ritorno del Re", "Autore", "I2", "Fantasy", "letto", 5);

        return Stream.of("Il","Re", "Leone")
                .map(parola -> DynamicTest.dynamicTest("Filtro per: " + parola, () -> {
                    List<Libro> risultati = controller.filtroPerStringa(parola);
                    assertTrue(risultati.stream().anyMatch(l -> l.getTitolo().contains(parola)));
                }));
    }

    private Libro creaLibroBase(String isbn) {
        return new Libro.LibroBuilder()
                .setTitolo("Titolo")
                .setAutore("Autore")
                .setISBN(isbn)
                .setGenere("Fantasy")
                .setStatus("letto")
                .setValutazione(4)
                .build();
    }
}