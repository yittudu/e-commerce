package com.project.ecommerce.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.project.ecommerce.data.entity.Product
import com.project.ecommerce.databinding.FragmentHomeBinding
import com.project.ecommerce.ui.adapters.ProductRecyclerViewAdapter
import com.project.ecommerce.ui.viewmodels.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var productList = listOf<Product>()
    private var categoryList = listOf<String>()
    private lateinit var adapter: ProductRecyclerViewAdapter
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.yemeklerRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        binding.progressBar2.visibility = View.VISIBLE
        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            Log.e("TAG", "yemeklist")
            this.productList = productList
            adapter = ProductRecyclerViewAdapter(productList, requireContext())
            binding.yemeklerRecyclerView.adapter = adapter
            binding.progressBar2.visibility = View.GONE
        }

        viewModel.categoryList.observe(viewLifecycleOwner) { categoryList ->
            this.categoryList = categoryList
        }


        binding.sortImageView.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)


            for (category in categoryList) {
                popupMenu.menu.add(category.replaceFirstChar { it.uppercase() })
            }

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                if (item.title != "All") {
                    viewModel.getCategoryProduct(
                        item.title.toString().replaceFirstChar { it.lowercase() })
                } else {
                    viewModel.getProducts()
                }

                true

//                when (item.itemId) {
//                    R.id.sort_price_up -> {
//                        yemekListesi=yemekListesi.sortedBy { yemek: Yemek -> yemek.yemekPrice  }
//                        adapter = ProductRecyclerViewAdapter(yemekListesi, requireContext())
//                        binding.yemeklerRecyclerView.adapter = adapter
//                        true
//                    }
//                    R.id.sort_price_down -> {
//                        yemekListesi=yemekListesi.sortedByDescending { yemek: Yemek -> yemek.yemekPrice  }
//                        adapter = ProductRecyclerViewAdapter(yemekListesi, requireContext())
//                        binding.yemeklerRecyclerView.adapter = adapter
//                        true
//                    }
//                    R.id.sort_point_up -> {
//                       false
//                    }
//                    R.id.sort_point_down -> {
//                        false
//                    }
//                    else -> false
//                }
            }

            popupMenu.show()
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    viewModel.searchProducts(newText)
                } else {
                    viewModel.getProducts()
                }

                println(newText)
                return true
            }

        }
        )

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}