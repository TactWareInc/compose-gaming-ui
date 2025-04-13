package net.tactware.gamingui.components.util

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.animations.pulsingGlow
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.drawSciFiBorder

/**
 * Multiplatform-specific modifier extensions for sci-fi UI components.
 * 
 * This file contains modifier extensions that apply sci-fi styling with
 * platform-specific adaptations.
 */

/**
 * Applies a sci-fi border with platform-adaptive glow effects.
 *
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 * @param borderWidth The width of the border
 * @param notchSize The size of the notches in the border
 * @param segmentLength The length of the segments in the border
 * @param gapLength The length of the gaps between segments
 * @return A modifier with the sci-fi border effect
 */
@Composable
fun Modifier.adaptiveSciFiBorder(
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f,
    borderWidth: Float = 1.5f,
    notchSize: Float = 8f,
    segmentLength: Float = 30f,
    gapLength: Float = 5f
): Modifier {
    // Adjust glow intensity based on platform
    val glowIntensity = getAdaptiveGlowIntensity(
        mobileGlowIntensity = baseGlowIntensity * 0.8f,
        desktopGlowIntensity = baseGlowIntensity
    )
    
    // Adjust border parameters based on platform
    val platform = PlatformUtils.detectPlatform()
    val (finalBorderWidth, finalNotchSize) = when (platform) {
        PlatformUtils.Platform.ANDROID, PlatformUtils.Platform.IOS -> Pair(borderWidth * 0.8f, notchSize * 0.8f)
        else -> Pair(borderWidth, notchSize)
    }
    
    return this.then(
        Modifier.drawWithContent {
            drawContent()
            // Draw sci-fi border with glow effect
            drawSciFiBorder(
                borderColor = borderColor.copy(alpha = glowIntensity),
                borderWidth = finalBorderWidth,
                notchSize = finalNotchSize,
                segmentLength = segmentLength,
                gapLength = gapLength
            )
        }
    )
}

/**
 * Applies a sci-fi holographic effect with platform-adaptive parameters.
 *
 * @param baseColor The base color of the holographic effect
 * @param intensity The intensity of the effect (0.0-1.0)
 * @return A modifier with the holographic effect
 */
@Composable
fun Modifier.sciFiHolographic(
    baseColor: Color = SciFiColors.primaryGlow.copy(alpha = 0.2f),
    intensity: Float = 0.5f
): Modifier {
    // Adjust effect intensity based on platform
    val effectIntensity = getAdaptiveGlowIntensity(
        mobileGlowIntensity = intensity * 0.7f,
        desktopGlowIntensity = intensity
    )
    
    // Adjust particle count based on platform
    val particleCount = getAdaptiveParticleCount()
    
    return this.then(
        Modifier.drawWithContent {
            drawContent()
            
            // Draw holographic overlay
            drawRect(
                color = baseColor.copy(alpha = baseColor.alpha * effectIntensity)
            )
            
            // Draw scan lines
            val scanLineSpacing = size.height / 30
            var y = 0f
            while (y < size.height) {
                drawLine(
                    color = baseColor.copy(alpha = 0.05f * effectIntensity),
                    start = androidx.compose.ui.geometry.Offset(0f, y),
                    end = androidx.compose.ui.geometry.Offset(size.width, y),
                    strokeWidth = 1f
                )
                y += scanLineSpacing
            }
        }
    )
}

/**
 * Applies a sci-fi glow effect with platform-adaptive parameters.
 *
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 * @param pulseEffect Whether to add a pulsing effect to the glow
 * @param pulseDuration The duration of one pulse cycle in milliseconds
 * @return A modifier with the glow effect
 */
@Composable
fun Modifier.adaptiveSciFiGlow(
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f,
    pulseEffect: Boolean = true,
    pulseDuration: Int = 2000
): Modifier {
    // Adjust glow intensity based on platform
    val glowIntensity = getAdaptiveGlowIntensity(
        mobileGlowIntensity = baseGlowIntensity * 0.8f,
        desktopGlowIntensity = baseGlowIntensity
    )
    
    // Adjust animation duration based on platform
    val animationDuration = getAdaptiveAnimationDuration(
        mobileDuration = pulseDuration,
        desktopDuration = (pulseDuration * 0.8).toInt()
    )
    
    return if (pulseEffect) {
        this.then(
            pulsingGlow(
                glowColor = glowColor,
                minAlpha = 0.2f * glowIntensity,
                maxAlpha = 0.8f * glowIntensity,
                pulseDuration = animationDuration
            )
        )
    } else {
        this.then(
            Modifier.drawWithContent {
                drawContent()
                drawRect(
                    color = glowColor.copy(alpha = 0.5f * glowIntensity)
                )
            }
        )
    }
}

/**
 * Applies adaptive touch target sizing based on the current platform.
 *
 * @param minSize The minimum size of the touch target
 * @return A modifier with the appropriate touch target size
 */
@Composable
fun Modifier.adaptiveSciFiTouchTarget(
    minSize: Dp = 48.dp
): Modifier {
    val touchTargetSize = PlatformUtils.adaptiveTouchTargetSize(
        forMobile = minSize,
        forDesktop = minSize * 0.75f
    )
    
    return this.then(Modifier.defaultMinSize(minWidth = touchTargetSize, minHeight = touchTargetSize))
}

/**
 * Applies adaptive padding based on the screen size.
 *
 * @param compact The padding for compact screens
 * @param medium The padding for medium screens
 * @param expanded The padding for expanded screens
 * @return A modifier with the appropriate padding for the current screen size
 */
//@Composable
//fun Modifier.adaptiveSciFiPadding(
//    compact: Dp = 8.dp,
//    medium: Dp = 16.dp,
//    expanded: Dp = 24.dp
//): Modifier {
//    val padding = ScreenUtils.getAdaptiveSpacing(
//        compactSpacing = compact,
//        mediumSpacing = medium,
//        expandedSpacing = expanded
//    )
//
//    return this.then(Modifier.padding(padding))
//}
