package com.example.passwordmanagerassignment.localDbLayer

import com.example.passwordmanagerassignment.modals.UserInfo
import com.example.passwordmanagerassignment.securityManager.GenerateKeyUtils
import com.example.passwordmanagerassignment.securityManager.SecureAESMechanism
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.jvm.Throws


@ViewModelScoped
class LocalDBRepo @Inject constructor(private val accessDao: AccessUserInfo) {

    init {
        try {
            GenerateKeyUtils.generateSecretKey()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun insertUserInfo(acName: String, acUserName: String, acPassword: String) {
        try {
            val secretKey = GenerateKeyUtils.getSecretKey()
            val userName = SecureAESMechanism.encrypt(acUserName, secretKey)
            val password = SecureAESMechanism.encrypt(acPassword, secretKey)
            val userInfo = UserInfo(acName, userName, password)

            accessDao.insertUserInfo(userInfo)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws
    fun getUserInfo(acName: String): Flow<UserInfo> {
        return accessDao.getUserInfo(acName).map {
            UserInfo(
                it.acName,
                SecureAESMechanism.decrypt(it.encryptedUserName, GenerateKeyUtils.getSecretKey()),
                SecureAESMechanism.decrypt(it.encryptedPassword, GenerateKeyUtils.getSecretKey())
            )
        }
    }

    @Throws
    fun getAllUserInfo(): Flow<List<UserInfo>> {
        return accessDao.getAllUserInfo().map { flow ->
            val secretKey = GenerateKeyUtils.getSecretKey()
            flow.map {
                UserInfo(
                    it.acName,
                    SecureAESMechanism.decrypt(it.encryptedUserName, secretKey),
                    SecureAESMechanism.decrypt(it.encryptedPassword, secretKey)
                )
            }

        }
    }

    fun updateUserInfo(acName: String, acUserName: String, acPassword: String) {
        try {
            val secretKey = GenerateKeyUtils.getSecretKey()
            val userName = SecureAESMechanism.encrypt(acUserName, secretKey)
            val password = SecureAESMechanism.encrypt(acPassword, secretKey)

            accessDao.updateUserInfo(acName, userName, password)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteUserInfo(acName: String) {
        accessDao.deleteUserInfo(acName)
    }

}
