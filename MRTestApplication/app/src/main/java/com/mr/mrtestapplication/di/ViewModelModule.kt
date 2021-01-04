package com.leopold.mvvm.di

import com.mr.mrtestapplication.view_model.ImageListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * @author Leopold
 */
val viewModelModule = module {
    viewModel {
        ImageListViewModel(get())
    }

}