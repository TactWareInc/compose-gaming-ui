package net.tactware.gamingui.components.ui.containers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    shape: Shape = SciFiShapes.sciFiCardShape,
    contentPadding: PaddingValues = PaddingValues(16.dp),
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
        
        // Dialog content
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}
