package glp.observer;

import glp.model.*;
import glp.storage.*;

import java.io.File;

public class AutoSaveObserver implements LibroObserver {
    private CatalogoLibri catalogo;
    private File file;

    public AutoSaveObserver(CatalogoLibri catalogo, File file) {
        this.catalogo = catalogo;
        this.file = file;
    }

    @Override
    public void aggiorna() {
        StorageTM storage = new StorageTM();
        storage.creazioneCSV(catalogo.getLibri(), file);
        System.out.println("[AutoSave] Catalogo salvato automaticamente.");
    }
}
