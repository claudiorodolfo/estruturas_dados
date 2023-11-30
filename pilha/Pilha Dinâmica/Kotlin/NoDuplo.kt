data class NoDuplo (
		var anterior: NoDuplo?,
		var dado: Any?,
		var proximo: NoDuplo?
	) {

	constructor(dado: Any?) : this(null,dado,null) {}
}