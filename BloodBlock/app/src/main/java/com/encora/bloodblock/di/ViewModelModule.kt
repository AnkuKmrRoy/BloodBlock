package com.leopold.mvvm.di

import com.encora.bloodblock.ui.bloodbank.BloodBankViewModel
import com.encora.bloodblock.ui.hospital.HospitalViewModel
import com.encora.bloodblock.ui.login.LoginViewModel
import com.encora.bloodblock.ui.patient.PatientViewModel
import com.encora.bloodblock.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * @author Leopold
 */
val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HospitalViewModel(get()) }
    viewModel { BloodBankViewModel(get()) }
    viewModel { PatientViewModel(get()) }
}