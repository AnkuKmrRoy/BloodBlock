package com.mr.mrtestapplication

import android.app.Application
import com.leopold.mvvm.di.roomModule
import com.leopold.mvvm.di.viewModelModule
import org.koin.android.ext.android.startKoin

class MRAssignmentApplication:Application() {

    companion object {
        lateinit var instance: MRAssignmentApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
            roomModule,
            viewModelModule
        ))
    }
}