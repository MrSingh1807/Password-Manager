package com.example.passwordmanagerassignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerassignment.ui.theme.SF_PRO_BLACK
import com.example.passwordmanagerassignment.ui.theme.SF_PRO_BOLD
import com.example.passwordmanagerassignment.ui.theme.SF_PRO_HEAVY
import com.example.passwordmanagerassignment.ui.theme.SF_PRO_MEDIUM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object HomeUI {

    @Composable
    fun MainUI() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 16.dp),
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


            /* val dummyList = (1..10).toList()
             LazyColumn {
                 items(dummyList) {
                     UserInfoItem()
                 }
             }*/


        }
        AddNewAccount()
//        AccountDetails()
    }
}


@Composable
fun UserInfoItem(
    acName: String = "Google"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .wrapContentSize()
            .padding(horizontal = 16.dp)
            .clip(CircleShape)
            .background(color = Color.White)
            .border(.5.dp, Color.LightGray, CircleShape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = acName,
            modifier = Modifier
                .padding(start = 16.dp)
                .padding(vertical = 16.dp),
            style = TextStyle(fontFamily = SF_PRO_MEDIUM, fontSize = 20.sp, color = Color.Black),
        )
        Text(
            text = "* * * * * *", textAlign = TextAlign.Center, color = Color.Gray,
            modifier = Modifier.padding(horizontal = 15.dp), fontSize = 18.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.right_arrow),
            contentDescription = " ",
            modifier = Modifier.padding(end = 20.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewAccount(
    addNewACListener: (acName: String, userName: String, passWord: String) -> Unit = { _, _, _ -> }
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.White,
        cursorColor = Color.Black,
        containerColor = Color.Transparent
    )
    val addNewAccountSheetState = rememberModalBottomSheetState()

    var accountName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }

    ModalBottomSheet(
        sheetState = addNewAccountSheetState,
        containerColor = colorResource(id = R.color.btm_dialog_background),
        onDismissRequest = { }) {
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            TextField(
                value = accountName, onValueChange = {
                    accountName = it
                }, colors = textFieldColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .border(.5.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                placeholder = { Text(text = "Account Name") }
            )
            TextField(
                value = userName, onValueChange = {
                    userName = it
                }, colors = textFieldColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .border(.5.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                placeholder = { Text(text = "Username / Email") }
            )
            TextField(
                value = passWord, onValueChange = {
                    passWord = it
                }, colors = textFieldColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .border(.5.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                placeholder = { Text(text = "Password") }
            )

            Button(
                onClick = {
                    addNewACListener.invoke(accountName, userName, passWord)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.add_new_btn_color))
            ) {
                Text(
                    text = "Add New Account", modifier = Modifier.padding(vertical = 5.dp),
                    style = TextStyle(fontFamily = SF_PRO_MEDIUM, color = Color.White)
                )
            }

        }
    }


    CoroutineScope(Dispatchers.Main).launch {
        addNewAccountSheetState.show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetails(
    acName: String = "Facebook",
    userName: String = "Example@gmail.com",
    password: String = "********",
    editListener: () -> Unit = {},
    deleteListener: () -> Unit = {}
) {

    val accountDetailsBtmSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = accountDetailsBtmSheetState,
        containerColor = colorResource(id = R.color.btm_dialog_background),
        onDismissRequest = { }) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Account Details", modifier = Modifier.padding(bottom = 20.dp),
                style = TextStyle(
                    color = colorResource(id = R.color.edit_title_color),
                    fontFamily = SF_PRO_HEAVY,
                    fontSize = 16.sp
                ),
            )

            Text(
                text = "Account Type",
                style = TextStyle(
                    color = Color.Gray, fontFamily = SF_PRO_MEDIUM, fontSize = 9.sp
                )
            )
            Text(
                text = acName, modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
                style = TextStyle(
                    color = Color.Black, fontFamily = SF_PRO_BOLD, fontSize = 15.sp
                )
            )

            Text(
                text = "Username / Email",
                style = TextStyle(
                    color = Color.Gray, fontFamily = SF_PRO_MEDIUM, fontSize = 9.sp
                )
            )
            Text(
                text = userName, modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
                style = TextStyle(
                    color = Color.Black, fontFamily = SF_PRO_BOLD, fontSize = 15.sp
                )
            )

            Text(
                text = "Password",
                style = TextStyle(
                    color = Color.Gray, fontFamily = SF_PRO_MEDIUM, fontSize = 9.sp
                )
            )
            Text(
                text = password, modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
                style = TextStyle(
                    color = Color.Black, fontFamily = SF_PRO_BOLD, fontSize = 15.sp
                )
            )

            Row {
                Button(
                    onClick = { editListener.invoke() },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.add_new_btn_color)),
                    elevation = ButtonDefaults.elevatedButtonElevation(1.dp)
                ) {
                    Text(
                        text = "Edit", modifier = Modifier.padding(vertical = 5.dp),
                        style = TextStyle(fontFamily = SF_PRO_MEDIUM, color = Color.White)
                    )
                }
                Button(
                    onClick = { deleteListener.invoke() },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.delete_btn_color)),
                    elevation = ButtonDefaults.elevatedButtonElevation(1.dp)
                ) {
                    Text(
                        text = "Delete", modifier = Modifier.padding(vertical = 5.dp),
                        style = TextStyle(fontFamily = SF_PRO_MEDIUM, color = Color.White)
                    )
                }
            }
        }
    }

    CoroutineScope(Dispatchers.Main).launch {
        accountDetailsBtmSheetState.show()
    }
}


@Composable
@Preview(showBackground = true)
fun ShowPreview() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.home_background))
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.home_background))
        ) {
            HomeUI.MainUI()
        }
    }

}
