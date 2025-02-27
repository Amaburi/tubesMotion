package com.example.roomcompose.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.R
import com.example.roomcompose.utils.LoginText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController, authViewModel: AuthViewModel) {
    var EmailValue by remember { mutableStateOf("") };
    var PassValue by remember { mutableStateOf("") };
    var Pass2Value by remember { mutableStateOf("") };
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordVisible2 by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = Color(0xFF232222),
                    shape = RoundedCornerShape(20.dp)
                )

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                modifier = Modifier
                    .padding(top = 69.dp, bottom = 41.dp, start = 10.dp, end = 35.dp)
                    .height(30.dp)
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp),
                        painter = painterResource(id = R.drawable.leftarrow),
                        contentDescription = "",
                        tint = colorResource(id = R.color.yellowpm)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Create Your Character",
                        color = Color(0xFFE2F163),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                }
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 40.dp)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Forge Your Path – Enter the Gaming Realm!",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(88.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 46.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Email",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextField(
                    value = EmailValue,
                    onValueChange = { newText -> EmailValue = newText },
                    placeholder = { Text("example@example.com") },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(bottom = 22.dp, start = 41.dp, end = 41.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .clip(RoundedCornerShape(25.dp))
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(25.dp))
                        .padding(vertical = 10.dp, horizontal = 14.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color(0xFF232222)
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 46.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Password",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextField(
                    value = PassValue,
                    onValueChange = { newText -> PassValue = newText },

                    placeholder = { Text("Password") },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(
                                    id = if (passwordVisible) R.drawable.eyes_passvisible else R.drawable.eyes_passhidden
                                ),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = Color.Black
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(bottom = 22.dp, start = 41.dp, end = 41.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .clip(RoundedCornerShape(25.dp))
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(25.dp))
                        .padding(vertical = 10.dp, horizontal = 14.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color(0xFF232222)
                    )

                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 46.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Confirm Password",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextField(
                    value = Pass2Value,
                    onValueChange = { newText -> Pass2Value = newText },
                    isError = passwordError,
                    placeholder = { Text("Password") },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),

                    visualTransformation = if (passwordVisible2) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible2 = !passwordVisible2 }) {
                            Icon(
                                painter = painterResource(
                                    id = if (passwordVisible2) R.drawable.eyes_passvisible else R.drawable.eyes_passhidden
                                ),
                                contentDescription = if (passwordVisible2) "Hide password" else "Show password",
                                tint = Color.Black
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(bottom = 22.dp, start = 41.dp, end = 41.dp)
                        .border(
                            width = 1.dp,
                            color = if (passwordError) Color.Red else Color.White,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .clip(RoundedCornerShape(25.dp))
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(25.dp))
                        .padding(vertical = 10.dp, horizontal = 14.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color(0xFF232222)
                    )

                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(25.dp)
                            )
                            .padding(bottom = 22.dp, start = 41.dp, end = 41.dp)
                            .fillMaxWidth(),
                        onClick = {
                            val isMatch = Pass2Value == PassValue
                            if (isMatch) {
                                authViewModel.signUp(EmailValue, PassValue) { success, error ->
                                    if (success) {
                                        navController.navigate("login")
                                    } else {
                                        errorMessage = error
                                    }
                                }
                            }else{
                                passwordError = true
                            }
                        },
                        colors = ButtonDefaults.outlinedButtonColors(

                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(vertical = 14.dp)
                        ) {
                            Text(
                                "Sign Up",
                                color = Color(0xFFFFFFFF),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                    LoginText(navController = navController, name = "Login", name2 = "Already has a character?  ", "login")
                }

            }


        }

    }
}