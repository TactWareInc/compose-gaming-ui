package net.tactware.gamingui.components.theme

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp

/**
 * Shape definitions for the Sci-Fi UI theme.
 * 
 * These shapes are designed to evoke a futuristic sci-fi aesthetic with
 * angular corners, notches, and asymmetrical designs.
 */
object SciFiShapes {
    // Basic shapes with rounded corners
    val smallRoundedCorner = RoundedCornerShape(4.dp)
    val mediumRoundedCorner = RoundedCornerShape(8.dp)
    val largeRoundedCorner = RoundedCornerShape(12.dp)
    
    // Standard button shape with slight rounding
    val buttonShape = RoundedCornerShape(4.dp)
    
    // Sci-fi button with notched corners
    val sciFiButtonShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height
        val cornerSize = minOf(width, height) * 0.2f
        
        // Start from top-left with inset
        moveTo(0f, cornerSize)
        lineTo(0f, height - cornerSize)
        lineTo(cornerSize, height)
        lineTo(width - cornerSize, height)
        lineTo(width, height - cornerSize)
        lineTo(width, cornerSize)
        lineTo(width - cornerSize, 0f)
        lineTo(cornerSize, 0f)
        close()
    }
    
    // Asymmetrical button with right side notch
    val asymButtonShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height
        val cornerSize = minOf(width, height) * 0.15f
        val notchSize = minOf(width, height) * 0.1f
        
        // Start from top-left
        moveTo(cornerSize, 0f)
        lineTo(width - cornerSize - notchSize, 0f)
        lineTo(width - notchSize, cornerSize)
        lineTo(width, cornerSize)
        lineTo(width, height - cornerSize)
        lineTo(width - cornerSize, height)
        lineTo(cornerSize, height)
        lineTo(0f, height - cornerSize)
        lineTo(0f, cornerSize)
        close()
    }
    
    // Card shape with top-right corner cut
    val cardShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height
        val cornerSize = minOf(width, height) * 0.1f
        val cutSize = minOf(width, height) * 0.15f
        
        // Start from top-left
        moveTo(0f, cornerSize)
        lineTo(0f, height - cornerSize)
        lineTo(cornerSize, height)
        lineTo(width - cornerSize, height)
        lineTo(width, height - cornerSize)
        lineTo(width, cornerSize + cutSize)
        lineTo(width - cutSize, cornerSize)
        lineTo(width - cutSize - cornerSize, cornerSize)
        lineTo(width - cutSize - cornerSize, 0f)
        lineTo(cornerSize, 0f)
        close()
    }
    
    // Panel shape with bottom-left corner extended
    val panelShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height
        val cornerSize = minOf(width, height) * 0.08f
        val extSize = minOf(width, height) * 0.12f
        
        // Start from top-left with slight rounding
        moveTo(cornerSize, 0f)
        lineTo(width - cornerSize, 0f)
        lineTo(width, cornerSize)
        lineTo(width, height - cornerSize)
        lineTo(width - cornerSize, height)
        lineTo(cornerSize + extSize, height)
        lineTo(cornerSize, height - extSize)
        lineTo(0f, height - extSize - cornerSize)
        lineTo(0f, cornerSize)
        close()
    }
    
    // Dialog shape with top and bottom notches
    val dialogShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height
        val cornerSize = minOf(width, height) * 0.05f
        val notchWidth = minOf(width, height) * 0.2f
        val notchHeight = minOf(width, height) * 0.05f
        
        // Calculate center position for top notch
        val topNotchStart = (width - notchWidth) / 2
        val topNotchEnd = topNotchStart + notchWidth
        
        // Start from top-left, after the notch
        moveTo(cornerSize, 0f)
        lineTo(topNotchStart, 0f)
        lineTo(topNotchStart + notchWidth * 0.3f, notchHeight)
        lineTo(topNotchEnd - notchWidth * 0.3f, notchHeight)
        lineTo(topNotchEnd, 0f)
        lineTo(width - cornerSize, 0f)
        lineTo(width, cornerSize)
        lineTo(width, height - cornerSize)
        lineTo(width - cornerSize, height)
        lineTo(width * 0.6f, height)
        lineTo(width * 0.55f, height - notchHeight)
        lineTo(width * 0.45f, height - notchHeight)
        lineTo(width * 0.4f, height)
        lineTo(cornerSize, height)
        lineTo(0f, height - cornerSize)
        lineTo(0f, cornerSize)
        close()
    }
    
    // TextField shape with subtle angles
    val textFieldShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height
        val cornerSize = minOf(width, height) * 0.1f
        val inset = minOf(width, height) * 0.05f
        
        // Start from top-left with inset
        moveTo(inset, 0f)
        lineTo(width - inset, 0f)
        lineTo(width, inset)
        lineTo(width, height - inset)
        lineTo(width - inset, height)
        lineTo(inset, height)
        lineTo(0f, height - inset)
        lineTo(0f, inset)
        close()
    }
    
    // Switch track shape (pill with notches)
    val switchTrackShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height
        val cornerRadius = height / 2
        val notchDepth = height * 0.15f
        val notchWidth = height * 0.2f
        
        // Top edge with notch
        moveTo(cornerRadius, 0f)
        lineTo(width * 0.4f - notchWidth / 2, 0f)
        lineTo(width * 0.4f, notchDepth)
        lineTo(width * 0.4f + notchWidth / 2, 0f)
        lineTo(width - cornerRadius, 0f)
        
        // Right curved edge
        arcTo(
            rect = Rect(
                left = width - height,
                top = 0f,
                right = width,
                bottom = height
            ),
            startAngleDegrees = 270f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )
        
        // Bottom edge with notch
        lineTo(width * 0.6f + notchWidth / 2, height)
        lineTo(width * 0.6f, height - notchDepth)
        lineTo(width * 0.6f - notchWidth / 2, height)
        lineTo(cornerRadius, height)
        
        // Left curved edge
        arcTo(
            rect = Rect(
                left = 0f,
                top = 0f,
                right = height,
                bottom = height
            ),
            startAngleDegrees = 90f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )
        
        close()
    }
    
    // Switch thumb shape (angular with cutout)
    val switchThumbShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height
        val cornerSize = minOf(width, height) * 0.2f
        val cutoutSize = minOf(width, height) * 0.3f
        
        // Start from top-left
        moveTo(cornerSize, 0f)
        lineTo(width - cornerSize, 0f)
        lineTo(width, cornerSize)
        lineTo(width, height - cornerSize)
        lineTo(width - cornerSize, height)
        lineTo(cornerSize, height)
        lineTo(0f, height - cornerSize)
        lineTo(0f, cornerSize)
        
        // Add a small triangular cutout on the left side
        moveTo(0f, height * 0.5f - cutoutSize / 2)
        lineTo(cutoutSize, height * 0.5f)
        lineTo(0f, height * 0.5f + cutoutSize / 2)
        
        close()
    }
}
