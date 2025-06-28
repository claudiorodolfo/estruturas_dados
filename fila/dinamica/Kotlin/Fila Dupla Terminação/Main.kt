fun main() {
	var fila: EnfileiravelDupla = FilaDinamicaDuplaTerminacao()
	fila.enfileirarInicio("A")
	println(fila.imprimirFrentePraTras()) //[A]
	println(fila.tras()) //A
	println(fila.frente()) //A	
	fila.enfileirarInicio("B")
	fila.desenfileirarFim()
    fila.enfileirarFim("c")
	fila.atualizarFim("C")
	fila.desenfileirarInicio()
	fila.enfileirarInicio("D") 
	println(fila.imprimirFrentePraTras()) //[D,C]
	fila.frente()
	fila.desenfileirarFim()
	println(fila.imprimirFrentePraTras())
	println(fila.tras()) //D    
	fila.enfileirarInicio("e")
	fila.atualizarInicio("E")    
	println(fila.imprimirFrentePraTras())//[E,D]
	fila.enfileirarFim("F")
	fila.desenfileirarInicio()
	fila.enfileirarFim("G")
	println(fila.tras()) //G
	fila.enfileirarInicio("H")
	fila.desenfileirarFim()
	fila.enfileirarFim("I")
	println(fila.imprimirTrasPraFrente()) //[I,F,D,H]
	fila.enfileirarInicio("J")
	fila.enfileirarFim("K")
	fila.enfileirarFim("L")
	println(fila.frente()) //J
	fila.desenfileirarInicio()
	println(fila.imprimirFrentePraTras()) //[H,D,F,I,K,L]
}