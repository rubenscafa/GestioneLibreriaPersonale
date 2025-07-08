package glp.observer;


public interface Observable {
	void aggiungiObserver(LibroObserver o);
	void rimuoviObserver(LibroObserver o);
	void notificaObserver();

}
