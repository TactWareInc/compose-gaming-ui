package net.tactware.gamingui.components.ui.containers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder
import net.tactware.gamingui.components.theme.sciFiHolographic

/**
 * A sci-fi themed card with glowing borders and hover effects.
 *
 * @param modifier The modifier to be applied to the card
 * @param backgroundColor The background color of the card
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 * @param shape The shape of the card
 * @param contentPadding The padding values to be applied to the content of the card
 * @param content The content to be displayed inside the card
 */
@Composable
fun SciFiCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.5f,
    shape: Shape = SciFiShapes.cardShape,
    contentPadding: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when hovered
    val glowIntensity by animateFloatAsState(
        targetValue = if (isHovered) 0.8f else baseGlowIntensity,
        label = "glowIntensity"
    )
    
    Box(
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
                    notchSize = 8f,
                    segmentLength = 30f,
                    gapLength = 5f
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
    shape: Shape = SciFiShapes.panelShape,
    contentPadding: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(16.dp),
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
                    shape = androidx.compose.ui.graphics.RectangleShape
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

/**
 * A sci-fi themed dialog with glowing borders and a title header.
 *
 * @param title The title to be displayed in the header of the dialog
 * @param modifier The modifier to be applied to the dialog
 * @param backgroundColor The background color of the dialog
 * @param headerColor The background color of the header
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param titleColor The color of the title text
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 * @param shape The shape of the dialog
 * @param contentPadding The padding values to be applied to the content of the dialog
 * @param content The content to be displayed inside the dialog
 */
@Composable
fun SciFiDialog(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    headerColor: Color = SciFiColors.primary.copy(alpha = 0.2f),
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    titleColor: Color = SciFiColors.primary,
    baseGlowIntensity: Float = 0.7f,
    shape: Shape = SciFiShapes.dialogShape,
    contentPadding: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
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
                    borderColor = borderColor.copy(alpha = baseGlowIntensity),
                    borderWidth = 2f,
                    notchSize = 12f,
                    segmentLength = 30f,
                    gapLength = 4f
                )
            }
    ) {
        // Dialog header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = headerColor,
                    shape = androidx.compose.ui.graphics.RectangleShape
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                style = SciFiTypography.titleMedium,
                color = titleColor
            )
        }
        
        // Dialog content
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}

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
    shape: Shape = SciFiShapes.cardShape,
    contentPadding: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(16.dp),
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

/**
 * A sci-fi themed info box for displaying messages or notifications.
 *
 * @param message The message to be displayed in the info box
 * @param modifier The modifier to be applied to the info box
 * @param type The type of info box (info, success, warning, error)
 * @param borderColor The color of the border (overrides the default color based on type)
 * @param glowColor The color of the glow effect (overrides the default color based on type)
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 * @param shape The shape of the info box
 * @param contentPadding The padding values to be applied to the content of the info box
 */
@Composable
fun SciFiInfoBox(
    message: String,
    modifier: Modifier = Modifier,
    type: InfoBoxType = InfoBoxType.Info,
    borderColor: Color? = null,
    glowColor: Color? = null,
    baseGlowIntensity: Float = 0.6f,
    shape: Shape = SciFiShapes.smallRoundedCorner,
    contentPadding: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(12.dp)
) {
    // Determine colors based on type
    val (backgroundColor, defaultBorderColor, defaultGlowColor, textColor) = when (type) {
        InfoBoxType.Info -> Quadruple(
            SciFiColors.primary.copy(alpha = 0.1f),
            SciFiColors.border,
            SciFiColors.primaryGlow,
            SciFiColors.onSurface
        )
        InfoBoxType.Success -> Quadruple(
            SciFiColors.success.copy(alpha = 0.1f),
            SciFiColors.success,
            SciFiColors.successGlow,
            SciFiColors.onSurface
        )
        InfoBoxType.Warning -> Quadruple(
            SciFiColors.warning.copy(alpha = 0.1f),
            SciFiColors.warning,
            SciFiColors.warningGlow,
            Color.Black
        )
        InfoBoxType.Error -> Quadruple(
            SciFiColors.error.copy(alpha = 0.1f),
            SciFiColors.error,
            SciFiColors.errorGlow,
            SciFiColors.onSurface
        )
    }
    
    val finalBorderColor = borderColor ?: defaultBorderColor
    val finalGlowColor = glowColor ?: defaultGlowColor
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = shape
            )
            .drawWithContent {
                drawContent()
                // Draw sci-fi border with glow effect
                drawSciFiBorder(
                    borderColor = finalBorderColor.copy(alpha = baseGlowIntensity),
                    borderWidth = 1.5f,
                    notchSize = 5f,
                    segmentLength = 15f,
                    gapLength = 3f
                )
            }
            .padding(contentPadding)
    ) {
        Text(
            text = message,
            style = SciFiTypography.bodyMedium,
            color = textColor
        )
    }
}

/**
 * Types of info boxes.
 */
enum class InfoBoxType {
    Info,
    Success,
    Warning,
    Error
}

/**
 * Helper class for storing four values.
 */
private data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)
