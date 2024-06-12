package com.example.passwordmanagerassignment

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.passwordmanagerassignment.ui.theme.PasswordManagerAssignmentTheme
import com.example.passwordmanagerassignment.ui.theme.SF_PRO_MEDIUM
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.text.TextStyle
import com.example.passwordmanagerassignment.modals.UserInfo

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordManagerAssignmentTheme {
                HomeScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreen() {
        val addNewAC = rememberModalBottomSheetState()
        var isAddNeACShow by rememberSaveable {
            mutableStateOf(false)
        }
        val editBtmSheet = rememberModalBottomSheetState()
        var isEditDialogOpen by rememberSaveable {
            mutableStateOf(false)
        }

        val aboutUser = remember {
            mutableStateOf(UserInfo("", "", ""))
        }
        val editUser = remember {
            mutableStateOf(UserInfo("", "", ""))
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            ConstraintLayout {
                val (btmFIB, homeUI) = createRefs()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 20.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .padding(vertical = 15.dp, horizontal = 16.dp),
                        text = stringResource(id = R.string.home_title),
                        style = TextStyle(
                            fontFamily = SF_PRO_MEDIUM,
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                    )
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(color = Color.LightGray)
                    )

                    LazyColumn {
                        items(mViewModel.userInfos.value) {
                            UserInfoItem(it.acName) {
                                isEditDialogOpen = true
                                aboutUser.value = it
                            }
                        }
                    }

                }

                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 16.dp, bottom = 25.dp)
                        .constrainAs(btmFIB) {
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .clickable { isAddNeACShow = true },
                    painter = painterResource(id = R.drawable.ic_home_fab),
                    contentDescription = "Floating BTN"
                )

                if (isAddNeACShow) {
                    AddNewAccount(
                        userInfo = editUser.value,
                        addNewAccountSheetState = addNewAC,
                        dismissReq = {
                            isAddNeACShow = false
                        },
                        addNewACListener = { acName, userName, passWord, isUpdate ->
                            if (isUpdate) mViewModel.updateUserData(acName, userName, passWord)
                            else mViewModel.insertUserData(acName, userName, passWord)
//                            isAddNeACShow = false
                        }
                    )
                }

                if (isEditDialogOpen) {
                    AccountDetails(
                        userInfo = aboutUser.value,
                        accountDetailsBtmSheetState = editBtmSheet,
                        dismissReq = { isEditDialogOpen = false },
                        editListener = {
                            editUser.value = aboutUser.value
                            isEditDialogOpen = false
                            isAddNeACShow = true
                        },
                        deleteListener = { mViewModel.deleteUserData(aboutUser.value.acName) }
                    )
                }

            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        PasswordManagerAssignmentTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                HomeScreen()
            }
        }
    }

}

