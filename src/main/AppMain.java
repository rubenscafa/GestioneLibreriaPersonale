package main;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import glp.controller.LibreriaController;
import glp.facade.LibreriaFacade;
import glp.utils.FiltraggioEVisualizzazione;
import glp.model.*;
import glp.command.*;
import glp.strategy.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.List;

public class AppMain extends Application {
    private LibreriaController controller;
    private static final String CONFIG_FILE = "catalogo.csv";
    
    private String leggiUltimoCatalogo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    public void start(Stage StagePrimario) throws Exception {
    	ListView<String> listaLibri = new ListView<>();
        CatalogoLibri catalogo = new CatalogoLibri();
        CommandManager commManager = new CommandManager();
        OrdinamentoImpl ordinamento = new OrdinamentoImpl();
        LibreriaFacade facade = new LibreriaFacade(catalogo, commManager,ordinamento);
        FiltraggioEVisualizzazione filtraggio = new FiltraggioEVisualizzazione();
        controller = new LibreriaController(facade, filtraggio);
        String ultimoCatalogo = leggiUltimoCatalogo();
        if (ultimoCatalogo != null) {
            File file = new File(ultimoCatalogo);
            if (file.exists()) {
                controller.caricaCSV(file);
                listaLibri.getItems().clear();
                for (Libro l : controller.facade.getCatalogo().getLibri()) {
                    listaLibri.getItems().add(formattaLibro(l));
                }
            }
        }


        // Componenti UI
        Label titoloLabel = new Label("Titolo:");
        TextField titoloField = new TextField();

        Label autoreLabel = new Label("Autore:");
        TextField autoreField = new TextField();

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();

        Label genereLabel = new Label("Genere:");
        TextField genereField = new TextField();

        Label statusLabel = new Label("Status:");
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("letto", "in lettura", "da leggere");
        statusComboBox.setValue("letto");
        HBox statusBox = new HBox(5, statusLabel, statusComboBox);

        Label valutazioneLabel = new Label("Valutazione (0-5):");
        ComboBox<Integer> valutazioneComboBox = new ComboBox<>();
        valutazioneComboBox.getItems().addAll(0,1, 2, 3, 4, 5);
        valutazioneComboBox.setValue(1);
        HBox valutazioneBox = new HBox(5, valutazioneLabel, valutazioneComboBox);
        
        ComboBox<String> statusBoxFiltro = new ComboBox<>();
    	statusBoxFiltro.getItems().addAll("letto", "da leggere", "in lettura");
    	statusBoxFiltro.setValue("letto");

        Button aggiungiButton = new Button("Aggiungi Libro    ➕");
        Button salvaCSVButton = new Button("Salva 💾");
        Button caricaCSVButton = new Button("Carica file CSV");
        Button rimuoviButton = new Button("Rimuovi Libro 🗑");
        Button modificaButton = new Button("Modifica Libro 🔄");
        Button annullaButton = new Button ("Annulla operazione");
        Button ordinaPerValutazione = new Button ("Ordine per valutazione");

        TextField filtroAutoreField = new TextField();
        filtroAutoreField.setPromptText("Filtra per autore");
        Button filtraAutoreButton = new Button("🔎");
        HBox filtroAutoreBox = new HBox(5, filtroAutoreField, filtraAutoreButton);

        TextField filtroGenereField = new TextField();
        filtroGenereField.setPromptText("Filtra per genere");
        Button filtraGenereButton = new Button("🔎");
        HBox filtroGenereBox = new HBox(5, filtroGenereField, filtraGenereButton);

        TextField filtroTitoloField = new TextField();
        filtroTitoloField.setPromptText("Filtra per titolo");
        Button filtraTitoloButton = new Button("🔎");
        HBox filtroTitoloBox = new HBox(5, filtroTitoloField, filtraTitoloButton);

        TextField filtroStatusField = new TextField();
        filtroStatusField.setPromptText("Filtra per status");
        Button filtraStatusButton = new Button("🔎");
        HBox filtroStatusBox = new HBox(5,statusBoxFiltro , filtraStatusButton);

        Button mostraTuttiButton = new Button("Mostra Tutti");

        // aggiungi libro 
        aggiungiButton.setOnAction(e -> {
            try {
                String titolo = titoloField.getText();
                String autore = autoreField.getText();
                String isbn = isbnField.getText();
                String genere = genereField.getText();
                String status = statusComboBox.getValue();
                int valutazione = valutazioneComboBox.getValue();

                controller.aggiungiLibro(titolo, autore, isbn, genere, status, valutazione);

                listaLibri.getItems().add(titolo + " - " + autore + " (" + isbn + ") " + genere + " - " + status + " - " + valutazione);

                titoloField.clear();
                autoreField.clear();
                isbnField.clear();
                genereField.clear();
                statusComboBox.setValue("letto"); // reset valore di default
                valutazioneComboBox.setValue(1);  // reset valore di default
            } catch (Exception ex) {
                System.out.println("Errore nell'inserimento: " + ex.getMessage());
            }
        });

        // Rimozione libro selezionato
        rimuoviButton.setOnAction(e -> {
            int index = listaLibri.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                Libro libro = controller.facade.getCatalogo().getLibri().get(index);
                controller.rimuoviLibro(libro);
                listaLibri.getItems().remove(index);
            }
        });

        // Modifica libro selezionato
        modificaButton.setOnAction(e -> {
            int index = listaLibri.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                Libro vecchio = controller.facade.getCatalogo().getLibri().get(index);
                try {
                    Libro nuovo = new Libro.LibroBuilder()
                            .setTitolo(titoloField.getText())
                            .setAutore(autoreField.getText())
                            .setISBN(isbnField.getText())
                            .setGenere(genereField.getText())
                            .setStatus(statusComboBox.getValue())
                            .setValutazione(valutazioneComboBox.getValue())
                            .build();

                    controller.modificaLibro(vecchio, nuovo);
                    listaLibri.getItems().set(index, nuovo.getTitolo() + " - " + nuovo.getAutore() + " (" + nuovo.getISBN() + ") " + nuovo.getGenere() + " - " + nuovo.getStatus() + " - " + nuovo.getValutazione());
                } catch (Exception ex) {
                    System.out.println("Errore nella modifica: " + ex.getMessage());
                }
            }
        });

        // salvataggio e caricamento catalogo 
        salvaCSVButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salva Catalogo");
            fileChooser.setInitialFileName("catalogo.csv");
            File file = fileChooser.showSaveDialog(StagePrimario);
            if (file != null) {
                try {
                    controller.salvaCSV(file);
                    System.out.println("Salvato in " + file.getAbsolutePath());
                } catch (Exception ex) {
                    System.out.println("Errore nel salvataggio: " + ex.getMessage());
                }
            }
        });

        caricaCSVButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Carica Catalogo");
            File file = fileChooser.showOpenDialog(StagePrimario);
            if (file != null) {
                try {
                    controller.caricaCSV(file);
                    // Dopo caricamento aggiorna lista
                    mostraTuttiButton.fire();
                } catch (Exception ex) {
                    System.out.println("Errore nel caricamento: " + ex.getMessage());
                }
            }
        });
        
        

        // operazioni di filtraggio e ordinamento
        filtraAutoreButton.setOnAction(e -> {
            String autore = filtroAutoreField.getText();
            List<Libro> filtrati = controller.filtroPerAutore(autore);
            listaLibri.getItems().clear();
            for (Libro l : filtrati) {
                listaLibri.getItems().add(formattaLibro(l));
            }
        });

        filtraGenereButton.setOnAction(e -> {
            String genere = filtroGenereField.getText();
            List<Libro> filtrati = controller.filtroPerGenere(genere);
            listaLibri.getItems().clear();
            for (Libro l : filtrati) {
                listaLibri.getItems().add(formattaLibro(l));
            }
        });

        filtraTitoloButton.setOnAction(e -> {
            String titolo = filtroTitoloField.getText();
            List<Libro> filtrati = controller.filtroPerTitolo(titolo);
            listaLibri.getItems().clear();
            for (Libro l : filtrati) {
                listaLibri.getItems().add(formattaLibro(l));
            }
        });

        filtraStatusButton.setOnAction(e -> {
            String status = statusBoxFiltro.getValue();
            List<Libro> filtrati = controller.filtraPerStatus(status);
            listaLibri.getItems().clear();
            for (Libro l : filtrati) {
                listaLibri.getItems().add(formattaLibro(l));
            }
        });
        ordinaPerValutazione.setOnAction(e -> {
            List<Libro> libri = facade.listaLibri();
            controller.ordinaLibri(libri);
            listaLibri.getItems().clear();
            for (Libro l : libri) {
                listaLibri.getItems().add(formattaLibro(l));
            }
        });


        mostraTuttiButton.setOnAction(e -> {
            listaLibri.getItems().clear();
            for (Libro l : controller.facade.getCatalogo().getLibri()) {
                listaLibri.getItems().add(formattaLibro(l));
            }
        });

        // Layout form principale
        VBox form = new VBox(15,
                titoloLabel, titoloField,
                autoreLabel, autoreField,
                isbnLabel, isbnField,
                genereLabel, genereField,
                statusBox,valutazioneBox,
                aggiungiButton, rimuoviButton, modificaButton
        );
        form.setPadding(new Insets(0, 0, 0, 20)); // sopra, destra, sotto, sinitra
        VBox form2 = new VBox(15,
                salvaCSVButton,
                caricaCSVButton,
                new Label("Filtra per autore:"),filtroAutoreBox,
                new Label("Filtra per genere:"),filtroGenereBox,
                new Label("Filtra per titolo:"),filtroTitoloBox,
                new Label("Filtra per status:"),filtroStatusBox,
                ordinaPerValutazione,
                mostraTuttiButton
        );
        form2.setPadding(new Insets(0, 20, 0,0));

        HBox mainLayout = new HBox(10, form, listaLibri, form2);
        mainLayout.setPrefSize(1140, 720);
        listaLibri.setPrefWidth(600);

        Scene scene = new Scene(mainLayout);

        StagePrimario.setTitle("Libreria - JavaFX");
        StagePrimario.setScene(scene);
        StagePrimario.show();

        mostraTuttiButton.fire(); // mostra tutti all'avvio
    }

    private String formattaLibro(Libro libro) {
        return libro.getTitolo() + " - " + libro.getAutore() + " (" + libro.getISBN() + ") " + libro.getGenere() + " - " + libro.getStatus() + " - " + libro.getValutazione();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
