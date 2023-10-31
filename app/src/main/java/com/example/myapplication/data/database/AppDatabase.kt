package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.database.buildDao.BuildingInfoDao
import com.example.myapplication.data.database.buildDao.BuildingInfoDbModel
import com.example.myapplication.data.database.mapDao.MapInfoDao
import com.example.myapplication.data.database.mapDao.MapInfoDbModel
import com.example.myapplication.data.database.roomDao.RoomInfoDao
import com.example.myapplication.data.database.roomDao.RoomInfoDbModel

@Database(
    entities = [RoomInfoDbModel::class, BuildingInfoDbModel::class, MapInfoDbModel::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                    val instance =
                        Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DB_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    db = instance
                    return instance
                }
            }
        }

    abstract fun roomInfoDao(): RoomInfoDao
    abstract fun buildingInfoDao(): BuildingInfoDao
    abstract fun mapInfoDao(): MapInfoDao

}