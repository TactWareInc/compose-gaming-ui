package net.tactware.gamingui.components.ui.containers.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.drawSciFiBorder
import net.tactware.gamingui.components.theme.sciFiHolographic

/**
 * A sci-fi themed container with a holographic background effect.
 *
 * @param modifier The modifier to be applied to the container
 * @param backgroundColor The background color of the container
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 * @param shape The shape of the container
 * @param contentPadding The padding values to be applied to the content of the container
 * @param content The content to be displayed inside the container
 */
@Composable
fun SciFiHolographicContainer(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SciFiColors.backgroundMedium.copy(alpha = 0.7f),
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.6f,
    shape: Shape = SciFiShapes.sciFiCardShape,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when hovered
    val glowIntensity by animateFloatAsState(
        targetValue = if (isHovered) 0.9f else baseGlowIntensity,
        label = "glowIntensity"
    )
    
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .then(Modifier.sciFiHolographic(baseColor = glowColor.copy(alpha = 0.2f)))
            .drawWithContent {
                drawContent()
                // Draw sci-fi border with glow effect
                drawSciFiBorder(
                    borderColor = borderColor.copy(alpha = glowIntensity),
                    borderWidth = 1.5f,
                    notchSize = 8f,
                    segmentLength = 20f,
                    gapLength = 3f
                )
            }
            .hoverable(interactionSource)
    ) {
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}
