package glp.model;

public interface LibroBuilderIF {
	  LibroBuilderIF setTitolo(String titolo);
	  LibroBuilderIF setAutore(String autore);
	  LibroBuilderIF setISBN(String isbn);
	  LibroBuilderIF setGenere(String genere);
	  LibroBuilderIF setValutazione(int valutazione);
	  LibroBuilderIF setStatus(String status);
	  Libro build();
}
