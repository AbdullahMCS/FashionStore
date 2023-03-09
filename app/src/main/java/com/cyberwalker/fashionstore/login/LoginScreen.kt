package com.cyberwalker.fashionstore.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.ui.theme.*
import com.cyberwalker.fashionstore.util.AuthenticationResult

@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(), // Question what is this?
    onAction: (actions: LoginScreenActions) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        LoginScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
    }
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onAction: (actions: LoginScreenActions) -> Unit
) {
    // Question: since the "remember" variables are on the "LoginScreenContent" Level,
    // does that mean if they change all the composables inside this function will be redrawn?
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordVisible = remember { mutableStateOf(false) }

    val state = remember { viewModel.userLoginStatus }.collectAsState()

    Column(
        modifier = modifier
            .padding(40.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Top()

        OutlinedTextField(
            label = { Text(text = "Email") },
            value = email.value,
            shape = RoundedCornerShape(12.dp),
            onValueChange = { email.value = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = highlight,
                focusedLabelColor = dark
            )
        )

        OutlinedTextField(
            label = { Text(text = "Password") },
            value = password.value,
            shape = RoundedCornerShape(12.dp),
            onValueChange = { password.value = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = highlight,
                focusedLabelColor = dark
            ),
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = image, null)
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(
                onClick = {
                    viewModel.login(email.value.text, password.value.text)
                    when (state.value) {
                        is AuthenticationResult.Success -> {
                            onAction(LoginScreenActions.LoadHome)
                        }
                        is AuthenticationResult.Error -> {  }
                        is AuthenticationResult.Loading -> {  }
                    }
                    Log.d("LoginScreen", "Success: ${state.value is AuthenticationResult.Success}")
                    Log.d("LoginScreen", "Error: ${state.value is AuthenticationResult.Error}")
                    Log.d("LoginScreen", "Loading: ${state.value is AuthenticationResult.Loading}")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = highlight),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .widthIn(120.dp)
                    .defaultMinSize(minHeight = 40.dp)
            ) {
                Text(
                    text = "Login",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = {
                    //viewModel.loginWithGitHub()
                    onAction(LoginScreenActions.LoadHome)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = highlight),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .widthIn(120.dp)
                    .defaultMinSize(minHeight = 40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icons8_github),
                    modifier = Modifier
                        .size(30.dp),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Composable
fun Top() {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Login",
            style = MaterialTheme.typography.large.copy(
                shadow = Shadow(
                    color = dark,
                    offset = Offset(0.0f, 4.0f),
                    blurRadius = 5f
                )
            ),
        )

        Image(
            modifier = Modifier
                .defaultMinSize(minWidth = 293.dp, minHeight = 387.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.ic_indicator),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentDescription = null
        )
    }
}

sealed class LoginScreenActions {
    object LoadHome : LoginScreenActions()
}