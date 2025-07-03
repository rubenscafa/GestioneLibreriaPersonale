package glp.facade;

import java.io.File;
import java.util.*;
import java.io.*;
import glp.command.*;
import glp.model.*;
import glp.storage.*;
import glp.strategy.OrdinamentoImpl;

public class LibreriaFacade {
    private CatalogoLibri catalogo;
    private CommandManager commManager;
    private StorageTM storage;
    private OrdinamentoImpl ordinamento;

    public LibreriaFacade(CatalogoLibri catalogo, CommandManager commManager,OrdinamentoImpl ordinamento) {
        this.catalogo = catalogo;
        this.commManager = commManager;
        this.storage = new StorageTM(); 
        this.ordinamento = ordinamento;
    }

    public void aggiuntaLibro(Libro l) {
        Command cmd = new aggiungiCommand(catalogo, l);
        commManager.eseguiCommand(cmd);
        catalogo.notificaObserver();
    }

    public void modificaLibro(Libro l1, Libro l2) {
        Command cmd = new modificaCommand(catalogo, l1, l2);
        commManager.eseguiCommand(cmd);
        catalogo.notificaObserver();
    }

    public void rimozioneLibro(Libro l) {
        Command cmd = new rimuoviCommand(catalogo, l);
        commManager.eseguiCommand(cmd);
        catalogo.notificaObserver();
    }

    public List<Libro> listaLibri() {
        return catalogo.getLibri();
    }
    public List<Libro> ordinaLibri(List<Libro>libri) {
    	if(ordinamento != null) {
    		ordinamento.ordina(libri);
    	}
    	return libri;
    }
    public void undo() {
        commManager.undo();
        catalogo.notificaObserver();  // per aggiornare la UI dopo l'undo
    }



    public CatalogoLibri getCatalogo() {
        return this.catalogo;
    }

    public void salva(File file) {
        storage.creazioneCSV(catalogo.getLibri(), file);
    }

    public void carica(File file) {
        List<Libro> libriCSV = storage.caricamentoCSV(file);
        catalogo.setLibri(libriCSV);
        catalogo.notificaObserver();
    }
    private void caricaCatalogoIniziale() {
        File configFile = new File("catalogo.csv");

        if (configFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                String percorso = reader.readLine();
                if (percorso != null && !percorso.isBlank()) {
                    File fileCatalogo = new File(percorso);
                    if (fileCatalogo.exists()) {
                        List<Libro> libri = storage.caricamentoCSV(fileCatalogo);
                        catalogo.setLibri(libri);
                        catalogo.notificaObserver();
                        System.out.println("Catalogo caricato automaticamente da: " + percorso);
                    } else {
                        System.out.println("File catalogo indicato in config.txt non trovato.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Errore nella lettura di config.txt");
                e.printStackTrace();
            }
        } else {
            System.out.println("config.txt non trovato, nessun catalogo caricato all'avvio.");
        }
    }
}
