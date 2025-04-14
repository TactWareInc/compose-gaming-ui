package net.tactware.gamingui.components.ui.dropdown.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.ui.dropdown.Orientation


/**
 * A sci-fi themed radio button group with glowing effects.
 *
 * @param selectedOption The currently selected option
 * @param options The list of available options
 * @param onOptionSelected The callback to be invoked when an option is selected
 * @param modifier The modifier to be applied to the radio button group
 * @param enabled Controls the enabled state of the radio button group
 * @param label The optional label to be displayed above the radio button group
 * @param orientation The orientation of the radio button group (vertical or horizontal)
 * @param textColor The color of the text
 * @param selectedColor The color of the selected radio button
 * @param unselectedColor The color of the unselected radio button
 * @param labelColor The color of the label text
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun <T> SciFiRadioGroup(
    selectedOption: T,
    options: List<T>,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    orientation: Orientation = Orientation.Vertical,
    textColor: Color = SciFiColors.onSurface,
    selectedColor: Color = SciFiColors.primary,
    unselectedColor: Color = SciFiColors.backgroundMedium,
    labelColor: Color = SciFiColors.primary,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f,
    optionToString: (T) -> String = { it.toString() }
) {
    Column(modifier = modifier) {
        // Optional label
        if (label != null) {
            Text(
                text = label,
                style = SciFiTypography.labelMedium,
                color = if (enabled) labelColor else labelColor.copy(alpha = 0.5f),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        
        // Radio button group
        if (orientation == Orientation.Vertical) {
            // Vertical layout
            Column(modifier = Modifier.fillMaxWidth()) {
                options.forEach { option ->
                    SciFiRadioButton(
                        selected = option == selectedOption,
                        onClick = { onOptionSelected(option) },
                        enabled = enabled,
                        text = optionToString(option),
                        textColor = textColor,
                        selectedColor = selectedColor,
                        unselectedColor = unselectedColor,
                        glowColor = glowColor,
                        baseGlowIntensity = baseGlowIntensity,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }
            }
        } else {
            // Horizontal layout
            Row(modifier = Modifier.fillMaxWidth()) {
                options.forEachIndexed { index, option ->
                    SciFiRadioButton(
                        selected = option == selectedOption,
                        onClick = { onOptionSelected(option) },
                        enabled = enabled,
                        text = optionToString(option),
                        textColor = textColor,
                        selectedColor = selectedColor,
                        unselectedColor = unselectedColor,
                        glowColor = glowColor,
                        baseGlowIntensity = baseGlowIntensity,
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Add spacer between radio buttons (except after the last one)
                    if (index < options.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}
