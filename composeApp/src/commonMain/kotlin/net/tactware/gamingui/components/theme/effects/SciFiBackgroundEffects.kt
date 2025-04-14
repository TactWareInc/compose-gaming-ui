package net.tactware.gamingui.components.theme.effects

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random

/**
 * Background effects utilities for the Sci-Fi UI theme.
 *
 * These effects create various background styles for sci-fi themed components.
 */

/**
 * Creates a sci-fi background with subtle grid pattern.
 *
 * @param backgroundColor The main background color
 * @param gridColor The color of the grid lines
 * @param gridSpacing The spacing between grid lines in dp
 * @param gridAlpha The opacity of the grid (0.0-1.0)
 */
fun Modifier.sciFiBackground(
    backgroundColor: Color,
    gridColor: Color = Color.Cyan.copy(alpha = 0.1f),
    gridSpacing: Dp = 20.dp,
    gridAlpha: Float = 0.1f
): Modifier = this.drawBehind {
    // Draw the background
    drawRect(color = backgroundColor)

    // Draw the grid
    val adjustedGridColor = gridColor.copy(alpha = gridAlpha)
    val spacing = gridSpacing.toPx()

    // Draw horizontal lines
    var y = 0f
    while (y < size.height) {
        drawLine(
            color = adjustedGridColor,
            start = androidx.compose.ui.geometry.Offset(0f, y),
            end = androidx.compose.ui.geometry.Offset(size.width, y),
            strokeWidth = 1f
        )
        y += spacing
    }

    // Draw vertical lines
    var x = 0f
    while (x < size.width) {
        drawLine(
            color = adjustedGridColor,
            start = androidx.compose.ui.geometry.Offset(x, 0f),
            end = androidx.compose.ui.geometry.Offset(x, size.height),
            strokeWidth = 1f
        )
        x += spacing
    }
}

/**
 * Creates a holographic effect with subtle noise and gradient.
 *
 * @param baseColor The main color of the holographic effect
 * @param noiseAlpha The opacity of the noise effect (0.0-1.0)
 * @param noiseScale The scale of the noise pattern
 */
fun Modifier.sciFiHolographic(
    baseColor: Color,
    noiseAlpha: Float = 0.05f,
    noiseScale: Float = 0.1f
): Modifier = this.drawWithContent {
    // Draw the original content
    drawContent()

    // Draw a subtle gradient overlay
    drawRect(
        brush = Brush.verticalGradient(
            colors = listOf(
                baseColor.copy(alpha = 0.05f),
                baseColor.copy(alpha = 0.1f),
                baseColor.copy(alpha = 0.05f)
            )
        )
    )

    // Draw noise effect (simplified version)
    // In a real implementation, you would use a noise texture or algorithm
    val random = Random(10) // Fixed seed for consistent pattern
    val cellSize = (size.width * noiseScale).coerceAtLeast(1f)
    val cols = (size.width / cellSize).toInt()
    val rows = (size.height / cellSize).toInt()

    for (i in 0 until cols) {
        for (j in 0 until rows) {
            if (random.nextFloat() > 0.8f) {
                val x = i * cellSize
                val y = j * cellSize
                drawRect(
                    color = baseColor.copy(alpha = random.nextFloat() * noiseAlpha),
                    topLeft = androidx.compose.ui.geometry.Offset(x, y),
                    size = androidx.compose.ui.geometry.Size(cellSize, cellSize)
                )
            }
        }
    }
}

/**
 * Creates a gradient background for components.
 *
 * @param colors List of colors for the gradient
 * @param vertical Whether the gradient should be vertical (true) or horizontal (false)
 */
fun Modifier.sciFiGradientBackground(
    colors: List<Color>,
    vertical: Boolean = true
): Modifier = this.drawBehind {
    val brush = if (vertical) {
        Brush.verticalGradient(colors)
    } else {
        Brush.horizontalGradient(colors)
    }
    drawRect(brush = brush)
}
