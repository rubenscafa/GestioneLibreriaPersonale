package glp.storage;
import java.util.*;
import glp.model.Libro;
import java.io.*;
public class StorageTM extends AbstractStorageTM{

	private BufferedWriter writer;

    @Override
	protected void apriFile() {
        try {
            writer = new BufferedWriter(new FileWriter("libreria.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
	public void creazioneCSV(List<Libro> libri) {
        try {
            for (Libro libro : libri) {
                writer.write(libro.getTitolo() + "," +
                             libro.getAutore() + "," +
                             libro.getISBN() + "," +
                             libro.getGenere() + "," +
                             libro.getValutazione() + "," +
                             libro.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
    @Override
    public List<Libro> caricamentoCSV() {
        List<Libro> libri = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("libreria.csv"))) {
            String riga;

            while ((riga = reader.readLine()) != null) {
                String[] campi = riga.split(",");

                if (campi.length == 6) {
                    try {
                        int valutazione = Integer.parseInt(campi[5]);

                        Libro libro = new Libro.LibroBuilder()
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


    @Override
	protected void chiudiFile() {
        try {
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	
	
	
}
