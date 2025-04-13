package net.tactware.gamingui.demo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import net.tactware.gamingui.components.ui.containers.SciFiPanel
import net.tactware.gamingui.components.ui.switches.SciFiSegmentedToggle
import net.tactware.gamingui.components.ui.switches.SciFiSwitch
import net.tactware.gamingui.components.ui.switches.SciFiToggleSwitch

/**
 * Screen showcasing the SciFi UI switch and toggle components.
 */
@Composable
fun SwitchesScreen(
    onNavigateUp: () -> Unit
) {
    ComponentShowcaseScaffold(
        title = "Switches & Toggles",
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
            // Standard Switches
            SciFiPanel(
                title = "Standard Switches",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Standard switches with different states",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Basic switch
                var switchState1 by remember { mutableStateOf(false) }
                SciFiSwitch(
                    checked = switchState1,
                    onCheckedChange = { switchState1 = it },
                    label = "Basic Switch",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Switch with initial state
                var switchState2 by remember { mutableStateOf(true) }
                SciFiSwitch(
                    checked = switchState2,
                    onCheckedChange = { switchState2 = it },
                    label = "Switch (initially ON)",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled switch (off)
                SciFiSwitch(
                    checked = false,
                    onCheckedChange = { },
                    label = "Disabled Switch (OFF)",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled switch (on)
                SciFiSwitch(
                    checked = true,
                    onCheckedChange = { },
                    label = "Disabled Switch (ON)",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Toggle Switches
            SciFiPanel(
                title = "Toggle Switches",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Toggle switches with ON/OFF labels",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Basic toggle switch
                var toggleState1 by remember { mutableStateOf(false) }
                SciFiToggleSwitch(
                    checked = toggleState1,
                    onCheckedChange = { toggleState1 = it },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Toggle switch with custom labels
                var toggleState2 by remember { mutableStateOf(true) }
                SciFiToggleSwitch(
                    checked = toggleState2,
                    onCheckedChange = { toggleState2 = it },
                    onText = "ACTIVE",
                    offText = "INACTIVE",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Toggle switch with custom colors
                var toggleState3 by remember { mutableStateOf(false) }
                SciFiToggleSwitch(
                    checked = toggleState3,
                    onCheckedChange = { toggleState3 = it },
                    checkedTrackColor = SciFiColors.success,
                    checkedGlowColor = SciFiColors.successGlow,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled toggle switch
                SciFiToggleSwitch(
                    checked = true,
                    onCheckedChange = { },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Segmented Toggles
            SciFiPanel(
                title = "Segmented Toggles",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Segmented toggles for multiple options",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Two-option toggle
                val binaryOptions = listOf("ON", "OFF")
                var selectedBinaryOption by remember { mutableStateOf(binaryOptions[0]) }
                
                SciFiSegmentedToggle(
                    selectedOption = selectedBinaryOption,
                    options = binaryOptions,
                    onOptionSelected = { selectedBinaryOption = it },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Three-option toggle
                val qualityOptions = listOf("Low", "Medium", "High")
                var selectedQualityOption by remember { mutableStateOf(qualityOptions[1]) }
                
                SciFiSegmentedToggle(
                    selectedOption = selectedQualityOption,
                    options = qualityOptions,
                    onOptionSelected = { selectedQualityOption = it },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Four-option toggle
                val speedOptions = listOf("0.5x", "1.0x", "1.5x", "2.0x")
                var selectedSpeedOption by remember { mutableStateOf(speedOptions[1]) }
                
                SciFiSegmentedToggle(
                    selectedOption = selectedSpeedOption,
                    options = speedOptions,
                    onOptionSelected = { selectedSpeedOption = it },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled segmented toggle
                SciFiSegmentedToggle(
                    selectedOption = "Option 2",
                    options = listOf("Option 1", "Option 2", "Option 3"),
                    onOptionSelected = { },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Interactive Demo
            SciFiPanel(
                title = "Interactive Settings Demo",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "This demo shows how switches can be used in a settings panel",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Notifications toggle
                var notificationsEnabled by remember { mutableStateOf(true) }
                SciFiSwitch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    label = "Enable Notifications",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Sound toggle
                var soundEnabled by remember { mutableStateOf(true) }
                SciFiSwitch(
                    checked = soundEnabled,
                    onCheckedChange = { soundEnabled = it },
                    label = "Sound Effects",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Vibration toggle
                var vibrationEnabled by remember { mutableStateOf(false) }
                SciFiSwitch(
                    checked = vibrationEnabled,
                    onCheckedChange = { vibrationEnabled = it },
                    label = "Vibration",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Theme selection
                val themeOptions = listOf("Light", "Dark", "System")
                var selectedTheme by remember { mutableStateOf(themeOptions[1]) }
                
                Text(
                    text = "Theme",
                    style = SciFiTypography.bodyLarge,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                SciFiSegmentedToggle(
                    selectedOption = selectedTheme,
                    options = themeOptions,
                    onOptionSelected = { selectedTheme = it },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Display current settings
                Text(
                    text = "Current Settings:",
                    style = SciFiTypography.titleSmall,
                    color = SciFiColors.primary,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
                
                Text(
                    text = "• Notifications: ${if (notificationsEnabled) "Enabled" else "Disabled"}",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface
                )
                
                Text(
                    text = "• Sound Effects: ${if (soundEnabled) "Enabled" else "Disabled"}",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface
                )
                
                Text(
                    text = "• Vibration: ${if (vibrationEnabled) "Enabled" else "Disabled"}",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface
                )
                
                Text(
                    text = "• Theme: $selectedTheme",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface
                )
            }
        }
    }
}
