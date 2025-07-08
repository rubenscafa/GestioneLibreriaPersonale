package glp.model;
import java.util.*;

import glp.observer.*;
import glp.observer.Observable;

public class CatalogoLibri implements Observable {
	List<Libro> Libri= new ArrayList<>();
	List<LibroObserver> libriObserver = new ArrayList<>();
	
	public void aggiungiLibro(Libro l) {
		Libri.add(l);
		notificaObserver();
	}
	
	public void rimuoviLibro(Libro l) {
		Libri.remove(l);
		notificaObserver();
	}
	public void modificaLibro(Libro l1, Libro l2) {
	    int indice = -1;
	    for (int i = 0; i < Libri.size(); i++) {
	        if (Libri.get(i).getISBN().equals(l1.getISBN())) {
	            indice = i;
	            break;
	        }
	    }

	    if (indice != -1) {
	        Libro libroEsistente = Libri.get(indice);

	        if (l2.getTitolo() != null && !l2.getTitolo().isEmpty()) {
	            libroEsistente.setTitolo(l2.getTitolo());
	        }

	        if (l2.getAutore() != null && !l2.getAutore().isEmpty()) {
	            libroEsistente.setAutore(l2.getAutore());
	        }

	        if (l2.getValutazione() >= 0) {
	            libroEsistente.setValutazione(l2.getValutazione());
	        }

	        if (l2.getStatus() != null && !l2.getStatus().isEmpty()) {
	            libroEsistente.setStatus(l2.getStatus());
	        }

	        if (l2.getGenere() != null && !l2.getGenere().isEmpty()) {
	            libroEsistente.setGenere(l2.getGenere());
	        }

	        System.out.println("Libro modificato con successo.");
	    } else {
	        System.out.println("Libro da modificare non trovato.");
	    }
	}


	
	  public List<Libro> getLibri() {
	        return Libri;
	    }
	  public void setLibri(List<Libro> libri) {
		  this.Libri=libri;
	  }

	    public void aggiungiObserver(LibroObserver observer) {
	        libriObserver.add(observer);    
	    }

	    public void rimuoviObserver(LibroObserver observer) {
	        libriObserver.remove(observer);
	    }

	    public void notificaObserver() {
	        for (LibroObserver observer : libriObserver) {
	            observer.aggiorna();
	        }
	    }
}
