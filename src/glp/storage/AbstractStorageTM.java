package glp.storage;

import java.io.File;
import java.util.List;
import glp.model.Libro;

public abstract class AbstractStorageTM {

    public final void salvaCatalogo(List<Libro> libri, File file) {
        creazioneCSV(libri, file);
        conferma();
    }

    protected abstract void creazioneCSV(List<Libro> libri, File file);

    protected abstract List<Libro> caricamentoCSV(File file);

    protected void conferma() {
        System.out.println("Salvataggio eseguito con successo\n");
    }
}
