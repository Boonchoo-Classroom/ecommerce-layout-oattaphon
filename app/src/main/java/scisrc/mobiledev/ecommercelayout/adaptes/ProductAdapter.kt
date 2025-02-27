package scisrc.mobiledev.ecommercelayout.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.databinding.ItemProductBinding
import scisrc.mobiledev.ecommercelayout.ui.models.Product
import scisrc.mobiledev.ecommercelayout.utils.FavoriteManager

class ProductAdapter(
    private var productList: List<Product>,
    private val onFavoriteUpdated: () -> Unit,  // Callback เมื่อรายการโปรดเปลี่ยน
    private val onItemClick: (Product) -> Unit  // Callback เมื่อกดดูรายละเอียด
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, onFavorite: () -> Unit, onClick: (Product) -> Unit) {
            binding.productImage.setImageResource(product.imageRes)
            binding.productName.text = product.name
            binding.productPrice.text = product.price

            // กดเพื่อเพิ่มในรายการโปรด
            binding.root.setOnLongClickListener {
                FavoriteManager.addFavorite(binding.root.context, product)
                onFavorite()
                true
            }

            // กดเพื่อดูรายละเอียดสินค้า
            binding.root.setOnClickListener {
                onClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position], { onFavoriteUpdated() }, onItemClick)
    }

    override fun getItemCount(): Int = productList.size

    // ฟังก์ชันอัปเดตรายการสินค้า
    fun updateList(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()x
    }
}
