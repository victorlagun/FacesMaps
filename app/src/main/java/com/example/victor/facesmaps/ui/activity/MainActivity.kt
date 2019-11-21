package com.example.victor.facesmaps.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.victor.facesmaps.R
import com.example.victor.facesmaps.util.alert
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    var isNetworkAvailable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        if (!isNetworkAvailable) {
                            alert(this@MainActivity, "Подключение восстановлено")
                            isNetworkAvailable = true
                        }
                    }

                    override fun onLost(network: Network?) {
                        alert(this@MainActivity, "Отсутствует подключение к сети")
                        isNetworkAvailable = false
                    }
                })
            }
        }
    }
}
