package com.encora.bloodblock.ui.patient

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.encora.bloodblock.core.BaseViewModel
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.data.repositories.BloodBlockRepositories

class PatientViewModel(private val repository: BloodBlockRepositories) : BaseViewModel(),
    Observable {

    fun getPatientDetails(mNo:String): LiveData<HospitalData> {
        return repository.getPatientID(mNo)
    }



    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }
}