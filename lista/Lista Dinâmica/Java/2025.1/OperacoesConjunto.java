public class OperacoesConjunto<T> {

	private void validarListas(ListaDinamicaGenerica<T> listaA, ListaDinamicaGenerica<T> listaB) {
		if (listaA == null || listaB == null) {
			throw new IllegalArgumentException("Listas não podem ser nulas");
		}
	}

	private boolean contem(T[] array, T elemento) {
		for (T item : array) {
			if (elemento.equals(item)) 
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public T[] difference(ListaDinamicaGenerica<T> listaA, ListaDinamicaGenerica<T> listaB) {
		validarListas(listaA, listaB);
		ListaDinamicaGenerica<T> resultado = new ListaDinamicaGenerica<>();
		T[] elementosA = listaA.selecionarTodos();
		T[] elementosB = listaB.selecionarTodos();

		for (T a : elementosA) {
			if (!contem(elementosB, a)) {
				resultado.anexar(a);
			}
		}

		return resultado.selecionarTodos();
	}


	@SuppressWarnings("unchecked")
	public T[] union(ListaDinamicaGenerica<T> listaA, ListaDinamicaGenerica<T> listaB) {
		validarListas(listaA, listaB);
		ListaDinamicaGenerica<T> resultado = new ListaDinamicaGenerica<>();
		T[] elementosA = listaA.selecionarTodos(); 
		T[] elementosB = listaB.selecionarTodos();

		for (T a : elementosA) {
			resultado.anexar(a);
		}

		for (T b : elementosB) {
			if (!contem(elementosA, b))  {
				resultado.anexar(b);
			}
		}

		return resultado.selecionarTodos();
	}
		
	@SuppressWarnings("unchecked")
	public T[] intersection(ListaDinamicaGenerica<T> listaA, ListaDinamicaGenerica<T> listaB) {
		validarListas(listaA, listaB);
		ListaDinamicaGenerica<T> resultado = new ListaDinamicaGenerica<>();
		T[] elementosA = listaA.selecionarTodos();
		T[] elementosB = listaB.selecionarTodos();

		for (T a : elementosA) {
			if (contem(elementosB, a)) {
				resultado.anexar(a);
			}
		}

		return resultado.selecionarTodos();
	}
}