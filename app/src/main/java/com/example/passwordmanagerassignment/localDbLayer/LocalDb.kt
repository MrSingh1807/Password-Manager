package com.example.passwordmanagerassignment.localDbLayer

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.passwordmanagerassignment.modals.UserInfo


@Database(entities = [UserInfo::class], version = 1)
abstract class LocalDb : RoomDatabase() {
    abstract fun accessUserInfo(): AccessUserInfo

}