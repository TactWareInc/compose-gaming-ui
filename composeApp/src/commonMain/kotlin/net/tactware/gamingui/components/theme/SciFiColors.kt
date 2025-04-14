package net.tactware.gamingui.components.theme

import androidx.compose.ui.graphics.Color

/**
 * Color definitions for the Sci-Fi UI theme.
 *
 * These colors are inspired by futuristic sci-fi interfaces with glowing elements,
 * dark backgrounds, and high contrast elements.
 */
object SciFiColors {
    // Primary colors
    val primary = Color(0xFF00BFFF) // Deep cyan blue
    val primaryVariant = Color(0xFF0080FF) // Slightly more blue variant
    val primaryDark = Color(0xFF0066CC) // Darker blue for pressed states

    // Glow colors (more saturated for glow effects)
    val primaryGlow = Color(0xFF00FFFF) // Bright cyan for glow effects
    val primaryGlowDim = Color(0x8000FFFF) // Dimmed cyan for subtle glow

    // Secondary colors
    val secondary = Color(0xFF00FFCC) // Bright teal
    val secondaryVariant = Color(0xFF00CC99) // Slightly darker teal
    val secondaryDark = Color(0xFF009977) // Darker teal for pressed states

    // Secondary glow colors
    val secondaryGlow = Color(0xFF00FF99) // Bright teal for glow effects
    val secondaryGlowDim = Color(0x8000FF99) // Dimmed teal for subtle glow

    // Background colors
    val background = Color(0xFF000011) // Very dark blue, almost black
    val backgroundLight = Color(0xFF001122) // Slightly lighter background
    val backgroundMedium = Color(0xFF002244) // Medium background for cards

    // Surface colors
    val surface = Color(0xFF001133) // Dark blue surface
    val surfaceAccent = Color(0xFF002255) // Slightly lighter surface

    // Text and content colors
    val onPrimary = Color(0xFFFFFFFF) // White text on primary
    val onBackground = Color(0xFFCCDDFF) // Light blue-white text on background
    val onSurface = Color(0xFFDDEEFF) // Very light blue text on surface
    val onSurfaceMedium = Color(0xFFAABBCC) // Medium blue-gray text

    // Status colors
    val success = Color(0xFF00FF66) // Green for success/positive states
    val successGlow = Color(0xFF00FF99) // Brighter green for glow
    val warning = Color(0xFFFFCC00) // Amber for warnings
    val warningGlow = Color(0xFFFFDD33) // Brighter amber for glow
    val error = Color(0xFFFF3333) // Red for errors/negative states
    val errorGlow = Color(0xFFFF6666) // Brighter red for glow

    // Accent colors for variety
    val accent1 = Color(0xFF33CCFF) // Light blue accent
    val accent2 = Color(0xFF66FFCC) // Teal accent
    val accent3 = Color(0xFFCC66FF) // Purple accent

    // Gradient colors
    val gradientStart = Color(0xFF000033) // Dark blue for gradients
    val gradientEnd = Color(0xFF003366) // Medium blue for gradients

    // Border colors
    val border = Color(0xFF0099CC) // Border blue
    val borderDim = Color(0x800099CC) // Semi-transparent border

    // Disabled state colors
    val disabled = Color(0xFF334455) // Disabled component color
    val onDisabled = Color(0xFF778899) // Text on disabled components
}
