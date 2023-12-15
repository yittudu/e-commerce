package com.project.ecommerce.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.ecommerce.R
import com.project.ecommerce.databinding.CartAlertDialogBinding
import com.project.ecommerce.databinding.FragmentCartBinding
import com.project.ecommerce.ui.adapters.CartRecyclerViewAdapter
import com.project.ecommerce.ui.viewmodels.CartFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CartFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.cartRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.cartList.observe(viewLifecycleOwner) { userCartResponse ->
            if (userCartResponse.carts.isNotEmpty()){
                val cart = userCartResponse.carts.first()
                var adapter = CartRecyclerViewAdapter(cart.products, requireContext())
                binding.cartRecyclerView.adapter = adapter

                binding.totalPriceFabButton.text = buildString {
                    append(cart.discountedTotal.toString())
                    append(" $ ")
                }
            }
        }

        binding.totalPriceFabButton.setOnClickListener {
            showConfirmDialogBox()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartList()
    }

    private fun showConfirmDialogBox() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        val view =
            CartAlertDialogBinding.inflate(LayoutInflater.from(requireContext()), null, false)
        dialog.setContentView(view.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        view.positiveButton.setOnClickListener {
            dialog.dismiss()
            MotionToast.createColorToast(
                requireActivity(),
                "Başarılı",
                "Siparişiniz başarıyla alındı.",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.SHORT_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.poppins_medium)
            )

        }
        view.negativeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


}