package net.tactware.gamingui.components.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Multiplatform-specific component configurations.
 * 
 * This object provides default values and configurations for components
 * that need to adapt to different platforms (desktop vs mobile).
 */
object PlatformConfig {
    
    /**
     * Button configurations for different platforms.
     */
    object Button {
        // Default padding for buttons
        val mobilePadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 12.dp,
            vertical = 8.dp
        )
        val desktopPadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 16.dp,
            vertical = 10.dp
        )
        
        // Default sizes for buttons
        val mobileMinWidth: Dp = 64.dp
        val desktopMinWidth: Dp = 88.dp
        val mobileMinHeight: Dp = 36.dp
        val desktopMinHeight: Dp = 40.dp
        
        // Default sizes for icon buttons
        val mobileIconSize: Dp = 48.dp
        val desktopIconSize: Dp = 40.dp
        
        // Default glow intensities
        val mobileGlowIntensity: Float = 0.6f
        val desktopGlowIntensity: Float = 0.8f
    }
    
    /**
     * Text field configurations for different platforms.
     */
    object TextField {
        // Default padding for text fields
        val mobilePadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 12.dp,
            vertical = 8.dp
        )
        val desktopPadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 16.dp,
            vertical = 10.dp
        )
        
        // Default sizes for text fields
        val mobileMinHeight: Dp = 48.dp
        val desktopMinHeight: Dp = 40.dp
        
        // Default glow intensities
        val mobileGlowIntensity: Float = 0.5f
        val desktopGlowIntensity: Float = 0.7f
    }
    
    /**
     * Card and panel configurations for different platforms.
     */
    object Container {
        // Default padding for containers
        val mobilePadding = androidx.compose.foundation.layout.PaddingValues(
            all = 12.dp
        )
        val desktopPadding = androidx.compose.foundation.layout.PaddingValues(
            all = 16.dp
        )
        
        // Default corner sizes
        val mobileCornerSize: Float = 0.08f
        val desktopCornerSize: Float = 0.1f
        
        // Default glow intensities
        val mobileGlowIntensity: Float = 0.5f
        val desktopGlowIntensity: Float = 0.7f
    }
    
    /**
     * Switch and toggle configurations for different platforms.
     */
    object Switch {
        // Default sizes for switches
        val mobileTrackWidth: Dp = 52.dp
        val desktopTrackWidth: Dp = 56.dp
        val mobileTrackHeight: Dp = 28.dp
        val desktopTrackHeight: Dp = 32.dp
        val mobileThumbSize: Dp = 24.dp
        val desktopThumbSize: Dp = 28.dp
        
        // Default glow intensities
        val mobileGlowIntensity: Float = 0.6f
        val desktopGlowIntensity: Float = 0.8f
    }
    
    /**
     * Dropdown and menu configurations for different platforms.
     */
    object Dropdown {
        // Default padding for dropdowns
        val mobilePadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 12.dp,
            vertical = 8.dp
        )
        val desktopPadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 16.dp,
            vertical = 10.dp
        )
        
        // Default sizes for dropdowns
        val mobileMinHeight: Dp = 48.dp
        val desktopMinHeight: Dp = 40.dp
        
        // Default glow intensities
        val mobileGlowIntensity: Float = 0.5f
        val desktopGlowIntensity: Float = 0.7f
    }
    
    /**
     * Animation configurations for different platforms.
     */
    object Animation {
        // Default animation durations
        val mobileTransitionDuration: Int = 300
        val desktopTransitionDuration: Int = 250
        
        // Default animation speeds
        val mobileAnimationSpeed: Float = 1.0f
        val desktopAnimationSpeed: Float = 1.2f
        
        // Default particle counts for effects
        val mobileParticleCount: Int = 30
        val desktopParticleCount: Int = 50
    }
    
    /**
     * Touch and interaction configurations for different platforms.
     */
    object Interaction {
        // Default touch target sizes
        val mobileTouchTargetSize: Dp = 48.dp
        val desktopTouchTargetSize: Dp = 36.dp
        
        // Default hover effect settings
        val mobileHoverEnabled: Boolean = false
        val desktopHoverEnabled: Boolean = true
        
        // Default focus highlight intensities
        val mobileFocusIntensity: Float = 0.8f
        val desktopFocusIntensity: Float = 1.0f
    }
}
