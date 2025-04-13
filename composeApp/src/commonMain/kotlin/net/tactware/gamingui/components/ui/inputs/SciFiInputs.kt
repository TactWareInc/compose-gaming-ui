package net.tactware.gamingui.components.ui.inputs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder

/**
 * A sci-fi themed search field with glowing borders and an optional search icon.
 *
 * @param value The input text to be shown in the search field
 * @param onValueChange The callback that is triggered when the input service updates the text
 * @param onSearch The callback that is triggered when the search action is performed
 * @param modifier The modifier to be applied to the search field
 * @param enabled Controls the enabled state of the search field
 * @param placeholder The optional placeholder to be displayed when the search field is empty
 * @param searchIcon The optional icon to be displayed for the search action
 * @param backgroundColor The background color of the search field
 * @param textColor The color of the input text
 * @param placeholderColor The color of the placeholder text
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "Search...",
    searchIcon: ImageVector? = null,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    textColor: Color = SciFiColors.onSurface,
    placeholderColor: Color = SciFiColors.onSurfaceMedium,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.5f
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SciFiTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            enabled = enabled,
            placeholder = placeholder,
            singleLine = true,
            keyboardActions = androidx.compose.foundation.text.KeyboardActions(
                onSearch = { onSearch(value) }
            ),
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                imeAction = androidx.compose.ui.text.input.ImeAction.Search
            ),
            backgroundColor = backgroundColor,
            textColor = textColor,
            placeholderColor = placeholderColor,
            borderColor = borderColor,
            glowColor = glowColor,
            baseGlowIntensity = baseGlowIntensity
        )
        
        if (searchIcon != null) {
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { onSearch(value) },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = searchIcon,
                    contentDescription = "Search",
                    tint = if (enabled) glowColor else glowColor.copy(alpha = 0.5f)
                )
            }
        }
    }
}

/**
 * A sci-fi themed slider with glowing track and thumb.
 *
 * @param value The current value of the slider
 * @param onValueChange The callback that is triggered when the slider value changes
 * @param modifier The modifier to be applied to the slider
 * @param enabled Controls the enabled state of the slider
 * @param valueRange The range of values that the slider can take
 * @param steps The number of discrete steps that the slider can take
 * @param label The optional label to be displayed above the slider
 * @param showValue Whether to show the current value next to the slider
 * @param trackColor The color of the slider track
 * @param activeTrackColor The color of the active part of the slider track
 * @param thumbColor The color of the slider thumb
 * @param labelColor The color of the label text
 * @param valueColor The color of the value text
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    label: String? = null,
    showValue: Boolean = false,
    trackColor: Color = SciFiColors.backgroundMedium,
    activeTrackColor: Color = SciFiColors.primary,
    thumbColor: Color = SciFiColors.primaryGlow,
    labelColor: Color = SciFiColors.primary,
    valueColor: Color = SciFiColors.onSurface,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when focused or hovered
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.2f
            isFocused -> 1.0f
            isHovered -> 0.85f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    Column(modifier = modifier) {
        // Optional label and value
        if (label != null || showValue) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (label != null) {
                    Text(
                        text = label,
                        style = SciFiTypography.labelMedium,
                        color = if (enabled) labelColor else labelColor.copy(alpha = 0.5f)
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                if (showValue) {
                    Text(
                        text = value.toString(),
                        style = SciFiTypography.labelMedium,
                        color = if (enabled) valueColor else valueColor.copy(alpha = 0.5f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(4.dp))
        }
        
        // Custom sci-fi slider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = trackColor,
                    shape = SciFiShapes.smallRoundedCorner
                )
                .drawWithContent {
                    drawContent()
                    if (enabled) {
                        // Draw sci-fi border with glow effect
                        drawSciFiBorder(
                            borderColor = glowColor.copy(alpha = glowIntensity * 0.5f),
                            borderWidth = 1f,
                            notchSize = 3f,
                            segmentLength = 15f,
                            gapLength = 3f
                        )
                    }
                }
        ) {
            // Active track
            val progress = (value - valueRange.start) / (valueRange.endInclusive - valueRange.start)
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(24.dp)
                    .background(
                        color = if (enabled) activeTrackColor else activeTrackColor.copy(alpha = 0.5f),
                        shape = SciFiShapes.smallRoundedCorner
                    )
            )
            
            // Thumb
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
                    .height(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterStart)
                        .border(
                            width = 1.dp,
                            color = if (enabled) thumbColor else thumbColor.copy(alpha = 0.5f),
                            shape = SciFiShapes.smallRoundedCorner
                        )
                        .background(
                            color = if (enabled) thumbColor.copy(alpha = 0.3f) else thumbColor.copy(alpha = 0.1f),
                            shape = SciFiShapes.smallRoundedCorner
                        )
                        .then(
                            Modifier.drawWithContent {
                                drawContent()
                                if (enabled) {
                                    // Draw glow effect around thumb
                                    drawSciFiBorder(
                                        borderColor = glowColor.copy(alpha = glowIntensity),
                                        borderWidth = 1.5f,
                                        notchSize = 2f,
                                        segmentLength = 5f,
                                        gapLength = 1f
                                    )
                                }
                            }
                        )
                )
            }
            
            // Slider interaction
            androidx.compose.material3.Slider(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
                enabled = enabled,
                valueRange = valueRange,
                steps = steps,
                interactionSource = interactionSource,
                colors = androidx.compose.material3.SliderDefaults.colors(
                    thumbColor = Color.Transparent,
                    activeTrackColor = Color.Transparent,
                    inactiveTrackColor = Color.Transparent,
                    activeTickColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent
                )
            )
        }
    }
}

/**
 * A sci-fi themed checkbox with glowing borders and custom styling.
 *
 * @param checked Whether the checkbox is currently checked
 * @param onCheckedChange The callback to be invoked when the checkbox is clicked
 * @param modifier The modifier to be applied to the checkbox
 * @param enabled Controls the enabled state of the checkbox
 * @param label The optional label to be displayed next to the checkbox
 * @param checkedColor The color when the checkbox is checked
 * @param uncheckedColor The color when the checkbox is unchecked
 * @param labelColor The color of the label text
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    checkedColor: Color = SciFiColors.primary,
    uncheckedColor: Color = SciFiColors.backgroundMedium,
    labelColor: Color = SciFiColors.onSurface,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when hovered or pressed
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.2f
            isPressed -> 1.0f
            isHovered -> 0.85f
            checked -> 0.8f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    // Adjust colors based on state
    val backgroundColor = when {
        !enabled -> (if (checked) checkedColor else uncheckedColor).copy(alpha = 0.5f)
        checked -> checkedColor
        else -> uncheckedColor
    }
    
    val borderColor = when {
        !enabled -> (if (checked) glowColor else SciFiColors.border).copy(alpha = 0.5f)
        checked -> glowColor
        else -> SciFiColors.border
    }
    
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Custom checkbox with sci-fi styling
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = backgroundColor,
                    shape = SciFiShapes.smallRoundedCorner
                )
                .then(
                    Modifier.drawWithContent {
                        drawContent()
                        if (enabled) {
                            // Draw sci-fi border with glow effect

                            drawSciFiBorder(
                                borderColor = borderColor.copy(alpha = glowIntensity),
                                borderWidth = 1.5f,
                                notchSize = 3f,
                                segmentLength = 8f,
                                gapLength = 2f
                            )
                        }
                    }
                )
                .clickable(
                    onClick = { if (enabled) onCheckedChange(!checked) },
                    interactionSource = interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = SciFiColors.onPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        
        // Optional label
        if (label != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = SciFiTypography.bodyMedium,
                color = if (enabled) labelColor else labelColor.copy(alpha = 0.5f),
                modifier = Modifier.clickable(enabled = enabled) { onCheckedChange(!checked) }
            )
        }
    }
}
