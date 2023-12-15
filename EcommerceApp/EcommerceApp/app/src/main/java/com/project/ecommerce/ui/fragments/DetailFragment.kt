package com.project.ecommerce.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.project.ecommerce.R
import com.project.ecommerce.data.entity.cart.AddToCartRequest
import com.project.ecommerce.data.entity.cart.CartResponse
import com.project.ecommerce.data.entity.cart.ProductInCart
import com.project.ecommerce.databinding.FragmentDetailBinding
import com.project.ecommerce.ui.adapters.ImagePagerAdapter
import com.project.ecommerce.ui.viewmodels.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var amount: Int = 1
    private var singlePrice = 0
    private lateinit var viewModel: DetailFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val bundle: DetailFragmentArgs by navArgs()
        val productValue = bundle.productArg
        initFragment(productValue.price)
        val adapter = ImagePagerAdapter(requireContext(), productValue.images)
        binding.detailViewPager.adapter = adapter

        //viewModel.checkFavorited(productValue.yemekId)
        binding.detailFavoriteButton.setImageResource(R.drawable.baseline_favorite_24)

        with(binding) {
            detailLocationTextView.text = productValue.stock.toString()
            detailStarTextView.text = productValue.rating.toString()
            detailNameTextView.text = productValue.title

            saleTextView.text = buildString {
                append(" %")
                append(productValue.discountPercentage)
            }
            detailPriceTextView.text = buildString {
                append(productValue.price)
                append(" $")
            }
            detailCategoryTextView.text = productValue.category.replaceFirstChar { it.uppercase() }
            detailDescriptionTextView.text = productValue.description

            decreaseButton.setOnClickListener {
                amount--
                if (amount < 1) amount = 1
                binding.amountTextView.text = amount.toString()
                binding.totalPrice.text = calculateTotalPrice(singlePrice, amount)
            }
            detailBackButton.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            increaseButton.setOnClickListener {
                amount++
                if (amount > 10) amount = 10
                binding.amountTextView.text = amount.toString()
                binding.totalPrice.text = calculateTotalPrice(singlePrice, amount)

            }

            addCartButton.setOnClickListener {
                val productsToAdd = listOf(
                    ProductInCart(id = productValue.id, quantity = amount),

                    )

                val addToCartRequest = AddToCartRequest(
                    userId = 1,
                    products = productsToAdd
                )

                viewModel.addCart(addToCartRequest, onSuccess = { response: CartResponse ->
                    showProductDetailsDialog(requireContext(), response)
                }, onFailure = {
                    MotionToast.createColorToast(
                        requireActivity(),
                        "Hata",
                        "Hata:$it",
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.SHORT_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.poppins_medium)
                    )
                })
                MotionToast.createColorToast(
                    requireActivity(),
                    "Başarılı",
                    "Ürününüz sepete eklenildi.",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.poppins_medium)
                )

                Log.e("TAG", productValue.toString())
            }
        }

        binding.detailFavoriteButton.setOnClickListener {
//            if (!isFavorite) {
//                viewModel.insertFavorite(yemekValue)
//            } else {
//                viewModel.deleteFavorite(yemekValue.yemekId)
//            }

        }


        val view = binding.root
        return view
    }

    fun showProductDetailsDialog(context: Context, cartResponse: CartResponse) {
        val product = cartResponse.products.first()
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_product_details, null)

        // Layout içindeki view'ları bul ve verileri set et
        val imageThumbnail: ImageView = dialogView.findViewById(R.id.image_thumbnail)
        val textTitle: TextView = dialogView.findViewById(R.id.text_title)
        val textPrice: TextView = dialogView.findViewById(R.id.text_price)
        val textQuantity: TextView = dialogView.findViewById(R.id.text_quantity)
        val textTotal: TextView = dialogView.findViewById(R.id.text_total)
        val textDiscountPercentage: TextView = dialogView.findViewById(R.id.text_discountPercentage)
        val textDiscountedPrice: TextView = dialogView.findViewById(R.id.text_discountedPrice)

        // Resmi yükleyin
        Glide.with(context).load(product.thumbnail).into(imageThumbnail)

        textTitle.text = product.title
        textPrice.text = "Fiyat: ${product.price}"
        textQuantity.text = "Miktar: ${product.quantity}"
        textTotal.text = "Toplam: ${product.total}"
        textDiscountPercentage.text = "İndirim: ${product.discountPercentage}%"
        textDiscountedPrice.text = "İndirimli Fiyat: ${product.discountedPrice} $"

        // Dialog oluştur ve göster
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setPositiveButton("Kapat", null)
            .create()

        dialog.show()
    }

    private fun calculateTotalPrice(singleItemPrice: Int, amount: Int): String {
        return buildString {
            append(singleItemPrice * amount)
            append(" $")
        }
    }

    private fun initFragment(price: Int): Unit {
        singlePrice = price
        binding.amountTextView.text = "1"
        binding.totalPrice.text = calculateTotalPrice(singlePrice, amount)

    }

}