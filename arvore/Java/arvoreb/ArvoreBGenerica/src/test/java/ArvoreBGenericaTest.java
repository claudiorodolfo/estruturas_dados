public class ArvoreBGenericaTest {
    
    public static void main(String[] args) {
        System.out.println("=== Testando Árvore B Genérica ===");
        
        // Teste 1: Inserção e busca
        System.out.println("\n1. Teste de Inserção e Busca:");
        ArvoreB<Integer> arvore = new ArvoreB<>(3);
        
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(25);
        
        System.out.println("Inseridos: 10, 20, 5, 15, 25");
        System.out.print("Árvore em ordem: ");
        arvore.imprimirEmOrdem();
        
        // Teste de busca
        NoArvoreB<Integer> resultado = arvore.buscar(10);
        System.out.println("Busca 10: " + (resultado != null ? "Encontrado" : "Não encontrado"));
        resultado = arvore.buscar(100);
        System.out.println("Busca 100: " + (resultado != null ? "Encontrado" : "Não encontrado"));
        
        // Teste 2: Inserção com divisão
        System.out.println("\n2. Teste de Inserção com Divisão:");
        ArvoreB<Integer> arvore2 = new ArvoreB<>(3);
        
        for (int i = 1; i <= 10; i++) {
            arvore2.inserir(i);
        }
        
        System.out.println("Inseridos números de 1 a 10");
        System.out.print("Árvore em ordem: ");
        arvore2.imprimirEmOrdem();
        
        // Teste 3: Remoção
        System.out.println("\n3. Teste de Remoção:");
        System.out.println("Removendo 5...");
        arvore2.apagar(5);
        System.out.print("Após remoção: ");
        arvore2.imprimirEmOrdem();
        
        // Teste 4: Diferentes ordens
        System.out.println("\n4. Teste com Diferentes Ordens:");
        ArvoreB<Integer> arvoreOrdem4 = new ArvoreB<>(4);
        ArvoreB<Integer> arvoreOrdem6 = new ArvoreB<>(6);
        
        for (int i = 1; i <= 15; i++) {
            arvoreOrdem4.inserir(i);
            arvoreOrdem6.inserir(i);
        }
        
        System.out.print("Árvore ordem 4: ");
        arvoreOrdem4.imprimirEmOrdem();
        System.out.print("Árvore ordem 6: ");
        arvoreOrdem6.imprimirEmOrdem();
        
        System.out.println("\n=== Todos os testes concluídos ===");
    }
}
