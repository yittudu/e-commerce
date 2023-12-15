package com.project.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.NavHostFragment
import com.project.ecommerce.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val smoothbar = binding.bottomBar
        val popupMenu = PopupMenu(this, smoothbar)
        val menuResId = R.menu.bottom_nav_menu
        popupMenu.menuInflater.inflate(menuResId, popupMenu.menu)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainFragmentView) as NavHostFragment
        val navController = navHostFragment.navController

        smoothbar.setupWithNavController(popupMenu.menu,navController)

    }
}