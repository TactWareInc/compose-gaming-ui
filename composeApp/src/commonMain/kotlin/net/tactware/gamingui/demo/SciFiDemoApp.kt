package net.tactware.gamingui.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.tactware.gamingui.components.theme.SciFiTheme
import net.tactware.gamingui.demo.navigation.SciFiNavHost

/**
 * Main entry point for the SciFi UI Demo application.
 * 
 * This composable sets up the SciFi theme and navigation for the demo app.
 */
@Composable
fun SciFiDemoApp() {
    SciFiTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                SciFiNavHost()
            }
        }
    }
}
