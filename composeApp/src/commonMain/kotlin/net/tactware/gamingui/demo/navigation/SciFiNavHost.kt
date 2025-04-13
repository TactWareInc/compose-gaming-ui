package net.tactware.gamingui.demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.tactware.gamingui.demo.screens.ButtonsScreen
import net.tactware.gamingui.demo.screens.CardsScreen
import net.tactware.gamingui.demo.screens.DropdownsScreen
import net.tactware.gamingui.demo.screens.HomeScreen
import net.tactware.gamingui.demo.screens.InputsScreen
import net.tactware.gamingui.demo.screens.SwitchesScreen
import net.tactware.gamingui.demo.screens.AnimationsScreen

/**
 * Navigation destinations for the SciFi UI Demo app.
 */
object SciFiDestinations {
    const val HOME_ROUTE = "home"
    const val BUTTONS_ROUTE = "buttons"
    const val INPUTS_ROUTE = "inputs"
    const val DROPDOWNS_ROUTE = "dropdowns"
    const val CARDS_ROUTE = "cards"
    const val SWITCHES_ROUTE = "switches"
    const val ANIMATIONS_ROUTE = "animations"
}

/**
 * Navigation host for the SciFi UI Demo app.
 * 
 * This composable sets up the navigation graph for the demo app.
 */
@Composable
fun SciFiNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = SciFiDestinations.HOME_ROUTE
) {
    val actions = remember(navController) { SciFiNavigationActions(navController) }
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(SciFiDestinations.HOME_ROUTE) {
            HomeScreen(
                onNavigateToButtons = actions.navigateToButtons,
                onNavigateToInputs = actions.navigateToInputs,
                onNavigateToDropdowns = actions.navigateToDropdowns,
                onNavigateToCards = actions.navigateToCards,
                onNavigateToSwitches = actions.navigateToSwitches,
                onNavigateToAnimations = actions.navigateToAnimations
            )
        }
        
        composable(SciFiDestinations.BUTTONS_ROUTE) {
            ButtonsScreen(onNavigateUp = actions.navigateUp)
        }
        
        composable(SciFiDestinations.INPUTS_ROUTE) {
            InputsScreen(onNavigateUp = actions.navigateUp)
        }
        
        composable(SciFiDestinations.DROPDOWNS_ROUTE) {
            DropdownsScreen(onNavigateUp = actions.navigateUp)
        }
        
        composable(SciFiDestinations.CARDS_ROUTE) {
            CardsScreen(onNavigateUp = actions.navigateUp)
        }
        
        composable(SciFiDestinations.SWITCHES_ROUTE) {
            SwitchesScreen(onNavigateUp = actions.navigateUp)
        }
        
        composable(SciFiDestinations.ANIMATIONS_ROUTE) {
            AnimationsScreen(onNavigateUp = actions.navigateUp)
        }
    }
}

/**
 * Navigation actions for the SciFi UI Demo app.
 */
class SciFiNavigationActions(navController: NavHostController) {
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
    
    val navigateToButtons: () -> Unit = {
        navController.navigate(SciFiDestinations.BUTTONS_ROUTE)
    }
    
    val navigateToInputs: () -> Unit = {
        navController.navigate(SciFiDestinations.INPUTS_ROUTE)
    }
    
    val navigateToDropdowns: () -> Unit = {
        navController.navigate(SciFiDestinations.DROPDOWNS_ROUTE)
    }
    
    val navigateToCards: () -> Unit = {
        navController.navigate(SciFiDestinations.CARDS_ROUTE)
    }
    
    val navigateToSwitches: () -> Unit = {
        navController.navigate(SciFiDestinations.SWITCHES_ROUTE)
    }
    
    val navigateToAnimations: () -> Unit = {
        navController.navigate(SciFiDestinations.ANIMATIONS_ROUTE)
    }
}
