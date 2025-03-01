package me.tranduchuy.supabasejetpackcompose

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import me.tranduchuy.supabasejetpackcompose.presentation.feature.addproduct.composables.SuccessScreen
import me.tranduchuy.supabasejetpackcompose.presentation.navigation.AuthenticationDestination
import me.tranduchuy.supabasejetpackcompose.presentation.navigation.ProductListDestination
import me.tranduchuy.supabasejetpackcompose.presentation.navigation.navRegistration
import me.tranduchuy.supabasejetpackcompose.ui.theme.SupabaseJetpackComposeTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var supabaseClient: SupabaseClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller: WindowInsetsController? = window.insetsController
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }


        val currentUser = supabaseClient.auth.currentSessionOrNull()

        setContent {
            SupabaseJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination

                val startDestination = if (currentUser == null) {
                    AuthenticationDestination.route
                } else {
                    ProductListDestination.route
                }

                Scaffold { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = startDestination,
                        Modifier.padding(innerPadding)
                    ) {
                        navRegistration(navController)
                    }
                }
            }
        }
    }

    @Preview(
        name = "SuccessScreen"
    )
    @Composable
    fun SuccessScreenPreview() {
        SupabaseJetpackComposeTheme {
            SuccessScreen(message = "Success", onMoreAction = {}, onNavigateBack = {})
        }
    }

}