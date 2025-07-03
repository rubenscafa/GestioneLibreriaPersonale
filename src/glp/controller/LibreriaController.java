package glp.controller;
import glp.command.*;

import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.util.*;
import glp.facade.*;
import glp.model.*;
import glp.utils.*;
public class LibreriaController{
	public LibreriaFacade facade;
	private FiltraggioEVisualizzazione filtraggio;
	private CommandManager commManager;
	private CatalogoLibri catalogo;

	
	public LibreriaController(LibreriaFacade facade, FiltraggioEVisualizzazione filtraggio) {
		this.facade= facade;
		this.filtraggio= filtraggio;
		
	}
	public void aggiungiLibro(String titolo, String autore, String isbn, String genere, String status, int valutazione) {
        Libro libro = new Libro.LibroBuilder()
                .setTitolo(titolo)
                .setAutore(autore)
                .setISBN(isbn)
                .setGenere(genere)
                .setStatus(status)
                .setValutazione(valutazione)
                .build();

        facade.aggiuntaLibro(libro);
    }
	public void rimuoviLibro(Libro l) {
		facade.rimozioneLibro(l);
		System.out.printf("Sto rimuovendo il libro:%s\n", l);
	}
	public void modificaLibro(Libro vecchioLibro, Libro nuovoLibro) {
		facade.modificaLibro(vecchioLibro, nuovoLibro);
		System.out.printf("Sto modificando il libro:%s con il libro:%s\n",vecchioLibro,nuovoLibro);
	}
	public void ordinaLibri(List<Libro>libri) {
		facade.ordinaLibri(libri);
		System.out.println("Ordino i libri per valutazione\n");
	}
	public void salvaCSV (File file) {
		facade.salva(file);
		System.out.println("Catalogo libri salvato con successo");
	}
	private void salvaUltimoCatalogo(String pathCsv) {
	    try (FileWriter writer = new FileWriter("catalogo.csv")) {
	        writer.write(pathCsv);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void caricaCSV (File file) {
		facade.carica(file);
		salvaUltimoCatalogo(file.getAbsolutePath());

	}

	public void undo(Libro attuale, Libro originale) {
		facade.undo();
	}
	public List<Libro> filtroPerAutore(String autore){
		return filtraggio.getLibriByAutore(autore, facade.getCatalogo());
	}
	public List<Libro> filtroPerGenere(String genere){
		return filtraggio.getLibriByGenere(genere, facade.getCatalogo());
	}
	public List<Libro> filtroPerTitolo(String titolo){
		return filtraggio.getLibriByTitolo(titolo, facade.getCatalogo());
	}
	public List<Libro> filtraPerStatus(String status) {
        return filtraggio.getLibriPerStatus(status, facade.getCatalogo());
    }
	
}
