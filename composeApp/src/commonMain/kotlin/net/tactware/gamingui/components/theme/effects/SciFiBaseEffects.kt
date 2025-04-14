package net.tactware.gamingui.components.theme.effects

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

/**
 * Base effects utilities for the Sci-Fi UI theme.
 *
 * These effects create the futuristic sci-fi look with glowing borders,
 * scan lines, and other visual effects.
 */

/**
 * Creates a glowing border effect around a composable.
 *
 * @param borderColor The main color of the border
 * @param glowColor The color of the glow effect
 * @param borderWidth The width of the border in dp
 * @param glowWidth The width of the glow effect in dp
 * @param glowIntensity The intensity of the glow (0.0-1.0)
 * @param shape Optional custom path function to define the border shape
 */
fun Modifier.sciFiGlowingBorder(
    borderColor: Color,
    glowColor: Color,
    borderWidth: Dp = 1.dp,
    glowWidth: Dp = 4.dp,
    glowIntensity: Float = 1f,
    shape: (androidx.compose.ui.geometry.Size) -> Path = { size ->
        Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
    }
): Modifier = this.drawWithContent {
    // Draw the original content
    drawContent()

    // Create the path for the border
    val path = shape(size)

    // Draw the glow effect
    if (glowIntensity > 0f) {
        val adjustedGlowColor = glowColor.copy(alpha = glowColor.alpha * glowIntensity)

        // Draw outer glow
        drawPath(
            path = path,
            color = adjustedGlowColor,
            style = Stroke(width = (borderWidth + glowWidth).toPx()),
            blendMode = BlendMode.SrcOver
        )

        // Draw inner glow
        drawPath(
            path = path,
            color = adjustedGlowColor.copy(alpha = adjustedGlowColor.alpha * 0.5f),
            style = Stroke(width = (borderWidth + glowWidth * 0.5f).toPx()),
            blendMode = BlendMode.SrcOver
        )
    }

    // Draw the border
    drawPath(
        path = path,
        color = borderColor,
        style = Stroke(width = borderWidth.toPx()),
        blendMode = BlendMode.SrcOver
    )
}

/**
 * Creates a pulsing glow effect.
 *
 * @param glowColor The color of the glow
 * @param minAlpha The minimum alpha value during pulsing
 * @param maxAlpha The maximum alpha value during pulsing
 * @param pulseProgress The current progress of the pulse (0.0-1.0)
 */
fun Modifier.sciFiPulsingGlow(
    glowColor: Color,
    minAlpha: Float = 0.2f,
    maxAlpha: Float = 0.8f,
    pulseProgress: Float
): Modifier = this.drawWithContent {
    // Draw the original content
    drawContent()

    // Calculate the current alpha based on pulse progress
    // Using a sine wave for smooth pulsing
    val progress = (sin(pulseProgress * PI * 2).toFloat() + 1f) / 2f
    val currentAlpha = minAlpha + (maxAlpha - minAlpha) * progress

    // Draw the glow overlay
    drawRect(
        color = glowColor.copy(alpha = currentAlpha),
        blendMode = BlendMode.SrcOver
    )
}

/**
 * Draws a custom sci-fi border with notches and segments.
 *
 * @param borderColor The color of the border
 * @param borderWidth The width of the border
 * @param notchSize The size of the notches
 * @param segmentLength The length of each border segment
 * @param gapLength The length of gaps between segments
 */
fun DrawScope.drawSciFiBorder(
    borderColor: Color,
    borderWidth: Float,
    notchSize: Float = 10f,
    segmentLength: Float = 50f,
    gapLength: Float = 10f
) {
    val width = size.width
    val height = size.height

    // Function to draw a segmented line
    fun drawSegmentedLine(start: androidx.compose.ui.geometry.Offset, end: androidx.compose.ui.geometry.Offset) {
        val dx = end.x - start.x
        val dy = end.y - start.y
        val length = kotlin.math.sqrt(dx * dx + dy * dy)
        val unitX = dx / length
        val unitY = dy / length

        var distanceCovered = 0f
        while (distanceCovered < length) {
            val segmentStart = androidx.compose.ui.geometry.Offset(
                start.x + unitX * distanceCovered,
                start.y + unitY * distanceCovered
            )

            val currentSegmentLength = kotlin.math.min(segmentLength, length - distanceCovered)
            val segmentEnd = androidx.compose.ui.geometry.Offset(
                segmentStart.x + unitX * currentSegmentLength,
                segmentStart.y + unitY * currentSegmentLength
            )

            drawLine(
                color = borderColor,
                start = segmentStart,
                end = segmentEnd,
                strokeWidth = borderWidth
            )

            distanceCovered += currentSegmentLength + gapLength
        }
    }

    // Top edge with notch
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(0f, 0f),
        end = androidx.compose.ui.geometry.Offset(width * 0.4f - notchSize, 0f)
    )
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(width * 0.4f, notchSize),
        end = androidx.compose.ui.geometry.Offset(width * 0.4f + notchSize, 0f)
    )
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(width * 0.4f + notchSize, 0f),
        end = androidx.compose.ui.geometry.Offset(width, 0f)
    )

    // Right edge
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(width, 0f),
        end = androidx.compose.ui.geometry.Offset(width, height)
    )

    // Bottom edge with notch
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(width, height),
        end = androidx.compose.ui.geometry.Offset(width * 0.6f + notchSize, height)
    )
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(width * 0.6f + notchSize, height),
        end = androidx.compose.ui.geometry.Offset(width * 0.6f, height - notchSize)
    )
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(width * 0.6f, height - notchSize),
        end = androidx.compose.ui.geometry.Offset(width * 0.6f - notchSize, height)
    )
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(width * 0.6f - notchSize, height),
        end = androidx.compose.ui.geometry.Offset(0f, height)
    )

    // Left edge
    drawSegmentedLine(
        start = androidx.compose.ui.geometry.Offset(0f, height),
        end = androidx.compose.ui.geometry.Offset(0f, 0f)
    )
}
