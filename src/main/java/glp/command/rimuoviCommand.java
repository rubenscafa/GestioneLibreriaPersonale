package glp.command;

import glp.model.CatalogoLibri;
import glp.model.Libro;

public class rimuoviCommand implements Command {
	private CatalogoLibri catalogo;
	private Libro libro;

	public rimuoviCommand(CatalogoLibri catalogo, Libro libro) {
        this.catalogo = catalogo;
        this.libro = libro;
        
    }

    public void execute() {
        catalogo.rimuoviLibro(libro);
    }
	
}
