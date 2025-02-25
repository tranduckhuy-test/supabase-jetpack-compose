package me.tranduchuy.supabasejetpackcompose.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import me.tranduchuy.supabasejetpackcompose.presentation.feature.addproduct.AddProductScreen
import me.tranduchuy.supabasejetpackcompose.presentation.feature.productdetails.ProductDetailsScreen
import me.tranduchuy.supabasejetpackcompose.presentation.feature.productlist.ProductListScreen
import me.tranduchuy.supabasejetpackcompose.presentation.feature.signin.SignInScreen
import me.tranduchuy.supabasejetpackcompose.presentation.feature.signup.SignUpScreen

fun NavGraphBuilder.navRegistration(navController: NavController) {
    composable(ProductListDestination.route) {
        ProductListScreen(
            navController = navController
        )
    }

    composable(AuthenticationDestination.route) {
        SignInScreen(
            navController = navController
        )
    }

    composable(SignUpDestination.route) {
        SignUpScreen(
            navController = navController
        )
    }

    composable(AddProductDestination.route) {
        AddProductScreen(
            navController = navController
        )
    }

    composable(
        route = "${ProductDetailsDestination.route}/{${ProductDetailsDestination.PRODUCT_ID}}",
        arguments = ProductDetailsDestination.arguments
    ) { navBackStackEntry ->
        val productId =
            navBackStackEntry.arguments?.getString(ProductDetailsDestination.PRODUCT_ID)
        ProductDetailsScreen(
            productId = productId,
            navController = navController,
        )
    }

}