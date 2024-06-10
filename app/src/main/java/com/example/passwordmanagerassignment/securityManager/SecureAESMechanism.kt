package com.example.passwordmanagerassignment.securityManager

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import android.util.Base64

object SecureAESMechanism {

    private const val ALGO = "AES/CBC/PKCS5Padding"
    private const val IV = "RandomInitVector" // 16 bytes IV

    fun encrypt(value: String, key: SecretKey): String {
        val cipher = Cipher.getInstance(ALGO)
        val iv = IvParameterSpec(IV.toByteArray(Charsets.UTF_8))
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val encrypted = cipher.doFinal(value.toByteArray())
        return Base64.encodeToString(encrypted, Base64.DEFAULT)
    }

    fun decrypt(encrypted: String, key: SecretKey): String {
        val cipher = Cipher.getInstance(ALGO)
        val iv = IvParameterSpec(IV.toByteArray(Charsets.UTF_8))
        cipher.init(Cipher.DECRYPT_MODE, key, iv)
        val original = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT))
        return String(original)
    }
}
