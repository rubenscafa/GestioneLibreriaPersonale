package glp.strategy;
import java.util.List;
import glp.model.Libro;

public interface Ordinamento {
	List<Libro> ordina(List<Libro> libri);
}
