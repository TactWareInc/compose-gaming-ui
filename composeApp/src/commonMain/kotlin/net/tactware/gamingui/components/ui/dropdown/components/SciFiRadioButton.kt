package net.tactware.gamingui.components.ui.dropdown.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder
import net.tactware.gamingui.components.theme.sciFiBackground
import net.tactware.gamingui.components.theme.sciFiCircuitBorder
import net.tactware.gamingui.components.theme.sciFiDiagonalStripedBorder
import net.tactware.gamingui.components.theme.sciFiGlowingBorder
import net.tactware.gamingui.components.theme.sciFiHexagonalBorder
import net.tactware.gamingui.components.theme.sciFiPulsingGlow
import net.tactware.gamingui.components.theme.sciFiScanLine
import net.tactware.gamingui.components.theme.sciFiSegmentedBorder

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
fun SciFiRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String? = null,
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
            isHovered -> 0.85f
            selected -> 0.8f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    // Adjust colors based on state
    val outerColor = when {
        !enabled -> (if (selected) selectedColor else unselectedColor).copy(alpha = 0.5f)
        selected -> selectedColor
        else -> unselectedColor
    }
    
    val borderColor = when {
        !enabled -> (if (selected) glowColor else SciFiColors.border).copy(alpha = 0.5f)
        selected -> glowColor
        else -> SciFiColors.border
    }
    
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Custom radio button with sci-fi styling
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = outerColor,
                    shape = SciFiShapes.hexagonalShape
                ).border(BorderStroke(1.dp, borderColor), SciFiShapes.hexagonalShape)
                .clickable(
                    onClick = { if (enabled) onClick() },
                    interactionSource = interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                // Inner circle for selected state
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = SciFiColors.onPrimary,
                            shape = SciFiShapes.hexagonalShape
                        )
                )
            }
        }
        
        // Optional text
        if (text != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = SciFiTypography.bodyMedium,
                color = if (enabled) textColor else textColor.copy(alpha = 0.5f),
                modifier = Modifier
                    .clickable(enabled = enabled) { onClick() }
                    .padding(vertical = 2.dp)
            )
        }
    }
}
