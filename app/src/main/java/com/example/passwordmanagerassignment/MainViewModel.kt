package com.example.passwordmanagerassignment

import androidx.lifecycle.ViewModel
import com.example.passwordmanagerassignment.localDbLayer.LocalDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: LocalDBRepo) : ViewModel() {





}