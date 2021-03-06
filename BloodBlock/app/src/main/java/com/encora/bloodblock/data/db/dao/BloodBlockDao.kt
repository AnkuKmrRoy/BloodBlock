package com.encora.bloodblock.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.encora.bloodblock.data.db.entity.BloodBankData
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.data.db.entity.LoginData

@Dao
interface BloodBlockDao {

    @Query("SELECT * FROM hospitalData")
    fun getAllProducts():LiveData<List<HospitalData>>

    @Query("DELETE FROM hospitalData")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM hospitalData WHERE status=:status ")
    fun getRequestStatusWise(status: Boolean): LiveData<List<HospitalData>>

    @Query("SELECT * FROM LoginUsers WHERE mobile_no = :mNo ")
    fun checkUserExistOrNot(mNo: String): LoginData

    @Query("SELECT * FROM hospitalData WHERE patientMobile = :mNo ")
    fun getExistingRequest(mNo:String) :LiveData<HospitalData>

    @Query("SELECT * FROM blood_bank_data WHERE patientID = :pID")
    fun getExistingQRCode(pID:String):LiveData<BloodBankData>

    @Insert
    suspend fun insertNewRequest(newRequest: HospitalData)

    @Insert
    suspend fun insertBloodData(bloodDetails: BloodBankData)


    @Delete
    suspend fun deleteProducts(product: HospitalData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProducts(product: HospitalData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHospitalData(hospitalData: HospitalData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoginCredentials(loginCredentials: LoginData)

}