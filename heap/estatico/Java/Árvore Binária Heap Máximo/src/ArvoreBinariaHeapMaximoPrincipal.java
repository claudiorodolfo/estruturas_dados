/**
 * Classe principal que demonstra o uso da árvore binária heap máximo.
 * Esta classe contém exemplos de uso das operações básicas do heap.
 * 
 * @author mac
 * @version 1.0
 */
public class ArvoreBinariaHeapMaximoPrincipal {
	
   /**
    * Método principal que demonstra as operações do heap.
    * 
    * @param args argumentos da linha de comando (não utilizados)
    */
   public static void main(String[] args) {
        Amontoavel heap = new ArvoreBinariaHeapMaximo(10);

        heap.inserir(4);
        heap.inserir(8);
        heap.inserir(2);
        heap.inserir(7);
        heap.inserir(1);
        System.out.println(heap.imprimir());	//[8,7,2,4,1]
        System.out.println(heap.extrair());	    //8
        System.out.println(heap.obterRaiz());	//7      
        System.out.println(heap.obterRaiz());	//7          
		System.out.println(heap.imprimir()); //[7,4,2,1]
    }
}