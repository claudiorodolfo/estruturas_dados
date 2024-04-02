public class ArvoreBinariaHeapMinimoPrincipal {
	
   public static void main(String[] args) {
        Amontoavel heap = new ArvoreBinariaHeapMinimo(10);

        heap.inserir(4);
        heap.inserir(8);
        heap.inserir(2);
        heap.inserir(7);
        heap.inserir(1);
        System.out.println(heap.imprimir());	//[1,2,4,7,8]
        System.out.println(heap.extrair());	//1    
        System.out.println(heap.obterRaiz());	//2     
        System.out.println(heap.obterRaiz());	//2                                                 
		System.out.println(heap.imprimir()); //[2,7,4,8]
		
    }
}