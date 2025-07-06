package glp.model;

public class LibroBuilder implements LibroBuilderIF {
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
