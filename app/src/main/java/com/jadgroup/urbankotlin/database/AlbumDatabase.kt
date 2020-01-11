package com.jadgroup.urbankotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jadgroup.urbankotlin.pojos.Album

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao

    companion object {
        var INSTANCE: AlbumDatabase? = null

        fun getAppDataBase(context: Context): AlbumDatabase? {
            if (INSTANCE == null) {
                synchronized(AlbumDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AlbumDatabase::class.java,
                        "albumDB"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}