package net.tactware.gamingui.components.ui.switches

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder
import kotlin.math.roundToInt

/**
 * A sci-fi themed switch with glowing effects and animations.
 *
 * @param checked Whether the switch is currently checked
 * @param onCheckedChange The callback to be invoked when the switch is clicked
 * @param modifier The modifier to be applied to the switch
 * @param enabled Controls the enabled state of the switch
 * @param label The optional label to be displayed next to the switch
 * @param checkedTrackColor The color of the track when the switch is checked
 * @param uncheckedTrackColor The color of the track when the switch is unchecked
 * @param checkedThumbColor The color of the thumb when the switch is checked
 * @param uncheckedThumbColor The color of the thumb when the switch is unchecked
 * @param labelColor The color of the label text
 * @param checkedGlowColor The color of the glow effect when checked
 * @param uncheckedGlowColor The color of the glow effect when unchecked
 * @param trackShape The shape of the switch track
 * @param thumbShape The shape of the switch thumb
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    checkedTrackColor: Color = SciFiColors.primary,
    uncheckedTrackColor: Color = SciFiColors.backgroundMedium,
    checkedThumbColor: Color = SciFiColors.primaryGlow,
    uncheckedThumbColor: Color = SciFiColors.border,
    labelColor: Color = SciFiColors.onSurface,
    checkedGlowColor: Color = SciFiColors.primaryGlow,
    uncheckedGlowColor: Color = SciFiColors.borderDim,
    trackShape: Shape = SciFiShapes.switchTrackShape,
    thumbShape: Shape = SciFiShapes.switchThumbShape,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when hovered
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.3f
            isHovered -> 0.9f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    // Determine colors based on state
    val trackColor = when {
        !enabled -> (if (checked) checkedTrackColor else uncheckedTrackColor).copy(alpha = 0.5f)
        checked -> checkedTrackColor
        else -> uncheckedTrackColor
    }
    
    val thumbColor = when {
        !enabled -> (if (checked) checkedThumbColor else uncheckedThumbColor).copy(alpha = 0.5f)
        checked -> checkedThumbColor
        else -> uncheckedThumbColor
    }
    
    val glowColor = if (checked) checkedGlowColor else uncheckedGlowColor
    
    // Animate thumb position
    val thumbPosition by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "thumbPosition"
    )
    
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Switch track and thumb
        Box(
            modifier = Modifier
                .width(52.dp)
                .height(28.dp)
                .background(
                    color = trackColor,
                    shape = trackShape
                )
                .drawWithContent {
                    drawContent()
                    if (enabled) {
                        // Draw sci-fi border with glow effect
                        drawSciFiBorder(
                            borderColor = glowColor.copy(alpha = glowIntensity),
                            borderWidth = 1.5f,
                            notchSize = 4f,
                            segmentLength = if (checked) 15f else 10f,
                            gapLength = if (checked) 2f else 4f
                        )
                    }
                }
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onCheckedChange(!checked) }
                )
        ) {
            // Thumb
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .offset {
                        IntOffset(
                            x = (thumbPosition * 24.dp.toPx()).roundToInt(),
                            y = 0
                        )
                    }
                    .padding(2.dp)
                    .background(
                        color = thumbColor,
                        shape = thumbShape
                    )
                    .drawWithContent {
                        drawContent()
                        if (enabled) {
                            // Draw glow effect around thumb
                            drawSciFiBorder(
                                borderColor = glowColor.copy(alpha = glowIntensity),
                                borderWidth = 1f,
                                notchSize = 2f,
                                segmentLength = 5f,
                                gapLength = 1f
                            )
                        }
                    }
                    .align(Alignment.CenterStart)
            )
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

/**
 * A sci-fi themed toggle switch with ON/OFF labels and glowing effects.
 *
 * @param checked Whether the switch is currently checked
 * @param onCheckedChange The callback to be invoked when the switch is clicked
 * @param modifier The modifier to be applied to the switch
 * @param enabled Controls the enabled state of the switch
 * @param onText The text to display when the switch is ON
 * @param offText The text to display when the switch is OFF
 * @param checkedTrackColor The color of the track when the switch is checked
 * @param uncheckedTrackColor The color of the track when the switch is unchecked
 * @param checkedThumbColor The color of the thumb when the switch is checked
 * @param uncheckedThumbColor The color of the thumb when the switch is unchecked
 * @param onTextColor The color of the ON text
 * @param offTextColor The color of the OFF text
 * @param checkedGlowColor The color of the glow effect when checked
 * @param uncheckedGlowColor The color of the glow effect when unchecked
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiToggleSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onText: String = "ON",
    offText: String = "OFF",
    checkedTrackColor: Color = SciFiColors.primary,
    uncheckedTrackColor: Color = SciFiColors.backgroundMedium,
    checkedThumbColor: Color = SciFiColors.primaryGlow,
    uncheckedThumbColor: Color = SciFiColors.border,
    onTextColor: Color = SciFiColors.onPrimary,
    offTextColor: Color = SciFiColors.onSurfaceMedium,
    checkedGlowColor: Color = SciFiColors.primaryGlow,
    uncheckedGlowColor: Color = SciFiColors.borderDim,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when hovered
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.3f
            isHovered -> 0.9f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    // Determine colors based on state
    val trackColor = when {
        !enabled -> (if (checked) checkedTrackColor else uncheckedTrackColor).copy(alpha = 0.5f)
        checked -> checkedTrackColor
        else -> uncheckedTrackColor
    }
    
    val thumbColor = when {
        !enabled -> (if (checked) checkedThumbColor else uncheckedThumbColor).copy(alpha = 0.5f)
        checked -> checkedThumbColor
        else -> uncheckedThumbColor
    }
    
    val glowColor = if (checked) checkedGlowColor else uncheckedGlowColor
    
    // Animate thumb position
    val thumbPosition by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "thumbPosition"
    )
    
    // Switch track and thumb
    Box(
        modifier = modifier
            .width(80.dp)
            .height(32.dp)
            .background(
                color = trackColor,
                shape = SciFiShapes.smallRoundedCorner
            )
            .drawWithContent {
                drawContent()
                if (enabled) {
                    // Draw sci-fi border with glow effect
                    drawSciFiBorder(
                        borderColor = glowColor.copy(alpha = glowIntensity),
                        borderWidth = 1.5f,
                        notchSize = 4f,
                        segmentLength = if (checked) 15f else 10f,
                        gapLength = if (checked) 2f else 4f
                    )
                }
            }
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = { onCheckedChange(!checked) }
            )
    ) {
        // ON text
        Text(
            text = onText,
            style = SciFiTypography.labelSmall,
            color = if (enabled) onTextColor else onTextColor.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp)
        )
        
        // OFF text
        Text(
            text = offText,
            style = SciFiTypography.labelSmall,
            color = if (enabled) offTextColor else offTextColor.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        )
        
        // Thumb
        Box(
            modifier = Modifier
                .size(28.dp)
                .offset {
                    IntOffset(
                        x = (thumbPosition * 48.dp.toPx()).roundToInt(),
                        y = 0
                    )
                }
                .padding(2.dp)
                .background(
                    color = thumbColor,
                    shape = SciFiShapes.smallRoundedCorner
                )
                .drawWithContent {
                    drawContent()
                    if (enabled) {
                        // Draw glow effect around thumb
                        drawSciFiBorder(
                            borderColor = glowColor.copy(alpha = glowIntensity),
                            borderWidth = 1f,
                            notchSize = 2f,
                            segmentLength = 5f,
                            gapLength = 1f
                        )
                    }
                }
                .align(Alignment.CenterStart)
        )
    }
}

/**
 * A sci-fi themed segmented toggle with multiple options and glowing effects.
 *
 * @param selectedOption The currently selected option
 * @param options The list of available options
 * @param onOptionSelected The callback to be invoked when an option is selected
 * @param modifier The modifier to be applied to the segmented toggle
 * @param enabled Controls the enabled state of the segmented toggle
 * @param backgroundColor The background color of the segmented toggle
 * @param selectedColor The background color of the selected segment
 * @param textColor The color of the text
 * @param selectedTextColor The color of the text in the selected segment
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun <T> SciFiSegmentedToggle(
    selectedOption: T,
    options: List<T>,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    selectedColor: Color = SciFiColors.primary,
    textColor: Color = SciFiColors.onSurfaceMedium,
    selectedTextColor: Color = SciFiColors.onPrimary,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f,
    optionToString: (T) -> String = { it.toString() }
) {
    Row(
        modifier = modifier
            .height(36.dp)
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
                    .fillMaxHeight()
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
                    style = SciFiTypography.labelMedium,
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
