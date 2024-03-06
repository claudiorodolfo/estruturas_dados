fun main() {
	var fila: DuplamenteEnfileiravel = FilaEstaticaDuplaTerminacao()
	fila.enfileirarInicio("A")
	println(fila.imprimir()) //[A]
	println(fila.tras()) //A
	println(fila.frente()) //A	
	fila.enfileirarInicio("B")
	fila.desenfileirarFim()
    fila.enfileirarFim("c")
	fila.atualizarFim("C")
	fila.desenfileirarInicio()
	fila.enfileirarInicio("D") 
	println(fila.imprimir()) //[D,C]
	fila.frente() 
	fila.desenfileirarFim()
	println(fila.tras()) //D    
	fila.enfileirarInicio("e")
	fila.atualizarInicio("E")    
	println(fila.imprimir())//[E,D]
	fila.enfileirarFim("F")
	fila.desenfileirarInicio()
	fila.enfileirarFim("G")
	println(fila.tras()) //G
	fila.enfileirarInicio("H")
	fila.desenfileirarFim()
	fila.enfileirarFim("I")
	println(fila.imprimir()) //[H,D,F,I]
	fila.enfileirarInicio("J")
	fila.enfileirarFim("K")
	fila.enfileirarFim("L")
	println(fila.frente()) //J
	fila.desenfileirarInicio()
	println(fila.imprimir()) //[H,D,F,I,K,L]
}
