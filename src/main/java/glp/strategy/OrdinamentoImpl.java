package glp.strategy;

import java.util.List;

import glp.model.Libro;

import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoImpl implements Ordinamento{

	@Override
	public List<Libro> ordina(List<Libro> libri) {
		Collections.sort(libri, Comparator.comparingInt(Libro::getValutazione).reversed());
		return libri;
	}

}
