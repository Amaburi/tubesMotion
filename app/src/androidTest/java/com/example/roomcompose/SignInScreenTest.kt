package com.example.roomcompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roomcompose.Internal.AuthRepository
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.Model.AuthViewModelFactory
import com.example.roomcompose.Screen.SignIn
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>() // Activity Rule

    @Test
    fun checkIfSignInScreenDisplaysProperly() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            // Get ViewModel properly using Local Context
            val authViewModel: AuthViewModel = viewModel(
                factory = AuthViewModelFactory(AuthRepository())
            )

            SignIn(navController = navController, authViewModel = authViewModel)
        }

        // Check if "Enter The Arena" is displayed
        composeTestRule.onNodeWithText("Enter The Arena").assertIsDisplayed()

        // Check if email input field exists
        composeTestRule.onNodeWithText("example@example.com").assertIsDisplayed()

        // Check if password input field exists
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()

        // Check if Sign In button exists
        composeTestRule.onNodeWithText("Sign In").assertIsDisplayed()
    }

    @Test
    fun testUserCanTypeEmailAndPassword() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel(
                factory = AuthViewModelFactory(AuthRepository())
            )
            SignIn(navController = navController, authViewModel = authViewModel)
        }

        // Wait for UI to fully render
        composeTestRule.waitForIdle()

        // Ensure fields exist
        composeTestRule.onNodeWithTag("EmailField").assertExists()
        composeTestRule.onNodeWithTag("PasswordField").assertExists()

        // Enter email
        composeTestRule.onNodeWithTag("EmailField").performTextInput("test@gmail.com")

        // Enter password
        composeTestRule.onNodeWithTag("PasswordField").performTextInput("password123")

        // Wait for input processing
        composeTestRule.waitForIdle()


        // Verify inputs
        composeTestRule.onNodeWithTag("EmailField").assertTextEquals("test@gmail.com")
        composeTestRule.onNodeWithTag("PasswordField").assertTextEquals("password123")

    }



    @Test
    fun testSignInButtonClick() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            // Correct way to initialize ViewModel
            val authViewModel: AuthViewModel = viewModel(
                factory = AuthViewModelFactory(AuthRepository())
            )

            SignIn(navController = navController, authViewModel = authViewModel)
        }

        // Click the Sign In button
        composeTestRule.onNodeWithText("Sign In").performClick()
    }
}
