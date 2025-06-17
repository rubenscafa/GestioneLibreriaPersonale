package glp.model;

public class Libro {
	private String titolo;
	private String autore;
	private String ISBN;
	private String genere;
	private int valutazione;
	private String status;
	
	public Libro(LibroBuilder builder) {
		this.autore= builder.autore;
		this.titolo= builder.titolo;
		this.genere= builder.genere;
		this.ISBN= builder.ISBN;
		this.valutazione= builder.valutazione;
		this.status= builder.status;
	}

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
	
	private static class LibroBuilder {
		private String titolo;
		private String autore;
		private String ISBN;
		private String genere;
		private int valutazione;
		private String status;
		
		public void setTitle(String titolo) {
			this.titolo = titolo;
		}
		public void setAuthor(String autore) {
			this.autore = autore; 
		}
		public void setISBN(String iSBN) {
			ISBN = iSBN; 
		}
		public void setGenre(String genere) {
			this.genere = genere; 
		}
		public void setRating(int valutazione) {
			this.valutazione = valutazione;
		}
		public void setState(String status) {
			this.status = status; 
		}
		
		 public Libro build() {
	            return new Libro(this);
	        }
		
		
	}
	 public LibroMemento saveToMemento() {
	        return new LibroMemento(titolo, autore, ISBN, genere, valutazione, status);
	    }

	    public void restoreFromMemento(LibroMemento memento) {
	        this.titolo = memento.getTitolo();
	        this.autore = memento.getAutore();
	        this.ISBN = memento.getISBN();
	        this.genere = memento.getGenere();
	        this.valutazione = memento.getValutazione();
	        this.status = memento.getStatus();
	    }

}
