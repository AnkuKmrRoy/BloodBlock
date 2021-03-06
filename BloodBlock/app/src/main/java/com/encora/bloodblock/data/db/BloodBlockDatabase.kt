package com.encora.bloodblock.data.db

import android.content.Context
import androidx.room.*
import com.encora.bloodblock.data.db.BloodBlockDatabase.Companion.DB_VERSION
import com.encora.bloodblock.data.db.dao.BloodBlockDao
import com.encora.bloodblock.data.db.entity.BloodBankData
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.data.db.entity.LoginData

@Database(entities = [LoginData::class,HospitalData::class,BloodBankData::class], version = DB_VERSION, exportSchema = false)
abstract class BloodBlockDatabase :RoomDatabase(){
    abstract fun getHospital(): BloodBlockDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "blood_block.db"
        @Volatile
        private var INSTANCE: BloodBlockDatabase? = null

        fun getInstance(context: Context?): BloodBlockDatabase{
            synchronized(this){
                var instance:BloodBlockDatabase?= INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context!!.applicationContext,
                        BloodBlockDatabase::class.java,
                        DB_NAME
                    ).build()
                }
               return instance
            }

        }
    }

}