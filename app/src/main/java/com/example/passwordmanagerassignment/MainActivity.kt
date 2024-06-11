package com.example.passwordmanagerassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.passwordmanagerassignment.ui.theme.PasswordManagerAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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


        val coroutinesScope = rememberCoroutineScope()

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            ConstraintLayout {
                val (btmFIB, homeUI) = createRefs()
                HomeUi()

                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 16.dp, bottom = 16.dp)
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
                        addNewAccountSheetState = addNewAC,
                        dismissReq = {
                            isAddNeACShow = false
                        })
                }

                if (isEditDialogOpen) {
                    AccountDetails(accountDetailsBtmSheetState = editBtmSheet, dismissReq = { isEditDialogOpen = false})
                }

            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        PasswordManagerAssignmentTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                HomeScreen()
            }
        }
    }

}

