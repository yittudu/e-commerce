package com.project.ecommerce.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.ecommerce.MainActivity
import com.project.ecommerce.R
import com.project.ecommerce.data.entity.UserResponse
import com.project.ecommerce.databinding.FragmentErrorBottomSheetBinding
import com.project.ecommerce.databinding.FragmentProfileBinding
import com.project.ecommerce.ui.viewmodels.ProfileFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.full.memberProperties

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var user: UserResponse? = null
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileFragmentViewModel
    private lateinit var gender:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProfileFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.scrollView2.visibility = View.GONE
        binding.progressBar3.visibility = View.VISIBLE

        binding.profileUpdateButton.setOnClickListener {
            Log.e("TAG", "set onclick listener")
            checkValidation(R.drawable.outline_info_24, "Lütfen gerekli alanları doldurunuz...") {
                if (user != null) {
                    Log.e("TAG", "updateuser before")
                    viewModel.updateUser(
                        user!!.id,
                        hashMapOf(
                            "firstName" to binding.profileNameEditText.text.toString().trim(),
                            "lastName" to binding.profileLastnameEditText.text.toString().trim(),
                            "email" to binding.profileEmailEditText.text.toString()
                                .trim(),
                            "username" to binding.profileUsernameEditText.text.toString().trim(),
                            "gender" to gender
                        ),
                    ) { result ->
                        with(binding) {
                            profileNameEditText.setText(result.firstName)
                            profileLastnameEditText.setText(result.lastName)
                            profileEmailEditText.setText(result.email)
                            profileUsernameEditText.setText(result.username)
                            profilePasswordEditText.setText(result.password)

                            Glide.with(requireContext()).load(result.image).into(binding.imageView4);
                            gender=result.gender
                            when (result.gender) {
                                "female" -> {
                                    checkboxFemale.isChecked = true
                                    checkboxMale.isChecked = false
                                }

                                "male" -> {
                                    checkboxFemale.isChecked = false
                                    checkboxMale.isChecked = true
                                }

                                else -> {
                                    checkboxFemale.isChecked = false
                                    checkboxMale.isChecked = false
                                }
                            }
                        }
                        val differences = compareSelectedProperties(viewModel.getUserResult.value!!, result)
                        showBottomSheetDialog(
                            R.drawable.success_gif_im,
                            "Güncelleme işlemi başarılı olmuştur.Değişen alanlar; ${differences.joinToString(",")}"
                        )

                        Log.e("TAG", differences.toString())

                    }

                }
            }
        }

        binding.signOutButton.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.checkboxFemale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.checkboxMale.isChecked = false
                gender="female"
            }
        }

        binding.checkboxMale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.checkboxFemale.isChecked = false
                gender="male"
            }
        }



        viewModel.getUserResult.observe(viewLifecycleOwner) { result ->
            Log.e("TAG", "$result getUserResult")

            if (result != null) {
                user=result
                with(binding) {
                    profileNameEditText.setText(result.firstName)
                    profileLastnameEditText.setText(result.lastName)
                    profileEmailEditText.setText(result.email)
                    profileUsernameEditText.setText(result.username)
                    profilePasswordEditText.setText(result.password)

                    Glide.with(requireContext()).load(result.image).into(binding.imageView4);
                    gender=result.gender
                    when (result.gender) {
                        "female" -> {
                            checkboxFemale.isChecked = true
                            checkboxMale.isChecked = false
                        }

                        "male" -> {
                            checkboxFemale.isChecked = false
                            checkboxMale.isChecked = true
                        }

                        else -> {
                            checkboxFemale.isChecked = false
                            checkboxMale.isChecked = false
                        }
                    }
                }
            }
            binding.scrollView2.visibility = View.VISIBLE
            binding.progressBar3.visibility = View.GONE
        }


        return binding.root
    }

    fun compareSelectedProperties(user1: UserResponse, user2: UserResponse): List<String> {
        val differences = mutableListOf<String>()

        if (user1.firstName != user2.firstName) {
            differences.add("İsim")
        }
        if (user1.lastName != user2.lastName) {
            differences.add("Soyisim")
        }
        if (user1.email != user2.email) {
            differences.add("E-mail")
        }
        if (user1.username != user2.username) {
            differences.add("Kullanıcı Adı")
        }
        if (user1.password != user2.password) {
            differences.add("Şifre")
        }
        if (user1.gender != user2.gender) {
            differences.add("Cinsiyet")
        }

        return differences
    }



    private fun checkValidation(resInt: Int, info: String, onSuccessful: () -> Unit) {
        if (binding.profilePasswordEditText.text.isNullOrBlank() ||binding.profileNameEditText.text.isNullOrBlank() || binding.profileLastnameEditText.text.isNullOrBlank() || binding.profileEmailEditText.text.isNullOrBlank() || binding.profileUsernameEditText.text.isNullOrBlank()) {

            showBottomSheetDialog(resInt, info)

            with(binding) {
                if (profileNameEditText.text.isNullOrBlank()) profileNameEditText.error =
                    "İsim boş olamaz."
                if (profileLastnameEditText.text.isNullOrBlank()) profileLastnameEditText.error =
                    "Soyisim boş olamaz."
                if (profileEmailEditText.text.isNullOrBlank()) profileEmailEditText.error =
                    "E-mail boş olamaz."
                if (profileUsernameEditText.text.isNullOrBlank()) profileUsernameEditText.error =
                    "Kullanıcı adı boş olamaz."
                if (profilePasswordEditText.text.isNullOrBlank()) profilePasswordEditText.error =
                    "Şifre boş olamaz"
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

        Glide.with(requireContext()).load(resInt)
            .into(bottomSheetBinding.errorImage);
        bottomSheetBinding.errorDescription.text = info
        dialog.setCancelable(true)
        dialog.setContentView(bottomSheetBinding.root)
        dialog.show()
    }


}