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
	public void salvaUltimoCatalogo(String pathCsv) {
	    try (FileWriter writer = new FileWriter("pathCatalogo.txt")) {
	        writer.write(pathCsv);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void caricaCSV (File file) {
		facade.carica(file);
		salvaUltimoCatalogo(file.getAbsolutePath());

	}

	public List<Libro> filtraPerStatus(String status) {
	    return facade.filtroPerStatus(status);
	}

	public List<Libro> filtroPerStringa(String stringa) {
	    return facade.filtroPerStringa(stringa);
	}
	
}
