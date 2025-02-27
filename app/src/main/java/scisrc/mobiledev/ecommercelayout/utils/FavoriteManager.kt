package scisrc.mobiledev.ecommercelayout.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import scisrc.mobiledev.ecommercelayout.ui.models.Product

object FavoriteManager {
    private const val PREF_NAME = "favorite_products"
    private const val KEY_FAVORITES = "favorites"

    fun addFavorite(context: Context, product: Product) {
        val favorites = getFavorites(context).toMutableList()
        if (!favorites.contains(product)) {
            favorites.add(product)
            saveFavorites(context, favorites)
        }
    }

    fun removeFavorite(context: Context, product: Product) {
        val favorites = getFavorites(context).toMutableList()
        favorites.remove(product)
        saveFavorites(context, favorites)
    }

    fun getFavorites(context: Context): List<Product> {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_FAVORITES, "[]")
        val type = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(json, type)
    }

    private fun saveFavorites(context: Context, favorites: List<Product>) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(KEY_FAVORITES, Gson().toJson(favorites))
            apply()
        }
    }
}
