package glp.storage;

import java.util.*;
import glp.model.Libro;
public abstract class AbstractStorageTM {
	
	protected final void salvaCatalogo(List<Libro> libri) {
		apriFile();
		creazioneCSV(libri);
		caricamentoCSV();
		chiudiFile();
		conferma();
		
	}
	protected abstract void apriFile();
	protected abstract void creazioneCSV(List<Libro> libri);
	protected abstract List<Libro>caricamentoCSV();
	protected void conferma() {
		System.out.println("Salvataggio eseguito con successo\n");
	}
	protected abstract void chiudiFile();
}
