package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import scisrc.mobiledev.ecommercelayout.R
import scisrc.mobiledev.ecommercelayout.databinding.FragmentHomeBinding
import scisrc.mobiledev.ecommercelayout.ui.adapters.BannerAdapter
import scisrc.mobiledev.ecommercelayout.ui.adapters.ProductAdapter
import scisrc.mobiledev.ecommercelayout.ui.models.Product

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 ตั้งค่า ViewPager2 สำหรับแบนเนอร์
        val bannerImages = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
        val bannerAdapter = BannerAdapter(bannerImages)
        binding.bannerViewPager.adapter = bannerAdapter

        // 🔹 ตั้งค่า RecyclerView สำหรับสินค้าแนะนำ
        val recommendedProducts = listOf(
            Product(R.drawable.shoe1, "Nike Air Max", "฿3,500"),
            Product(R.drawable.shoe2, "Adidas Superstar", "฿2,800"),
            Product(R.drawable.shoe3, "Puma Running", "฿2,200")
        )
        binding.recommendedProductsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ProductAdapter(recommendedProducts)
        }

        // 🔹 ตั้งค่า RecyclerView สำหรับโปรโมชั่น
        val promotions = listOf(
            Product(R.drawable.promo1, "ลด 50% ทุกชิ้น!", "ใช้โค้ด: SALE50"),
            Product(R.drawable.promo2, "ซื้อ 1 แถม 1", "เฉพาะสินค้าร่วมรายการ")
        )
        binding.promotionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ProductAdapter(promotions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = nullx
    }
}
