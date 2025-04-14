package net.tactware.gamingui.demo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.ui.buttons.SciFiAsymButton
import net.tactware.gamingui.components.ui.buttons.SciFiButton
import net.tactware.gamingui.components.ui.buttons.SciFiButton2
import net.tactware.gamingui.components.ui.buttons.SciFiCircleIconButton
import net.tactware.gamingui.components.ui.buttons.SciFiDangerButton
import net.tactware.gamingui.components.ui.buttons.SciFiGlowButton
import net.tactware.gamingui.components.ui.buttons.SciFiIconButton
import net.tactware.gamingui.components.ui.buttons.SciFiSuccessButton
import net.tactware.gamingui.components.ui.containers.components.SciFiCard
import net.tactware.gamingui.components.ui.containers.components.SciFiPanel


/**
 * Screen showcasing the SciFi UI button components.
 */
@Composable
fun ButtonsScreen(
    onNavigateUp: () -> Unit
) {
    ComponentShowcaseScaffold(
        title = "Buttons",
        onNavigateUp = onNavigateUp
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Standard Buttons
            SciFiPanel(
                title = "Standard Buttons",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Standard buttons with different states and colors",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Primary Button
                SciFiButton(
                    onClick = { /* Do something */ },
                    text = "Primary Button",
                    modifier = Modifier.fillMaxWidth(),
                )
                
                Spacer(modifier = Modifier.height(8.dp))

                SciFiButton2 (
                    onClick = { /* Do something */ },
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Text(
                            text = "Primary Button 2",
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                SciFiGlowButton (
                    onClick = { /* Do something */ },
                    modifier = Modifier.fillMaxWidth() .height(60.dp),
                    text = "Test"
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Danger Button
                SciFiDangerButton(
                    onClick = { /* Do something */ },
                    text = "Danger Button",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Success Button
                SciFiSuccessButton(
                    onClick = { /* Do something */ },
                    text = "Success Button",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Disabled Button
                SciFiButton(
                    onClick = { /* Do something */ },
                    text = "Disabled Button",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Icon Buttons
            SciFiPanel(
                title = "Icon Buttons",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Buttons with icons in different configurations",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Icon-only buttons
                    SciFiIconButton(
                        onClick = { /* Do something */ },
                        icon = Icons.Default.Add,
                    )
                    
                    SciFiIconButton(
                        onClick = { /* Do something */ },
                        icon = Icons.Default.Favorite,
                    )

                    SciFiCircleIconButton(
                        onClick = { /* Do something */ },
                        icon = Icons.Default.Close,
                    )
                    
                    SciFiIconButton(
                        onClick = { /* Do something */ },
                        icon = Icons.Default.Settings,
                        enabled = false
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Buttons with icons
                SciFiButton(
                    onClick = { /* Do something */ },
                    text = "Button with Leading Icon",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                SciFiButton(
                    onClick = { /* Do something */ },
                    text = "Button with Trailing Icon",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Button Sizes
            SciFiPanel(
                title = "Button Sizes",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Buttons in different sizes",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                SciFiButton(
                    onClick = { /* Do something */ },
                    text = "Large Button",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                SciFiButton(
                    onClick = { /* Do something */ },
                    text = "Medium Button",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                SciFiButton(
                    onClick = { /* Do something */ },
                    text = "Small Button",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Interactive Button Demo
            SciFiCard(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                var clickCount by remember { mutableStateOf(0) }
                
                Text(
                    text = "Interactive Button Demo",
                    style = SciFiTypography.titleMedium,
                    color = SciFiColors.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Text(
                    text = "Click count: $clickCount",
                    style = SciFiTypography.bodyLarge,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SciFiButton(
                        onClick = { clickCount++ },
                        text = "Increment",
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    SciFiButton(
                        onClick = { if (clickCount > 0) clickCount-- },
                        text = "Decrement",
                        enabled = clickCount > 0
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    SciFiButton(
                        onClick = { clickCount = 0 },
                        text = "Reset",
                        enabled = clickCount > 0
                    )
                }
            }
        }
    }
}
