package com.azamat.nycschoolforartech.ui.activity

import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.azamat.nycschoolforartech.R
import com.azamat.nycschoolforartech.base.BaseActivity
import com.azamat.nycschoolforartech.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController }

    private val mainVM by viewModel<MainViewModel>()

    override fun layoutResId() = R.layout.activity_main

    override fun viewDidLoad() {
        initSearchView()
        setListener()
        mainVM.getAllScoresFromNetWorkToRoom()
        observeData()
    }

    private fun observeData() {
        mainVM.error.observe(this, {
            it?.let {
                if (it.contains("2147483647")) {
                    Toast.makeText(this,  "NO INTERNET CONNECTION\nConnect with Internet to get the updated data!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.scoreFragment) {
            navController.navigateUp()
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Initiate SearchBar
     */
    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(keyword: String?): Boolean {
                mainVM.keyword.set(keyword)
                return false
            }

        })

        binding.ibBack.setOnClickListener {
            if (navController.currentDestination?.id == R.id.scoreFragment) {
                navController.navigateUp()
            }
        }
    }


    private fun setListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.schoolsListFragment -> {
                    binding.searchView.visibility = View.VISIBLE
                    binding.ibBack.visibility = View.GONE

                }
                R.id.scoreFragment -> {
                    binding.searchView.visibility = View.GONE
                    binding.ibBack.visibility = View.VISIBLE

                }
            }
        }
    }
}
