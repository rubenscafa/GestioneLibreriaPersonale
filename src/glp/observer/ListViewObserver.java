package glp.observer;

import glp.model.*;
import javafx.application.Platform;
import javafx.scene.control.ListView;

public class ListViewObserver implements LibroObserver {
    private ListView<String> listaLibri;
    private CatalogoLibri catalogo;

    public ListViewObserver(ListView<String> listaLibri, CatalogoLibri catalogo) {
        this.listaLibri = listaLibri;
        this.catalogo = catalogo;
    }

    @Override
    public void aggiorna() {
        Platform.runLater(() -> {
            listaLibri.getItems().clear();
            for (Libro l : catalogo.getLibri()) {
                listaLibri.getItems().add(
                    l.getTitolo() + " - " + l.getAutore() + " (" +
                    l.getISBN() + ") " + l.getGenere() + " - " +
                    l.getStatus() + " - " + l.getValutazione()
                );
            }
            System.out.println("[UI] Lista aggiornata.");
        });
    }
}
