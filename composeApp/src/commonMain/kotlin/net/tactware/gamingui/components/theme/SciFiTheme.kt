package net.tactware.gamingui.components.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import net.tactware.gamingui.components.theme.SciFiColors

/**
 * Custom sci-fi theme properties that extend beyond Material3's capabilities.
 */
@Immutable
data class SciFiThemeExtras(
    val glowIntensity: Float = 1.0f,
    val animationSpeed: Float = 1.0f,
    val borderWidth: Float = 1.0f,
    val isHolographicEnabled: Boolean = true,
    val primaryGlow: Color = SciFiColors.primaryGlow,
    val secondaryGlow: Color = SciFiColors.successGlow,
    val errorGlow: Color = SciFiColors.errorGlow,
    val backgroundEffect: BackgroundEffect = BackgroundEffect.Grid
)

/**
 * Background effect options for sci-fi UI.
 */
enum class BackgroundEffect {
    None,
    Grid,
    Particles,
    ScanLines
}

/**
 * Local composition for sci-fi theme extras.
 */
val LocalSciFiThemeExtras = staticCompositionLocalOf {
    SciFiThemeExtras()
}

/**
 * Dark color scheme for the sci-fi theme.
 */
private val DarkSciFiColorScheme = darkColorScheme(
    primary = SciFiColors.primary,
    onPrimary = SciFiColors.onPrimary,
    primaryContainer = SciFiColors.primaryDark,
    onPrimaryContainer = SciFiColors.onPrimary,
    secondary = SciFiColors.accent1,
    onSecondary = SciFiColors.onPrimary,
    secondaryContainer = SciFiColors.accent1.copy(alpha = 0.7f),
    onSecondaryContainer = SciFiColors.onPrimary,
    tertiary = SciFiColors.accent2,
    onTertiary = SciFiColors.onPrimary,
    tertiaryContainer = SciFiColors.accent2.copy(alpha = 0.7f),
    onTertiaryContainer = SciFiColors.onPrimary,
    error = SciFiColors.error,
    onError = SciFiColors.onPrimary,
    errorContainer = SciFiColors.error.copy(alpha = 0.7f),
    onErrorContainer = SciFiColors.onPrimary,
    background = SciFiColors.background,
    onBackground = SciFiColors.onBackground,
    surface = SciFiColors.surface,
    onSurface = SciFiColors.onSurface,
    surfaceVariant = SciFiColors.surfaceAccent,
    onSurfaceVariant = SciFiColors.onSurfaceMedium,
    outline = SciFiColors.border
)

/**
 * Light color scheme for the sci-fi theme.
 * Note: Sci-fi UIs typically use dark themes, but we provide a light option for completeness.
 */
private val LightSciFiColorScheme = lightColorScheme(
    primary = SciFiColors.primary,
    onPrimary = Color.White,
    primaryContainer = SciFiColors.primaryDark,
    onPrimaryContainer = Color.White,
    secondary = SciFiColors.accent1,
    onSecondary = Color.White,
    secondaryContainer = SciFiColors.accent1.copy(alpha = 0.7f),
    onSecondaryContainer = Color.White,
    tertiary = SciFiColors.accent2,
    onTertiary = Color.White,
    tertiaryContainer = SciFiColors.accent2.copy(alpha = 0.7f),
    onTertiaryContainer = Color.White,
    error = SciFiColors.error,
    onError = Color.White,
    errorContainer = SciFiColors.error.copy(alpha = 0.7f),
    onErrorContainer = Color.White,
    background = Color(0xFF001133), // Lighter but still dark for sci-fi feel
    onBackground = Color.White,
    surface = Color(0xFF002255),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF003366),
    onSurfaceVariant = Color.White,
    outline = SciFiColors.border
)

/**
 * Main theme composable for the sci-fi UI.
 *
 * @param darkTheme Whether to use dark theme colors (default is based on system setting)
 * @param dynamicColor Whether to use dynamic color (ignored in this theme as we use custom colors)
 * @param glowIntensity The intensity of glow effects (0.0-1.0)
 * @param animationSpeed The speed multiplier for animations
 * @param isHolographicEnabled Whether to enable holographic effects
 * @param content The content to be styled with this theme
 */
@Composable
fun SciFiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Ignored as we use custom colors
    glowIntensity: Float = 1.0f,
    animationSpeed: Float = 1.0f,
    isHolographicEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    // Always use dark theme for sci-fi UI, but allow override
    val colorScheme = if (darkTheme) DarkSciFiColorScheme else LightSciFiColorScheme
    
    // Create custom sci-fi theme extras
    val sciFiExtras = SciFiThemeExtras(
        glowIntensity = glowIntensity,
        animationSpeed = animationSpeed,
        isHolographicEnabled = isHolographicEnabled,
        primaryGlow = SciFiColors.primaryGlow,
        secondaryGlow = SciFiColors.successGlow,
        errorGlow = SciFiColors.errorGlow,
        backgroundEffect = BackgroundEffect.Grid
    )
    
    // Provide both Material theme and custom sci-fi extras
    CompositionLocalProvider(
        LocalSciFiThemeExtras provides sciFiExtras
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = SciFiTypography.material3Typography,
            content = content
        )
    }
}

/**
 * Access the current sci-fi theme extras.
 */
object SciFiTheme {
    val extras: SciFiThemeExtras
        @Composable
        get() = LocalSciFiThemeExtras.current
    
    val colors: SciFiColors
        get() = SciFiColors
    
    val typography: SciFiTypography
        get() = SciFiTypography
    
    val shapes: SciFiShapes
        get() = SciFiShapes

}
