package com.example.sampleapplication.mainUi

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.authentication.AuthenticationActivity
import com.example.sampleapplication.databinding.ActivityMainBinding
import com.example.sampleapplication.session.PrefManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseAuthProvider


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var auth: FirebaseAuth
    lateinit var prefManager: PrefManager
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appbar.appbar)

        auth = FirebaseAuth.getInstance()
        prefManager = PrefManager(this)

        navController = findNavController(R.id.mainFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph,binding.drawerLayout)

        binding.navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)


        transparentStatusBar()
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dashboard->{
                    navController.navigate(R.id.dashboard)
                }
                R.id.tabLayout -> {
                    navController.navigate(R.id.tabLayoutFragment)
                }
                R.id.firebaseDb -> {
                    navController.navigate(R.id.firebaseDbFragment)
                }
                R.id.gMap -> {
                    navController.navigate(R.id.googleMapFragment)
                }
                R.id.logout -> {
                    logoutFromApplication()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

   override fun onSupportNavigateUp(): Boolean {
       val navController = findNavController(R.id.mainFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun logoutFromApplication() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Logout")
        dialog.setMessage("Are you sure want to logout?")
        dialog.setPositiveButton(android.R.string.yes) { _, _ ->
            signOut()
        }
        dialog.setNegativeButton(android.R.string.no) { _, _ ->
            Toast.makeText(this, "Logout canceled", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

    private fun signOut(){
        startActivity(Intent(this, AuthenticationActivity::class.java))
        this.finish()
        auth.signOut()
        prefManager.clear()
    }

    private fun transparentStatusBar() {
        window.apply { decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR }
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }
}