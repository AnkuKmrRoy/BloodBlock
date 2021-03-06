package com.encora.bloodblock.data.repositories

import androidx.lifecycle.LiveData
import com.encora.bloodblock.data.db.dao.BloodBlockDao
import com.encora.bloodblock.data.db.entity.BloodBankData
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.data.db.entity.LoginData

class BloodBlockRepositories(private val dao: BloodBlockDao) {

    val products = dao.getAllProducts()


    suspend fun insetNewRequest(newRequest:HospitalData){
        dao.insertNewRequest(newRequest)
    }

    fun  getRequestUsingStatus(status:Boolean):LiveData<List<HospitalData>>{
       return dao.getRequestStatusWise(status)
    }

    suspend fun updateRequests(updatePatientData: HospitalData){
        dao.updateHospitalData(updatePatientData)
    }

    suspend fun deleteRequests(product: HospitalData){
        dao.deleteProducts(product)
    }

    suspend fun deleteAllRequests(){
        dao.deleteAllProducts()
    }



    suspend fun insetUserCredentials(loginCredentials: LoginData){
        dao.insertLoginCredentials(loginCredentials)
    }



     fun  getUser(mNo:String) : LoginData{
       return dao.checkUserExistOrNot(mNo)
    }

     fun getPatientID(mNo:String) : LiveData<HospitalData>{
        return dao.getExistingRequest(mNo)
    }

    fun getQRCodeByPatId(pID:String):LiveData<BloodBankData>{
        return dao.getExistingQRCode(pID);
    }

    suspend fun insetBloodDetails(bloodDetails:BloodBankData){
        dao.insertBloodData(bloodDetails)
    }
}