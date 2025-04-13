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
import net.tactware.gamingui.components.ui.dropdown.Orientation
import net.tactware.gamingui.components.ui.dropdown.SciFiDropdown
import net.tactware.gamingui.components.ui.dropdown.SciFiRadioGroup
import net.tactware.gamingui.components.ui.dropdown.SciFiSegmentedControl

/**
 * Screen showcasing the SciFi UI dropdown and selector components.
 */
@Composable
fun DropdownsScreen(
    onNavigateUp: () -> Unit
) {
    ComponentShowcaseScaffold(
        title = "Dropdowns & Selectors",
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
            // Standard Dropdown
            SciFiPanel(
                title = "Dropdown Menu",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Standard dropdown menu with selectable options",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
                var selectedOption by remember { mutableStateOf<String?>(null) }
                
                SciFiDropdown(
                    selectedOption = selectedOption,
                    options = options,
                    onOptionSelected = { selectedOption = it },
                    label = "Select an option",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Dropdown with initial selection
                var selectedPlanet by remember { mutableStateOf("Earth") }
                val planets = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
                
                SciFiDropdown(
                    selectedOption = selectedPlanet,
                    options = planets,
                    onOptionSelected = { selectedPlanet = it },
                    label = "Select a planet",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled dropdown
                SciFiDropdown(
                    selectedOption = "Disabled Option",
                    options = listOf("Disabled Option", "Cannot Select", "Not Available"),
                    onOptionSelected = { },
                    label = "Disabled dropdown",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Segmented Control
            SciFiPanel(
                title = "Segmented Control",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Segmented control for selecting from a small set of options",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                val viewOptions = listOf("Day", "Week", "Month", "Year")
                var selectedViewOption by remember { mutableStateOf(viewOptions[0]) }
                
                SciFiSegmentedControl(
                    selectedOption = selectedViewOption,
                    options = viewOptions,
                    onOptionSelected = { selectedViewOption = it },
                    label = "Time Period",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Segmented control with custom colors
                val colorOptions = listOf("Blue", "Green", "Red", "Purple")
                var selectedColorOption by remember { mutableStateOf(colorOptions[0]) }
                
                val colorMap = mapOf(
                    "Blue" to SciFiColors.primary,
                    "Green" to SciFiColors.success,
                    "Red" to SciFiColors.error,
                    "Purple" to SciFiColors.secondary
                )
                
                SciFiSegmentedControl(
                    selectedOption = selectedColorOption,
                    options = colorOptions,
                    onOptionSelected = { selectedColorOption = it },
                    label = "Color Theme",
                    selectedColor = colorMap[selectedColorOption] ?: SciFiColors.primary,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled segmented control
                SciFiSegmentedControl(
                    selectedOption = "Option 2",
                    options = listOf("Option 1", "Option 2", "Option 3"),
                    onOptionSelected = { },
                    label = "Disabled segmented control",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Radio Group
            SciFiPanel(
                title = "Radio Group",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Radio button group for selecting a single option",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Vertical radio group
                val difficultyOptions = listOf("Easy", "Medium", "Hard", "Extreme")
                var selectedDifficulty by remember { mutableStateOf(difficultyOptions[1]) }
                
                SciFiRadioGroup(
                    selectedOption = selectedDifficulty,
                    options = difficultyOptions,
                    onOptionSelected = { selectedDifficulty = it },
                    label = "Difficulty Level",
                    orientation = Orientation.Vertical,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Horizontal radio group
                val alignmentOptions = listOf("Left", "Center", "Right")
                var selectedAlignment by remember { mutableStateOf(alignmentOptions[1]) }
                
                SciFiRadioGroup(
                    selectedOption = selectedAlignment,
                    options = alignmentOptions,
                    onOptionSelected = { selectedAlignment = it },
                    label = "Text Alignment",
                    orientation = Orientation.Horizontal,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled radio group
                SciFiRadioGroup(
                    selectedOption = "Option 1",
                    options = listOf("Option 1", "Option 2", "Option 3"),
                    onOptionSelected = { },
                    label = "Disabled radio group",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Interactive Demo
            SciFiPanel(
                title = "Interactive Selection Demo",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "This demo shows how selections can work together",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Category selection
                val categories = listOf("Weapons", "Armor", "Consumables", "Crafting")
                var selectedCategory by remember { mutableStateOf(categories[0]) }
                
                SciFiSegmentedControl(
                    selectedOption = selectedCategory,
                    options = categories,
                    onOptionSelected = { selectedCategory = it },
                    label = "Item Category",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Item selection based on category
                val itemsByCategory = mapOf(
                    "Weapons" to listOf("Laser Rifle", "Plasma Sword", "Quantum Blaster", "Gravity Hammer"),
                    "Armor" to listOf("Nano Shield", "Energy Armor", "Stealth Suit", "Heavy Plating"),
                    "Consumables" to listOf("Health Pack", "Energy Cell", "Shield Booster", "Stim Pack"),
                    "Crafting" to listOf("Rare Metal", "Power Crystal", "Synthetic Fiber", "Circuit Board")
                )
                
                var selectedItem by remember { mutableStateOf(itemsByCategory[selectedCategory]?.first()) }
                
                SciFiDropdown(
                    selectedOption = selectedItem,
                    options = itemsByCategory[selectedCategory] ?: emptyList(),
                    onOptionSelected = { selectedItem = it },
                    label = "Select Item",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Display selected item
                if (selectedItem != null) {
                    Text(
                        text = "Selected: $selectedCategory - $selectedItem",
                        style = SciFiTypography.bodyLarge,
                        color = SciFiColors.primary,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
