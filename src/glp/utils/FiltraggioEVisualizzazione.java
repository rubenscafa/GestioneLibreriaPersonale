package glp.utils;
import java.util.*;
import glp.model.*;
public class FiltraggioEVisualizzazione {
		
	public List<Libro> getLibriByAutore(String autore,CatalogoLibri catalogo){
		List<Libro> libriAutore = new ArrayList<>();
		for(Libro l:catalogo.getLibri()) {
			if(l.getAutore().equalsIgnoreCase(autore)) {
				libriAutore.add(l);
			}
		}
		return libriAutore;
	}
	public List<Libro> getLibriByGenere(String genere,CatalogoLibri catalogo){
		List<Libro> libriPerGenere = new ArrayList<>();
		for(Libro l:catalogo.getLibri()) {
			if(l.getGenere().equalsIgnoreCase(genere)) {
				libriPerGenere.add(l);
			}
		}
		return libriPerGenere;
	}
	public List<Libro> getLibriByTitolo(String titolo,CatalogoLibri catalogo){
		List<Libro> libriPerTitolo = new ArrayList<>();
		for(Libro l:catalogo.getLibri()) {
			if(l.getTitolo().contains(titolo)) {
				libriPerTitolo.add(l);
			}
		}
		return libriPerTitolo;
	}
	public List<Libro> getLibriPerStatus(String status,CatalogoLibri catalogo){
		List<Libro> libriPerStatus = new ArrayList<>();
		for(Libro l:catalogo.getLibri()) {
			if(l.getStatus().equalsIgnoreCase(status)) {
				libriPerStatus.add(l);
			}
		}
		return libriPerStatus;
	}
}
