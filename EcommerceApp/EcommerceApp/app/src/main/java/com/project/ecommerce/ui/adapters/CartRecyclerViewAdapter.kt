package com.project.ecommerce.ui.adapters

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.project.ecommerce.data.entity.usercart.UserProduct
import com.project.ecommerce.databinding.CartSingleItemBinding


class CartRecyclerViewAdapter(
    var productList: List<UserProduct>,
    var mContext: Context,

    ) :
    RecyclerView.Adapter<CartRecyclerViewAdapter.CartViewHolder>() {


    inner class CartViewHolder(var view: CartSingleItemBinding) : ViewHolder(view.root) {
        fun bind(cartItem: UserProduct) {
            with(view) {

                val quantity = cartItem.quantity
                val price = cartItem.price
                val discountedPrice = cartItem.discountedPrice
                val thumbnail = cartItem.thumbnail
                val total = cartItem.total
                val title = cartItem.title
                Glide.with(mContext).load(thumbnail).into(orderImageView);


                orderQuantityTextView.text = buildString {
                    append("Miktar: ")
                    append(quantity.toString())
                }


                orderDiscountedTotalPrice.text = buildString {
                    append(discountedPrice.toString())
                    append(" $")
                }

                val spannableString = SpannableString("$total $")
                spannableString.setSpan(
                    StrikethroughSpan(),
                    0,
                    spannableString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                orderTotalPrice.text =  spannableString

                orderNameTextView.text=title
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = CartSingleItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentCartItem = productList[position]
        holder.bind(currentCartItem)
    }



}