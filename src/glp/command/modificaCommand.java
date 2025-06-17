package glp.command;

import glp.model.CatalogoLibri;
import glp.model.Libro;

public class modificaCommand implements Command{
	private CatalogoLibri catalogo;
    private Libro libroOld;
    private Libro libroNew;

    public modificaCommand(CatalogoLibri catalogo, Libro libroOld, Libro libroNew) {
        this.catalogo = catalogo;
        this.libroOld = libroOld;
        this.libroNew = libroNew;
    }

    
    public void execute() {
        catalogo.modificaLibro(libroOld, libroNew);
    }
}
