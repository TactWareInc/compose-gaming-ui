package net.tactware.gamingui.components.ui.inputs.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder

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
