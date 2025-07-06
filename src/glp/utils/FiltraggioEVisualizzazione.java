package glp.utils;
import java.util.*;
import glp.model.*;
public class FiltraggioEVisualizzazione {
		
	public List<Libro> getLibriByString(String string, CatalogoLibri catalogo){
	    List<Libro> libri = new ArrayList<>();
	    String parametro = string.toLowerCase();

	    for (Libro l : catalogo.getLibri()) {
	        if (l.getAutore().toLowerCase().contains(parametro) ||
	            l.getTitolo().toLowerCase().contains(parametro) ||
	            l.getGenere().toLowerCase().contains(parametro)) {
	            libri.add(l);
	        }
	    }
	    return libri;
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
