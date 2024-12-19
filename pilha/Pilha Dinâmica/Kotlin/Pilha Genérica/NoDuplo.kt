data class NoDuplo<T> (var dado: T? = null) {
	var anterior: NoDuplo<T>? = null
	var proximo: NoDuplo<T>? = null
}