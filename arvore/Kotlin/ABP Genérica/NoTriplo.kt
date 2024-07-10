data class NoTriplo<T>(var dado: T? = null) {
	var genitor: NoTriplo<T>? = null
	var esquerda: NoTriplo<T>? = null
	var direita: NoTriplo<T>? = null
}