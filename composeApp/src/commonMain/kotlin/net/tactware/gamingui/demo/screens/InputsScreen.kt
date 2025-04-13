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
import net.tactware.gamingui.components.ui.inputs.SciFiCheckbox
import net.tactware.gamingui.components.ui.inputs.SciFiNumberField
import net.tactware.gamingui.components.ui.inputs.SciFiPasswordField
import net.tactware.gamingui.components.ui.inputs.SciFiSearchField
import net.tactware.gamingui.components.ui.inputs.SciFiSlider
import net.tactware.gamingui.components.ui.inputs.SciFiTextArea
import net.tactware.gamingui.components.ui.inputs.SciFiTextField

/**
 * Screen showcasing the SciFi UI text field and input components.
 */
@Composable
fun InputsScreen(
    onNavigateUp: () -> Unit
) {
    ComponentShowcaseScaffold(
        title = "Text Fields & Inputs",
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
            // Text Fields
            SciFiPanel(
                title = "Text Fields",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Standard text fields with different states and configurations",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Standard Text Field
                var standardText by remember { mutableStateOf("") }
                SciFiTextField(
                    value = standardText,
                    onValueChange = { standardText = it },
                    label = "Standard Text Field",
                    placeholder = "Enter text here",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Text Field with Icon
                var nameText by remember { mutableStateOf("") }
                SciFiTextField(
                    value = nameText,
                    onValueChange = { nameText = it },
                    label = "Name",
                    placeholder = "Enter your name",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Email Text Field
                var emailText by remember { mutableStateOf("") }
                SciFiTextField(
                    value = emailText,
                    onValueChange = { emailText = it },
                    label = "Email",
                    placeholder = "Enter your email",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled Text Field
                SciFiTextField(
                    value = "Disabled Text Field",
                    onValueChange = { },
                    label = "Disabled",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Error Text Field
                var errorText by remember { mutableStateOf("") }
                SciFiTextField(
                    value = errorText,
                    onValueChange = { errorText = it },
                    label = "Error Example",
                    placeholder = "Enter text here",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Password Field
            SciFiPanel(
                title = "Password Field",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Password field with visibility toggle",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var password by remember { mutableStateOf("") }
                SciFiPasswordField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    placeholder = "Enter your password",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Search Field
            SciFiPanel(
                title = "Search Field",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Search field with clear button",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var searchQuery by remember { mutableStateOf("") }
                SciFiSearchField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    onSearch = { /* Perform search */ },
                    placeholder = "Search...",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Number Field
            SciFiPanel(
                title = "Number Field",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Number field with increment/decrement buttons",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var numberValue by remember { mutableStateOf(0) }
                SciFiNumberField(
                    value = numberValue.toString(),
                    onValueChange = { numberValue = it.toInt() },
                    label = "Quantity",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Text Area
            SciFiPanel(
                title = "Text Area",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Multiline text area for longer content",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var textAreaContent by remember { mutableStateOf("") }
                SciFiTextArea(
                    value = textAreaContent,
                    onValueChange = { textAreaContent = it },
                    label = "Description",
                    placeholder = "Enter a detailed description here...",
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Slider
            SciFiPanel(
                title = "Slider",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Slider with value display",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var sliderValue by remember { mutableStateOf(50f) }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Value: ${sliderValue.toInt()}",
                        style = SciFiTypography.bodyMedium,
                        color = SciFiColors.onSurface
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    SciFiSlider(
                        value = sliderValue,
                        onValueChange = { sliderValue = it },
                        valueRange = 0f..100f,
                        steps = 10,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Disabled Slider
                var disabledSliderValue by remember { mutableStateOf(30f) }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Disabled Slider: ${disabledSliderValue.toInt()}",
                        style = SciFiTypography.bodyMedium,
                        color = SciFiColors.onSurface.copy(alpha = 0.6f)
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    SciFiSlider(
                        value = disabledSliderValue,
                        onValueChange = { disabledSliderValue = it },
                        valueRange = 0f..100f,
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            // Checkbox
            SciFiPanel(
                title = "Checkbox",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Checkbox with label",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var isChecked1 by remember { mutableStateOf(false) }
                SciFiCheckbox(
                    checked = isChecked1,
                    onCheckedChange = { isChecked1 = it },
                    label = "Accept terms and conditions",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                var isChecked2 by remember { mutableStateOf(true) }
                SciFiCheckbox(
                    checked = isChecked2,
                    onCheckedChange = { isChecked2 = it },
                    label = "Subscribe to newsletter",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                SciFiCheckbox(
                    checked = true,
                    onCheckedChange = { },
                    label = "Disabled checkbox (checked)",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                SciFiCheckbox(
                    checked = false,
                    onCheckedChange = { },
                    label = "Disabled checkbox (unchecked)",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
