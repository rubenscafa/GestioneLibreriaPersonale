package glp.model;

import glp.model.Libro.LibroBuilder;

public class LibroDirector {

    public Libro costruisciLibroParziale(Libro originale, String titolo, String autore, String genere, String status, Integer valutazione) {
        LibroBuilder builder = new Libro.LibroBuilder();

        builder.setTitolo((titolo == null || titolo.isBlank()) ? originale.getTitolo() : titolo);
        builder.setAutore((autore == null || autore.isBlank()) ? originale.getAutore() : autore);
        builder.setGenere((genere == null || genere.isBlank()) ? originale.getGenere() : genere);
        builder.setStatus((status == null || status.isBlank()) ? originale.getStatus() : status);
        builder.setValutazione((valutazione == null) ? originale.getValutazione() : valutazione);

        builder.setISBN(originale.getISBN());

        return builder.build();
    }
}
