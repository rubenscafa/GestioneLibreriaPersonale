package glp.command;

import glp.model.CatalogoLibri;
import glp.model.Libro;

public class aggiungiCommand implements Command {
	private CatalogoLibri catalogo;
	private Libro libro;
	
	public aggiungiCommand(CatalogoLibri catalogo, Libro libro) {
        this.catalogo = catalogo;
        this.libro = libro;
    }

    public void execute() {
        catalogo.aggiungiLibro(libro);
    }
	
}
