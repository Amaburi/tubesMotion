package com.example.roomcompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roomcompose.Internal.AuthRepository
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.Model.AuthViewModelFactory
import com.example.roomcompose.Screen.SignUp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>() // Activity Rule

    @Test
    fun testSignUpScreenDisplaysProperly() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(
                AuthRepository()
            ))
            SignUp(navController = navController, authViewModel = authViewModel)
        }

        // Ensure screen title is displayed
        composeTestRule.onNodeWithText("Create Your Character").assertIsDisplayed()

        // Check if email input field exists
        composeTestRule.onNodeWithText("example@example.com").assertExists()

        // Check if password input field exists
        composeTestRule.onNodeWithText("Password").assertExists()

        // Check if Sign Up button exists
        composeTestRule.onNodeWithText("Sign Up").assertExists()

        // Check if login redirect text exists
        composeTestRule.onNodeWithText("Already has a character?").assertExists()
    }

    @Test
    fun testUserCanTypeEmailAndPassword() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(AuthRepository()))
            SignUp(navController = navController, authViewModel = authViewModel)
        }

        composeTestRule.waitForIdle()

        // Enter email
        composeTestRule.onNodeWithText("example@example.com").performTextInput("test@gmail.com")

        // Enter password
        composeTestRule.onNodeWithTag("PasswordField").performTextInput("password123")

        // Enter confirm password
        composeTestRule.onNodeWithTag("ConfirmPasswordField").performTextInput("password123")

        // Wait for input processing
        composeTestRule.waitForIdle()

        // Verify inputs
        composeTestRule.onNodeWithText("example@example.com").assertTextEquals("test@gmail.com")
        composeTestRule.onNodeWithTag("PasswordField").assertTextEquals("password123")
        composeTestRule.onNodeWithTag("ConfirmPasswordField").assertTextEquals("password123")
    }

    @Test
    fun testSignUpFailsWhenPasswordsDoNotMatch() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(AuthRepository()))
            SignUp(navController = navController, authViewModel = authViewModel)
        }

        composeTestRule.waitForIdle()

        // Enter email
        composeTestRule.onNodeWithText("example@example.com").performTextInput("test@gmail.com")

        // Enter password
        composeTestRule.onNodeWithTag("PasswordField").performTextInput("password123")

        // Enter different confirm password
        composeTestRule.onNodeWithTag("ConfirmPasswordField").performTextInput("password321")

        // Click Sign Up
        composeTestRule.onNodeWithText("Sign Up").performClick()

        // Verify password error message appears
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Passwords do not match").assertIsDisplayed()
    }

    @Test
    fun testSignUpButtonClick() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(AuthRepository()))
            SignUp(navController = navController, authViewModel = authViewModel)
        }

        // Click the Sign Up button
        composeTestRule.onNodeWithText("Sign Up").performClick()
    }
}
