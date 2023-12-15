package com.project.ecommerce.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.ecommerce.HomeActivity
import com.project.ecommerce.R
import com.project.ecommerce.data.entity.UserManager
import com.project.ecommerce.databinding.FragmentErrorBottomSheetBinding
import com.project.ecommerce.databinding.FragmentLoginBinding
import com.project.ecommerce.ui.viewmodels.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: LoginFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.loginEmailEditText.setText("kminchelle")
        binding.loginPasswordEditText.setText("0lelplR")
        binding.loginButton.setOnClickListener {
            checkValidation(R.drawable.outline_info_24, "Lütfen gerekli yerleri doldurunuz.") {
                viewModel.login(
                    binding.loginEmailEditText.text.toString().trim(),
                    binding.loginPasswordEditText.text.toString().trim()
                )
            }

        }
        binding.signupButton.setOnClickListener {

        }
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                MotionToast.createColorToast(
                    requireActivity(),
                    "Başarılı",
                    "Kullanıcı Tokeni: ${user.token}",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.poppins_medium)
                )
                UserManager.setUser(user)
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }

        viewModel.exception.observe(viewLifecycleOwner) { error ->
            showBottomSheetDialog(R.drawable.failure_gif_im, error.toString())

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkValidation(resInt: Int, info: String, onSuccessful: () -> Unit) {
        if (binding.loginEmailEditText.text.isNullOrBlank() || binding.loginPasswordEditText.text.isNullOrBlank()) {
            showBottomSheetDialog(resInt, info)
            with(binding) {
                if (loginEmailEditText.text.isNullOrBlank()) loginEmailEditText.error =
                    "Kullanıcı adı boş olamaz."
                if (loginPasswordEditText.text.isNullOrBlank()) loginPasswordEditText.error =
                    "Şifre boş olamaz."
            }
        } else {
            onSuccessful()

        }

    }

    private fun showBottomSheetDialog(resInt: Int, info: String) {
        val dialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = FragmentErrorBottomSheetBinding.inflate(
            LayoutInflater.from(requireContext()),
            null,
            false
        )
        Glide.with(requireContext()).load(resInt).into(bottomSheetBinding.errorImage);

        bottomSheetBinding.errorDescription.text = info
        dialog.setCancelable(true)
        dialog.setContentView(bottomSheetBinding.root)
        dialog.show()
    }


}