package net.tactware.gamingui.components.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Shape definitions for the Sci-Fi UI theme.
 *
 * These shapes are designed to evoke a futuristic sci-fi aesthetic with
 * angular corners, notches, and asymmetrical designs.
 */
/**
 * Shapes for the Sci-Fi UI theme.
 *
 * These shapes define the appearance of various UI components with a sci-fi aesthetic.
 */
object SciFiShapes {
    // Basic shapes with rounded corners
    val smallRoundedCorner = RoundedCornerShape(4.dp)
    val mediumRoundedCorner = RoundedCornerShape(8.dp)
    val largeRoundedCorner = RoundedCornerShape(12.dp)

    // Basic shapes with cut corners
    val smallCutCorner = CutCornerShape(4.dp)
    val mediumCutCorner = CutCornerShape(8.dp)
    val largeCutCorner = CutCornerShape(12.dp)

    // Asymmetric shapes for buttons and cards
    val asymButtonShape = CutCornerShape(
        topStart = 0.dp,
        topEnd = 8.dp,
        bottomStart = 8.dp,
        bottomEnd = 0.dp
    )

    val asymCardShape = CutCornerShape(
        topStart = 0.dp,
        topEnd = 16.dp,
        bottomStart = 16.dp,
        bottomEnd = 0.dp
    )

    // Sci-fi button shape with angled corners
    val sciFiButtonShape = CutCornerShape(
        topStart = 4.dp,
        topEnd = 12.dp,
        bottomStart = 12.dp,
        bottomEnd = 4.dp
    )

    // Sci-fi card shape with more pronounced cut corners
    val sciFiCardShape = CutCornerShape(
        topStart = 0.dp,
        topEnd = 24.dp,
        bottomStart = 24.dp,
        bottomEnd = 0.dp
    )

    // Sci-fi panel shape with all corners cut
    val sciFiPanelShape = CutCornerShape(12.dp)

    // Shapes for switches and toggles
    val switchTrackShape = RoundedCornerShape(16.dp)
    val switchThumbShape = CircleShape

    // Hexagonal shape for buttons or icons
    val hexagonalShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                val centerX = size.width / 2
                val centerY = size.height / 2
                val radius = minOf(size.width, size.height) / 2

                // Create a hexagon
                for (i in 0 until 6) {
                    val angle = (i * 60f) * (Math.PI.toFloat() / 180f)
                    val x = centerX + radius * kotlin.math.cos(angle)
                    val y = centerY + radius * kotlin.math.sin(angle)

                    if (i == 0) {
                        moveTo(x, y)
                    } else {
                        lineTo(x, y)
                    }
                }
                close()
            }
            return Outline.Generic(path)
        }

    }

    // Diamond shape for special buttons or indicators
    val diamondShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                val centerX = size.width / 2
                val centerY = size.height / 2

                // Create a diamond
                moveTo(centerX, 0f)
                lineTo(size.width, centerY)
                lineTo(centerX, size.height)
                lineTo(0f, centerY)
                close()
            }
            return Outline.Generic(path)
        }
    }

    // Octagonal shape for stop buttons or warnings
    val octagonalShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                val centerX = size.width / 2
                val centerY = size.height / 2
                val radius = minOf(size.width, size.height) / 2

                // Create an octagon
                for (i in 0 until 8) {
                    val angle = (i * 45f) * (Math.PI.toFloat() / 180f)
                    val x = centerX + radius * kotlin.math.cos(angle)
                    val y = centerY + radius * kotlin.math.sin(angle)

                    if (i == 0) {
                        moveTo(x, y)
                    } else {
                        lineTo(x, y)
                    }
                }
                close()
            }
            return Outline.Generic(path)
        }
    }

    // Triangular shape for directional buttons or indicators
    val triangularShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                // Create a triangle pointing right
                moveTo(0f, 0f)
                lineTo(size.width, size.height / 2)
                lineTo(0f, size.height)
                close()
            }
            return Outline.Generic(path)
        }
    }

    // Notched rectangle shape for tech-looking components
    val notchedRectangleShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                val notchSize = minOf(size.width, size.height) * 0.1f

                // Start from top-left with a notch
                moveTo(0f, notchSize)
                lineTo(notchSize, 0f)

                // Top edge
                lineTo(size.width - notchSize, 0f)

                // Top-right notch
                lineTo(size.width, notchSize)

                // Right edge
                lineTo(size.width, size.height - notchSize)

                // Bottom-right notch
                lineTo(size.width - notchSize, size.height)

                // Bottom edge
                lineTo(notchSize, size.height)

                // Bottom-left notch
                lineTo(0f, size.height - notchSize)

                // Close the path
                close()
            }
            return Outline.Generic(path)
        }
    }

    // Asymmetric tech shape for futuristic components
    val techShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                val width = size.width
                val height = size.height
                val cornerSize = minOf(width, height) * 0.15f

                // Start from top-left
                moveTo(0f, cornerSize)
                lineTo(cornerSize, 0f)

                // Top edge with notch
                lineTo(width * 0.4f, 0f)
                lineTo(width * 0.5f, cornerSize)
                lineTo(width - cornerSize, 0f)

                // Top-right corner
                lineTo(width, cornerSize)

                // Right edge
                lineTo(width, height - cornerSize)

                // Bottom-right corner
                lineTo(width - cornerSize, height)

                // Bottom edge with notch
                lineTo(width * 0.6f, height)
                lineTo(width * 0.5f, height - cornerSize)
                lineTo(cornerSize, height)

                // Bottom-left corner
                lineTo(0f, height - cornerSize)

                // Close the path
                close()
            }
            return Outline.Generic(path)
        }
    }

    // Circular progress indicator shape with a gap
    val progressIndicatorShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                val centerX = size.width / 2
                val centerY = size.height / 2
                val radius = minOf(size.width, size.height) / 2

                // Create a circle with a gap at the top
                arcTo(
                    rect = Rect(
                        left = centerX - radius,
                        top = centerY - radius,
                        right = centerX + radius,
                        bottom = centerY + radius,
                    ),
                    startAngleDegrees = 30f,
                    sweepAngleDegrees = 300f,
                    forceMoveTo = true
                )
            }
            return Outline.Generic(path)
        }
    }
}
