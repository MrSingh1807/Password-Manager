package com.example.passwordmanagerassignment.modals

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val acName: String,
    val encryptedUserName: String,
    val encryptedPassword: String,
) {
    constructor(acName: String, encryptedUserName: String, encryptedPassword: String) :
            this(0, acName, encryptedUserName, encryptedPassword)

}