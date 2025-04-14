package net.tactware.gamingui.components.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import kotlin.random.Random

/**
 * Effects utilities for the Sci-Fi UI theme.
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
    shape: (Size) -> Path = { size ->
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
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1f
        )
        y += spacing
    }

    // Draw vertical lines
    var x = 0f
    while (x < size.width) {
        drawLine(
            color = adjustedGridColor,
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = 1f
        )
        x += spacing
    }
}

/**
 * Creates a scan line effect that moves across the composable.
 *
 * @param scanLineColor The color of the scan line
 * @param scanLineAlpha The opacity of the scan line (0.0-1.0)
 * @param scanLineWidth The width of the scan line in dp
 * @param progress The progress of the scan line (0.0-1.0)
 */
fun Modifier.sciFiScanLine(
    scanLineColor: Color = Color.Cyan,
    scanLineAlpha: Float = 0.2f,
    scanLineWidth: Dp = 2.dp,
    progress: Float
): Modifier = this.drawWithContent {
    // Draw the original content
    drawContent()

    // Calculate the position of the scan line
    val scanLineX = size.width * progress
    val halfWidth = scanLineWidth.toPx() / 2

    // Draw the scan line
    drawRect(
        color = scanLineColor.copy(alpha = scanLineAlpha),
        topLeft = Offset(scanLineX - halfWidth, 0f),
        size = Size(scanLineWidth.toPx(), size.height)
    )

    // Draw a subtle glow around the scan line
    drawRect(
        brush = Brush.horizontalGradient(
            colors = listOf(
                Color.Transparent,
                scanLineColor.copy(alpha = scanLineAlpha * 0.5f),
                scanLineColor.copy(alpha = scanLineAlpha),
                scanLineColor.copy(alpha = scanLineAlpha * 0.5f),
                Color.Transparent
            ),
            startX = scanLineX - scanLineWidth.toPx() * 2,
            endX = scanLineX + scanLineWidth.toPx() * 2
        ),
        size = Size(scanLineWidth.toPx() * 4, size.height)
    )
}

/**
 * Creates a holographic effect with subtle noise and gradient.
 *
 * @param baseColor The main color of the holographic effect
 * @param noiseAlpha The opacity of the noise effect (0.0-1.0)
 * @param noiseScale The scale of the noise pattern
 */
fun Modifier.sciFiHolographic(
    baseColor: Color = SciFiColors.primaryGlow,
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
                    topLeft = Offset(x, y),
                    size = Size(cellSize, cellSize)
                )
            }
        }
    }
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
    glowColor: Color = SciFiColors.primaryGlow,
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
//    val width = size.width
//    val height = size.height
//
//    // Function to draw a segmented line
//    fun drawSegmentedLine(start: Offset, end: Offset) {
//        val dx = end.x - start.x
//        val dy = end.y - start.y
//        val length = kotlin.math.sqrt(dx * dx + dy * dy)
//        val unitX = dx / length
//        val unitY = dy / length
//
//        var distanceCovered = 0f
//        while (distanceCovered < length) {
//            val segmentStart = Offset(
//                start.x + unitX * distanceCovered,
//                start.y + unitY * distanceCovered
//            )
//
//            val currentSegmentLength = kotlin.math.min(segmentLength, length - distanceCovered)
//            val segmentEnd = Offset(
//                segmentStart.x + unitX * currentSegmentLength,
//                segmentStart.y + unitY * currentSegmentLength
//            )
//
//            drawLine(
//                color = borderColor,
//                start = segmentStart,
//                end = segmentEnd,
//                strokeWidth = borderWidth
//            )
//
//            distanceCovered += currentSegmentLength + gapLength
//        }
//    }
//
//    // Top edge with notch
//    drawSegmentedLine(
//        start = Offset(0f, 0f),
//        end = Offset(width * 0.4f - notchSize, 0f)
//    )
//    drawSegmentedLine(
//        start = Offset(width * 0.4f, notchSize),
//        end = Offset(width * 0.4f + notchSize, 0f)
//    )
//    drawSegmentedLine(
//        start = Offset(width * 0.4f + notchSize, 0f),
//        end = Offset(width, 0f)
//    )
//
//    // Right edge
//    drawSegmentedLine(
//        start = Offset(width, 0f),
//        end = Offset(width, height)
//    )
//
//    // Bottom edge with notch
//    drawSegmentedLine(
//        start = Offset(width, height),
//        end = Offset(width * 0.6f + notchSize, height)
//    )
//    drawSegmentedLine(
//        start = Offset(width * 0.6f + notchSize, height),
//        end = Offset(width * 0.6f, height - notchSize)
//    )
//    drawSegmentedLine(
//        start = Offset(width * 0.6f, height - notchSize),
//        end = Offset(width * 0.6f - notchSize, height)
//    )
//    drawSegmentedLine(
//        start = Offset(width * 0.6f - notchSize, height),
//        end = Offset(0f, height)
//    )
//
//    // Left edge
//    drawSegmentedLine(
//        start = Offset(0f, height),
//        end = Offset(0f, 0f)
//    )
}

