package glp.storage;

import java.util.*;
import glp.model.Libro;
import glp.model.LibroBuilder;

import java.io.*;

public class StorageTM extends AbstractStorageTM {

    @Override
    public void creazioneCSV(List<Libro> libri, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Libro libro : libri) {
                writer.write(libro.getTitolo() + "," +
                             libro.getAutore() + "," +
                             libro.getISBN() + "," +
                             libro.getGenere() + "," +
                             libro.getStatus() + "," +
                             libro.getValutazione());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file CSV: " + e.getMessage());
        }
    }

    @Override
    public List<Libro> caricamentoCSV(File file) {
        List<Libro> libri = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String riga;

            while ((riga = reader.readLine()) != null) {
                String[] campi = riga.split(",");

                if (campi.length == 6) {
                    try {
                        int valutazione = Integer.parseInt(campi[5]);

                        Libro libro = new LibroBuilder()
                                .setTitolo(campi[0])
                                .setAutore(campi[1])
                                .setISBN(campi[2])
                                .setGenere(campi[3])
                                .setStatus(campi[4])
                                .setValutazione(valutazione)
                                .build();

                        libri.add(libro);
                    } catch (NumberFormatException e) {
                        System.err.println("Errore nella conversione della valutazione: " + campi[5]);
                    }
                } else {
                    System.err.println("Formato riga non valido: " + riga);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file CSV: " + e.getMessage());
        }

        return libri;
    }
    
}
