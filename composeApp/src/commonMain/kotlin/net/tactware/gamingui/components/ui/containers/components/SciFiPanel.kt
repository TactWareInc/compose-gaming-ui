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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder

/**
 * A sci-fi themed panel with a title header and glowing borders.
 *
 * @param title The title to be displayed in the header of the panel
 * @param modifier The modifier to be applied to the panel
 * @param backgroundColor The background color of the panel
 * @param headerColor The background color of the header
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param titleColor The color of the title text
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 * @param shape The shape of the panel
 * @param contentPadding The padding values to be applied to the content of the panel
 * @param content The content to be displayed inside the panel
 */
@Composable
fun SciFiPanel(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    headerColor: Color = SciFiColors.primary.copy(alpha = 0.2f),
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    titleColor: Color = SciFiColors.primary,
    baseGlowIntensity: Float = 0.6f,
    shape: Shape = SciFiShapes.sciFiPanelShape,
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
    
    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .drawWithContent {
                drawContent()
                // Draw sci-fi border with glow effect
                drawSciFiBorder(
                    borderColor = borderColor.copy(alpha = glowIntensity),
                    borderWidth = 1.5f,
                    notchSize = 10f,
                    segmentLength = 40f,
                    gapLength = 5f
                )
            }.hoverable(interactionSource)
    ) {
        // Panel header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = headerColor,
                    shape = RectangleShape
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                style = SciFiTypography.titleMedium,
                color = titleColor
            )
        }
        
        // Panel content
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}
