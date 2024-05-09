package com.example.androidlesson11.Adapters

import android.app.appsearch.SearchResult
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson11.Fragments.ProductFragmentDirections
import com.example.androidlesson11.Models.Product
import com.example.androidlesson11.R
import com.example.androidlesson11.databinding.CardViewBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    private val productList = ArrayList<Product>()

    inner class ProductViewHolder(val binding: CardViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.binding.apply {
            productimageView.setImageResource(product.drawableimage ?: R.drawable.ic_launcher_background)
            nametextView.text = product.productname
            pricetextView.text = "$${product.newprice.toString()}"
            oldpricetextView.text ="$${product.oldprice.toString()}"
            discounttextView.text = "${product.discount.toString()}% Off"

            oldpricetextView.paintFlags = oldpricetextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            setStarRating(product.raiting, star1, star2, star3, star4, star5)

            materialCardView.setOnClickListener {
                val action = ProductFragmentDirections.actionProductFragmentToDetailFragment(product)
                Navigation.findNavController(holder.itemView).navigate(action)
            }


        }




    }

    private fun setStarRating(rating: Double?, vararg starImageViews: ImageView) {
        val maxStars = starImageViews.size
        val filledStars = (rating ?: 0.0).coerceIn(0.0, maxStars.toDouble()).toInt()

        for (i in 0 until maxStars) {
            if (i < filledStars) {
                starImageViews[i].setImageResource(R.drawable.yellowstar)
            } else {
                starImageViews[i].setImageResource(R.drawable.greystar)
            }
        }
    }

    fun updateList(newList: ArrayList<Product>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }


    fun deleteList(newList: ArrayList<Product>) {
        newList.clear()
        notifyDataSetChanged()
    }
}
