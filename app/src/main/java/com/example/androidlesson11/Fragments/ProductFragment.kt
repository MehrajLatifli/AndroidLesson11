package com.example.androidlesson11.Fragments

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidlesson11.Adapters.ProductAdapter
import com.example.androidlesson11.Models.Product
import com.example.androidlesson11.R
import com.example.androidlesson11.databinding.FragmentProductBinding
import java.text.DecimalFormat

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private var productAdapter = ProductAdapter()
    private var itemList = ArrayList<Product>()

    private var filteredItemList = ArrayList<Product>()

    private var sortOrder = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        refreshui()
        populateItemList()

        binding.inputSearch.addTextChangedListener { text ->
            val searchText = text.toString().trim()
            filterItemList(searchText)
        }

        binding.sortimageview.animate().rotation(180f).start()

        binding.sortimageview.setOnClickListener {


            sortbyNewPrice()
        }

    }


    private fun setUpRecyclerView() {
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.recycleViewHome.layoutManager = gridLayoutManager
        binding.recycleViewHome.adapter = productAdapter
    }


    private fun populateItemList() {
        itemList.add(Product(R.drawable.shoes_1,"Nike Air Max 270 React ENG",4.0,299.50,534.50,(DecimalFormat("#.##").format(299.50*100/534.50).toDouble())))
        itemList.add(Product(R.drawable.shoes_2,"Nike Air Max 280 React ENG",3.0,199.40,434.35,(DecimalFormat("#.##").format(199.40*100/434.35).toDouble())))
        itemList.add(Product(R.drawable.shoes_3,"Nike Air Max 290 React ENG",4.0,259.30,404.30,(DecimalFormat("#.##").format(259.30*100/404.30).toDouble())))
        itemList.add(Product(R.drawable.shoes_4,"Nike Air Max 275 React ENG",5.0,229.50,524.10,(DecimalFormat("#.##").format(229.50*100/524.10).toDouble())))
        itemList.add(Product(R.drawable.shoes_5,"Nike Air Max 200  White Anthracite",4.0,299.50,504.50,(DecimalFormat("#.##").format(299.50*100/504.50).toDouble())))
        itemList.add(Product(R.drawable.shoes_6,"Nike Air Max Alpha Trainer 5",4.0,209.90,425.85,(DecimalFormat("#.##").format(209.90*100/425.85).toDouble())))
        productAdapter.updateList(itemList)
    }


    private fun filterItemList(query: String) {

        refreshui()
        filteredItemList.clear()
        if (query.isEmpty()) {
            filteredItemList.addAll(itemList)
        } else {
            for (item in itemList) {
                if (item.toString().lowercase().contains(query.lowercase())) {
                    filteredItemList.add(item)
                }
            }
        }
        productAdapter.updateList(filteredItemList)
    }

    private  fun refreshui(){


        productAdapter.deleteList(itemList)
        productAdapter.deleteList(filteredItemList)
        filteredItemList.clear()

        setUpRecyclerView()
        populateItemList()


    }

    private fun sortbyNewPrice() {

        refreshui()

        sortOrder = (sortOrder + 1) % 2


        if (sortOrder == 0) {



            itemList.sortBy { it.newprice }

            binding.sortimageview.animate().rotation(180f).start()
        } else {


            itemList.sortByDescending { it.newprice }

            binding.sortimageview.animate().rotation(0f).start()
        }

        productAdapter.updateList(itemList)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
