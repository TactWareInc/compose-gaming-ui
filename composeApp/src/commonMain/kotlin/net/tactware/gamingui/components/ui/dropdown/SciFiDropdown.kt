package net.tactware.gamingui.components.ui.dropdown

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder
import net.tactware.gamingui.components.theme.sciFiCircuitBorder

/**
 * A sci-fi themed dropdown menu with glowing borders and hover effects.
 *
 * @param selectedOption The currently selected option
 * @param options The list of available options
 * @param onOptionSelected The callback to be invoked when an option is selected
 * @param modifier The modifier to be applied to the dropdown
 * @param enabled Controls the enabled state of the dropdown
 * @param label The optional label to be displayed above the dropdown
 * @param placeholder The text to be displayed when no option is selected
 * @param backgroundColor The background color of the dropdown
 * @param textColor The color of the text
 * @param labelColor The color of the label text
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun <T> SciFiDropdown(
    selectedOption: T?,
    options: List<T>,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    placeholder: String = "Select an option",
    backgroundColor: Color = SciFiColors.backgroundMedium,
    textColor: Color = SciFiColors.onSurface,
    labelColor: Color = SciFiColors.primary,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.5f,
    optionToString: (T) -> String = { it.toString() }
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    
    // Increase glow intensity when hovered, focused, or expanded
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.2f
            expanded || isFocused -> 1.0f
            isHovered -> 0.85f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    // Adjust colors based on state
    val currentBorderColor = when {
        !enabled -> borderColor.copy(alpha = 0.5f)
        expanded || isFocused -> glowColor
        else -> borderColor
    }
    
    val currentTextColor = when {
        !enabled -> textColor.copy(alpha = 0.5f)
        else -> textColor
    }
    
    // Arrow rotation animation
    val arrowRotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "arrowRotation"
    )
    
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
        
        // Dropdown field with sci-fi styling
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (enabled) backgroundColor else backgroundColor.copy(alpha = 0.7f),
                    shape = SciFiShapes.textFieldShape
                )
                .sciFiCircuitBorder(
                    borderColor = currentBorderColor,
                    glowColor = glowColor,
                    glowIntensity = glowIntensity,
                )
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    expanded = !expanded
                }
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display selected option or placeholder
                Text(
                    text = selectedOption?.let { optionToString(it) } ?: placeholder,
                    style = SciFiTypography.bodyLarge,
                    color = if (selectedOption != null) currentTextColor else currentTextColor.copy(alpha = 0.7f)
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Dropdown arrow icon
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = currentTextColor,
                    modifier = Modifier.rotate(arrowRotation)
                )
            }
            
            // Dropdown menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(SciFiColors.backgroundMedium)
                    .border(
                        width = 1.dp,
                        color = glowColor.copy(alpha = 0.7f),
                        shape = SciFiShapes.smallRoundedCorner
                    )
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = optionToString(option),
                                style = SciFiTypography.bodyMedium,
                                color = SciFiColors.onSurface
                            )
                        },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        modifier = Modifier
                            .background(
                                color = if (option == selectedOption) 
                                    SciFiColors.primary.copy(alpha = 0.2f) 
                                else 
                                    Color.Transparent
                            )
                    )
                }
            }
        }
    }
}


/**
 * Orientation options for UI components.
 */
enum class Orientation {
    Vertical,
    Horizontal
}
