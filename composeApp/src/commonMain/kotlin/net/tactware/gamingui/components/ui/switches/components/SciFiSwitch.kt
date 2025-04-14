package net.tactware.gamingui.components.ui.switches.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder
import net.tactware.gamingui.components.theme.sciFiGradientBackground
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
 * @param useGradient Whether to use a gradient background for the track
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
    baseGlowIntensity: Float = 0.7f,
    useGradient: Boolean = false
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
                .then(
                    if (useGradient) {
                        Modifier.sciFiGradientBackground(
                            colors = if (checked) {
                                listOf(
                                    trackColor.copy(alpha = 0.7f),
                                    trackColor,
                                    trackColor.copy(alpha = 0.8f)
                                )
                            } else {
                                listOf(
                                    trackColor.copy(alpha = 0.5f),
                                    trackColor.copy(alpha = 0.7f),
                                    trackColor.copy(alpha = 0.5f)
                                )
                            },
                            vertical = false
                        )
                    } else {
                        Modifier.background(
                            color = trackColor,
                            shape = trackShape
                        )
                    }
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
                    
                    // Draw a subtle indicator line on the track to improve visibility in off state
                    if (!checked) {
                        val trackIndicatorColor = uncheckedGlowColor.copy(alpha = 0.3f)
                        drawLine(
                            color = trackIndicatorColor,
                            start = Offset(size.width * 0.25f, size.height * 0.5f),
                            end = Offset(size.width * 0.75f, size.height * 0.5f),
                            strokeWidth = 1f
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
