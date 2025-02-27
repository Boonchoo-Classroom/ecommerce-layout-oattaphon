package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import scisrc.mobiledev.ecommercelayout.R
import scisrc.mobiledev.ecommercelayout.databinding.FragmentProductListBinding
import scisrc.mobiledev.ecommercelayout.ui.adapters.ProductAdapter
import scisrc.mobiledev.ecommercelayout.ui.models.Product

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter
    private val allProducts = listOf(
        Product(R.drawable.pen, "ปากกา", "฿50", "อุปกรณ์เครื่องเขียน"),
        Product(R.drawable.notebook, "สมุดโน้ต", "฿120", "อุปกรณ์เครื่องเขียน"),
        Product(R.drawable.basketball, "ลูกบาสเก็ตบอล", "฿500", "อุปกรณ์กีฬา"),
        Product(R.drawable.smartwatch, "Smart Watch", "฿2,500", "Gadget"),
        Product(R.drawable.tshirt, "เสื้อยืด", "฿300", "เสื้อผ้า")
    )
    private var filteredProducts = allProducts

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 ตั้งค่า Spinner (Dropdown)
        val categories = resources.getStringArray(R.array.product_categories)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)
        binding.categorySpinner.adapter = spinnerAdapter

        // 🔹 ตั้งค่า RecyclerView
        productAdapter = ProductAdapter(filteredProducts)
        binding.productRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }

        // 🔹 กรองสินค้าตามหมวดหมู่ที่เลือก
        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = categories[position]
                filteredProducts = if (selectedCategory == "ทั้งหมด") {
                    allProducts
                } else {
                    allProducts.filter { it.category == selectedCategory }
                }
                productAdapter.updateList(filteredProducts)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = nullx
    }
}
