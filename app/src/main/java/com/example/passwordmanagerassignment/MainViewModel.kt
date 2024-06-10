package com.example.passwordmanagerassignment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanagerassignment.localDbLayer.LocalDBRepo
import com.example.passwordmanagerassignment.modals.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: LocalDBRepo) : ViewModel() {

    private val allUsers = mutableStateOf(emptyList<UserInfo>())

    private fun getAllUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUserInfo().collect {
                allUsers.value = it
            }
        }
    }

    private fun deleteUserData(acName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserInfo(acName)
        }
    }

    private fun insertUserData(acName: String, userName: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUserInfo(acName, userName, pass)
        }
    }

    private fun updateUserData(acName: String, userName: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserInfo(acName, userName, pass)
        }
    }


}