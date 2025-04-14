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

/**
 * Creates a segmented border effect where segments change color on hover.
 *
 * @param primaryColor The primary color of the border
 * @param secondaryColor The secondary color of the border (for hover effect)
 * @param borderWidth The width of the border
 * @param segmentLength The length of each border segment
 * @param gapLength The length of gaps between segments
 * @param hoverProgress The progress of the hover effect (0.0-1.0)
 */
fun Modifier.sciFiSegmentedBorder(
    primaryColor: Color,
    secondaryColor: Color,
    borderWidth: Dp = 1.dp,
    segmentLength: Float = 20f,
    gapLength: Float = 5f,
    hoverProgress: Float = 0f
): Modifier = this.drawWithContent {
    // Draw the original content
    drawContent()

    val width = size.width
    val height = size.height
    val strokeWidth = borderWidth.toPx()

    // Function to draw a segmented line with hover effect
    fun drawSegmentedLineWithHover(start: Offset, end: Offset, segmentIndex: Int) {
        val dx = end.x - start.x
        val dy = end.y - start.y
        val length = kotlin.math.sqrt(dx * dx + dy * dy)
        val unitX = dx / length
        val unitY = dy / length

        var distanceCovered = 0f
        var currentSegment = 0

        while (distanceCovered < length) {
            val segmentStart = Offset(
                start.x + unitX * distanceCovered,
                start.y + unitY * distanceCovered
            )

            val currentSegmentLength = kotlin.math.min(segmentLength, length - distanceCovered)
            val segmentEnd = Offset(
                segmentStart.x + unitX * currentSegmentLength,
                segmentStart.y + unitY * currentSegmentLength
            )

            // Determine if this segment should use the hover color
            // We use a pattern where every other segment changes color on hover
            val useHoverColor = (currentSegment + segmentIndex) % 2 == 0
            val segmentColor = if (useHoverColor) {
                // Interpolate between primary and secondary color based on hover progress
                Color(
                    red = primaryColor.red + (secondaryColor.red - primaryColor.red) * hoverProgress,
                    green = primaryColor.green + (secondaryColor.green - primaryColor.green) * hoverProgress,
                    blue = primaryColor.blue + (secondaryColor.blue - primaryColor.blue) * hoverProgress,
                    alpha = primaryColor.alpha + (secondaryColor.alpha - primaryColor.alpha) * hoverProgress
                )
            } else {
                primaryColor
            }

            drawLine(
                color = segmentColor,
                start = segmentStart,
                end = segmentEnd,
                strokeWidth = strokeWidth
            )

            distanceCovered += currentSegmentLength + gapLength
            currentSegment++
        }
    }

    // Draw the four edges with segmented lines
    // Top edge
    drawSegmentedLineWithHover(
        start = Offset(0f, 0f),
        end = Offset(width, 0f),
        segmentIndex = 0
    )

    // Right edge
    drawSegmentedLineWithHover(
        start = Offset(width, 0f),
        end = Offset(width, height),
        segmentIndex = 1
    )

    // Bottom edge
    drawSegmentedLineWithHover(
        start = Offset(width, height),
        end = Offset(0f, height),
        segmentIndex = 2
    )

    // Left edge
    drawSegmentedLineWithHover(
        start = Offset(0f, height),
        end = Offset(0f, 0f),
        segmentIndex = 3
    )
}

/**
 * Creates a hexagonal pattern border effect.
 *
 * @param borderColor The color of the border
 * @param borderWidth The width of the border
 */
fun Modifier.sciFiHexagonalBorder(
    borderColor: Color,
    borderWidth: Dp = 1.dp
): Modifier = this.drawWithContent {
    // Draw the original content
    drawContent()

    val width = size.width
    val height = size.height
    val strokeWidth = borderWidth.toPx()

    // Calculate hexagon size based on component dimensions
    val hexSize = (width.coerceAtMost(height) * 0.1f).coerceAtLeast(10f)

    // Draw hexagons along the border
    val path = Path()

    // Function to draw a hexagon at a given position
    fun drawHexagon(centerX: Float, centerY: Float) {
        path.reset()

        for (i in 0 until 6) {
            val angle = (i * 60f) * (PI.toFloat() / 180f)
            val x = centerX + hexSize * kotlin.math.cos(angle)
            val y = centerY + hexSize * kotlin.math.sin(angle)

            if (i == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        path.close()

        drawPath(
            path = path,
            color = borderColor,
            style = Stroke(width = strokeWidth)
        )
    }

    // Draw hexagons along the top and bottom edges
    val hexSpacing = hexSize * 1.5f
    var x = hexSize
    while (x < width) {
        drawHexagon(x, hexSize / 2)
        drawHexagon(x, height - hexSize / 2)
        x += hexSpacing
    }

    // Draw hexagons along the left and right edges
    var y = hexSize * 1.5f
    while (y < height - hexSize) {
        drawHexagon(hexSize / 2, y)
        drawHexagon(width - hexSize / 2, y)
        y += hexSpacing
    }
}

/**
 * Creates a circuit-like border pattern.
 *
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param borderWidth The width of the border
 * @param glowIntensity The intensity of the glow (0.0-1.0)
 */
fun Modifier.sciFiCircuitBorder(
    borderColor: Color,
    glowColor: Color,
    borderWidth: Dp = 1.dp,
    glowIntensity: Float = 0.7f
): Modifier = this.drawWithContent {
    // Draw the original content
    drawContent()

    val width = size.width
    val height = size.height
    val strokeWidth = borderWidth.toPx()

    // Draw the main border
    drawRect(
        color = borderColor,
        style = Stroke(width = strokeWidth)
    )

    // Draw circuit-like patterns at the corners
    val cornerSize = (width.coerceAtMost(height) * 0.15f).coerceAtLeast(10f)
    val path = Path()

    // Top-left corner
    path.moveTo(0f, cornerSize)
    path.lineTo(0f, 0f)
    path.lineTo(cornerSize, 0f)
    path.moveTo(cornerSize / 2, 0f)
    path.lineTo(cornerSize / 2, cornerSize / 2)
    path.lineTo(cornerSize, cornerSize / 2)

    // Top-right corner
    path.moveTo(width - cornerSize, 0f)
    path.lineTo(width, 0f)
    path.lineTo(width, cornerSize)
    path.moveTo(width - cornerSize / 2, 0f)
    path.lineTo(width - cornerSize / 2, cornerSize / 2)
    path.lineTo(width - cornerSize, cornerSize / 2)

    // Bottom-right corner
    path.moveTo(width, height - cornerSize)
    path.lineTo(width, height)
    path.lineTo(width - cornerSize, height)
    path.moveTo(width - cornerSize / 2, height)
    path.lineTo(width - cornerSize / 2, height - cornerSize / 2)
    path.lineTo(width - cornerSize, height - cornerSize / 2)

    // Bottom-left corner
    path.moveTo(cornerSize, height)
    path.lineTo(0f, height)
    path.lineTo(0f, height - cornerSize)
    path.moveTo(cornerSize / 2, height)
    path.lineTo(cornerSize / 2, height - cornerSize / 2)
    path.lineTo(cornerSize, height - cornerSize / 2)

    // Draw the circuit patterns
    drawPath(
        path = path,
        color = borderColor,
        style = Stroke(width = strokeWidth)
    )

    // Draw glow effect if intensity > 0
    if (glowIntensity > 0f) {
        drawPath(
            path = path,
            color = glowColor.copy(alpha = glowIntensity * 0.5f),
            style = Stroke(width = strokeWidth * 3)
        )
    }
}

/**
 * Creates a diagonal striped border effect.
 *
 * @param borderColor The color of the border
 * @param borderWidth The width of the border
 * @param stripeAngle The angle of the stripes in degrees
 * @param stripeWidth The width of each stripe
 * @param stripeGap The gap between stripes
 */
fun Modifier.sciFiDiagonalStripedBorder(
    borderColor: Color,
    borderWidth: Dp = 1.dp,
    stripeAngle: Float = 45f,
    stripeWidth: Float = 10f,
    stripeGap: Float = 5f
): Modifier = this.drawWithContent {
    // Draw the original content
    drawContent()

    val width = size.width
    val height = size.height
    val strokeWidth = borderWidth.toPx()

    // Draw the main border
    drawRect(
        color = borderColor,
        style = Stroke(width = strokeWidth)
    )

    // Calculate the diagonal length
    val diagonalLength = kotlin.math.sqrt(width * width + height * height)
    val angleRad = stripeAngle * (PI.toFloat() / 180f)
    val cos = kotlin.math.cos(angleRad)
    val sin = kotlin.math.sin(angleRad)

    // Draw diagonal stripes
    val stripeCount = (diagonalLength / (stripeWidth + stripeGap)).toInt() + 1
    val startOffset = -diagonalLength / 2

    for (i in 0 until stripeCount) {
        val offset = startOffset + i * (stripeWidth + stripeGap)

        // Calculate start and end points for the stripe
        val x1 = width / 2 + offset * cos - diagonalLength / 2 * sin
        val y1 = height / 2 + offset * sin + diagonalLength / 2 * cos
        val x2 = width / 2 + offset * cos + diagonalLength / 2 * sin
        val y2 = height / 2 + offset * sin - diagonalLength / 2 * cos

        // Only draw the stripe if it intersects with the border
        if (x1 <= 0 || x1 >= width || y1 <= 0 || y1 >= height ||
            x2 <= 0 || x2 >= width || y2 <= 0 || y2 >= height) {

            drawLine(
                color = borderColor,
                start = Offset(x1.toFloat(), y1.toFloat()),
                end = Offset(x2.toFloat(), y2.toFloat()),
                strokeWidth = strokeWidth
            )
        }
    }
}
