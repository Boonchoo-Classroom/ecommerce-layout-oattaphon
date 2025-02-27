package scisrc.mobiledev.ecommercelayout.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.databinding.ItemCartBinding
import scisrc.mobiledev.ecommercelayout.ui.models.CartItem
import scisrc.mobiledev.ecommercelayout.utils.CartManager

class CartAdapter(private var cartList: List<CartItem>, private val onCartUpdated: () -> Unit) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem, onRemove: () -> Unit) {
            binding.cartProductImage.setImageResource(cartItem.product.imageRes)
            binding.cartProductName.text = cartItem.product.name
            binding.cartProductPrice.text = cartItem.product.price
            binding.cartRemoveButton.setOnClickListener {
                CartManager.removeFromCart(cartItem)
                onRemove()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position]) { onCartUpdated() }
    }

    override fun getItemCount(): Int = cartList.size

    fun updateList(newList: List<CartItem>) {
        cartList = newList
        notifyDataSetChanged()
    }
}
