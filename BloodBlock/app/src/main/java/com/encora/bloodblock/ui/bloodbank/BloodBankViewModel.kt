package com.encora.bloodblock.ui.bloodbank

import androidx.databinding.Observable
import androidx.lifecycle.viewModelScope
import com.encora.bloodblock.core.BaseViewModel
import com.encora.bloodblock.data.db.entity.BloodBankData
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.data.repositories.BloodBlockRepositories
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BloodBankViewModel(private val repository: BloodBlockRepositories) : BaseViewModel(), Observable {

     fun saveBloodDetails(patientID:String,qr_code:String){
         insertRequestBlood(BloodBankData(patientID,qr_code))
     }

    fun insertRequestBlood(requestedBlood: BloodBankData): Job =
        viewModelScope.launch {
            repository.insetBloodDetails(requestedBlood)
        }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }
}