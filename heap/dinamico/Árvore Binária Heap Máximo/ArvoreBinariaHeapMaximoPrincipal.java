public class ArvoreBinariaHeapMaximoPrincipal {
	
   public static void main(String[] args) {
        Amontoavel heap = new ArvoreBinariaHeapMaximo(10);

        heap.inserir(4);
        heap.inserir(8);
        heap.inserir(2);
        heap.inserir(7);
        heap.inserir(1);
        System.out.println(heap.imprimir());	//[8,7,2,4,1]
        System.out.println(heap.extrair());	//8
        System.out.println(heap.obterRaiz());	//7      
        System.out.println(heap.obterRaiz());	//7            
		System.out.println(heap.imprimir()); //[7,4,2,1]
    }
}