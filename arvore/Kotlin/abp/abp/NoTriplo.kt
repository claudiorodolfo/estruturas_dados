data class NoTriplo(var dado: Any? = null) {
	var genitor: NoTriplo? = null
	var esquerda: NoTriplo? = null
	var direita: NoTriplo? = null
}