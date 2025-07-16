public class ArvoreBProdutoTest {
    
    public static void main(String[] args) {
        System.out.println("=== Testando Árvore B de Produtos ===");
        
        // Teste 1: Inserção e busca de produtos
        System.out.println("\n1. Teste de Inserção e Busca de Produtos:");
        ArvoreB<Produto> arvore = new ArvoreB<>(3);
        
        Produto p1 = new Produto("Notebook", 123456789L);
        Produto p2 = new Produto("Mouse", 987654321L);
        Produto p3 = new Produto("Teclado", 456789123L);
        Produto p4 = new Produto("Monitor", 789123456L);
        Produto p5 = new Produto("Headset", 321654987L);
        
        arvore.inserir(p1);
        arvore.inserir(p2);
        arvore.inserir(p3);
        arvore.inserir(p4);
        arvore.inserir(p5);
        
        System.out.println("Produtos inseridos: Notebook, Mouse, Teclado, Monitor, Headset");
        System.out.print("Produtos em ordem: ");
        arvore.imprimirEmOrdem();
        
        // Teste de busca
        NoArvoreB<Produto> resultado = arvore.buscar(p1);
        System.out.println("Busca Notebook: " + (resultado != null ? "Encontrado" : "Não encontrado"));
        
        Produto buscaInexistente = new Produto("Produto Inexistente", 999999999L);
        resultado = arvore.buscar(buscaInexistente);
        System.out.println("Busca produto inexistente: " + (resultado != null ? "Encontrado" : "Não encontrado"));
        
        // Teste 2: Inserção com divisão
        System.out.println("\n2. Teste de Inserção com Divisão:");
        ArvoreB<Produto> arvore2 = new ArvoreB<>(3);
        
        for (int i = 1; i <= 10; i++) {
            Produto produto = new Produto("Produto " + i, 100000000L + i);
            arvore2.inserir(produto);
        }
        
        System.out.println("Inseridos 10 produtos");
        System.out.print("Produtos em ordem: ");
        arvore2.imprimirEmOrdem();
        
        // Teste 3: Remoção
        System.out.println("\n3. Teste de Remoção:");
        Produto produtoRemover = new Produto("Produto 5", 100000005L);
        System.out.println("Removendo Produto 5...");
        arvore2.apagar(produtoRemover);
        System.out.print("Após remoção: ");
        arvore2.imprimirEmOrdem();
        
        // Teste 4: Diferentes ordens
        System.out.println("\n4. Teste com Diferentes Ordens:");
        ArvoreB<Produto> arvoreOrdem4 = new ArvoreB<>(4);
        ArvoreB<Produto> arvoreOrdem6 = new ArvoreB<>(6);
        
        for (int i = 1; i <= 8; i++) {
            Produto produto = new Produto("Produto Ordem " + i, 200000000L + i);
            arvoreOrdem4.inserir(produto);
            arvoreOrdem6.inserir(produto);
        }
        
        System.out.print("Árvore ordem 4: ");
        arvoreOrdem4.imprimirEmOrdem();
        System.out.print("Árvore ordem 6: ");
        arvoreOrdem6.imprimirEmOrdem();
        
        // Teste 5: Comparação de produtos
        System.out.println("\n5. Teste de Comparação de Produtos:");
        Produto produto1 = new Produto("A", 1L);
        Produto produto2 = new Produto("B", 2L);
        Produto produto3 = new Produto("C", 1L); // Mesmo código, nome diferente
        
        System.out.println("Comparação produto1 < produto2: " + (produto1.compareTo(produto2) < 0));
        System.out.println("Comparação produto1 == produto3: " + (produto1.compareTo(produto3) == 0));
        
        System.out.println("\n=== Todos os testes concluídos ===");
    }
} 