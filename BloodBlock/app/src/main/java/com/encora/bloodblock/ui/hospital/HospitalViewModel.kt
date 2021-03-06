package com.encora.bloodblock.ui.hospital

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.encora.bloodblock.core.BaseViewModel
import com.encora.bloodblock.data.db.entity.BloodBankData
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.data.db.entity.LoginData
import com.encora.bloodblock.data.repositories.BloodBlockRepositories
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.ext.castValue

class HospitalViewModel(private val repository: BloodBlockRepositories) : BaseViewModel(), Observable {

    var scannedQRCode = MutableLiveData<String>()

    fun saveRequestData(
        patientID:String,
        hospitalName:String,
        patientName:String,
        patientMobile:String,
        fatherName:String,
        address:String,
        disease:String,
        needed_blood_group:String,
        gender:String,
        remark:String?,
        status:Boolean,
        qr_code: String?
    ){
        insertRequest(HospitalData(patientID,hospitalName,patientName,patientMobile,fatherName,address,disease,needed_blood_group,gender,remark,status,qr_code))
    }


    fun insertRequest(newRequest: HospitalData): Job =
        viewModelScope.launch {
            repository.insetNewRequest(newRequest)
        }



    fun getPatientUniqueID(mNo:String): LiveData<HospitalData> {
        return repository.getPatientID(mNo)
    }


    fun getRequestList(status: Boolean):LiveData<List<HospitalData>>{
       return repository.getRequestUsingStatus(status)
    }


    fun getQRCodeByPatientID(pID:String):LiveData<BloodBankData>{
        return repository.getQRCodeByPatId(pID)
    }

    fun updateHospitalData(hospitalData: HospitalData):Job = viewModelScope.launch{
        repository.updateRequests(hospitalData)
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }
}