package com.project.ecommerce.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.ecommerce.data.entity.Product
import com.project.ecommerce.databinding.ProductSingleItemBinding
import com.project.ecommerce.ui.fragments.HomeFragmentDirections

class ProductRecyclerViewAdapter(var productList: List<Product>, var mContext: Context) :
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(var view: ProductSingleItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(product: Product) {
            with(view) {
                root.setOnClickListener {

                    val direction=HomeFragmentDirections.actionHomeFragmentToDetailFragment(product)
                    Navigation.findNavController(it).navigate(direction)
                }
                nameTextView.text = product.title
                priceTextView.text = buildString {
                    append(product.price.toString())
                    append(" $")
                }
                starTextView.text=product.rating.toString()
                brandTextView.text=product.brand
                Glide.with(mContext).load(product.thumbnail).into(productImageView);

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ProductSingleItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentYemek = productList[position]
        holder.bind(currentYemek)
    }
}