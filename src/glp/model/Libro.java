package glp.model;

public class Libro {
	private String titolo;
	private String autore;
	private String ISBN;
	private String genere;
	private int valutazione;
	private String status;
	

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public static class LibroBuilder implements LibroBuilderIF {
		private Libro libro;
		
		public LibroBuilder() {
			this.libro = new Libro();
		}

		@Override
		public LibroBuilderIF setTitolo(String titolo) {
			libro.setTitolo(titolo);return this;
		}

		@Override
		public LibroBuilderIF setAutore(String autore) {
			libro.setAutore(autore);return this;
		}

		@Override
		public LibroBuilderIF setISBN(String isbn) {
			libro.setISBN(isbn);return this;
		}

		@Override
		public LibroBuilderIF setGenere(String genere) {
			libro.setGenere(genere);return this;
		}

		@Override
		public LibroBuilderIF setValutazione(int valutazione) {
			libro.setValutazione(valutazione);return this;
		}

		@Override
		public LibroBuilderIF setStatus(String status) {
			libro.setStatus(status);return this;
		}

		@Override
		public Libro build() {
			return this.libro;
		}
	}

}
