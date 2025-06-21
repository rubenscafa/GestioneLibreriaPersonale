package glp.facade;
import java.util.*;
import glp.command.*;
import glp.model.*;
import glp.storage.*;
public class LibreriaFacade {
	private CatalogoLibri catalogo;
	private CommandManager commManager;
	private StorageTM storage;
	
	public LibreriaFacade(CatalogoLibri catalogo, CommandManager commManager, StorageTM storage) {
		
		this.catalogo=catalogo;
		this.commManager=commManager;
		this.storage=storage;
		
	}
	public void aggiuntaLibro(Libro l) {
		Command cmd = new aggiungiCommand(catalogo,l);
		commManager.eseguiCommand(cmd);
		catalogo.notificaObserver();
	}
	public void modificaLibro(Libro l1, Libro l2) {
		Command cmd = new modificaCommand(catalogo,l1,l2);
		commManager.eseguiCommand(cmd);
		catalogo.notificaObserver();
	}
	public void rimozioneLibro( Libro l) {
		Command cmd = new rimuoviCommand(catalogo,l);
		commManager.eseguiCommand(cmd);
		catalogo.notificaObserver();
	}
	public List<Libro> listaLibri(){
		return catalogo.getLibri();
	}
	
	public void salva() {
		storage.creazioneCSV(catalogo.getLibri());
	}
	
	public void carica(){
		List<Libro> libriCSV= storage.caricamentoCSV();
		catalogo.setLibri(libriCSV);
		catalogo.notificaObserver();
	}
	/*public void undo(){
	}*/
	
	 /* metodi observer
    public void addObserver(LibroObserver o) { catalogo.aggiungiObserver(o); }
    private void notifyObservers() { catalogo.notificaObserver(); }*/
} 
