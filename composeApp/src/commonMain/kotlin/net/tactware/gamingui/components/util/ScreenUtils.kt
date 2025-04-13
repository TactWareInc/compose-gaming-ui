//package net.tactware.gamingui.components.util
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.unit.dp
//
///**
// * Utilities for handling different screen sizes and orientations.
// *
// * This class provides utilities to adapt UI components based on screen size
// * and orientation for better user experience across different devices.
// */
//object ScreenUtils {
//
//    /**
//     * Screen size categories for adaptive layouts.
//     */
//    enum class ScreenSizeClass {
//        COMPACT,    // Small phones
//        MEDIUM,     // Large phones, small tablets
//        EXPANDED    // Tablets, desktops
//    }
//
//    /**
//     * Determines the screen size class based on the current screen width.
//     *
//     * @return The appropriate screen size class
//     */
//    @Composable
//    fun getScreenSizeCass(): ScreenSizeClass {
//        val configuration = LocalConfiguration.current
//        val screenWidth = configuration.screenWidthDp.dp
//
//        return when {
//            screenWidth < 600.dp -> ScreenSizeClass.COMPACT
//            screenWidth < 840.dp -> ScreenSizeClass.MEDIUM
//            else -> ScreenSizeClass.EXPANDED
//        }
//    }
//
//    /**
//     * Determines if the device is in landscape orientation.
//     *
//     * @return True if the device is in landscape orientation, false otherwise
//     */
//    @Composable
//    fun isLandscape(): Boolean {
//        val configuration = LocalConfiguration.current
//        return configuration.screenWidthDp > configuration.screenHeightDp
//    }
//
//    /**
//     * Gets the appropriate spacing based on the screen size.
//     *
//     * @param compactSpacing The spacing for compact screens
//     * @param mediumSpacing The spacing for medium screens
//     * @param expandedSpacing The spacing for expanded screens
//     * @return The appropriate spacing for the current screen size
//     */
//    @Composable
//    fun getAdaptiveSpacing(
//        compactSpacing: androidx.compose.ui.unit.Dp = 8.dp,
//        mediumSpacing: androidx.compose.ui.unit.Dp = 16.dp,
//        expandedSpacing: androidx.compose.ui.unit.Dp = 24.dp
//    ): androidx.compose.ui.unit.Dp {
//        return when (getScreenSizeClass()) {
//            ScreenSizeClass.COMPACT -> compactSpacing
//            ScreenSizeClass.MEDIUM -> mediumSpacing
//            ScreenSizeClass.EXPANDED -> expandedSpacing
//        }
//    }
//
//    /**
//     * Gets the appropriate component size based on the screen size.
//     *
//     * @param compactSize The size for compact screens
//     * @param mediumSize The size for medium screens
//     * @param expandedSize The size for expanded screens
//     * @return The appropriate size for the current screen size
//     */
//    @Composable
//    fun getAdaptiveSize(
//        compactSize: androidx.compose.ui.unit.Dp,
//        mediumSize: androidx.compose.ui.unit.Dp,
//        expandedSize: androidx.compose.ui.unit.Dp
//    ): androidx.compose.ui.unit.Dp {
//        return when (getScreenSizeClass()) {
//            ScreenSizeClass.COMPACT -> compactSize
//            ScreenSizeClass.MEDIUM -> mediumSize
//            ScreenSizeClass.EXPANDED -> expandedSize
//        }
//    }
//
//    /**
//     * Gets the appropriate font size based on the screen size.
//     *
//     * @param compactSize The font size for compact screens
//     * @param mediumSize The font size for medium screens
//     * @param expandedSize The font size for expanded screens
//     * @return The appropriate font size for the current screen size
//     */
//    @Composable
//    fun getAdaptiveFontSize(
//        compactSize: androidx.compose.ui.unit.TextUnit,
//        mediumSize: androidx.compose.ui.unit.TextUnit,
//        expandedSize: androidx.compose.ui.unit.TextUnit
//    ): androidx.compose.ui.unit.TextUnit {
//        return when (getScreenSizeClass()) {
//            ScreenSizeClass.COMPACT -> compactSize
//            ScreenSizeClass.MEDIUM -> mediumSize
//            ScreenSizeClass.EXPANDED -> expandedSize
//        }
//    }
//
//    /**
//     * Gets the appropriate number of columns for a grid based on the screen size.
//     *
//     * @param compactColumns The number of columns for compact screens
//     * @param mediumColumns The number of columns for medium screens
//     * @param expandedColumns The number of columns for expanded screens
//     * @return The appropriate number of columns for the current screen size
//     */
//    @Composable
//    fun getAdaptiveColumns(
//        compactColumns: Int = 1,
//        mediumColumns: Int = 2,
//        expandedColumns: Int = 3
//    ): Int {
//        return when (getScreenSizeClass()) {
//            ScreenSizeClass.COMPACT -> compactColumns
//            ScreenSizeClass.MEDIUM -> mediumColumns
//            ScreenSizeClass.EXPANDED -> expandedColumns
//        }
//    }
//
//    /**
//     * Gets the appropriate layout orientation based on the screen size and orientation.
//     *
//     * @return True if the layout should be horizontal, false if it should be vertical
//     */
//    @Composable
//    fun shouldUseHorizontalLayout(): Boolean {
//        val screenSizeClass = getScreenSizeClass()
//        val isLandscape = isLandscape()
//
//        return when (screenSizeClass) {
//            ScreenSizeClass.COMPACT -> isLandscape
//            ScreenSizeClass.MEDIUM, ScreenSizeClass.EXPANDED -> true
//        }
//    }
//}
