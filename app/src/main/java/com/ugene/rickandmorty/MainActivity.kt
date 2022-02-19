package com.ugene.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.ugene.rickandmorty.components.ApiService
import com.ugene.rickandmorty.components.ApisEntity
import com.ugene.rickandmorty.components.ApisEntityJsonAdapter
import com.ugene.rickandmorty.components.ServiceFactory
import com.ugene.rickandmorty.di.IHeater
import com.ugene.rickandmorty.databinding.ActivityMainBinding
import com.ugene.rickandmorty.ui.dashboard.DashboardViewModel
import dagger.android.AndroidInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    public lateinit var dashboardViewModel: DashboardViewModel

    @Inject
    public lateinit var serviceFactory: ServiceFactory

    @Inject
    lateinit var heater: IHeater

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        lifecycleScope.launchWhenResumed {
            withContext(Dispatchers.IO) {
                val service = serviceFactory.create<ApiService>()
                val entity = service.getApis()
                val b = entity.characters
            }
        }
    }
}

