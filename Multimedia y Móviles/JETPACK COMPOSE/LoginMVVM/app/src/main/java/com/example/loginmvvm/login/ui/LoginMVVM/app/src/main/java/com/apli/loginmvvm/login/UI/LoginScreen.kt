package com.example.loginmvvm.login.ui


import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apli.loginmvvm.R
import com.apli.loginmvvm.login.UI.LoginViewModel


@Composable
fun LoginScreen(loginVM: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), loginVM)
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Footer(mod: Modifier) {
    Column(modifier = mod.fillMaxWidth()){
        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFF9f9f9f),
            modifier = Modifier.fillMaxWidth()

        )
    }

}

@Composable
fun Body(mod: Modifier, loginVM: LoginViewModel) {
    val email by loginVM.email.observeAsState("")
    val password by loginVM.password.observeAsState("")
    val isLoginEnable by loginVM.isLoginEnabled.observeAsState(false)
    Column(modifier = mod) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            loginVM.onEmailChange(it)
            loginVM.onLoginChange(email,password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginVM.onPasswordChange(it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "LogoFB",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue as David",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(color = Color(0xFF9F9F9F),
            thickness = 1.dp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 6.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFFB5B5B5)
        )
        HorizontalDivider(color = Color(0xFF9F9F9F),
            thickness = 1.dp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SignUp(){

}


@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(
        onClick = {  },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text("Log In")
    }
}

@Composable
fun ForgotPassword(mod: Modifier) {
    Text(
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = mod
    )
}

@Composable
fun Password(password: String, onPassChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onPassChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFFB2B2B2),
            focusedContainerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Email(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFFB2B2B2),
            focusedContainerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ImageLogo(mod: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = mod
    )
}

@Composable
fun Header(mod: Modifier) {
    val activity = LocalContext.current as? Activity
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "Close APP",
        modifier = mod.clickable { activity?.finish() })
}
