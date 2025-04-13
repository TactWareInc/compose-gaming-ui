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
                .drawWithContent {
                    drawContent()
                    if (enabled) {
                        // Draw sci-fi border with glow effect
                        drawSciFiBorder(
                            borderColor = currentBorderColor.copy(alpha = glowIntensity),
                            borderWidth = 1.5f,
                            notchSize = 4f,
                            segmentLength = if (expanded) 30f else 20f,
                            gapLength = if (expanded) 2f else 4f
                        )
                    }
                }
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
 * A sci-fi themed segmented control with glowing borders and hover effects.
 *
 * @param selectedOption The currently selected option
 * @param options The list of available options
 * @param onOptionSelected The callback to be invoked when an option is selected
 * @param modifier The modifier to be applied to the segmented control
 * @param enabled Controls the enabled state of the segmented control
 * @param label The optional label to be displayed above the segmented control
 * @param backgroundColor The background color of the segmented control
 * @param selectedColor The background color of the selected segment
 * @param textColor The color of the text
 * @param selectedTextColor The color of the text in the selected segment
 * @param labelColor The color of the label text
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun <T> SciFiSegmentedControl(
    selectedOption: T,
    options: List<T>,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    selectedColor: Color = SciFiColors.primary,
    textColor: Color = SciFiColors.onSurfaceMedium,
    selectedTextColor: Color = SciFiColors.onPrimary,
    labelColor: Color = SciFiColors.primary,
    borderColor: Color = SciFiColors.border,
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
        
        // Segmented control with sci-fi styling
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    color = if (enabled) backgroundColor else backgroundColor.copy(alpha = 0.7f),
                    shape = SciFiShapes.smallRoundedCorner
                )
                .drawWithContent {
                    drawContent()
                    if (enabled) {
                        // Draw sci-fi border with glow effect
                        drawSciFiBorder(
                            borderColor = borderColor.copy(alpha = baseGlowIntensity),
                            borderWidth = 1.5f,
                            notchSize = 4f,
                            segmentLength = 20f,
                            gapLength = 3f
                        )
                    }
                }
                .padding(2.dp)
        ) {
            options.forEachIndexed { index, option ->
                val isSelected = option == selectedOption
                val interactionSource = remember { MutableInteractionSource() }
                val isHovered by interactionSource.collectIsHoveredAsState()
                
                // Increase glow intensity when hovered or selected
                val glowIntensity by animateFloatAsState(
                    targetValue = when {
                        !enabled -> 0.2f
                        isSelected -> 1.0f
                        isHovered -> 0.85f
                        else -> 0f
                    },
                    label = "segmentGlowIntensity"
                )
                
                // Individual segment
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(36.dp)
                        .background(
                            color = when {
                                !enabled -> (if (isSelected) selectedColor else Color.Transparent).copy(alpha = 0.5f)
                                isSelected -> selectedColor
                                else -> Color.Transparent
                            },
                            shape = SciFiShapes.smallRoundedCorner
                        )
                        .drawWithContent {
                            drawContent()
                            if (enabled && (isSelected || isHovered)) {
                                // Draw glow effect for selected or hovered segment
                                drawSciFiBorder(
                                    borderColor = glowColor.copy(alpha = glowIntensity),
                                    borderWidth = 1f,
                                    notchSize = 2f,
                                    segmentLength = 10f,
                                    gapLength = 2f
                                )
                            }
                        }
                        .clickable(
                            enabled = enabled && !isSelected,
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onOptionSelected(option)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = optionToString(option),
                        style = SciFiTypography.bodyMedium,
                        color = when {
                            !enabled -> (if (isSelected) selectedTextColor else textColor).copy(alpha = 0.5f)
                            isSelected -> selectedTextColor
                            else -> textColor
                        }
                    )
                }
                
                // Add spacer between segments (except after the last one)
                if (index < options.size - 1) {
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
        }
    }
}

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

/**
 * A sci-fi themed radio button with glowing effects.
 *
 * @param selected Whether the radio button is currently selected
 * @param onClick The callback to be invoked when the radio button is clicked
 * @param modifier The modifier to be applied to the radio button
 * @param enabled Controls the enabled state of the radio button
 * @param text The text to be displayed next to the radio button
 * @param textColor The color of the text
 * @param selectedColor The color of the selected radio button
 * @param unselectedColor The color of the unselected radio button
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
private fun SciFiRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    textColor: Color = SciFiColors.onSurface,
    selectedColor: Color = SciFiColors.primary,
    unselectedColor: Color = SciFiColors.backgroundMedium,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when hovered or selected
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.2f
            selected -> 1.0f
            isHovered -> 0.85f
            else -> baseGlowIntensity
        },
        label = "radioGlowIntensity"
    )
    
    // Adjust colors based on state
    val radioColor = when {
        !enabled -> (if (selected) selectedColor else unselectedColor).copy(alpha = 0.5f)
        selected -> selectedColor
        else -> unselectedColor
    }
    
    val currentTextColor = when {
        !enabled -> textColor.copy(alpha = 0.5f)
        else -> textColor
    }
    
    Row(
        modifier = modifier.clickable(
            enabled = enabled,
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Radio button with sci-fi styling
        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .width(20.dp)
                .height(20.dp)
                .background(
                    color = radioColor,
                    shape = androidx.compose.foundation.shape.CircleShape
                )
                .drawWithContent {
                    drawContent()
                    if (enabled) {
                        // Draw glow effect
                        val borderColor = if (selected) glowColor else SciFiColors.border
                        drawSciFiBorder(
                            borderColor = borderColor.copy(alpha = glowIntensity),
                            borderWidth = 1.5f,
                            notchSize = 0f, // No notches for circular shape
                            segmentLength = 5f,
                            gapLength = 1f
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .background(
                            color = SciFiColors.onPrimary,
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                )
            }
        }
        
        // Radio button text
        Text(
            text = text,
            style = SciFiTypography.bodyMedium,
            color = currentTextColor
        )
    }
}

/**
 * Orientation options for UI components.
 */
enum class Orientation {
    Vertical,
    Horizontal
}
