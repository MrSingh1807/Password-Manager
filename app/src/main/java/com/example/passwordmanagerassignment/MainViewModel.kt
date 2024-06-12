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

    val userInfos = mutableStateOf(emptyList<UserInfo>())


    init {
        getAllUserInfo()
    }


    private fun getAllUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUserInfo().collect {
                userInfos.value = it
            }
        }
    }

    fun deleteUserData(acName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserInfo(acName)
        }
    }

    fun insertUserData(acName: String, userName: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUserInfo(acName, userName, pass)
        }
    }

    fun updateUserData(acName: String, userName: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserInfo(acName, userName, pass)
        }
    }




}