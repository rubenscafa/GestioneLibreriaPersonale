package glp.command;

import glp.model.*;

public class modificaCommand implements Command {
    private CatalogoLibri catalogo;
    private Libro libroOriginale;      
    private Libro libroModificato;     
    private LibroMemento backup;       

    public modificaCommand(CatalogoLibri catalogo, Libro libroOriginale, Libro libroModificato) {
        this.catalogo = catalogo;
        this.libroOriginale = libroOriginale;
        this.libroModificato = libroModificato;
    }

    @Override
    public void execute() {
        catalogo.modificaLibro(libroOriginale, libroModificato);
    }
}
