package scisrc.mobiledev.ecommercelayout.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.databinding.ItemProductBinding
import scisrc.mobiledev.ecommercelayout.ui.models.Product
import scisrc.mobiledev.ecommercelayout.utils.FavoriteManager

class FavoriteAdapter(private var favoriteList: List<Product>, private val onFavoriteUpdated: () -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, onRemove: () -> Unit) {
            binding.productImage.setImageResource(product.imageRes)
            binding.productName.text = product.name
            binding.productPrice.text = product.price
            binding.root.setOnLongClickListener {
                FavoriteManager.removeFavorite(product)
                onRemove()
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position]) { onFavoriteUpdated() }
    }

    override fun getItemCount(): Int = favoriteList.size

    fun updateList(newList: List<Product>) {
        favoriteList = newList
        notifyDataSetChanged()
    }
}
