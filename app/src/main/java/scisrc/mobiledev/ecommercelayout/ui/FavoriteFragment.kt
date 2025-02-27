package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import scisrc.mobiledev.ecommercelayout.databinding.FragmentFavoriteBinding
import scisrc.mobiledev.ecommercelayout.ui.adapters.FavoriteAdapter
import scisrc.mobiledev.ecommercelayout.utils.FavoriteManager

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 ตั้งค่า RecyclerView
        favoriteAdapter = FavoriteAdapter(FavoriteManager.getFavorites(requireContext())) { updateFavorites() }
        binding.favoriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
        }

        updateFavorites()
    }

    private fun updateFavorites() {
        val favorites = FavoriteManager.getFavorites(requireContext())
        favoriteAdapter.updateList(favorites)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
