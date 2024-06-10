package com.example.passwordmanagerassignment

import android.util.Patterns
import java.util.regex.Pattern


fun isValidEmail(email: CharSequence): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: CharSequence): Boolean {
    val pattern: Pattern = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"
    )
    return pattern.matcher(password).matches()
}