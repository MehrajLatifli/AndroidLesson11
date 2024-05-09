package com.example.androidlesson11.Fragments

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.example.androidlesson11.R
import com.example.androidlesson11.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    private val args:DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.products

        binding.nametextView.text=product.productname?:""
        binding.productimageView.setImageResource(product.drawableimage?:0)
        binding.pricetextView.text = "$${product.newprice.toString()}"
        binding.oldpricetextView.text ="$${product.oldprice.toString()}"
        binding.discounttextView.text = "${product.discount.toString()}% Off"
        binding.oldpricetextView.paintFlags =  binding.oldpricetextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


        setStarRating(product.raiting, binding.star1, binding.star2, binding.star3, binding.star4, binding.star5)



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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}