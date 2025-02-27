data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
