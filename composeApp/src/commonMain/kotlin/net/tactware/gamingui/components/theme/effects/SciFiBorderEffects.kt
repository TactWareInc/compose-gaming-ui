package net.tactware.gamingui.components.theme.effects

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Border effects utilities for the Sci-Fi UI theme.
 *
 * These effects create various border styles for sci-fi themed components.
 */

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
    fun drawSegmentedLineWithHover(start: androidx.compose.ui.geometry.Offset, end: androidx.compose.ui.geometry.Offset, segmentIndex: Int) {
        val dx = end.x - start.x
        val dy = end.y - start.y
        val length = kotlin.math.sqrt(dx * dx + dy * dy)
        val unitX = dx / length
        val unitY = dy / length

        var distanceCovered = 0f
        var currentSegment = 0
        
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
        start = androidx.compose.ui.geometry.Offset(0f, 0f),
        end = androidx.compose.ui.geometry.Offset(width, 0f),
        segmentIndex = 0
    )
    
    // Right edge
    drawSegmentedLineWithHover(
        start = androidx.compose.ui.geometry.Offset(width, 0f),
        end = androidx.compose.ui.geometry.Offset(width, height),
        segmentIndex = 1
    )
    
    // Bottom edge
    drawSegmentedLineWithHover(
        start = androidx.compose.ui.geometry.Offset(width, height),
        end = androidx.compose.ui.geometry.Offset(0f, height),
        segmentIndex = 2
    )
    
    // Left edge
    drawSegmentedLineWithHover(
        start = androidx.compose.ui.geometry.Offset(0f, height),
        end = androidx.compose.ui.geometry.Offset(0f, 0f),
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
            val x = centerX + hexSize * cos(angle)
            val y = centerY + hexSize * sin(angle)
            
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
    val cos = cos(angleRad)
    val sin = sin(angleRad)
    
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
                start = androidx.compose.ui.geometry.Offset(x1.toFloat(), y1.toFloat()),
                end = androidx.compose.ui.geometry.Offset(x2.toFloat(), y2.toFloat()),
                strokeWidth = strokeWidth
            )
        }
    }
}
