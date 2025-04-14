package net.tactware.gamingui.components.ui.switches.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import net.tactware.gamingui.components.theme.sciFiGradientBackground

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
 * @param useGradient Whether to use a gradient background for the selected segment
 * @param optionToString Function to convert option to string
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
    useGradient: Boolean = false,
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
                    .then(
                        if (isSelected) {
                            if (useGradient) {
                                Modifier.sciFiGradientBackground(
                                    colors = listOf(
                                        selectedColor.copy(alpha = 0.7f),
                                        selectedColor,
                                        selectedColor.copy(alpha = 0.8f)
                                    ),
                                    vertical = false
                                )
                            } else {
                                Modifier.background(selectedColor)
                            }
                        } else {
                            Modifier
                        }
                    )
                    .drawWithContent {
                        drawContent()
                        if (enabled && (isSelected || isHovered)) {
                            // Draw glow effect
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
                        indication = null,
                        onClick = { onOptionSelected(option) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = optionToString(option),
                    style = SciFiTypography.labelMedium,
                    color = if (isSelected) selectedTextColor else textColor
                )
            }
        }
    }
}
