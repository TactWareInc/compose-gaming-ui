package net.tactware.gamingui.components.ui.switches.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography

/**
 * A sci-fi themed radio button with glowing effects.
 *
 * @param selected Whether the radio button is currently selected
 * @param onClick The callback to be invoked when the radio button is clicked
 * @param modifier The modifier to be applied to the radio button
 * @param enabled Controls the enabled state of the radio button
 * @param label The optional label to be displayed next to the radio button
 * @param selectedColor The color of the radio button when selected
 * @param unselectedColor The color of the radio button when not selected
 * @param labelColor The color of the label text
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    selectedColor: Color = SciFiColors.primary,
    unselectedColor: Color = SciFiColors.backgroundMedium,
    labelColor: Color = SciFiColors.onSurface,
    glowColor: Color = SciFiColors.primaryGlow,
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
    val outerCircleColor = when {
        !enabled -> (if (selected) selectedColor else unselectedColor).copy(alpha = 0.5f)
        selected -> selectedColor
        else -> unselectedColor
    }
    
    val innerCircleColor = when {
        !enabled -> glowColor.copy(alpha = 0.5f)
        selected -> glowColor
        else -> Color.Transparent
    }
    
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Radio button
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = Color.Transparent,
                    shape = SciFiShapes.switchThumbShape
                )
                .drawWithContent {
                    // Draw outer circle
                    drawCircle(
                        color = outerCircleColor,
                        radius = size.minDimension / 2 - 2f
                    )
                    
                    // Draw border with glow effect
                    if (enabled) {
                        drawCircle(
                            color = glowColor.copy(alpha = glowIntensity * 0.5f),
                            radius = size.minDimension / 2,
                            style = Stroke(width = 2f)
                        )
                    } else {
                        drawCircle(
                            color = outerCircleColor.copy(alpha = 0.7f),
                            radius = size.minDimension / 2,
                            style = Stroke(width = 1f)
                        )
                    }
                    
                    // Draw inner circle for selected state
                    if (selected || isHovered) {
                        drawCircle(
                            color = innerCircleColor.copy(
                                alpha = if (isHovered && !selected) 0.3f else innerCircleColor.alpha
                            ),
                            radius = size.minDimension / 4
                        )
                    }
                    
                    // Always draw a small dot in the center for better visibility in unselected state
                    if (!selected) {
                        drawCircle(
                            color = outerCircleColor.copy(alpha = 0.5f),
                            radius = size.minDimension / 8
                        )
                    }
                    
                    drawContent()
                }
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                )
        )
        
        // Optional label
        if (label != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = SciFiTypography.bodyMedium,
                color = if (enabled) labelColor else labelColor.copy(alpha = 0.5f),
                modifier = Modifier.clickable(enabled = enabled, onClick = onClick)
            )
        }
    }
}
