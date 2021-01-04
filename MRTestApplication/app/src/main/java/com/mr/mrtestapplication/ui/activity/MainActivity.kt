package com.mr.mrtestapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.leopold.mvvm.ui.BindingActivity
import com.mr.mrtestapplication.R
import com.mr.mrtestapplication.databinding.ActivityMainBinding
import com.mr.mrtestapplication.utils.MrTestConstants
import com.mr.mrtestapplication.view_model.ImageListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {
    private  val imageListViewModel: ImageListViewModel by viewModel()
    private lateinit var navigationController: NavController
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.imageViewModel = imageListViewModel
        binding.setLifecycleOwner(this)
        val host = NavHostFragment.create(R.navigation.fragment_navigation)
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, host)
            .setPrimaryNavigationFragment(host).commit()

    }


}

