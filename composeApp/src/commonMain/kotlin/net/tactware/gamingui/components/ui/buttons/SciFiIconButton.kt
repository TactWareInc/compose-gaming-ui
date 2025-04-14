package net.tactware.gamingui.components.ui.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.drawSciFiBorder
import net.tactware.gamingui.components.theme.sciFiGlowingBorder

/**
 * A sci-fi themed icon button with glowing borders and hover effects.
 *
 * @param onClick The callback to be invoked when the button is clicked
 * @param icon The icon to be displayed on the button
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param buttonColor The background color of the button
 * @param contentColor The color of the icon
 * @param glowColor The color of the glow effect around the button
 * @param shape The shape of the button
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonColor: Color = SciFiColors.primary,
    contentColor: Color = SciFiColors.onPrimary,
    glowColor: Color = SciFiColors.primaryGlow,
    shape: Shape = SciFiShapes.sciFiButtonShape,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when hovered or pressed
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.3f
            isPressed -> 1.0f
            isHovered -> 0.85f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    // Adjust colors based on state
    val backgroundColor = when {
        !enabled -> buttonColor.copy(alpha = 0.5f)
        isPressed -> buttonColor.copy(alpha = 0.8f)
        else -> buttonColor
    }
    
    val iconColor = when {
        !enabled -> contentColor.copy(alpha = 0.5f)
        else -> contentColor
    }
    
    // Apply sci-fi styling with glowing border
    val buttonModifier = modifier
        .size(48.dp)
        .then(
            Modifier.drawWithContent {
                drawContent()
                if (enabled) {
                    // Draw additional glow effect when button is interactive
                    drawSciFiBorder(
                        borderColor = glowColor.copy(alpha = glowIntensity),
                        borderWidth = 2f,
                        notchSize = 5f,
                        segmentLength = 15f,
                        gapLength = 3f
                    )
                }
            }
        )
    
    FilledIconButton (
        onClick = onClick,
        modifier = buttonModifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundColor,
            contentColor = iconColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = iconColor
        ),
        interactionSource = interactionSource,
        shape = shape,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor
        )
    }
}

/**
 * A sci-fi themed toggle button with glowing borders and state indication.
 *
 * @param checked Whether the toggle button is currently checked
 * @param onCheckedChange The callback to be invoked when the button is clicked
 * @param icon The icon to be displayed on the button
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param checkedColor The background color when the button is checked
 * @param uncheckedColor The background color when the button is unchecked
 * @param contentColor The color of the icon
 * @param checkedGlowColor The color of the glow effect when checked
 * @param uncheckedGlowColor The color of the glow effect when unchecked
 * @param shape The shape of the button
 */
@Composable
fun SciFiToggleIconButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checkedColor: Color = SciFiColors.primary,
    uncheckedColor: Color = SciFiColors.backgroundMedium,
    contentColor: Color = SciFiColors.onPrimary,
    checkedGlowColor: Color = SciFiColors.primaryGlow,
    uncheckedGlowColor: Color = SciFiColors.borderDim,
    shape: Shape = SciFiShapes.sciFiButtonShape
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Determine colors based on checked state
    val buttonColor = if (checked) checkedColor else uncheckedColor
    val glowColor = if (checked) checkedGlowColor else uncheckedGlowColor
    
    // Increase glow intensity when hovered or pressed
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.3f
            isPressed -> 1.0f
            isHovered -> 0.85f
            checked -> 0.8f
            else -> 0.4f
        },
        label = "glowIntensity"
    )
    
    // Adjust colors based on state
    val backgroundColor = when {
        !enabled -> buttonColor.copy(alpha = 0.5f)
        isPressed -> buttonColor.copy(alpha = 0.8f)
        else -> buttonColor
    }
    
    val iconColor = when {
        !enabled -> contentColor.copy(alpha = 0.5f)
        else -> contentColor
    }
    
    // Apply sci-fi styling with glowing border
    val buttonModifier = modifier
        .size(48.dp)
        .then(
            Modifier.drawWithContent {
                drawContent()
                if (enabled) {
                    // Draw additional glow effect when button is interactive
                    drawSciFiBorder(
                        borderColor = glowColor.copy(alpha = glowIntensity),
                        borderWidth = 2f,
                        notchSize = 5f,
                        segmentLength = if (checked) 20f else 10f,
                        gapLength = if (checked) 2f else 5f
                    )
                }
            }
        )
    
    IconButton(
        onClick = { onCheckedChange(!checked) },
        modifier = buttonModifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundColor,
            contentColor = iconColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = iconColor
        ),
        interactionSource = interactionSource
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor
        )
    }
}

/**
 * A sci-fi themed toggle button with text and glowing borders.
 *
 * @param checked Whether the toggle button is currently checked
 * @param onCheckedChange The callback to be invoked when the button is clicked
 * @param text The text to be displayed on the button
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param checkedColor The background color when the button is checked
 * @param uncheckedColor The background color when the button is unchecked
 * @param contentColor The color of the text
 * @param checkedGlowColor The color of the glow effect when checked
 * @param uncheckedGlowColor The color of the glow effect when unchecked
 */
@Composable
fun SciFiToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checkedColor: Color = SciFiColors.primary,
    uncheckedColor: Color = SciFiColors.backgroundMedium,
    contentColor: Color = SciFiColors.onPrimary,
    checkedGlowColor: Color = SciFiColors.primaryGlow,
    uncheckedGlowColor: Color = SciFiColors.borderDim
) {
    SciFiButton(
        onClick = { onCheckedChange(!checked) },
        modifier = modifier,
        enabled = enabled,
        text = text,
        buttonColor = if (checked) checkedColor else uncheckedColor,
        contentColor = contentColor,
        glowColor = if (checked) checkedGlowColor else uncheckedGlowColor,
        baseGlowIntensity = if (checked) 0.8f else 0.4f
    )
}

/**
 * A sci-fi themed circular icon button with glowing borders.
 *
 * @param onClick The callback to be invoked when the button is clicked
 * @param icon The icon to be displayed on the button
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param buttonColor The background color of the button
 * @param contentColor The color of the icon
 * @param glowColor The color of the glow effect around the button
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiCircleIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonColor: Color = SciFiColors.primary,
    contentColor: Color = SciFiColors.onPrimary,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when hovered or pressed
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.3f
            isPressed -> 1.0f
            isHovered -> 0.85f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    // Adjust colors based on state
    val backgroundColor = when {
        !enabled -> buttonColor.copy(alpha = 0.5f)
        isPressed -> buttonColor.copy(alpha = 0.8f)
        else -> buttonColor
    }
    
    val iconColor = when {
        !enabled -> contentColor.copy(alpha = 0.5f)
        else -> contentColor
    }
    
    Box(
        modifier = modifier
            .size(48.dp)
            .then(
                Modifier.sciFiGlowingBorder(
                    borderColor = buttonColor,
                    glowColor = glowColor,
                    borderWidth = 1.dp,
                    glowWidth = 4.dp,
                    glowIntensity = glowIntensity,
                    shape = { size ->
                        val path = androidx.compose.ui.graphics.Path()
                        // Create a circular path
                        val radius = size.minDimension / 2
                        path.addOval(
                            androidx.compose.ui.geometry.Rect(
                                left = (size.width - radius * 2) / 2,
                                top = (size.height - radius * 2) / 2,
                                right = (size.width + radius * 2) / 2,
                                bottom = (size.height + radius * 2) / 2
                            )
                        )
                        path
                    }
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onClick,
            enabled = enabled,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = backgroundColor,
                contentColor = iconColor,
                disabledContainerColor = backgroundColor,
                disabledContentColor = iconColor
            ),
            interactionSource = interactionSource
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor
            )
        }
    }
}
