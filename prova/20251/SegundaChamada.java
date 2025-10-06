public class SegundaChamada {

    //Questão 1
    public boolean estaBemFormado(String s) {
        Empilhavel<Character> pilha = new PilhaDinamica<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                pilha.empilhar(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (pilha.estaVazia()) return false;
                char t = pilha.desempilhar();
                if ((c == ')' && t != '(') ||
                    (c == '}' && t != '{') ||
                    (c == ']' && t != '['))
                    return false;
            }
        }
        return pilha.estaVazia();
    }

    //Questão 2
    public NodoDuplo<T> getInicio() {
        return ponteiroInicio;
    }

    public ListaDinamica<T> intercalacao(ListaDinamica<T> listaA, ListaDinamica<T> listaB) {
        NodoDuplo<T> p1 = listaA.getInicio();
        NodoDuplo<T> p2 = listaB.getInicio();
        ListaDinamica<T> resultado = new ListaDinamica<>();

        while (p1 != null && p2 != null) {
            if (p1.getDado().compareTo(p2.getDado()) <= 0) {
                resultado.anexar(p1.getDado());
                p1 = p1.getProximo();
            } else {
                resultado.anexar(p2.getDado());
                p2 = p2.getProximo();
            }
        }

        // Anexa o que sobrou de qualquer lista
        while (p1 != null) {
            resultado.anexar(p1.getDado());
            p1 = p1.getProximo();
        }
        while (p2 != null) {
            resultado.anexar(p2.getDado());
            p2 = p2.getProximo();
        }
        return resultado;
    }
}