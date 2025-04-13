package net.tactware.gamingui.components.util

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Extension functions for adapting components to different platforms.
 * 
 * This file contains extension functions that make it easier to apply
 * platform-specific configurations to components.
 */

/**
 * Applies platform-specific padding to a component.
 * 
 * @param mobilePadding The padding to apply on mobile platforms
 * @param desktopPadding The padding to apply on desktop platforms
 * @return A modifier with the appropriate padding for the current platform
 */
@Composable
fun Modifier.adaptivePadding(
    mobilePadding: androidx.compose.foundation.layout.PaddingValues,
    desktopPadding: androidx.compose.foundation.layout.PaddingValues
): Modifier {
    val platform = PlatformUtils.detectPlatform()
    val padding = when (platform) {
        PlatformUtils.Platform.ANDROID, PlatformUtils.Platform.IOS -> mobilePadding
        PlatformUtils.Platform.DESKTOP, PlatformUtils.Platform.WEB -> desktopPadding
        else -> mobilePadding
    }
    
    return this.then(
        Modifier.padding(
            start = padding.calculateLeftPadding(androidx.compose.ui.unit.LayoutDirection.Ltr),
            top = padding.calculateTopPadding(),
            end = padding.calculateRightPadding(androidx.compose.ui.unit.LayoutDirection.Ltr),
            bottom = padding.calculateBottomPadding()
        )
    )
}

/**
 * Applies platform-specific size constraints to a component.
 * 
 * @param mobileMinWidth The minimum width to apply on mobile platforms
 * @param desktopMinWidth The minimum width to apply on desktop platforms
 * @param mobileMinHeight The minimum height to apply on mobile platforms
 * @param desktopMinHeight The minimum height to apply on desktop platforms
 * @return A modifier with the appropriate size constraints for the current platform
 */
@Composable
fun Modifier.adaptiveMinSize(
    mobileMinWidth: Dp = 0.dp,
    desktopMinWidth: Dp = 0.dp,
    mobileMinHeight: Dp = 0.dp,
    desktopMinHeight: Dp = 0.dp
): Modifier {
    val platform = PlatformUtils.detectPlatform()
    val (minWidth, minHeight) = when (platform) {
        PlatformUtils.Platform.ANDROID, PlatformUtils.Platform.IOS -> Pair(mobileMinWidth, mobileMinHeight)
        PlatformUtils.Platform.DESKTOP, PlatformUtils.Platform.WEB -> Pair(desktopMinWidth, desktopMinHeight)
        else -> Pair(mobileMinWidth, mobileMinHeight)
    }
    
    return this.then(Modifier.defaultMinSize(minWidth = minWidth, minHeight = minHeight))
}

/**
 * Applies platform-specific hover effects to a component.
 * 
 * @param interactionSource The interaction source to track hover state
 * @param enabled Whether the component is enabled
 * @param onHover The callback to be invoked when hover state changes
 * @return A modifier with hover effects if the platform supports it
 */
@Composable
fun Modifier.adaptiveHover(
    interactionSource: androidx.compose.foundation.interaction.MutableInteractionSource,
    enabled: Boolean = true,
    onHover: ((Boolean) -> Unit)? = null
): Modifier {
    val hoverEnabled = PlatformUtils.shouldEnableHoverEffects()
    
    return if (hoverEnabled && enabled) {
        this.then(
            Modifier.hoverable(
                interactionSource = interactionSource,
                enabled = enabled
            )
        )
    } else {
        this
    }
}

/**
 * Applies platform-specific touch target sizing to a component.
 * 
 * @param mobileTouchTargetSize The touch target size for mobile platforms
 * @param desktopTouchTargetSize The touch target size for desktop platforms
 * @return A modifier with the appropriate touch target size for the current platform
 */
@Composable
fun Modifier.adaptiveTouchTarget(
    mobileTouchTargetSize: Dp = PlatformConfig.Interaction.mobileTouchTargetSize,
    desktopTouchTargetSize: Dp = PlatformConfig.Interaction.desktopTouchTargetSize
): Modifier {
    val touchTargetSize = PlatformUtils.adaptiveTouchTargetSize(
        forMobile = mobileTouchTargetSize,
        forDesktop = desktopTouchTargetSize
    )
    
    return this.then(Modifier.defaultMinSize(minWidth = touchTargetSize, minHeight = touchTargetSize))
}

/**
 * Gets the appropriate glow intensity for the current platform.
 * 
 * @param mobileGlowIntensity The glow intensity for mobile platforms
 * @param desktopGlowIntensity The glow intensity for desktop platforms
 * @return The appropriate glow intensity for the current platform
 */
@Composable
fun getAdaptiveGlowIntensity(
    mobileGlowIntensity: Float = 0.5f,
    desktopGlowIntensity: Float = 0.7f
): Float {
    return PlatformUtils.adaptiveGlowIntensity(
        forMobile = mobileGlowIntensity,
        forDesktop = desktopGlowIntensity
    )
}

/**
 * Gets the appropriate animation duration for the current platform.
 * 
 * @param mobileDuration The animation duration for mobile platforms
 * @param desktopDuration The animation duration for desktop platforms
 * @return The appropriate animation duration for the current platform
 */
@Composable
fun getAdaptiveAnimationDuration(
    mobileDuration: Int = PlatformConfig.Animation.mobileTransitionDuration,
    desktopDuration: Int = PlatformConfig.Animation.desktopTransitionDuration
): Int {
    val platform = PlatformUtils.detectPlatform()
    return when (platform) {
        PlatformUtils.Platform.ANDROID, PlatformUtils.Platform.IOS -> mobileDuration
        PlatformUtils.Platform.DESKTOP, PlatformUtils.Platform.WEB -> desktopDuration
        else -> mobileDuration
    }
}

/**
 * Gets the appropriate animation spec for the current platform.
 * 
 * @param mobileDuration The animation duration for mobile platforms
 * @param desktopDuration The animation duration for desktop platforms
 * @return The appropriate animation spec for the current platform
 */
@Composable
fun getAdaptiveAnimationSpec(
    mobileDuration: Int = PlatformConfig.Animation.mobileTransitionDuration,
    desktopDuration: Int = PlatformConfig.Animation.desktopTransitionDuration
): androidx.compose.animation.core.FiniteAnimationSpec<Float> {
    val duration = getAdaptiveAnimationDuration(mobileDuration, desktopDuration)
    return androidx.compose.animation.core.tween(
        durationMillis = duration,
        easing = androidx.compose.animation.core.FastOutSlowInEasing
    )
}

/**
 * Gets the appropriate particle count for effects based on the current platform.
 * 
 * @param mobileCount The particle count for mobile platforms
 * @param desktopCount The particle count for desktop platforms
 * @return The appropriate particle count for the current platform
 */
@Composable
fun getAdaptiveParticleCount(
    mobileCount: Int = PlatformConfig.Animation.mobileParticleCount,
    desktopCount: Int = PlatformConfig.Animation.desktopParticleCount
): Int {
    val platform = PlatformUtils.detectPlatform()
    return when (platform) {
        PlatformUtils.Platform.ANDROID, PlatformUtils.Platform.IOS -> mobileCount
        PlatformUtils.Platform.DESKTOP, PlatformUtils.Platform.WEB -> desktopCount
        else -> mobileCount
    }
}
