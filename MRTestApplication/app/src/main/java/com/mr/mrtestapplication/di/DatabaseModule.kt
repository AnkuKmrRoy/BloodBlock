package com.leopold.mvvm.di

import com.mr.mrtestapplication.data.db.MRAssignmentDatabase
import com.mr.mrtestapplication.data.repositories.ImageRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * @author Leopold
 */
val roomModule = module {
    single { MRAssignmentDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<MRAssignmentDatabase>().getImageDao() }
    single { ImageRepository(get()) }
}


