package net.tactware.gamingui.components.ui.containers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder

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
    contentPadding: PaddingValues = PaddingValues(12.dp)
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
data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)
