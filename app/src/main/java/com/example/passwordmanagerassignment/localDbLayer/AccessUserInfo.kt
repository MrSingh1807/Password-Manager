package com.example.passwordmanagerassignment.localDbLayer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.passwordmanagerassignment.modals.UserInfo
import kotlinx.coroutines.flow.Flow


@Dao
interface AccessUserInfo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(userInfo: UserInfo)

    @Query("SELECT * FROM UserInfo WHERE acName = :acName")
    fun getUserInfo(acName: String): Flow<UserInfo>

    @Query("SELECT * FROM UserInfo")
    fun getAllUserInfo(): Flow<List<UserInfo>>

    @Query("UPDATE UserInfo SET  encryptedUserName = :userName,  encryptedPassword = :userPassword WHERE acName = :acName")
    fun updateUserInfo(acName: String, userName: String, userPassword: String)

    @Query("DELETE FROM UserInfo WHERE acName = :acName")
    fun deleteUserInfo(acName: String)

}