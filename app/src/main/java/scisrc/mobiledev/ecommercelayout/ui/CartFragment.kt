package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import scisrc.mobiledev.ecommercelayout.databinding.FragmentCartBinding
import scisrc.mobiledev.ecommercelayout.ui.adapters.CartAdapter
import scisrc.mobiledev.ecommercelayout.ui.models.CartItem
import scisrc.mobiledev.ecommercelayout.utils.CartManager

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 ตั้งค่า RecyclerView
        cartAdapter = CartAdapter(CartManager.getCartItems()) { updateCart() }
        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }

        // 🔹 อัปเดตราคารวม
        updateCart()

        // 🔹 ตั้งค่าปุ่ม Checkout
        binding.checkoutButton.setOnClickListener {
            CartManager.clearCart()
            updateCart()
        }
    }

    private fun updateCart() {
        val totalPrice = CartManager.calculateTotalPrice()
        binding.totalPriceTextView.text = "รวม: ฿$totalPrice"
        binding.checkoutButton.isEnabled = totalPrice > 0
        cartAdapter.updateList(CartManager.getCartItems())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = nullx
    }
}
