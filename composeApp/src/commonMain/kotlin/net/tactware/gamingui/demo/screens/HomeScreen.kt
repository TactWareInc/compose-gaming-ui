package net.tactware.gamingui.demo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.ui.buttons.SciFiButton
import net.tactware.gamingui.components.ui.buttons.SciFiIconButton
import net.tactware.gamingui.components.ui.containers.components.SciFiCard
import net.tactware.gamingui.components.ui.containers.components.SciFiPanel

/**
 * Home screen for the SciFi UI Demo app.
 * 
 * This screen serves as the main menu for navigating to different component showcases.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToButtons: () -> Unit,
    onNavigateToInputs: () -> Unit,
    onNavigateToDropdowns: () -> Unit,
    onNavigateToCards: () -> Unit,
    onNavigateToSwitches: () -> Unit,
    onNavigateToAnimations: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SciFi UI Components", style = SciFiTypography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SciFiColors.background,
                    titleContentColor = SciFiColors.primary
                )
            )
        },
        containerColor = SciFiColors.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SciFiPanel(
                title = "SciFi UI Component Library",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Welcome to the SciFi UI Component Library Demo. This showcase demonstrates custom Jetpack Compose UI components designed with a sci-fi gaming aesthetic.",
                    style = SciFiTypography.bodyLarge,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Text(
                    text = "Select a component category below to explore:",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            SciFiButton(
                onClick = onNavigateToButtons,
                text = "Buttons",
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SciFiButton(
                onClick = onNavigateToInputs,
                text = "Text Fields & Inputs",
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SciFiButton(
                onClick = onNavigateToDropdowns,
                text = "Dropdowns & Selectors",
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SciFiButton(
                onClick = onNavigateToCards,
                text = "Cards & Panels",
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SciFiButton(
                onClick = onNavigateToSwitches,
                text = "Switches & Toggles",
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SciFiButton(
                onClick = onNavigateToAnimations,
                text = "Animations & Effects",
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            SciFiCard(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(
                    text = "This library is designed for Kotlin Multiplatform and works on both desktop and mobile platforms with appropriate adaptations for each platform.",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface
                )
            }
        }
    }
}

/**
 * Common scaffold for component showcase screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentShowcaseScaffold(
    title: String,
    onNavigateUp: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, style = SciFiTypography.titleLarge) },
                navigationIcon = {
                    SciFiIconButton(
                        onClick = onNavigateUp,
                        icon = Icons.Default.ArrowBack,
                        shape = SciFiShapes.smallCutCorner
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SciFiColors.background,
                    titleContentColor = SciFiColors.primary,
                    navigationIconContentColor = SciFiColors.primary
                )
            )
        },
        containerColor = SciFiColors.background,
        content = content
    )
}
