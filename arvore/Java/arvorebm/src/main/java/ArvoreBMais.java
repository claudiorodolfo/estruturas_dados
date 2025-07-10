package com.estruturasdados;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de uma árvore B+ genérica (ÁrvoreBMais).
 * 
 * @param <C> Tipo da chave
 * @param <V> Tipo do valor
 */
public class ArvoreBMais<C extends Comparable<C>, V> {
    
    private NoBMais<C, V> raiz;
    private int ordem;
    private int tamanho;
    
    /**
     * Construtor da árvore B+.
     * 
     * @param ordem Ordem da árvore (número máximo de chaves por nó)
     */
    public ArvoreBMais(int ordem) {
        this.ordem = ordem;
        this.raiz = null;
        this.tamanho = 0;
    }
    
    /**
     * Insere uma chave-valor na árvore.
     * 
     * @param chave Chave a ser inserida
     * @param valor Valor associado à chave
     */
    public void inserir(C chave, V valor) {
        if (raiz == null) {
            raiz = new NoBMais<>(true);
            raiz.getChaves().add(chave);
            raiz.getValores().add(valor);
        } else {
            inserirNo(raiz, chave, valor);
        }
        tamanho++;
    }
    
    private void inserirNo(NoBMais<C, V> no, C chave, V valor) {
        if (no.isFolha()) {
            int i = no.getQuantidadeChaves() - 1;
            while (i >= 0 && chave.compareTo(no.getChaves().get(i)) < 0) {
                i--;
            }
            no.getChaves().add(i + 1, chave);
            no.getValores().add(i + 1, valor);
        } else {
            int i = 0;
            while (i < no.getQuantidadeChaves() && chave.compareTo(no.getChaves().get(i)) >= 0) {
                i++;
            }
            inserirNo(no.getFilhos().get(i), chave, valor);
        }
    }
    
    /**
     * Busca um valor pela chave.
     * 
     * @param chave Chave a ser buscada
     * @return Valor associado à chave, ou null se não encontrado
     */
    public V buscar(C chave) {
        if (raiz == null) {
            return null;
        }
        return buscarNo(raiz, chave);
    }
    private V buscarNo(NoBMais<C, V> no, C chave) {
        if (no.isFolha()) {
            for (int i = 0; i < no.getQuantidadeChaves(); i++) {
                if (chave.compareTo(no.getChaves().get(i)) == 0) {
                    return no.getValores().get(i);
                }
            }
            return null;
        } else {
            int i = 0;
            while (i < no.getQuantidadeChaves() && chave.compareTo(no.getChaves().get(i)) >= 0) {
                i++;
            }
            return buscarNo(no.getFilhos().get(i), chave);
        }
    }
    
    /**
     * Remove uma chave-valor da árvore.
     * 
     * @param chave Chave a ser removida
     * @return Valor removido, ou null se não encontrado
     */
    public V remover(C chave) {
        if (raiz == null) {
            return null;
        }
        V valor = buscar(chave);
        if (valor == null) {
            return null;
        }
        removerNo(raiz, chave);
        tamanho--;
        return valor;
    }
    private void removerNo(NoBMais<C, V> no, C chave) {
        if (no.isFolha()) {
            for (int i = 0; i < no.getQuantidadeChaves(); i++) {
                if (chave.compareTo(no.getChaves().get(i)) == 0) {
                    no.getChaves().remove(i);
                    no.getValores().remove(i);
                    return;
                }
            }
        } else {
            int i = 0;
            while (i < no.getQuantidadeChaves() && chave.compareTo(no.getChaves().get(i)) >= 0) {
                i++;
            }
            removerNo(no.getFilhos().get(i), chave);
        }
    }
    
    /**
     * Retorna todos os valores em ordem.
     * 
     * @return Lista de valores em ordem
     */
    public List<V> obterTodosOsValores() {
        List<V> resultado = new ArrayList<>();
        if (raiz == null) {
            return resultado;
        }
        obterTodosOsValoresNo(raiz, resultado);
        return resultado;
    }
    private void obterTodosOsValoresNo(NoBMais<C, V> no, List<V> resultado) {
        if (no.isFolha()) {
            resultado.addAll(no.getValores());
        } else {
            for (NoBMais<C, V> filho : no.getFilhos()) {
                obterTodosOsValoresNo(filho, resultado);
            }
        }
    }
    public int tamanho() {
        return tamanho;
    }
    public boolean estaVazia() {
        return tamanho == 0;
    }
    public void limpar() {
        raiz = null;
        tamanho = 0;
    }
    @Override
    public String toString() {
        if (raiz == null) {
            return "ArvoreBMais[vazia]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ArvoreBMais[tamanho=").append(tamanho).append("]\n");
        imprimirNo(raiz, 0, sb);
        return sb.toString();
    }
    private void imprimirNo(NoBMais<C, V> no, int profundidade, StringBuilder sb) {
        String indent = "  ".repeat(profundidade);
        sb.append(indent).append(no.toString()).append("\n");
        if (!no.isFolha()) {
            for (NoBMais<C, V> filho : no.getFilhos()) {
                imprimirNo(filho, profundidade + 1, sb);
            }
        }
    }
} 