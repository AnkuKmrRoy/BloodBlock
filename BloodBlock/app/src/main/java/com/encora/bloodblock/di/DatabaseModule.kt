package com.leopold.mvvm.di

import com.encora.bloodblock.data.db.BloodBlockDatabase
import com.encora.bloodblock.data.repositories.BloodBlockRepositories

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * @author Leopold
 */
val roomModule = module {
    single { BloodBlockDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<BloodBlockDatabase>().getHospital() }
    single { BloodBlockRepositories(get()) }

}


