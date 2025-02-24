package scisrc.mobiledev.ecommercelayout.utils

import scisrc.mobiledev.ecommercelayout.ui.models.CartItem
import scisrc.mobiledev.ecommercelayout.ui.models.Product

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product == product }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(product, 1))
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        cartItems.remove(cartItem)
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun calculateTotalPrice(): Int {
        return cartItems.sumOf { it.product.price.replace("฿", "").toInt() * it.quantity }
    }

    fun clearCart() {
        cartItems.clear()
    }
}
