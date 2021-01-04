package com.mr.mrtestapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mr.mrtestapplication.data.db.MRAssignmentDatabase.Companion.DB_VERSION
import com.mr.mrtestapplication.data.db.dao.MrAssignmentDao
import com.mr.mrtestapplication.data.db.entity.ImagesEntity


@Database(entities = [ImagesEntity::class], version = DB_VERSION, exportSchema = false)

abstract class MRAssignmentDatabase : RoomDatabase() {
    abstract fun getImageDao(): MrAssignmentDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "mr_assignment.db"
        @Volatile
        private var INSTANCE: MRAssignmentDatabase? = null

        fun getInstance(context: Context?): MRAssignmentDatabase{
            synchronized(this){
                var instance:MRAssignmentDatabase?= INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context!!.applicationContext,
                        MRAssignmentDatabase::class.java,
                        "mr_assignment_database"
                    ).build()
                }
                return instance
            }

        }

    }

}