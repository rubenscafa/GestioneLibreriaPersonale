package glp.model;

public class LibroMemento {
		
    private final String titolo;
    private final String autore;
    private final String ISBN;
    private final String genere;
    private final int valutazione;
    private final String status;

    public LibroMemento(String titolo, String autore, String ISBN, String genere, int valutazione, String status) {
        this.titolo = titolo;
        this.autore = autore;
        this.ISBN = ISBN;
        this.genere = genere;
        this.valutazione = valutazione;
        this.status = status;
    }

	public String getTitolo() {
		return titolo;
	}

	public String getAutore() {
		return autore;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getGenere() {
		return genere;
	}

	public int getValutazione() {
		return valutazione;
	}

	public String getStatus() {
		return status;
	}
    

}
