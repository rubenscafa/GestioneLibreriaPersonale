package glp.observer;


public abstract class Observable {
	public abstract void aggiungiObserver(LibroObserver o);
	public abstract void rimuoviObserver(LibroObserver o);
	public abstract void notificaObserver();

}
