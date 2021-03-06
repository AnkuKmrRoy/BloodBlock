package com.encora.bloodblock.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.encora.bloodblock.R
import com.encora.bloodblock.databinding.ActivityMainBinding
import com.encora.bloodblock.ui.login.LoginActivity
import com.leopold.mvvm.ui.BindingActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BindingActivity<ActivityMainBinding>() {
    private  val splashViewModel: SplashViewModel by viewModel()

    override fun getLayoutResId() = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.splashViewModel = splashViewModel
        binding.setLifecycleOwner(this)

        splashViewModel.liveData.observe(this, Observer {
            when (it) {
                is SplashState.MainActivity -> {
                    goToMainActivity()
                }
            }
        })
    }

    private fun goToMainActivity() {
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }


}